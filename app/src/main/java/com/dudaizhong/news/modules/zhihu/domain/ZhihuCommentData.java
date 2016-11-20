package com.dudaizhong.news.modules.zhihu.domain;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Markable on 2016/11/20.
 */

public class ZhihuCommentData implements Serializable {


    /**
     * long_comments : 5
     * popularity : 8
     * short_comments : 49
     * comments : 54
     */

    @SerializedName("long_comments")
    public int longComments;
    @SerializedName("popularity")
    public int popularity;
    @SerializedName("short_comments")
    public int shortComments;
    @SerializedName("comments")
    public int comments;
}
