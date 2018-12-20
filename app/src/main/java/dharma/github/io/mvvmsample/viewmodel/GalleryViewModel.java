package dharma.github.io.mvvmsample.viewmodel;

import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dharma.github.io.mvvmsample.data.ApiService;
import dharma.github.io.mvvmsample.data.model.GalleryModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class GalleryViewModel extends ViewModel {

    private List<GalleryModel> galleryList;
    private ApiService apiService;
    private CompositeDisposable compositeDisposable;
    private GalleryModelsCallback galleryModelsCallback;

    @Inject
    public GalleryViewModel(ApiService apiService) {
        compositeDisposable = new CompositeDisposable();
        this.apiService = apiService;
    }

    public void addGalleryItemsCallback(GalleryModelsCallback galleryModelsCallback) {
        this.galleryModelsCallback = galleryModelsCallback;
    }

    public void getGalleryList() {

        if (galleryList == null || galleryList.isEmpty()) {
            getGalleryListForTheFirstTime();
        } else {
            galleryModelsCallback.onItemsLoadedForFirstTime(galleryList);
        }
    }

    public void getGalleryListForTheFirstTime() {
        this.galleryList = new ArrayList<>();
        compositeDisposable.add(apiService.getPicturesList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::galleryListSuccess, this::galleryListError));
    }

    public void loadMoreGalleryItems() {

        compositeDisposable.add(apiService.getPicturesList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::moreGalleryListSuccess, this::galleryListError));
    }

    private void moreGalleryListSuccess(List<GalleryModel> galleryModels) {
        galleryModelsCallback.onItemsLoaded(galleryModels);
        this.galleryList.addAll(new ArrayList<>(galleryModels));
    }

    private void galleryListError(Throwable throwable) {
        galleryModelsCallback.onItemsError(throwable);
    }

    private void galleryListSuccess(List<GalleryModel> galleryModels) {
        galleryModelsCallback.onItemsLoadedForFirstTime(galleryModels);
        this.galleryList.addAll(new ArrayList<>(galleryModels));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
