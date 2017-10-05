package com.mushan.retrofitclient.network;

import com.mushan.retrofitclient.bean.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author : liujian
 * @since : 2017/10/4
 */

public interface UserAppInterface {
    @GET("portfolioId")
    Observable<List<User>> getUserList(String portfolioId);
}
