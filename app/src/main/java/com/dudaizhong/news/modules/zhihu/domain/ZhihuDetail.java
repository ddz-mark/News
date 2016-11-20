package com.dudaizhong.news.modules.zhihu.domain;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Markable on 2016/11/19.
 */

public class ZhihuDetail implements Serializable {

    private static final long serialVersionUID = -3081476346914501216L;


    /**
     * body : 片库
     * title : 至今仍是死亡最多的单一传染病，肺结核有这么难治吗？
     * image : http://pic1.zhimg.com/ac1ec871034ee6465827103e67987018.jpg
     * share_url : http://daily.zhihu.com/story/8982887
     * js : []
     * ga_prefix : 112007
     * images : ["http://pic3.zhimg.com/a47538addb8d3aa56369bf63340afbf6.jpg"]
     * type : 0
     * id : 8982887
     * css : ["http://news-at.zhihu.com/css/news_qa.auto.css?v=4b3e3"]
     */

    @SerializedName("body")
    public String body;
    @SerializedName("title")
    public String title;
    @SerializedName("image")
    public String image;
    @SerializedName("share_url")
    public String shareUrl;
    @SerializedName("ga_prefix")
    public String gaPrefix;
    @SerializedName("type")
    public int type;
    @SerializedName("id")
    public int id;
    @SerializedName("js")
    public List<String> js;
    @SerializedName("images")
    public List<String> images;
    @SerializedName("css")
    public List<String> css;
}
