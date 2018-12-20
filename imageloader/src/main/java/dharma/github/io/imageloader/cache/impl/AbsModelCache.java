package dharma.github.io.imageloader.cache.impl;

import dharma.github.io.imageloader.cache.ICache;
import dharma.github.io.imageloader.entity.ImageModel;

/**
 * Abstract image cache
 */
public abstract class AbsModelCache<I, O> implements ICache<I, O> {

    protected ImageModel model;

    public AbsModelCache(ImageModel model) {
        this.model = model;
    }

}