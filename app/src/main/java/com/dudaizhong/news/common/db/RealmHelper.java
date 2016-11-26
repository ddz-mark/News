package com.dudaizhong.news.common.db;

import android.content.Context;

import com.dudaizhong.news.modules.login.domain.User;
import com.dudaizhong.news.modules.main.domain.RealmLikeBean;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by Markable on 2016/11/26.
 */

public class RealmHelper {

    private static RealmHelper sIntance;
    public final Context mContext;
    private String realmName = "Reader.realm";
    private Realm mRealm;

    private RealmHelper(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 双检索单例
     *
     * @param context
     * @return
     */
    public static RealmHelper getIntance(Context context) {
        if (sIntance == null) {
            synchronized (RealmHelper.class) {
                if (sIntance == null) {
                    sIntance = new RealmHelper(context);
                }
            }
        }
        return sIntance;
    }

    /**
     * 获取realm对象
     *
     * @return
     */
    public Realm getRealm() {
        return mRealm = Realm.getInstance(
                new RealmConfiguration
                        .Builder(mContext)
                        .deleteRealmIfMigrationNeeded()
                        .name(realmName)
                        .build());
    }

    //===============================================================

    //增加收藏记录
    public void insertLikeBean(RealmLikeBean bean) {
        getRealm().beginTransaction();
        getRealm().copyToRealm(bean);
        getRealm().commitTransaction();
    }

    //查询收藏记录
    public boolean queryLikeId(int id) {
        RealmResults<RealmLikeBean> results = getRealm().where(RealmLikeBean.class).findAll();
        for (RealmLikeBean bean : results) {
            if (bean.getId() == id) {
                return true;
            }
        }
        return false;
    }

    //删除收藏记录
    public void deleteLikeBean(int id) {
        RealmLikeBean bean = getRealm().where(RealmLikeBean.class).equalTo("id", id).findFirst();
        getRealm().beginTransaction();
        bean.deleteFromRealm();
        getRealm().commitTransaction();
    }

    //查询所有收藏记录
    public List<RealmLikeBean> getLikeList() {
        //使用findAllSort ,先findAll再result.sort无效
        RealmResults<RealmLikeBean> results = getRealm().where(RealmLikeBean.class).findAllSorted("time", Sort.DESCENDING);
        return mRealm.copyFromRealm(results);
    }

    //注册
    public void register(User user) {
        getRealm().beginTransaction();
        getRealm().copyToRealm(user);
        getRealm().commitTransaction();
    }

    //验证密码
    public boolean login(String name, String password) {
        RealmResults<User> results = getRealm().where(User.class).findAll();
        for (User user : results) {
            if(user.getName().equals(name)&&user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

}
