package com.dudaizhong.news.modules.setting;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;

import com.dudaizhong.news.R;
import com.dudaizhong.news.base.utils.SharedPreferencesUtil;
import com.dudaizhong.news.base.utils.SharedUtil;

/**
 * Created by Markable on 2016/11/26.
 */

public class SettingFragment extends PreferenceFragment {

    private Preference mClearCache;
    private Preference mSuggest;
    private CheckBoxPreference mDayNight;
    private CheckBoxPreference mAutoCache;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);
        initView();
        setListener();
    }

    private void initView() {
        mDayNight = (CheckBoxPreference) findPreference(SharedPreferencesUtil.DAY_NIGHT);
        mAutoCache = (CheckBoxPreference) findPreference(SharedPreferencesUtil.AUTO_CACHE);
        mClearCache = findPreference(SharedPreferencesUtil.CLEAR_CACHE);
        mSuggest = findPreference(SharedPreferencesUtil.SUGGEST);

        mDayNight.setChecked(SharedPreferencesUtil.getDayNight());
        mAutoCache.setChecked(SharedPreferencesUtil.getAutoCache());
//        mClearCache.setSummary();
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
                if(SharedPreferencesUtil.getAutoCache()){
                    SharedPreferencesUtil.setAutoCache(false);
                }else {
                    SharedPreferencesUtil.setAutoCache(true);
                }
                return true;
            }
        });

        mClearCache.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {

                return true;
            }
        });

        mSuggest.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                SharedUtil.sendEmail(getActivity(),"选择邮件客户端");
                return false;
            }
        });
    }

}
