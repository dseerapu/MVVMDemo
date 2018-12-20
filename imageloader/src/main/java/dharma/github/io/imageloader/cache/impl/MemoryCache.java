package dharma.github.io.imageloader.cache.impl;

import android.graphics.Bitmap;

import dharma.github.io.imageloader.cache.LruCacheDispatcher;
import dharma.github.io.imageloader.entity.ImageModel;
import dharma.github.io.imageloader.utils.MD5Utils;

/**
 * Memory cache
 */
public class MemoryCache extends AbsModelCache<Bitmap, Bitmap> {

    public MemoryCache(ImageModel model) {
        super(model);
    }

    @Override
    public void setCache(Bitmap content) {
        if (content != null) {
            // Join the memory cache
            LruCacheDispatcher.getInstance().getMemoryCache().put(model.getMd5Code(), content);
        }
    }

    @Override
    public Bitmap getCache() {
        return LruCacheDispatcher.getInstance().getMemoryCache().get(model.getMd5Code());
    }

    /**
     * Clean up the specified memory cache
     */
    public static boolean clearCache(String key) {
        LruCacheDispatcher.getInstance().getMemoryCache().remove(MD5Utils.getMD5String(key));
        return true;
    }

    /**
     * Clear all memory
     */
    public static boolean clearAllCache() {
        LruCacheDispatcher.getInstance().clearCache();
        return true;
    }

    /**
     * Clear part of the memory
     */
    public static boolean trimCache(int level) {
        LruCacheDispatcher.getInstance().trimCache(level);
        return true;
    }

}