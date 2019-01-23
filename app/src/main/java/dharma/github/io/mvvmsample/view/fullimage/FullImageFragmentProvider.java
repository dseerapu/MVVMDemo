package dharma.github.io.mvvmsample.view.fullimage;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FullImageFragmentProvider {

    @ContributesAndroidInjector(modules = FullImageFragmentModule.class)
    abstract FullImageFragment provideFullImageFragmentFactory();
}
