package dharma.github.io.mvvmsample.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import dharma.github.io.mvvmsample.constants.Constants;
import dharma.github.io.mvvmsample.data.model.GalleryModel;

@Dao
public interface GalleryModelDao {

    @Query("SELECT * FROM " + Constants.DatabaseTables.GALLERY_MODEL)
    LiveData<List<GalleryModel>> getGalleryModels();

    @Query("SELECT * FROM " + Constants.DatabaseTables.GALLERY_MODEL)
    List<GalleryModel> getAllGalleryModels();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    List<Long> insertGallery(List<GalleryModel> galleryModels);
}
