package dharma.github.io.imageloader.connection.impl.disk;

import android.graphics.Bitmap;

import java.io.File;

import dharma.github.io.imageloader.connection.impl.base.DiskRequest;
import dharma.github.io.imageloader.utils.ImageCompress;
import dharma.github.io.imageloader.utils.Uri2Path;

/**
 * Content:// image loading
 */
public class MediaRequest extends DiskRequest<File> {

    @Override
    public Bitmap requestMem() {
        return memoryCache.getCache();
    }

    @Override
    public File requestDisk() {
        String path = Uri2Path.getImageAbsolutePath(model.getContext(), model.getUri());
        File file = new File(path);
        return file.exists() ? file : null;
    }

    @Override
    public void cacheInMem(File diskCache) {
        Bitmap bitmap = ImageCompress.getImage(diskCache, model.getViewHeight(), model.getViewWidth());
        memoryCache.setCache(bitmap);
    }
}