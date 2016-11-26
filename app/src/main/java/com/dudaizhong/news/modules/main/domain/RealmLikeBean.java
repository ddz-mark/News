package com.dudaizhong.news.modules.main.domain;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Markable on 2016/11/26.
 */

public class RealmLikeBean extends RealmObject implements Serializable {
    private static final long serialVersionUID = -654353139918062810L;

    @PrimaryKey
    private int id;

    private String image;

    private String title;

    private int type;

    private long time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
