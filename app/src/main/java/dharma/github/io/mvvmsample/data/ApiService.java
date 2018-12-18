package dharma.github.io.mvvmsample.data;

import java.util.List;

import dharma.github.io.mvvmsample.data.model.GalleryModel;
import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Developed by Dharma Sai Seerapu on 18th Dec 2018
 * <p>
 * Add new Api End Points here
 */
public interface ApiService {

    @GET("raw/wgkJgazE")
    Single<List<GalleryModel>> getPicturesList();
}
