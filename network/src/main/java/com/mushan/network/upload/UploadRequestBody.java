package com.mushan.network.upload;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

/**
 * @author : liujian
 * @since : 2017/10/5
 */

public class UploadRequestBody extends RequestBody {
    interface UploadListener {
        void onRead(int read);
    }

    private static final int DEFAULT_BUFFER_SIZE = 2048;
    @NonNull
    private File mFile;
    private UploadListener mListener;

    public UploadRequestBody(@NonNull File file, UploadListener listener) {
        this.mFile = file;
        this.mListener = listener;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return MediaType.parse("multipart/form-data");
    }

    @Override
    public void writeTo(@NonNull BufferedSink sink) throws IOException {
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        FileInputStream in = new FileInputStream(mFile);

        int read;
        while ((read = in.read(buffer)) != -1) {
            sink.write(buffer, 0, read);
            if (mListener != null) {
                mListener.onRead(read);
            }
        }
    }
}
