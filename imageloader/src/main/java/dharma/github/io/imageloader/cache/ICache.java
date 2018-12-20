package dharma.github.io.imageloader.cache;

/**
 * Cache interface
 */
public interface ICache<I,O> {

    /**
     * Setting the cache
     */
    void setCache(I content);

    /**
     * Get cache
     */
    O getCache();

}