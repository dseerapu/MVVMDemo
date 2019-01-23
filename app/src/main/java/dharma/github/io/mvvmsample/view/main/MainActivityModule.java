package dharma.github.io.mvvmsample.view.main;

import dagger.Module;
import dagger.Provides;
import dharma.github.io.mvvmsample.data.api.DataManager;
import dharma.github.io.mvvmsample.utils.NetworkUtils;
import dharma.github.io.mvvmsample.utils.rx.SchedulerProvider;

@Module
public class MainActivityModule {

    @Provides
    MainActivityViewModel providesMainActivityViewModel(DataManager dataManager, SchedulerProvider provider, NetworkUtils networkUtils) {
        return new MainActivityViewModel(dataManager, provider, networkUtils);
    }
}
