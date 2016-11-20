package com.dudaizhong.news.modules.zhihu.activity;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.dudaizhong.news.R;
import com.dudaizhong.news.app.Constants;
import com.dudaizhong.news.base.BaseActivity;
import com.dudaizhong.news.modules.gank.GankFragment;
import com.dudaizhong.news.modules.zhihu.adapter.ViewPagerAdapter;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuCommentData;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuDetail;
import com.dudaizhong.news.modules.zhihu.fragment.DailyFragment;
import com.dudaizhong.news.modules.zhihu.fragment.HotFragment;
import com.dudaizhong.news.modules.zhihu.fragment.LongCommentFragment;
import com.dudaizhong.news.modules.zhihu.fragment.SectionFragment;
import com.dudaizhong.news.modules.zhihu.fragment.ShortCommentFragment;
import com.dudaizhong.news.modules.zhihu.fragment.ThemeFragment;
import com.dudaizhong.news.modules.zhihu.fragment.ZhihuFragment;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Markable on 2016/11/20.
 */

public class ZhihuCommentActivity extends AppCompatActivity {

    @Bind(R.id.tab_comment)
    TabLayout mTabComment;
    @Bind(R.id.vp_comment)
    ViewPager mVpComment;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    private static final String COMMENT = "comment";
    private ZhihuCommentData commentData;
    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    ArrayList<String> titles = new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commentData = (ZhihuCommentData) getIntent().getSerializableExtra(COMMENT);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        fragmentList.add(new LongCommentFragment());
        fragmentList.add(new ShortCommentFragment());
        viewPagerAdapter.setData(fragmentList);

        titles.add("长评论" + "(" + commentData.longComments + ")");
        titles.add("短评论" + "(" + commentData.shortComments + ")");
        viewPagerAdapter.setTitles(titles);

        mVpComment.setAdapter(viewPagerAdapter);
        mTabComment.setupWithViewPager(mVpComment);
        mTabComment.setTabsFromPagerAdapter(viewPagerAdapter);
    }

    public static Intent getZhihuCommentIntent(Context context, ZhihuCommentData commentData) {
        Intent intent = new Intent(context, ZhihuCommentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(COMMENT, commentData);
        intent.putExtras(bundle);
        return intent;
    }

}
