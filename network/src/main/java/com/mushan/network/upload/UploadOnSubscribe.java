package com.mushan.network.upload;

import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;

/**
 * @author : liujian
 * @since : 2017/10/5
 */

public class UploadOnSubscribe implements FlowableOnSubscribe<Integer>, UploadRequestBody.UploadListener {
    private FlowableEmitter<Integer> mObservableEmitter;
    private long uploadLength;
    private int mPercent;

    private long mTotalLength;

    public UploadOnSubscribe(long totalLength) {
        this.mTotalLength = totalLength;
    }

    @Override
    public void subscribe(FlowableEmitter<Integer> e) throws Exception {
        this.mObservableEmitter = e;
    }

    @Override
    public void onRead(int read) {
        uploadLength += read;
        if (mTotalLength <= 0) {
            onProgress(-1);
        } else {
            onProgress((int) (100 * uploadLength / mTotalLength));
        }
    }


    private void onProgress(int percent) {
        if (mObservableEmitter == null)
            return;
        if (percent == mPercent)
            return;
        mPercent = percent;
        if (percent >= 100) {
            percent = 100;
            mObservableEmitter.onNext(percent);
            mObservableEmitter.onComplete();
            return;
        }
        mObservableEmitter.onNext(percent);
    }
}
