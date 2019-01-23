package dharma.github.io.mvvmsample.data.api;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dharma.github.io.mvvmsample.data.model.GalleryModel;
import io.reactivex.Observable;

@Singleton
public class ApiHelperImpl implements ApiHelper {

    private ApiService apiService;

    @Inject
    ApiHelperImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<List<GalleryModel>> getGalleryApiCall() {
        return apiService.getPicturesList();
    }
}
