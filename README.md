# RetrofitClient
Rxjava2和Retrofit2网络库的封装，**支持文件下载监听，单文件/多文件上传监听，**支持https。



把项目中网络层抽离开来做了一个封装。这个库的**特点**：

- 没法直接拿去用，但是给大家一个示例去封装网络层。把项目拉下来稍微改一下就能直接放到自己的项目中
- 使用Rxjava2与Retrofit2封装
- 完全分层



如果对大家有帮助，麻烦帮忙**star**一下

```
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

```





```
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
```