package dharma.github.io.mvvmsample.view.main;

import dharma.github.io.mvvmsample.data.api.DataManager;
import dharma.github.io.mvvmsample.utils.NetworkUtils;
import dharma.github.io.mvvmsample.utils.rx.SchedulerProvider;
import dharma.github.io.mvvmsample.viewmodel.base.BaseViewModel;

public class MainActivityViewModel extends BaseViewModel {

    public MainActivityViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, NetworkUtils networkUtils) {
        super(dataManager, schedulerProvider, networkUtils);
    }
}
