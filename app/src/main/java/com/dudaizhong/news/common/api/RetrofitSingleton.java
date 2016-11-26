package com.dudaizhong.news.common.api;

import android.content.Context;

import com.dudaizhong.news.BuildConfig;
import com.dudaizhong.news.app.App;
import com.dudaizhong.news.app.Constants;
import com.dudaizhong.news.base.utils.ToastUtil;
import com.dudaizhong.news.base.utils.Util;
import com.dudaizhong.news.base.utils.rxUtils.RxHelper;
import com.dudaizhong.news.modules.gank.domain.AIList;
import com.dudaizhong.news.modules.gank.domain.GankHttpResponse;
import com.dudaizhong.news.modules.gank.domain.VideoList;
import com.dudaizhong.news.modules.zhihu.domain.HotList;
import com.dudaizhong.news.modules.zhihu.domain.SectionDetail;
import com.dudaizhong.news.modules.zhihu.domain.SectionList;
import com.dudaizhong.news.modules.zhihu.domain.ThemeDetail;
import com.dudaizhong.news.modules.zhihu.domain.ThemeList;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuCommentData;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuDetail;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuList;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuShortCommentData;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by Dudaizhong on 2016/9/16.
 */
public class RetrofitSingleton {

    private static OkHttpClient okHttpClient = null;
    private static ZhihuApi zhihuApiService = null;
    private static GankApi gankApiService = null;

    private RetrofitSingleton() {
        initOkHttp();
        zhihuApiService = getZhihuApiService();
        gankApiService = getGankApiService();
    }

    public static RetrofitSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final RetrofitSingleton INSTANCE = new RetrofitSingleton();
    }

   /* private static void initOkHttp() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        if (BuildConfig.DEBUG) {
            // https://drakeet.me/retrofit-2-0-okhttp-3-0-config
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(loggingInterceptor);
//        }

        // http://www.jianshu.com/p/93153b34310e
        File cacheFile = new File(App.cacheDir,"/NetCache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!Util.isNetworkConnected(App.getAppContext())) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (Util.isNetworkConnected(App.getAppContext())) {
                    int maxAge = 0;
                    // 有网络时, 不缓存, 最大保存时长为0
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };
//        Interceptor apikey = new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request request = chain.request();
//                request = request.newBuilder()
//                        .addHeader("apikey",Constants.KEY_API)
//                        .build();
//                return chain.proceed(request);
//            }
//        };
//        builder.addInterceptor(apikey);

        //设置缓存
        builder.addNetworkInterceptor(cacheInterceptor);
        builder.addInterceptor(cacheInterceptor);
        builder.cache(cache);
        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        okHttpClient = builder.build();

       /* CookieJar mCookieJar = new CookieJar() {
            private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                cookieStore.put(url.host(), cookies);
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                List<Cookie> cookies = cookieStore.get(url.host());

                //cookies.get(0).getClass().
                return cookies != null ? cookies : new ArrayList<Cookie>();
            }
        };
    }*/

    private static void initOkHttp() {
        // https://drakeet.me/retrofit-2-0-okhttp-3-0-config
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // http://www.jianshu.com/p/93153b34310e
        File cacheFile = new File(App.cacheDir, "/NetCache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!Util.isNetworkConnected(App.getAppContext())) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (Util.isNetworkConnected(App.getAppContext())) {
                    int maxAge = 0;
                    // 有网络时, 不缓存, 最大保存时长为0
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };

        CookieJar mCookieJar = new CookieJar() {
            private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                cookieStore.put(url.host(), cookies);
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                List<Cookie> cookies = cookieStore.get(url.host());

                //cookies.get(0).getClass().
                return cookies != null ? cookies : new ArrayList<Cookie>();
            }
        };

        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addNetworkInterceptor(cacheInterceptor)
                .addInterceptor(cacheInterceptor)
                .cache(cache)
                .cookieJar(mCookieJar)
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();
    }

    private static ZhihuApi getZhihuApiService() {
        Retrofit zhihuRetrofit = new Retrofit.Builder()
                .baseUrl(ZhihuApi.HOST)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return zhihuRetrofit.create(ZhihuApi.class);
    }

    private static GankApi getGankApiService() {
        Retrofit gankRetrofit = new Retrofit.Builder()
                .baseUrl(GankApi.HOST)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return gankRetrofit.create(GankApi.class);
    }


    public static void disposeFailureInfo(Throwable t, Context context) {
        if (context != null) {
            if (t.toString().contains("GaiException")
                    || t.toString().contains("SocketTimeoutException")
                    || t.toString().contains("UnknownHostException")) {
                ToastUtil.showLongToast(context, "网络不给力");
            } else if (t.toString().contains("ConnectException")) {
                ToastUtil.showLongToast(context, "网络出错");
            } else {
                ToastUtil.showLongToast(context, t.getMessage());
            }
        }
    }

    /**推荐将请求接口都写在这里,让 activity 更专注于自身**/

    /************************
     * 知乎日报
     *************************/

    //知乎日报的列表数据
    public Observable<ZhihuList> getZhihuListNews() {
        return zhihuApiService.getZhihuListNews().compose(RxHelper.<ZhihuList>rxSchedulerHelper());
    }

    //知乎的热门
    public Observable<HotList> getHotList() {
        return zhihuApiService.getHotList().compose(RxHelper.<HotList>rxSchedulerHelper());
    }

    //知乎日报的详情页
    public Observable<ZhihuDetail> getZhihuDetail(int id) {
        return zhihuApiService.getZhihuDetailInfo(id).compose(RxHelper.<ZhihuDetail>rxSchedulerHelper());
    }

    //详情页的点赞数
    public Observable<ZhihuCommentData> getZhihuCommentInfo(int id) {
        return zhihuApiService.getZhihuCommentInfo(id).compose(RxHelper.<ZhihuCommentData>rxSchedulerHelper());
    }

    //短评论
    public Observable<ZhihuShortCommentData> getZhihuShortCommentInfo(int id) {
        return zhihuApiService.getZhihuShortComment(id).compose(RxHelper.<ZhihuShortCommentData>rxSchedulerHelper());
    }

    //长评论
    public Observable<ZhihuShortCommentData> getZhihuLongCommentInfo(int id) {
        return zhihuApiService.getZhihuLongComment(id).compose(RxHelper.<ZhihuShortCommentData>rxSchedulerHelper());
    }


    //知乎的主题
    public Observable<ThemeList> getThemeList() {
        return zhihuApiService.getThemeList().compose(RxHelper.<ThemeList>rxSchedulerHelper());
    }

    //知乎主题的详情页
    public Observable<ThemeDetail> getThemeDetail(int id) {
        return zhihuApiService.getThemeDetailInfo(id).compose(RxHelper.<ThemeDetail>rxSchedulerHelper());
    }

    //知乎的专栏
    public Observable<SectionList> getSectionList() {
        return zhihuApiService.getSectionList().compose(RxHelper.<SectionList>rxSchedulerHelper());
    }

    //知乎专栏的详情页
    public Observable<SectionDetail> getSetionDetail(int id) {
        return zhihuApiService.getSectionDetailInfo(id).compose(RxHelper.<SectionDetail>rxSchedulerHelper());
    }

    //==============================================================================================

    //Android,IOS,福利,视频列表页
    public Observable<GankHttpResponse<List<AIList>>> getGankList(String type, int num, int page) {
        return gankApiService.getGankData(type, num, page).compose(RxHelper.<GankHttpResponse<List<AIList>>>rxSchedulerHelper());
    }

}
