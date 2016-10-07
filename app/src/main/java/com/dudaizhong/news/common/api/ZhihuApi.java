package com.dudaizhong.news.common.api;

import com.dudaizhong.news.modules.zhihu.domain.HotList;
import com.dudaizhong.news.modules.zhihu.domain.SectionList;
import com.dudaizhong.news.modules.zhihu.domain.ThemeList;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuList;


import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Dudaizhong on 2016/9/16.
 */

public interface ZhihuApi {

    String HOST = "http://news-at.zhihu.com/api/";

    /**
     * 最新日报
     * @return
     */
    @GET("4/news/latest")
    Observable<ZhihuList> getZhihuListNews();


    /**
     * 主题日报
     */
    @GET("4/themes")
    Observable<ThemeList> getThemeList();

    /**
     * 专栏
     */
    @GET("3/sections")
    Observable<SectionList> getSectionList();

    /**
     * 热门
     */
    @GET("3/news/hot")
    Observable<HotList> getHotList();
}
