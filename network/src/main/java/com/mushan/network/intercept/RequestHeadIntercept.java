package com.mushan.network.intercept;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author : liujian
 * @since : 2017/10/4
 */

public class RequestHeadIntercept implements Interceptor {
    //传入一个获取header信息的接口
    public RequestHeadIntercept() {

    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        builder.addHeader("", "");
        return chain.proceed(builder.build());
    }
}
