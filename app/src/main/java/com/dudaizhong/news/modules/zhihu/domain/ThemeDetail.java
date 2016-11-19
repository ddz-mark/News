package com.dudaizhong.news.modules.zhihu.domain;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Markable on 2016/11/19.
 */

public class ThemeDetail implements Serializable {

    private static final long serialVersionUID = 6290781903405661459L;


    /**
     * stories : []
     * description : 为你发现最有趣的新鲜事，建议在 WiFi 下查看
     * background : http://pic1.zhimg.com/a5128188ed788005ad50840a42079c41.jpg
     * color : 8307764
     * name : 不许无聊
     * image : http://pic3.zhimg.com/da1fcaf6a02d1223d130d5b106e828b9.jpg
     * editors : [{"url":"http://www.zhihu.com/people/wezeit","bio":"微在 Wezeit 主编","id":70,"avatar":"http://pic4.zhimg.com/068311926_m.jpg","name":"益康糯米"},{"url":"http://www.zhihu.com/people/kuangzhou","bio":"明月般俊朗","id":71,"avatar":"http://pic4.zhimg.com/43d10de2b46c918a9ffe5d0472947b67_m.jpg","name":"顾惜朝"}]
     * image_source :
     */

    @SerializedName("description")
    public String description;
    @SerializedName("background")
    public String background;
    @SerializedName("color")
    public int color;
    @SerializedName("name")
    public String name;
    @SerializedName("image")
    public String image;
    @SerializedName("image_source")
    public String imageSource;
    @SerializedName("stories")
    public List<?> stories;
    @SerializedName("editors")
    public List<EditorsBean> editors;

    public static class EditorsBean implements Serializable{
        /**
         * url : http://www.zhihu.com/people/wezeit
         * bio : 微在 Wezeit 主编
         * id : 70
         * avatar : http://pic4.zhimg.com/068311926_m.jpg
         * name : 益康糯米
         */

        @SerializedName("url")
        public String url;
        @SerializedName("bio")
        public String bio;
        @SerializedName("id")
        public int id;
        @SerializedName("avatar")
        public String avatar;
        @SerializedName("name")
        public String name;
    }
}
