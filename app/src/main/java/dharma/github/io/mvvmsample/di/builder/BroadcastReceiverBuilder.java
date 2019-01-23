package dharma.github.io.mvvmsample.di.builder;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dharma.github.io.mvvmsample.network.NetWorkReceiver;

@Module
public abstract class BroadcastReceiverBuilder {

    @ContributesAndroidInjector
    abstract NetWorkReceiver bindNetWorkReceiver();
}
