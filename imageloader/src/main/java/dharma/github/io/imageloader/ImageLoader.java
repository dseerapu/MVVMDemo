package dharma.github.io.imageloader;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import dharma.github.io.imageloader.cache.impl.DiskCache;
import dharma.github.io.imageloader.cache.impl.MemoryCache;
import dharma.github.io.imageloader.connection.ICacheRequest;
import dharma.github.io.imageloader.engine.LoadTask;
import dharma.github.io.imageloader.engine.PathParser;
import dharma.github.io.imageloader.entity.ImageModel;
import dharma.github.io.imageloader.thread.ThreadPoolController;
import dharma.github.io.imageloader.utils.LogUtils;

public class ImageLoader {

    private final Builder mBuilder;

    public ImageLoader(Builder builder) {
        this.mBuilder = builder;
    }

    /**
     * Create task
     */
    public static Builder createTask() {
        return new Builder();
    }

    /**
     * Create task
     */
    public static void log() {
        LogUtils.switchLog(true);
    }

    /**
     * Clear memory cache
     */
    public static boolean clearMemCache() {
        boolean ret = MemoryCache.clearAllCache();
        LogUtils.log().addMsg("Clear memory cache" + (ret ? "success" : "failure")).build().execute();
        return ret;
    }

    /**
     * Reduce memory cache
     */
    public static boolean clearMemCache(int level) {
        boolean ret = MemoryCache.trimCache(level);
        LogUtils.log().addMsg("Reduce memory cache,Level:" + level + (ret ? ",success" : ",failure")).build().execute();
        return ret;
    }

    /**
     * Clear local cache
     */
    public static boolean clearDiskCache(Context context) {
        boolean ret = DiskCache.clearAllCache(context);
        LogUtils.log().addMsg("Clear local cache" + (ret ? "success" : "failure")).build().execute();
        return ret;
    }

    private void start() {

        final ImageModel imageModel = new ImageModel(mBuilder.imageView.getContext());

        imageModel.setViewWidth(mBuilder.imageView.getMeasuredWidth());
        imageModel.setViewHeight(mBuilder.imageView.getMeasuredHeight());

        if (imageModel.getViewWidth() <= 0 || imageModel.getViewHeight() <= 0) {

            mBuilder.imageView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {

                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom,
                                           int oldLeft, int oldTop, int oldRight, int oldBottom) {

                    imageModel.setViewWidth(mBuilder.imageView.getMeasuredWidth());
                    imageModel.setViewHeight(mBuilder.imageView.getMeasuredHeight());

                    startDownloadTask(imageModel);

                    mBuilder.imageView.removeOnLayoutChangeListener(this);
                }

            });

        } else {
            startDownloadTask(imageModel);
        }

    }

    /**
     * Start the download image task and display the results
     */
    private <D, W> void startDownloadTask(ImageModel model) {

        //Clear display
        mBuilder.imageView.setImageBitmap(null);

        //Show loading image
        if (mBuilder.loadingResId > 0) {
            mBuilder.imageView.setImageResource(mBuilder.loadingResId);
        }

        model.setUri(mBuilder.uri);

        ICacheRequest requestHandler = mBuilder.request == null ?
                PathParser.getRequest(mBuilder.type) ://Use the default loading framework
                mBuilder.request;//Use a custom loading framework

        if (requestHandler != null) {
            requestHandler.setModel(model);
        } else {
            return;
        }

        LoadTask loadTask = new LoadTask(requestHandler);
        loadTask.setListener(new LoadTask.OnResultListener() {
            @Override
            public void onGetBitmap(Bitmap bitmap) {
                if (bitmap != null) {
                    mBuilder.imageView.setImageBitmap(bitmap);
                    if (mBuilder.listener!=null){
                        mBuilder.listener.onSuccess(bitmap);
                    }
                } else {// Image acquisition failed
                    if (mBuilder.failedLoadResId > 0) {
                        mBuilder.imageView.setImageResource(mBuilder.failedLoadResId);
                    }
                    if (mBuilder.listener!=null){
                        mBuilder.listener.onFailed();
                    }
                }
            }
        });

        loadTask.executeOnExecutor(ThreadPoolController.getThreadPool());
    }

    public static class Builder {

        ImageView imageView;
        Uri uri;
        ICacheRequest request;
        PathParser.Type type;

        int loadingResId = -1;
        int failedLoadResId = -1;
        OnResultListener listener;

        public Builder web(String url) {
            type = PathParser.Type.WEB;

            // HTTP type, complete http://
            if (url.startsWith("www.")) {
                url = "http://" + url;
            }

            // HTTP type, complete http://
            if (!url.startsWith("https://") && !url.startsWith("http://")) {
                url = "http://" + url;
            }

            uri = Uri.parse(url);
            return this;
        }

        public Builder asset(String filename) {
            type = PathParser.Type.ASSERT;
            uri = Uri.parse(PathParser.PREFIX_ASSERT + filename);
            return this;
        }

        public Builder raw(Context context, int resId) {
            type = PathParser.Type.RAW;
            Resources resources = context.getResources();
            uri = Uri.parse(PathParser.PREFIX_RESOURCE
                    + resources.getResourcePackageName(resId) + "/"
                    + resources.getResourceTypeName(resId) + "/"
                    + resources.getResourceEntryName(resId));
            return this;
        }

        public Builder media(Uri uri) {
            type = PathParser.Type.MEDIA;
            this.uri = uri;
            return this;
        }

        public Builder media(String uriStr) {
            type = PathParser.Type.MEDIA;
            this.uri = Uri.parse(uriStr);
            return this;
        }

        public Builder res(int resId) {
            type = PathParser.Type.RESOURCE;
            uri = Uri.parse(String.valueOf(resId));//no cache，Don't care if Uri repeats
            return this;
        }

        public Builder local(String filepath) {
            type = PathParser.Type.LOCAL;
            if (!filepath.startsWith("file://")) {//Completion path
                filepath = "file://" + filepath;
            }
            uri = Uri.parse(filepath);
            return this;
        }

        public Builder into(ImageView imageView) {
            this.imageView = imageView;
            return this;
        }

        public <D, W> Builder using(ICacheRequest<D, W> request) {
            this.request = request;
            return this;
        }

        public Builder load(String customPath) {
            uri = Uri.parse(customPath);
            return this;
        }

        public Builder loadingRes(int resId) {
            loadingResId = resId;
            return this;
        }

        public Builder failedRes(int resId) {
            failedLoadResId = resId;
            return this;
        }

        public Builder callback(OnResultListener listener) {
            this.listener = listener;
            return this;
        }

        public void start() {

            //Automatically reclaim memory
            imageView.getContext().registerComponentCallbacks(new ComponentCallbacks2() {
                @Override
                public void onTrimMemory(int level) {
                    LogUtils.log().addMsg("Low memory，Automatically clear memory cache,Level:" + level).build().execute();
                    clearMemCache(level);
                }

                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                }

                @Override
                public void onLowMemory() {
                    LogUtils.log().addMsg("Not enough storage，Automatically clear the memory cache").build().execute();
                    clearMemCache();
                }
            });

            new ImageLoader(this).start();
        }

        public boolean cleanMemCache() {
            boolean ret = MemoryCache.clearCache(uri.toString());
            LogUtils.log().addMsg("Empty" + uri.toString() + "Memory cache" + (ret ? "success" : "failure")).build().execute();
            return ret;
        }

        public boolean cleanDiskCache(Context context) {
            boolean ret = DiskCache.clearCache(context, uri.toString());
            LogUtils.log().addMsg("Empty" + uri.toString() + "Local cache" + (ret ? "success" : "failure")).build().execute();
            return ret;
        }

        public boolean cleanCache(Context context) {
            return cleanMemCache() && cleanDiskCache(context);
        }

    }

    public interface OnResultListener{

        /**
         * Failed callback
         */
        void onFailed();

        /**
         * Successful callback
         */
        void onSuccess(Bitmap bitmap);
    }
}
