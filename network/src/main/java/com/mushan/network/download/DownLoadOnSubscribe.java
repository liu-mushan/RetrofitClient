package com.mushan.network.download;

import android.support.annotation.NonNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * @author : liujian
 * @since : 2017/10/4
 */

class DownLoadOnSubscribe implements FlowableOnSubscribe<Object> {
    private FlowableEmitter<Object> mEmitter;

    private long mTotalLength;//文件中大小
    private long mDownLoadLength;//已经下载的大小
    private int mPercent;

    private Source mProgressSource;
    private BufferedSink mSink;

    DownLoadOnSubscribe(@NonNull ResponseBody responseBody, String path, String fileName) throws FileNotFoundException {
        mTotalLength = responseBody.contentLength();
        mProgressSource = getProgressSource(responseBody.source());
        mSink = Okio.buffer(Okio.sink(new File(path + fileName)));
    }

    @Override
    public void subscribe(FlowableEmitter<Object> e) throws Exception {
        mEmitter = e;
        try {
            mSink.writeAll(Okio.buffer(mProgressSource));
            mSink.close();
            mEmitter.onComplete();
        } catch (Exception exception) {
            mEmitter.onError(exception);
        }
    }


    private Source getProgressSource(BufferedSource source) {
        return new ForwardingSource(source) {
            @Override
            public long read(@NonNull Buffer sink, long byteCount) throws IOException {
                long read = super.read(sink, byteCount);

                mDownLoadLength += read == -1 ? 0 : read;
                if (mTotalLength <= 0)
                    onProgress(-1);
                else {
                    onProgress((int) (100 * mDownLoadLength / mTotalLength));
                }
                return read;
            }
        };
    }

    private void onProgress(int percent) {
        if (mEmitter == null)
            return;
        if (percent == mPercent)
            return;
        mPercent = percent;
        if (percent >= 100) {
            percent = 100;
            mEmitter.onNext(percent);
            return;
        }
        mEmitter.onNext(percent);
    }


}
