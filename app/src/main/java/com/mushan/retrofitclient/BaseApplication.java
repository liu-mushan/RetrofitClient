package com.mushan.retrofitclient;

import android.app.Application;

import com.mushan.network.environment.DnsAppApi;
import com.mushan.retrofitclient.network.UserAppApi;

/**
 * @author : liujian
 * @since : 2017/10/4
 */

public class BaseApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        DnsAppApi.init();
        UserAppApi.init();
    }
}
