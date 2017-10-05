package com.mushan.retrofitclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mushan.network.RetrofitFactory;
import com.mushan.network.subscriber.DownloadSubscriber;
import com.mushan.retrofitclient.bean.User;
import com.mushan.retrofitclient.network.UserAppApi;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        demo();
        downloadDemo();
    }

    private void downloadDemo() {
        RetrofitFactory.getInstance()
                .downLoadFile("/uploads/VID_20170616_122618.mp4")
                .subscribe(new DownloadSubscriber() {
                    @Override
                    public void onProgress(Integer progress) {
                        Log.i(TAG, "onProgress: "+progress);
                    }

                    @Override
                    public void onError(String errorMessage) {
                        Log.e(TAG, "onError: " + errorMessage);
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                        Log.i(TAG, "onComplete: ");
                    }
                });
    }

    private void demo() {
        UserAppApi.getUserAppInterface().getUserList("1")
                .flatMap(new Function<List<User>, ObservableSource<User>>() {
                    @Override
                    public ObservableSource<User> apply(List<User> users) throws Exception {
                        return Observable.fromIterable(users);
                    }
                })
                .map(new Function<User, String>() {
                    @Override
                    public String apply(User user) throws Exception {
                        return user.getName();
                    }
                })
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return !s.isEmpty();
                    }
                })
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {

                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.i("TAG", "accept: " + s);
                    }
                });
    }
}
