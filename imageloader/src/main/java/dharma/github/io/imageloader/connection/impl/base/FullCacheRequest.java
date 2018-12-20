package dharma.github.io.imageloader.connection.impl.base;

import dharma.github.io.imageloader.cache.impl.DiskCache;
import dharma.github.io.imageloader.cache.impl.MemoryCache;
import dharma.github.io.imageloader.entity.ImageModel;

/**
 * Default implementation
 */
public abstract class FullCacheRequest<D, W> extends BaseRequest<D, W> {

    protected DiskCache diskCache;
    protected MemoryCache memoryCache;

    @Override
    public void setModel(ImageModel model) {
        super.setModel(model);
        diskCache = new DiskCache(model);
        memoryCache = new MemoryCache(model);
    }
}