package dharma.github.io.mvvmsample.view.gallery;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class GalleryFragmentProvider {

    @ContributesAndroidInjector(modules = GalleryFragmentModule.class)
    abstract GalleryFragment providesGalleryFragmentFactory();
}
