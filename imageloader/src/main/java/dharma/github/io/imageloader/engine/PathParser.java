package dharma.github.io.imageloader.engine;

import android.content.ContentResolver;

import dharma.github.io.imageloader.connection.ICacheRequest;
import dharma.github.io.imageloader.connection.impl.disk.AssetRequest;
import dharma.github.io.imageloader.connection.impl.disk.LocalRequest;
import dharma.github.io.imageloader.connection.impl.disk.MediaRequest;
import dharma.github.io.imageloader.connection.impl.disk.RawRequest;
import dharma.github.io.imageloader.connection.impl.mem.ResourceRequest;
import dharma.github.io.imageloader.connection.impl.web.WebImageRequest;

/**
 * Path parser
 */
public class PathParser {

    public static final String PREFIX_ASSERT = "file:///android_asset/";
    public static final String PREFIX_RESOURCE = ContentResolver.SCHEME_ANDROID_RESOURCE + "://";

    public enum Type {
        ASSERT,//xxx.jpg
        RAW,//R.raw.xxx
        RESOURCE,//R.drawable.xxx R.mipmap.xxx R.raw.xxx
        MEDIA,//content://
        LOCAL,//file://
        WEB//http:// https:// www.
    }

    public static ICacheRequest getRequest(Type type) {
        switch (type) {
            case ASSERT:
                return new AssetRequest();
            case RAW:
                return new RawRequest();
            case MEDIA:
                return new MediaRequest();
            case RESOURCE:
                return new ResourceRequest();
            case LOCAL:
                return new LocalRequest();
            case WEB:
                return new WebImageRequest();
        }
        return null;
    }

}