package dharma.github.io.mvvmsample.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import dharma.github.io.mvvmsample.constants.Constants;
import dharma.github.io.mvvmsample.data.model.ProfileImage;

@Dao
public interface ProfileImageDao {

    @Query("SELECT * FROM " + Constants.DatabaseTables.PROFILE_IMAGE)
    LiveData<List<ProfileImage>> getProfiles();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Long insertProfileImage(ProfileImage profileImage);

    @Query("SELECT * FROM " + Constants.DatabaseTables.PROFILE_IMAGE + " WHERE " + Constants.DatabaseTables.USER_ID + "=:userId")
    ProfileImage getProfileByUserId(String userId);
}
