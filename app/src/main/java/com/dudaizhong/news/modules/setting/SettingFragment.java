package com.dudaizhong.news.modules.setting;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.support.design.widget.Snackbar;

import com.dudaizhong.news.R;
import com.dudaizhong.news.app.App;
import com.dudaizhong.news.base.utils.FileSizeUtil;
import com.dudaizhong.news.base.utils.SharedPreferencesUtil;
import com.dudaizhong.news.base.utils.SharedUtil;
import com.dudaizhong.news.base.utils.rxUtils.RxHelper;
import com.dudaizhong.news.base.utils.rxUtils.RxUtils;
import com.dudaizhong.news.base.utils.rxUtils.SimpleSubscriber;

import java.io.File;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Markable on 2016/11/26.
 */

public class SettingFragment extends PreferenceFragment {

    private Preference mClearCache;
    private Preference mSuggest;
    private CheckBoxPreference mDayNight;
    private CheckBoxPreference mAutoCache;

    File cacheFile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);
        cacheFile = new File(App.getCachedir(), "/NetCache");
        initView();
        setListener();
    }

    private void initView() {
        mDayNight = (CheckBoxPreference) findPreference(SharedPreferencesUtil.DAY_NIGHT);
        mAutoCache = (CheckBoxPreference) findPreference(SharedPreferencesUtil.AUTO_CACHE);
        mClearCache = findPreference(SharedPreferencesUtil.CLEAR_CACHE);
        mSuggest = findPreference(SharedPreferencesUtil.SUGGEST);

        mClearCache.setSummary(FileSizeUtil.getCacheSize(cacheFile));

        mDayNight.setChecked(SharedPreferencesUtil.getDayNight());
        mAutoCache.setChecked(SharedPreferencesUtil.getAutoCache());

    }

    private void setListener() {
        mDayNight.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                if (SharedPreferencesUtil.getDayNight())
                    SharedPreferencesUtil.setDayNight(false);
                else
                    SharedPreferencesUtil.setDayNight(true);
                return true;
            }
        });

        mAutoCache.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                if (SharedPreferencesUtil.getAutoCache()) {
                    SharedPreferencesUtil.setAutoCache(false);
                } else {
                    SharedPreferencesUtil.setAutoCache(true);
                }
                return true;
            }
        });

        mClearCache.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Observable.just(FileSizeUtil.deleteDir(cacheFile))
                        .filter(new Func1<Boolean, Boolean>() {
                            @Override
                            public Boolean call(Boolean aBoolean) {
                                return aBoolean;
                            }
                        })
                        .compose(RxHelper.<Boolean>rxSchedulerHelper())
                        .subscribe(new SimpleSubscriber<Boolean>() {
                            @Override
                            public void onNext(Boolean aBoolean) {
                                mClearCache.setSummary(FileSizeUtil.getCacheSize(cacheFile));
                                Snackbar.make(getView(), "缓存已清除", Snackbar.LENGTH_SHORT).show();
                            }
                        });
                return true;
            }
        });

        mSuggest.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                SharedUtil.sendEmail(getActivity(), "选择邮件客户端");
                return false;
            }
        });
    }

}
