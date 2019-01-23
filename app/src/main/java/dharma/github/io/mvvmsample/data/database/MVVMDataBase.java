package dharma.github.io.mvvmsample.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import javax.inject.Singleton;

import dharma.github.io.mvvmsample.data.model.GalleryModel;
import dharma.github.io.mvvmsample.data.model.ProfileImage;
import dharma.github.io.mvvmsample.data.model.User;

//Note : Define all tables in entities brackets and their DAO below
@Database(entities = {GalleryModel.class, User.class, ProfileImage.class}, version = 1, exportSchema = false)
@Singleton
@TypeConverters({ListConverter.class, ArrayConverter.class})
public abstract class MVVMDataBase extends RoomDatabase {

    //Note : Add | Define All the tables here like

    public abstract GalleryModelDao galleryModelDao();

    public abstract UserDao userDao();

    public abstract ProfileImageDao profileImageDao();
}
