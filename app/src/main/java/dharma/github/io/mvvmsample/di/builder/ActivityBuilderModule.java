package dharma.github.io.mvvmsample.di.builder;

import dagger.Module;
import dharma.github.io.mvvmsample.view.MainActivity;

/**
 * Developed by Dharma Sai Seerapu on 18th Dec 2018
 * <p>
 * Builder class for all the activities
 * Declare all activities{@link android.app.Activity} here to use Dagger{@link dagger.android.DaggerActivity}
 */
@Module
public abstract class ActivityBuilderModule {

    //Hint : Write all activities here

    @SuppressWarnings("unused")
//    @ContributesAndroidInjector(modules = )
    abstract MainActivity mainActivity();

}
