package dharma.github.io.imageloader.connection.impl.mem;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import dharma.github.io.imageloader.connection.impl.base.MemRequest;

/**
 * Resource request mipmap/drawable
 */
public class ResourceRequest extends MemRequest {

    @Override
    public Bitmap requestMem() {
        return BitmapFactory.decodeResource(model.getContext().getResources(), Integer.valueOf(model.getPath()));
    }

}