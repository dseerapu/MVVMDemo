package dharma.github.io.mvvmsample;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ProcessLifecycleOwner;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.multidex.MultiDex;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasBroadcastReceiverInjector;
import dagger.android.HasServiceInjector;
import dharma.github.io.mvvmsample.data.api.DataManager;
import dharma.github.io.mvvmsample.di.component.DaggerAppComponent;
import dharma.github.io.mvvmsample.network.NetWorkReceiver;
import dharma.github.io.mvvmsample.network.NetworkJob;

import static android.net.ConnectivityManager.CONNECTIVITY_ACTION;

public class MVVMSampleApp extends Application implements HasActivityInjector,
        HasServiceInjector, HasBroadcastReceiverInjector, LifecycleObserver {

    private MVVMSampleApp instance;

    @Inject
    DataManager dataManager;

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Inject
    DispatchingAndroidInjector<Service> serviceDispatchingAndroidInjector;

    @Inject
    DispatchingAndroidInjector<BroadcastReceiver> broadcastReceiverDispatchingAndroidInjector;

    private BroadcastReceiver mNetWorkReceiver;

    @Override
    public void onCreate() {
        super.onCreate();

        initializeDaggerComponent();

        instance = this;

        //Initialize life cycle listener
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);

        Stetho.initializeWithDefaults(this);
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

    @Override
    public AndroidInjector<BroadcastReceiver> broadcastReceiverInjector() {
        return broadcastReceiverDispatchingAndroidInjector;
    }

    @Override
    public AndroidInjector<Service> serviceInjector() {
        return serviceDispatchingAndroidInjector;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onAppStarted() {
        startJobService();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onAppResumed() {

        if (!dataManager.isNetworkJobRunning()) {
            startJobService();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onAppDestroyed() {

        if (mNetWorkReceiver != null) {
            unregisterReceiver(mNetWorkReceiver);
        }

        stopService(new Intent(this, NetworkJob.class));
        finishJob();
    }

    private void startJobService() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            scheduleJob();
        } else {
            registerReceiver();
        }
    }

    public void scheduleJob() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

            JobScheduler jobScheduler = (JobScheduler) getSystemService(Context
                    .JOB_SCHEDULER_SERVICE);

            JobInfo myJob = new JobInfo.Builder(0, new ComponentName(this, NetworkJob
                    .class))
                    .setMinimumLatency(1000)
                    .setOverrideDeadline(2000)
                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                    .setPersisted(true)
                    .build();
            jobScheduler.schedule(myJob);

        }

    }

    public void registerReceiver() {
        mNetWorkReceiver = NetWorkReceiver.getInstance();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(CONNECTIVITY_ACTION);
        registerReceiver(mNetWorkReceiver, intentFilter);
    }

    public void finishJob() {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
            List<JobInfo> allPendingJobs = jobScheduler.getAllPendingJobs();
            if (allPendingJobs.size() > 0) {
                // Finish the last one
                int jobId = allPendingJobs.get(0).getId();
                jobScheduler.cancel(jobId);
                Toast.makeText(this, "Job Cancelled :\t" + jobId, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No Jobs cancelled", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
