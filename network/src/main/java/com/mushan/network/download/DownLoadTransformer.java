package com.mushan.network.download;

import android.support.annotation.NonNull;

import org.reactivestreams.Publisher;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;

/**
 * @author : liujian
 * @since : 2017/10/4
 */

public class DownLoadTransformer implements FlowableTransformer<ResponseBody, Object> {
    private String mPath;
    private String mFileName;

    public DownLoadTransformer(String path, String fileName) {
        this.mPath = path;
        this.mFileName = fileName;
    }

    @Override
    public Publisher<Object> apply(Flowable<ResponseBody> upstream) {
        return upstream.flatMap(new Function<ResponseBody, Publisher<Object>>() {
            @Override
            public Publisher<Object> apply(@NonNull ResponseBody responseBody) throws Exception {
                DownLoadOnSubscribe downLoadOnSubscribe = new DownLoadOnSubscribe(responseBody, mPath, mFileName);
                return Flowable.create(downLoadOnSubscribe, BackpressureStrategy.BUFFER);
            }
        });
    }
}
