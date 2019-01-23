package dharma.github.io.mvvmsample.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import dharma.github.io.mvvmsample.di.ApplicationContext;
import dharma.github.io.mvvmsample.di.PreferenceInfo;

@Singleton
public class PreferenceHelperImpl implements PreferenceHelper {

    private SharedPreferences preferences;
    private static final String IS_NETWORK_JOB_SERVICE_RUNNING = "is_network_job_service_running";

    @Inject
    public PreferenceHelperImpl(@ApplicationContext Context context, @PreferenceInfo String preferenceName) {
        preferences = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
    }

    @Override
    public void setNetworkJobRunning(boolean isNetworkJobRunning) {
        preferences.edit().putBoolean(IS_NETWORK_JOB_SERVICE_RUNNING, isNetworkJobRunning).apply();
    }

    @Override
    public boolean isNetworkJobRunning() {
        return preferences.getBoolean(IS_NETWORK_JOB_SERVICE_RUNNING, false);
    }

    //Note : Implement all the preference methods defined in Preference Helper class

}
