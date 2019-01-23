package dharma.github.io.mvvmsample.di.builder;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dharma.github.io.mvvmsample.network.NetworkJob;

@Module
public abstract class ServiceBuilderModule {

    @ContributesAndroidInjector
    abstract NetworkJob bindNetworkJob();
}
