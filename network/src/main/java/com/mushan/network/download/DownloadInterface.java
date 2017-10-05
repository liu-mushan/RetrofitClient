package com.mushan.network.download;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Streaming;

/**
 * @author : liujian
 * @since : 2017/10/4
 */

public interface DownloadInterface {
    @Streaming
    @GET("{link}")
    Flowable<ResponseBody> startDownLoad(@Path("link") String fileUrl);
}
