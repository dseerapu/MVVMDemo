package dharma.github.io.imageloader.engine;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import dharma.github.io.imageloader.connection.ICacheRequest;

/**
 * Load task
 */
public class LoadTask extends AsyncTask<Void, Void, Bitmap> {

    private ICacheRequest request;
    private OnResultListener listener;

    /**
     * Mark whether to cancel the task
     */
    private boolean isCancelled = false;

    public LoadTask(ICacheRequest request) {
        this.request = request;
    }

    public void setListener(OnResultListener listener) {
        this.listener = listener;
    }

    @Override
    protected Bitmap doInBackground(Void... params) {
        return new Engine().getImage(request);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (!isCancelled && listener != null) {
            listener.onGetBitmap(bitmap);
        }
    }

    /**
     * Cancel task
     * TODO:Binding context cycle
     */
    public void cancel() {
        isCancelled = true;
    }


    public interface OnResultListener {

        /**
         * Operation after successfully reading the picture
         *
         * @param bitmap picture
         */
        public void onGetBitmap(Bitmap bitmap);

    }

}