package dharma.github.io.mvvmsample.view.fullimage;

import dharma.github.io.mvvmsample.data.api.DataManager;
import dharma.github.io.mvvmsample.utils.NetworkUtils;
import dharma.github.io.mvvmsample.utils.rx.SchedulerProvider;
import dharma.github.io.mvvmsample.viewmodel.base.BaseViewModel;

public class FullImageViewModel extends BaseViewModel {

    public FullImageViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, NetworkUtils networkUtils) {
        super(dataManager, schedulerProvider, networkUtils);
    }
}
