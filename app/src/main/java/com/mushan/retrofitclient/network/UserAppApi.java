package com.mushan.retrofitclient.network;

import com.mushan.network.environment.DnsAppApi;
import com.mushan.network.RetrofitFactory;
import com.mushan.network.environment.Environment;

/**
 * @author : liujian
 * @since : 2017/10/4
 */

public class UserAppApi {
    private static UserAppInterface sUserAppInterface;

    public static void init() {
        sUserAppInterface = RetrofitFactory.getInstance().createServer(UserAppInterface.class, DnsAppApi.getBaseUrl(Environment.ApiType.USER));
    }

    public static UserAppInterface getUserAppInterface(){
        return sUserAppInterface;
    }
}
