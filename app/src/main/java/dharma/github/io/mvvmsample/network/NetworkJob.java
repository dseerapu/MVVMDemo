package dharma.github.io.mvvmsample.network;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.annotation.RequiresApi;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dharma.github.io.mvvmsample.data.api.DataManager;
import dharma.github.io.mvvmsample.utils.AppLogger;
import timber.log.Timber;

import static android.net.ConnectivityManager.CONNECTIVITY_ACTION;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class NetworkJob extends JobService {

    @Inject
    DataManager dataManager;


    private NetWorkReceiver mNetWorkReceiver;

    @Override
    public void onCreate() {
        super.onCreate();
        AppLogger.d("Service created");
        AndroidInjection.inject(this);
        mNetWorkReceiver = new NetWorkReceiver();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.d("Service destroyed");

        stopForeground(true);
    }

    /**
     * When the app's MainActivity is created, it starts this service. This is so that the
     * activity and this service can communicate back and forth. See "setUiCallback()"
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Timber.d("onStartCommand");
        return START_STICKY;
    }


    @Override
    public boolean onStartJob(JobParameters params) {
        Timber.d("onStartJob" + mNetWorkReceiver);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(CONNECTIVITY_ACTION);
        registerReceiver(mNetWorkReceiver, intentFilter);

        dataManager.setNetworkJobRunning(true);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Timber.d("onStopJob");
        dataManager.setNetworkJobRunning(false);
        unregisterReceiver(mNetWorkReceiver);
        jobFinished(params, true);
        return true;
    }
}