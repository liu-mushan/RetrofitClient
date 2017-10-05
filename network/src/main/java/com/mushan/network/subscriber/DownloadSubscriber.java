package com.mushan.network.subscriber;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * 带进度监听方法的Progress
 * @author : liujian
 * @since : 2017/10/4
 */

public abstract class DownloadSubscriber implements Subscriber<Object> {
    public abstract void onProgress(Integer progress);

    public abstract void onError(String errorMessage);

    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription s) {
        subscription = s;
        subscription.request(1);
    }

    @Override
    public final void onNext(Object o) {
        if (o instanceof Integer) {
            onProgress((Integer) o);
        }
        subscription.request(1);
    }

    @Override
    public final void onError(Throwable t) {
        onError(t.getMessage());
    }

    @Override
    public void onComplete() {
        subscription.cancel();
    }
}
