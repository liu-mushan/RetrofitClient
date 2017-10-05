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

public class TokenIntercept implements Interceptor {
    //此处传入token过期的回调接口
    public TokenIntercept() {

    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        okhttp3.Response response = chain.proceed(request);
        if (!response.isSuccessful() && response.body() != null) {
            String errorResponse = response.body().toString();
            //如果错误信息为token过期，回调
        }
        return response;
    }
}
