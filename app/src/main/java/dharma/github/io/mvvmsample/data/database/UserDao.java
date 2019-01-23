package dharma.github.io.mvvmsample.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import dharma.github.io.mvvmsample.constants.Constants;
import dharma.github.io.mvvmsample.data.model.User;

@Dao
public interface UserDao {

    @Query("SELECT * FROM " + Constants.DatabaseTables.USER)
    LiveData<List<User>> getUsersLiveData();

    @Query("SELECT * FROM " + Constants.DatabaseTables.USER)
    List<User> getUsers();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Long insertUser(User user);

    @Query("SELECT * FROM " + Constants.DatabaseTables.USER + " WHERE " + Constants.DatabaseTables.GALLERY_ID + "=:galleryId")
    User getUserByGalleryId(String galleryId);
}
