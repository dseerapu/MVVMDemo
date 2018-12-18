package dharma.github.io.mvvmsample.di.module;

import android.arch.lifecycle.ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import dharma.github.io.mvvmsample.viewmodel.GalleryViewModel;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(GalleryViewModel.class)
    abstract ViewModel bindsGalleryViewModel(GalleryViewModel galleryViewModel);


}
