package com.dudaizhong.news.common.api;

import com.dudaizhong.news.modules.gank.domain.AIList;
import com.dudaizhong.news.modules.gank.domain.GankHttpResponse;
import com.dudaizhong.news.modules.gank.domain.VideoList;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Markable on 2016/11/22.
 */

public interface GankApi {

    String HOST = "http://gank.io/api/";

    //http://gank.io/api/data/Android/10/1
    @GET("data/{type}/{num}/{page}")
    Observable<GankHttpResponse<List<AIList>>> getGankData(@Path("type") String type, @Path("num") int num, @Path("page") int page);

}
