package com.mushan.network;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.mushan.network.download.DownLoadTransformer;
import com.mushan.network.download.DownloadInterface;
import com.mushan.network.environment.DnsAppApi;
import com.mushan.network.environment.Environment;
import com.mushan.network.https.HttpsVerification;
import com.mushan.network.intercept.RequestHeadIntercept;
import com.mushan.network.intercept.TokenIntercept;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author : liujian
 * @since : 2017/10/4
 */

public class RetrofitFactory {
    private volatile static RetrofitFactory instance;
    private OkHttpClient okHttpClient;

    private RetrofitFactory() {
    }

    public static RetrofitFactory getInstance() {
        if (instance == null) {
            synchronized (RetrofitFactory.class) {
                if (instance == null) {
                    instance = new RetrofitFactory();
                }
            }
        }
        return instance;
    }

    public <S> S createServer(Class<S> serverClass, String baseUrl) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        okHttpClient = getOkHttpClient();
        Retrofit retrofit = builder.client(okHttpClient).build();
        return retrofit.create(serverClass);
    }

    public <S> S createServerInBackgroundThread(Class<S> serverClass, String baseUrl) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .callbackExecutor(backgroundThreadExecutor)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        okHttpClient = getOkHttpClient();
        Retrofit retrofit = builder.client(okHttpClient).build();
        return retrofit.create(serverClass);
    }

    private Executor backgroundThreadExecutor = new Executor() {
        private Handler handler;

        @Override
        public void execute(@NonNull final Runnable runnable) {
            if (handler == null) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        handler = new Handler(Looper.myLooper());
                        handler.post(runnable);
                        Looper.loop();
                    }
                });
                thread.start();
            } else {
                handler.post(runnable);
            }
        }
    };

    private OkHttpClient getOkHttpClient() {
        HttpsVerification.SSLParams sslParams = HttpsVerification.getSslSocketFactory();
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);

        builder.addInterceptor(new RequestHeadIntercept());
        builder.addInterceptor(new TokenIntercept());

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        //BODY打印信息,NONE不打印信息
        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        builder.addInterceptor(logging);

        return builder.build();
    }

    /**
     * Download进度监听
     */
    public Flowable<Object> downLoadFile(String url) {
        return downLoadFile(url, null, null);
    }


    public Flowable<Object> downLoadFile(String url, String savePath, String fileName) {
        if (TextUtils.isEmpty(savePath)) {
            savePath = Utils.getDefaultDownLoadPath();
        }
        if (fileName == null || fileName.trim().equals("")) {
            fileName = Utils.getDefaultDownLoadFileName(url);
        }

        //compose操作参数
        DownLoadTransformer downLoadTransformer = new DownLoadTransformer(savePath, fileName);
        return createServer(DownloadInterface.class, DnsAppApi.getBaseUrl(Environment.ApiType.STOCKS))
                .startDownLoad(url)
                .compose(downLoadTransformer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
