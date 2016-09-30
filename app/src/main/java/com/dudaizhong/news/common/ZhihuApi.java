package com.dudaizhong.news.common;

import com.dudaizhong.news.modules.zhihu.domain.ZhihuListNews;


import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Dudaizhong on 2016/9/16.
 */

public interface ZhihuApi {

    String HOST = "http://news-at.zhihu.com/api/4/";

    /**
     * 最新日报
     * @return
     */
    @GET("news/latest")
    Observable<ZhihuListNews> getZHihuListNews();

}
