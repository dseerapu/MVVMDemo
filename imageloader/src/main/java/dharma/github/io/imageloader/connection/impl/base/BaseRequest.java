package dharma.github.io.imageloader.connection.impl.base;

import dharma.github.io.imageloader.connection.ICacheRequest;
import dharma.github.io.imageloader.entity.ImageModel;

/**
 * Default implementation
 */
public abstract class BaseRequest<D, W> implements ICacheRequest<D, W> {

    protected ImageModel model;

    @Override
    public void setModel(ImageModel model) {
        this.model = model;
    }

    @Override
    public String getRequestPath() {
        return model.getPath();
    }
}