package dharma.github.io.imageloader.connection.impl.disk;

import android.graphics.Bitmap;

import java.io.File;

import dharma.github.io.imageloader.connection.impl.base.DiskRequest;
import dharma.github.io.imageloader.utils.ImageCompress;

/**
 * Local image loading
 */
public class LocalRequest extends DiskRequest<File> {

    @Override
    public Bitmap requestMem() {
        return memoryCache.getCache();
    }

    @Override
    public File requestDisk() {
        File file = new File(model.getUri().getPath());
        return file.exists() ? file : null;
    }

    @Override
    public void cacheInMem(File diskCache) {
        Bitmap bitmap = ImageCompress.getImage(diskCache, model.getViewHeight(), model.getViewWidth());
        memoryCache.setCache(bitmap);
    }
}