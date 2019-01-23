package dharma.github.io.mvvmsample.data.database;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import dharma.github.io.mvvmsample.data.model.GalleryModel;
import dharma.github.io.mvvmsample.data.model.User;
import io.reactivex.Observable;

public interface DBHelper {

    //Note : Define all DB queries here

    LiveData<List<GalleryModel>> getGalleryModels();

    Observable<List<Long>> insertGalleryList(List<GalleryModel> galleryModels);

    Observable<List<Long>> insertUserList();

    Observable<List<Long>> insertProfileImageList();

    Observable<List<User>> getUsers();

    Observable<User> getUserByGalleryId(@NonNull final String galleryId);

}
