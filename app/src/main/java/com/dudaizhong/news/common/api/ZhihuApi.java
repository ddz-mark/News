package com.dudaizhong.news.common.api;

import com.dudaizhong.news.modules.zhihu.domain.HotList;
import com.dudaizhong.news.modules.zhihu.domain.SectionDetail;
import com.dudaizhong.news.modules.zhihu.domain.SectionList;
import com.dudaizhong.news.modules.zhihu.domain.ThemeDetail;
import com.dudaizhong.news.modules.zhihu.domain.ThemeList;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuDetail;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuList;


import retrofit2.http.GET;
import retrofit2.http.Path;
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
     * 热门
     */
    @GET("3/news/hot")
    Observable<HotList> getHotList();


    /**
     * 日报详情内容
     * @param id
     * @return
     */
    @GET("4/news/{id}")
    Observable<ZhihuDetail> getZhihuDetailInfo(@Path("id") int id);


    /**
     * 主题日报
     */
    @GET("4/themes")
    Observable<ThemeList> getThemeList();

    /**
     * 主题日报的详情页
     * @param id
     * @return
     */
    @GET("4/theme/{id}")
    Observable<ThemeDetail> getThemeDetailInfo(@Path("id") int id);

    /**
     * 专栏
     */
    @GET("3/sections")
    Observable<SectionList> getSectionList();

    /**
     * 专栏的详情页
     * @param id
     * @return
     */
    @GET("3/section/{id}")
    Observable<SectionDetail> getSectionDetailInfo(@Path("id") int id);
}
