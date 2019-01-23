package dharma.github.io.mvvmsample.data.database;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dharma.github.io.mvvmsample.data.model.GalleryModel;
import dharma.github.io.mvvmsample.data.model.ProfileImage;
import dharma.github.io.mvvmsample.data.model.User;
import io.reactivex.Observable;

@Singleton
public class DBHelperImpl implements DBHelper {

    //Note : Implement all the DB Transaction Methods here and perform the required operation

    private MVVMDataBase mvvmDataBase;

    @Inject
    DBHelperImpl(MVVMDataBase mvvmDataBase) {
        this.mvvmDataBase = mvvmDataBase;
    }

    @Override
    public LiveData<List<GalleryModel>> getGalleryModels() {
        return mvvmDataBase.galleryModelDao().getGalleryModels();
    }

    @Override
    public Observable<List<Long>> insertGalleryList(List<GalleryModel> galleryModels) {

        return Observable.fromCallable(() -> {
            for (GalleryModel galleryModel : galleryModels) {

                User user = galleryModel.getUser();
                user.setGalleryId(galleryModel.getId());
                mvvmDataBase.userDao().insertUser(user);

                ProfileImage profileImage = user.getProfileImage();
                profileImage.setUserId(user.getId());

                mvvmDataBase.profileImageDao().insertProfileImage(profileImage);
            }
            return mvvmDataBase.galleryModelDao().insertGallery(galleryModels);
        });
    }

    @Override
    public Observable<List<Long>> insertUserList() {
        return null;
    }

    @Override
    public Observable<List<Long>> insertProfileImageList() {
        return null;
    }

    @Override
    public Observable<List<User>> getUsers() {

        return Observable.fromCallable(() -> {

            List<User> users = mvvmDataBase.userDao().getUsers();

            List<User> usersList = new ArrayList<>();
            for (User user : users) {
                ProfileImage profileImage = mvvmDataBase.profileImageDao().getProfileByUserId(user.getId());
                user.setProfileImage(profileImage);
                usersList.add(user);
            }
            return usersList;
        });
    }

    @Override
    public Observable<User> getUserByGalleryId(@NonNull String galleryId) {
        return Observable.fromCallable(() -> mvvmDataBase.userDao().getUserByGalleryId(galleryId));
    }

}
