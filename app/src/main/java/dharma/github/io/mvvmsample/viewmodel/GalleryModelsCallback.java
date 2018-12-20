package dharma.github.io.mvvmsample.viewmodel;

import android.support.annotation.NonNull;

import java.util.List;

import dharma.github.io.mvvmsample.data.model.GalleryModel;

public interface GalleryModelsCallback {

    void onItemsLoadedForFirstTime(@NonNull final List<GalleryModel> galleryModels);

    void onItemsLoaded(@NonNull final List<GalleryModel> galleryModels);

    void onItemsError(@NonNull final Throwable throwable);
}
