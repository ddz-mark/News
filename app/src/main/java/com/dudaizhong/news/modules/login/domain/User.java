package com.dudaizhong.news.modules.login.domain;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Markable on 2016/11/26.
 */

public class User extends RealmObject implements Serializable {

    @PrimaryKey
    private String name;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
