package dharma.github.io.imageloader.entity;

import android.content.Context;
import android.net.Uri;

import java.io.Serializable;

import dharma.github.io.imageloader.utils.MD5Utils;

/**
 * Picture data model
 */
public class ImageModel implements Serializable {

    /**
     * Context
     */
    private Context context;

    /**
     * The map's address
     */
    private Uri uri;

    /**
     * MD5 code of picture address, automatically calculated
     */
    private String md5Code;

    /**
     * Control height
     */
    private int viewHeight;

    /**
     * Control width
     */
    private int viewWidth;

    public ImageModel(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
        this.md5Code = MD5Utils.getMD5String(getPath());
    }

    public void setPath(String path){
        this.uri = Uri.parse(path);
    }

    public String getPath(){
        return uri.toString();
    }

    public String getMd5Code() {
        return md5Code;
    }

    public int getViewHeight() {
        return viewHeight;
    }

    public void setViewHeight(int viewHeight) {
        this.viewHeight = viewHeight;
    }

    public int getViewWidth() {
        return viewWidth;
    }

    public void setViewWidth(int viewWidth) {
        this.viewWidth = viewWidth;
    }
}