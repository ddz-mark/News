package com.dudaizhong.news.modules.setting;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;

import com.dudaizhong.news.R;

/**
 * Created by Markable on 2016/11/26.
 */

public class SettingFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener,Preference.OnPreferenceChangeListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        return false;
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        return false;
    }
}
