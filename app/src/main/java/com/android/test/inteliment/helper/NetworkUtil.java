package com.android.test.inteliment.helper;

import android.content.Context;
import android.content.ContextWrapper;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.test.inteliment.activities.App;

/**
 * Created by Chaitali on 12/04/2017.
 */

public class NetworkUtil extends ContextWrapper {

    private static NetworkUtil networkUtil;

    public NetworkUtil(Context base) {
        super(base);
    }

    public static NetworkUtil getInstance() {
        if (networkUtil == null) {
            networkUtil = new NetworkUtil(App.get());
        }
        return networkUtil;
    }


    /**
     * Checks if is online.
     *
     * @param context the context
     * @return true, if is online
     */
    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null) {
            return netInfo.isConnectedOrConnecting();
        }
        return false;
    }
}
