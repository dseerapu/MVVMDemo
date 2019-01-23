package dharma.github.io.mvvmsample.data.api;

import java.util.List;

import dharma.github.io.mvvmsample.data.model.GalleryModel;
import io.reactivex.Observable;

public interface ApiHelper {

    Observable<List<GalleryModel>> getGalleryApiCall();
}
