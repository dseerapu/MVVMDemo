package dharma.github.io.mvvmsample.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dharma.github.io.mvvmsample.constants.Constants;
import dharma.github.io.mvvmsample.utils.NetworkUtils;


public class NetWorkReceiver extends BroadcastReceiver {

    @Inject
    NetworkUtils networkUtils;

    private static NetWorkReceiver netWorkReceiver;
    private static NetworkListenerInterface networkListenerInterface;

    public static NetWorkReceiver getInstance() {
        if (netWorkReceiver == null) {
            synchronized (NetWorkReceiver.class) {
                netWorkReceiver = new NetWorkReceiver();
            }
        }

        return netWorkReceiver;
    }


    public void setNetworkListenerInterface(@NonNull NetworkListenerInterface listener) {
        networkListenerInterface = listener;
    }

    public void removeListener() {
        networkListenerInterface = null;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        //Dagger should be initialized here
        AndroidInjection.inject(this, context);

        if (networkListenerInterface == null) {
            return;
        }

        String status = networkUtils.getConnectivityStatusString(context);

        if (status.equalsIgnoreCase(Constants.CONNECTED_TO_WIFI)) {

            networkListenerInterface.isConnectedToWifi();
        } else if (status.equalsIgnoreCase(Constants.CONNECTED_TO_MOBILE)) {
            networkListenerInterface.isConnectedToMobileNetwork();
        } else {
            networkListenerInterface.isDisconnected();
        }
    }
}
