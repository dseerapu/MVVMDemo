package dharma.github.io.imageloader.engine;

import android.graphics.Bitmap;

import dharma.github.io.imageloader.connection.ICacheRequest;
import dharma.github.io.imageloader.utils.LogUtils;

/**
 * Picture processing
 */
public class Engine {

    LogUtils.LogInfo log;

    /**
     * Get images from the cache
     */
    public Bitmap getImage(ICacheRequest request) {
        log = LogUtils.log();
        log.addMsg("Loading image：" + request.getRequestPath());
        Bitmap bitmap = absGetImage(request, 1);
        log.addMsg("Loading image：" + (bitmap != null ? "success" : "failure"));
        log.build().execute();
        return bitmap;
    }

    private <D, W> Bitmap absGetImage(ICacheRequest<D, W> request, int time) {


        if (time > 3) {//Recursive maximum number of times
            return null;
        }

        log.addMsg("Read memory cache");
        Bitmap bitmap = request.requestMem();//Request memory


        if (bitmap == null) {

            log.addMsg("No memory cache, read local cache");
            D diskCache = request.requestDisk();//Request local

            if (diskCache == null) {

                log.addMsg("No local cache, request network data");
                W webCache = request.requestWeb();//Request network

                if (webCache == null) {
                    return null;
                }

                log.addMsg("Request network data success, set local cache");
                request.cacheInDisk(webCache);

            } else {
                log.addMsg("Read local cache successfully, set memory cache");
                request.cacheInMem(diskCache);
            }

            return absGetImage(request, time + 1);

        }

        return bitmap;

    }

}