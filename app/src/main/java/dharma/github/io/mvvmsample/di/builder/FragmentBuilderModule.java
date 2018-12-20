package dharma.github.io.mvvmsample.di.builder;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dharma.github.io.mvvmsample.view.FullImageFragment;
import dharma.github.io.mvvmsample.view.MainFragment;

@Module
public abstract class FragmentBuilderModule {

    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract MainFragment contributeMainFragment();

    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract FullImageFragment contributeFullImageFragment();

}
