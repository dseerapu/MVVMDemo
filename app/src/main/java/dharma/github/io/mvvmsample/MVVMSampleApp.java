package dharma.github.io.mvvmsample;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dharma.github.io.mvvmsample.di.component.DaggerAppComponent;

public class MVVMSampleApp extends Application implements HasActivityInjector {

    private MVVMSampleApp instance;

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        initializeDaggerComponent();

        // Application  instance initialization
        instance = this;
    }

    private void initializeDaggerComponent() {

        DaggerAppComponent.builder().application(this).build().inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    public MVVMSampleApp getInstance() {
        return instance;
    }
}
