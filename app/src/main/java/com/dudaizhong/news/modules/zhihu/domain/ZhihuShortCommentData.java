package com.dudaizhong.news.modules.zhihu.domain;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Markable on 2016/11/20.
 */

public class ZhihuShortCommentData implements Serializable {

    private static final long serialVersionUID = -5580602168944588740L;


    @SerializedName("comments")
    public List<CommentsBean> comments;

    public static class CommentsBean implements Serializable{
        /**
         * author : 每一天都在混水摸鱼
         * content : 钱会让它变的好吃
         * avatar : http://pic3.zhimg.com/0ecf2216c2612b04592126adc16affa2_im.jpg
         * time : 1413987020
         * id : 556780
         * likes : 0
         * reply_to : {"content":"我每次都不假思索选了牛肉，然后就深深的后悔没有试过一次鸡肉，到下一次又情不自禁选了牛肉，周而复始循环往复-_-#","status":0,"id":551969,"author":"怒放的腋毛"}
         */

        @SerializedName("author")
        public String author;
        @SerializedName("content")
        public String content;
        @SerializedName("avatar")
        public String avatar;
        @SerializedName("time")
        public long time;
        @SerializedName("id")
        public int id;
        @SerializedName("likes")
        public int likes;
        @SerializedName("reply_to")
        public ReplyToBean replyTo;

        public static class ReplyToBean implements Serializable{
            /**
             * content : 我每次都不假思索选了牛肉，然后就深深的后悔没有试过一次鸡肉，到下一次又情不自禁选了牛肉，周而复始循环往复-_-#
             * status : 0
             * id : 551969
             * author : 怒放的腋毛
             */

            @SerializedName("content")
            public String content;
            @SerializedName("status")
            public int status;
            @SerializedName("id")
            public int id;
            @SerializedName("author")
            public String author;
        }
    }
}
