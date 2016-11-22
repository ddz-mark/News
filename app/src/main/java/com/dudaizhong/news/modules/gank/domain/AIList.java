package com.dudaizhong.news.modules.gank.domain;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Markable on 2016/11/22.
 */

public class AIList implements Serializable {

    private static final long serialVersionUID = -5964666979992528812L;


    /**
     * _id : 58328488421aa929ac960b0d
     * createdAt : 2016-11-21T13:22:16.834Z
     * desc : Java 反射 使用总结
     * publishedAt : 2016-11-22T11:30:13.971Z
     * source : web
     * type : Android
     * url : http://www.cnblogs.com/zhaoyanjun/p/6074887.html
     * used : true
     * who : 赵彦军
     * images : ["http://img.gank.io/f8d19e7d-77e9-4e53-a83b-201120e6a67c"]
     */



    @SerializedName("_id")
    public String id;
    @SerializedName("createdAt")
    public String createdAt;
    @SerializedName("desc")
    public String desc;
    @SerializedName("publishedAt")
    public String publishedAt;
    @SerializedName("source")
    public String source;
    @SerializedName("type")
    public String type;
    @SerializedName("url")
    public String url;
    @SerializedName("used")
    public boolean used;
    @SerializedName("who")
    public String who;
    @SerializedName("images")
    public List<String> images;
}
