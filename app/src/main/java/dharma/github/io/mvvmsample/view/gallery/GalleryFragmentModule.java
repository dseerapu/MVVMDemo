package dharma.github.io.mvvmsample.view.gallery;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.GridLayoutManager;

import dagger.Module;
import dagger.Provides;
import dharma.github.io.mvvmsample.data.api.DataManager;
import dharma.github.io.mvvmsample.di.ViewModelProviderFactory;
import dharma.github.io.mvvmsample.utils.NetworkUtils;
import dharma.github.io.mvvmsample.utils.rx.SchedulerProvider;

@Module
public class GalleryFragmentModule {

    @Provides
    GalleryViewModel providesGalleryViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, NetworkUtils networkUtils) {
        return new GalleryViewModel(dataManager, schedulerProvider, networkUtils);
    }

    @Provides
    GalleryAdapter providesGalleryAdapter() {
        return new GalleryAdapter();
    }

    @Provides
    GridLayoutManager providesGridLayoutManager(GalleryFragment galleryFragment) {
        return new GridLayoutManager(galleryFragment.getActivity(), 2, GridLayoutManager.VERTICAL, false);
    }

    @Provides
    ViewModelProvider.Factory galleryViewModelProvider(GalleryViewModel galleryViewModel) {
        return new ViewModelProviderFactory<>(galleryViewModel);
    }
}
