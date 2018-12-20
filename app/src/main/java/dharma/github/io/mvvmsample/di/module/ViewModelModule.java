package dharma.github.io.mvvmsample.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import dharma.github.io.mvvmsample.viewmodel.GalleryViewModel;
import dharma.github.io.mvvmsample.viewmodel.ViewModelFactory;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(GalleryViewModel.class)
    @SuppressWarnings("unused")
    abstract ViewModel bindsGalleryViewModel(GalleryViewModel galleryViewModel);

    @Binds
    @SuppressWarnings("unused")
    abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory viewModelFactory);
}
