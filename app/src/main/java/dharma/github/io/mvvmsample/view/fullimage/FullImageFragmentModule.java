package dharma.github.io.mvvmsample.view.fullimage;

import android.arch.lifecycle.ViewModelProvider;

import dagger.Module;
import dagger.Provides;
import dharma.github.io.mvvmsample.data.api.DataManager;
import dharma.github.io.mvvmsample.di.ViewModelProviderFactory;
import dharma.github.io.mvvmsample.utils.NetworkUtils;
import dharma.github.io.mvvmsample.utils.rx.SchedulerProvider;

@Module
public class FullImageFragmentModule {

    @Provides
    FullImageViewModel provideFullImageViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, NetworkUtils networkUtils) {
        return new FullImageViewModel(dataManager, schedulerProvider, networkUtils);
    }

    @Provides
    ViewModelProvider.Factory fullImageViewModelProvider(FullImageViewModel fullImageViewModel) {
        return new ViewModelProviderFactory<>(fullImageViewModel);
    }
}
