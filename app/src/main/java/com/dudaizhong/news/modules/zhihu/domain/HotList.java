package com.dudaizhong.news.modules.zhihu.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Dudaizhong on 2016/10/1.
 */

public class HotList implements Serializable{


    /**
     * news_id : 8832725
     * url : http://news-at.zhihu.com/api/2/news/8832725
     * thumbnail : http://pic3.zhimg.com/ce0ff92a588071b8a20c35af8ec3e182.jpg
     * title : 瞎扯 · 如何正确地吐槽
     */

    private List<RecentBean> recent;

    public List<RecentBean> getRecent() {
        return recent;
    }

    public void setRecent(List<RecentBean> recent) {
        this.recent = recent;
    }

    public static class RecentBean implements Serializable{
        private int news_id;
        private String url;
        private String thumbnail;
        private String title;

        public int getNews_id() {
            return news_id;
        }

        public void setNews_id(int news_id) {
            this.news_id = news_id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
