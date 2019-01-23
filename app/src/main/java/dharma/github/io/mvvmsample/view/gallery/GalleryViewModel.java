package dharma.github.io.mvvmsample.view.gallery;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dharma.github.io.mvvmsample.data.api.DataManager;
import dharma.github.io.mvvmsample.data.model.GalleryModel;
import dharma.github.io.mvvmsample.data.model.User;
import dharma.github.io.mvvmsample.utils.NetworkUtils;
import dharma.github.io.mvvmsample.utils.SingleLiveEvent;
import dharma.github.io.mvvmsample.utils.rx.SchedulerProvider;
import dharma.github.io.mvvmsample.viewmodel.base.BaseViewModel;

public class GalleryViewModel extends BaseViewModel {

    private LiveData<List<GalleryModel>> galleryList = new MutableLiveData<>();
    private final SingleLiveEvent<Void> allDataLoadedEvent = new SingleLiveEvent<>();
    private List<User> usersList = new ArrayList<>();

    public GalleryViewModel(DataManager dataManager, SchedulerProvider provider, NetworkUtils networkUtils) {
        super(dataManager, provider, networkUtils);

        galleryList = getDataManager().getGalleryModels();
    }

    /**
     * Gets Gallery List whenever new Galley Model is added into Gallery Model {@link GalleryModel} table
     *
     * @return List of Gallery Models {@link List<GalleryModel>}
     */
    public LiveData<List<GalleryModel>> getGalleryList() {
        return galleryList;
    }

    /**
     * This event triggers when all galleries are loaded from the database
     */
    public SingleLiveEvent<Void> registerForAllGalleriesLoaded() {
        return allDataLoadedEvent;
    }

    /**
     * Gets Gallery Models list for the first time
     */
    public void getGalleryListForTheFirstTime() {

        showLoading();
        getCompositeDisposable().add(getDataManager().getPicturesList()
                .subscribeOn(getSchedulerProvider().io()).observeOn(getSchedulerProvider().ui())
                .subscribe(
                        this::galleryListSuccess,
                        throwable -> hideLoading()));
    }

    /**
     * Loads more gallery items
     */
    public void loadMoreGalleryItems() {

        getCompositeDisposable().add(getDataManager().getPicturesList()
                .subscribeOn(getSchedulerProvider().io()).observeOn(getSchedulerProvider().ui())
                .subscribe(
                        this::galleryListSuccess,
                        throwable -> {
                            //Note : Handle Error case
                        }));
    }

    /**
     * Gallery list is success from server and the details will be inserted into database
     *
     * @param galleryModels
     */
    private void galleryListSuccess(List<GalleryModel> galleryModels) {
        getCompositeDisposable().add(getDataManager().insertGalleryList(galleryModels).
                subscribeOn(getSchedulerProvider().io()).observeOn(getSchedulerProvider().ui())
                .subscribe(success -> {
                    //do nothing
                }, throwable -> {
                    //Note : Handle Error case
                }));
    }

    /**
     * Loads all Gallery Models from Database
     */
    public void loadAllUsers() {
        getCompositeDisposable().add(getDataManager().getUsers().subscribeOn(getSchedulerProvider().io()).observeOn(getSchedulerProvider().ui())
                .subscribe(users -> {
                    this.usersList.addAll(new ArrayList<>(users));
                    allDataLoadedEvent.call();
                }, error -> {
                    Log.e("Error", "Error:\t" + error.getMessage());
                })
        );
    }

    /**
     * All gallery Models
     *
     * @return
     */
    public List<User> getAllUsers() {
        return usersList;
    }
}
