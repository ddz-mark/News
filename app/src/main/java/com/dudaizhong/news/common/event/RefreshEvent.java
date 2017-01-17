package com.dudaizhong.news.common.event;

/**
 * Created by Markable on 2016/11/26.
 */

public class RefreshEvent {

    private String name;

    public RefreshEvent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
