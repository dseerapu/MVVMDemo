package dharma.github.io.mvvmsample.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dharma.github.io.mvvmsample.constants.Constants;
import dharma.github.io.mvvmsample.data.ApiConstants;
import dharma.github.io.mvvmsample.data.api.ApiHelper;
import dharma.github.io.mvvmsample.data.api.ApiHelperImpl;
import dharma.github.io.mvvmsample.data.api.ApiService;
import dharma.github.io.mvvmsample.data.api.DataManager;
import dharma.github.io.mvvmsample.data.api.DataManagerImpl;
import dharma.github.io.mvvmsample.data.database.DBHelper;
import dharma.github.io.mvvmsample.data.database.DBHelperImpl;
import dharma.github.io.mvvmsample.data.database.MVVMDataBase;
import dharma.github.io.mvvmsample.data.prefs.PreferenceHelper;
import dharma.github.io.mvvmsample.data.prefs.PreferenceHelperImpl;
import dharma.github.io.mvvmsample.di.ApplicationContext;
import dharma.github.io.mvvmsample.di.DatabaseInfo;
import dharma.github.io.mvvmsample.di.PreferenceInfo;
import dharma.github.io.mvvmsample.utils.NetworkUtils;
import dharma.github.io.mvvmsample.utils.rx.AppSchedulerProvider;
import dharma.github.io.mvvmsample.utils.rx.SchedulerProvider;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Developed by Dharma Sai Seerapu on 18th Dec 2018
 * BaseModule for the Application
 * Reusable dependencies will be initialized here
 */
@Module
public class AppModule {


    /**
     * Initializes and provides OKHttpClinet{@link OkHttpClient} instance
     *
     * @return OKHttpClinet {@link OkHttpClient}
     */
    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(ApiConstants.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.readTimeout(ApiConstants.READ_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.writeTimeout(ApiConstants.WRITE_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        return okHttpClient.build();
    }

    /**
     * Initializes and provides ApiService {@link ApiService} instance
     * All the api calls are declared here
     *
     * @param okHttpClient
     * @return ApiService {@link ApiService}
     */
    @Provides
    @Singleton
    ApiService provideRetrofitInstance(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit.create(ApiService.class);
    }

    /**
     * Provides Context of the Application
     *
     * @param application
     * @return
     */
    @Provides
    @ApplicationContext
    Context providesContext(Application application) {
        return application;
    }

    /**
     * Provides Schedular Provider to run the logic in specified thread
     *
     * @return
     */
    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    /**
     * Provides DataBase Name
     *
     * @return
     */
    @Provides
    @DatabaseInfo
    String providesDatabaseName() {
        return Constants.DB_NAME;
    }

    /**
     * Provides DB Version
     *
     * @return
     */
    @Provides
    @DatabaseInfo
    Integer providesDatabaseVersion() {
        return Constants.DB_VERSION;
    }

    /**
     * @return SharedPreferenceName
     */
    @Provides
    @PreferenceInfo
    String providesSharedPrefName() {
        return Constants.SHARED_PREFERENCE_NAME;
    }

    /**
     * Creates MVVMDataBase{@link MVVMDataBase} instance
     *
     * @param context
     * @param databaseName
     * @return {@link MVVMDataBase}
     */
    @Provides
    @Singleton
    MVVMDataBase providesMVVMDataBase(@ApplicationContext Context context, @DatabaseInfo String databaseName) {
        return Room.databaseBuilder(context, MVVMDataBase.class, databaseName).fallbackToDestructiveMigration().build();
    }


    /**
     * Provides Network Utils
     *
     * @param context
     * @return {@link NetworkUtils}
     */
    @Provides
    @Singleton
    NetworkUtils providesNetworkUtils(@ApplicationContext Context context) {
        return new NetworkUtils(context);
    }


    /**
     * Provides Data Manager instance
     *
     * @param dataManager
     * @return DataManager {@link DataManager}
     */
    @Provides
    @Singleton
    DataManager provicesDataManager(DataManagerImpl dataManager) {
        return dataManager;
    }


    /**
     * Provides ApiHelper instance
     *
     * @param apiHelper
     * @return ApiHelper {@link ApiHelper}
     */
    @Provides
    @Singleton
    ApiHelper providesApiHelper(ApiHelperImpl apiHelper) {
        return apiHelper;
    }

    /**
     * Provides DB Helper instance
     *
     * @param dbHelper
     * @return DBHelper {@link DBHelper}
     */
    @Provides
    @Singleton
    DBHelper providesDBHelper(DBHelperImpl dbHelper) {
        return dbHelper;
    }

    /**
     * Provides Preference Helper {@link PreferenceHelper}
     *
     * @param preferenceHelper
     * @return Preference Helper {@link PreferenceHelper}
     */
    @Provides
    @Singleton
    PreferenceHelper providesPreferenceHelper(PreferenceHelperImpl preferenceHelper) {
        return preferenceHelper;
    }
}
