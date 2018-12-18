package dharma.github.io.mvvmsample.di.module;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dharma.github.io.mvvmsample.data.ApiConstants;
import dharma.github.io.mvvmsample.data.ApiService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Developed by Dharma Sai Seerapu on 18th Dec 2018
 * BaseModule for the Application
 * Reusable dependencies will be initialized here
 */
@Module(includes = ViewModelModule.class)
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
//        okHttpClient.addInterceptor(new RequestInterceptor());
        okHttpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        return okHttpClient.build();
    }

    /**
     * Initializes and provides ApiService {@link ApiService} instance
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
                .client(okHttpClient)
                .build();
        return retrofit.create(ApiService.class);
    }


}
