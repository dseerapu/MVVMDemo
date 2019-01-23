package dharma.github.io.mvvmsample.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import java.util.Calendar;
import java.util.Locale;

import javax.inject.Singleton;

import dharma.github.io.mvvmsample.constants.Constants;
import dharma.github.io.mvvmsample.di.ApplicationContext;
import timber.log.Timber;

@Singleton
public class NetworkUtils {

    private Context context;
    private static int TYPE_WIFI = 1;
    private static int TYPE_MOBILE = 2;
    private static int TYPE_NOT_CONNECTED = 0;

    public NetworkUtils(@ApplicationContext Context context) {
        this.context = context;
    }

    private int getConnectivityStatus(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        assert cm != null;
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (null != activeNetwork) {

            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;

            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        }
        return TYPE_NOT_CONNECTED;
    }

    public String getConnectivityStatusString(Context context) {

        int conn = getConnectivityStatus(context);

        String status = null;
        if (conn == NetworkUtils.TYPE_WIFI) {
            //status = "Wifi enabled";
            status = Constants.CONNECTED_TO_WIFI;
        } else if (conn == NetworkUtils.TYPE_MOBILE) {
            //status = "Mobile data enabled";
            System.out.println(Constants.CONNECTED_TO_MOBILE);
            status = getNetworkClass(context);
        } else if (conn == NetworkUtils.TYPE_NOT_CONNECTED) {
            status = Constants.NOT_CONNECTED;
        }

        Timber.d("### Connection status " + status + " at " + Calendar.getInstance(Locale.getDefault()).getTimeInMillis());

        return status;
    }

    private String getNetworkClass(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null || !info.isConnected())
            return "-"; //not connected
        if (info.getType() == ConnectivityManager.TYPE_WIFI)
            return "WIFI";
        if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
            int networkType = info.getSubtype();
            switch (networkType) {
                case TelephonyManager.NETWORK_TYPE_HSPAP:  //api<13 : replace by 15
                    return "3G";
                case TelephonyManager.NETWORK_TYPE_LTE:    //api<11 : replace by 13
                    return "4G";
                default:
                    return "UNKNOWN";
            }
        }
        return "UNKNOWN";
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }


}