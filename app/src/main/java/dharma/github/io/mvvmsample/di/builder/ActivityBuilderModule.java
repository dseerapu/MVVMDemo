package dharma.github.io.mvvmsample.di.builder;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dharma.github.io.mvvmsample.view.main.MainActivity;
import dharma.github.io.mvvmsample.view.fullimage.FullImageFragmentProvider;
import dharma.github.io.mvvmsample.view.gallery.GalleryFragmentProvider;
import dharma.github.io.mvvmsample.view.main.MainActivityModule;

/**
 * Developed by Dharma Sai Seerapu on 18th Dec 2018
 * <p>
 * Builder class for all the activities
 * Declare all activities{@link android.app.Activity} here to use Dagger{@link dagger.android.DaggerActivity}
 */
@Module
public abstract class ActivityBuilderModule {

    //Note: Define All your activities and their corresponding Modules

    @SuppressWarnings("unused")
    @ContributesAndroidInjector(modules = {
            MainActivityModule.class,
            GalleryFragmentProvider.class,
            FullImageFragmentProvider.class
    })
    abstract MainActivity mainActivity();

}
