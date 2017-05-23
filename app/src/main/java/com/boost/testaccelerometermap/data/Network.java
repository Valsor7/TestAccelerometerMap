package com.boost.testaccelerometermap.data;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by yaroslav on 23.05.17.
 */

public class Network {

    private Context mContext;

    public Network(Context context) {
        mContext = context;
    }

    public boolean isNetwork() {
        final ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return !(activeNetworkInfo == null || !activeNetworkInfo.isConnected());
    }
}
