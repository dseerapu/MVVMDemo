package dharma.github.io.mvvmsample.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dharma.github.io.mvvmsample.MVVMSampleApp;
import dharma.github.io.mvvmsample.di.builder.ActivityBuilderModule;
import dharma.github.io.mvvmsample.di.module.AppModule;


@Singleton
@Component(modules = {
        AppModule.class,
        AndroidInjectionModule.class,
        ActivityBuilderModule.class})
/**
 * Developed by Dharma Sai Seerapu on 18th Dec 2018
 * <p>
 * Dagger Component : Bridge between Inject {@link javax.inject.Inject} and Module {@link dagger.Module} annotations
 * </p>
 * <p>
 * Above modules provides Dependencies to the particular activities declared in ActivityBuilderModule {@link ActivityBuilderModule}
 * </p>
 */
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(MVVMSampleApp mvvmSampleApp);
}
