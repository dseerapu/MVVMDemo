package dharma.github.io.mvvmsample.data.api;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dharma.github.io.mvvmsample.data.database.DBHelper;
import dharma.github.io.mvvmsample.data.model.GalleryModel;
import dharma.github.io.mvvmsample.data.model.User;
import dharma.github.io.mvvmsample.data.prefs.PreferenceHelper;
import dharma.github.io.mvvmsample.di.ApplicationContext;
import io.reactivex.Observable;

@Singleton
public class DataManagerImpl implements DataManager {


    private final Context context;
    private final DBHelper dbHelper;
    private final PreferenceHelper preferenceHelper;
    private final ApiHelper apiHelper;

    @Inject
    DataManagerImpl(@ApplicationContext Context context,
                    DBHelper dbHelper,
                    PreferenceHelper preferenceHelper,
                    ApiHelper apiHelper) {

        this.context = context;
        this.dbHelper = dbHelper;
        this.preferenceHelper = preferenceHelper;
        this.apiHelper = apiHelper;
    }

    @Override
    public Observable<List<GalleryModel>> getPicturesList() {
        return apiHelper.getGalleryApiCall();
    }

    @Override
    public LiveData<List<GalleryModel>> getGalleryModels() {
        return dbHelper.getGalleryModels();
    }

    @Override
    public Observable<List<Long>> insertGalleryList(List<GalleryModel> galleryModels) {
        return dbHelper.insertGalleryList(galleryModels);
    }

    @Override
    public Observable<List<Long>> insertUserList() {
        return dbHelper.insertUserList();
    }

    @Override
    public Observable<List<Long>> insertProfileImageList() {
        return dbHelper.insertProfileImageList();
    }

    @Override
    public Observable<List<User>> getUsers() {
        return dbHelper.getUsers();
    }

    @Override
    public Observable<User> getUserByGalleryId(@NonNull String galleryId) {
        return dbHelper.getUserByGalleryId(galleryId);
    }

    @Override
    public void setNetworkJobRunning(boolean isNetworkJobRunning) {
        preferenceHelper.setNetworkJobRunning(isNetworkJobRunning);
    }

    @Override
    public boolean isNetworkJobRunning() {
        return preferenceHelper.isNetworkJobRunning();
    }
}
