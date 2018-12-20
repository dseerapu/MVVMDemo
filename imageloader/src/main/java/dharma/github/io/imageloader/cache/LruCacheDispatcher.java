package dharma.github.io.imageloader.cache;

import android.graphics.Bitmap;
import android.os.Build;
import android.util.LruCache;

/**
 * Allocate memory cache, singleton
 */
public class LruCacheDispatcher {

    long maxCacheMemory;

    /**
     * Single case
     */
    private final static LruCacheDispatcher dispatcher = new LruCacheDispatcher();


    /**
     * LruCache,Store a collection of Bitmaps
     */
    private LruCache<String, Bitmap> mMemoryCache;

    /**
     * @return LruCache
     */
    public LruCache<String, Bitmap> getMemoryCache() {
        return mMemoryCache;
    }

    /**
     * It is forbidden to create instance objects. Please do not use reflection to create instances.
     */
    private LruCacheDispatcher() {

        maxCacheMemory = Runtime.getRuntime().maxMemory() / 8;// Set the maximum Cache to occupy 1/8 of the total memory of the application.
        mMemoryCache = new LruCache<String, Bitmap>((int) maxCacheMemory) {

            /**
             * Calculate the amount of memory used to return each Bitmap
             */
            @Override
            protected int sizeOf(String key, Bitmap value) {

                //Calculate image footprint
                return value.getRowBytes() * value.getHeight();

            }

        };

    }

    /**
     * Get instance object
     *
     * @return Single case
     */
    public static LruCacheDispatcher getInstance() {
        return dispatcher;
    }

    /**
     * Clean up memory
     */
    public boolean clearCache() {
        mMemoryCache.evictAll();
        return true;
    }

    /**
     * Clear some memory
     */
    public boolean trimCache(int level) {
        long size = maxCacheMemory * (level / 100);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            mMemoryCache.trimToSize((int) size);
        }
        return true;
    }

}