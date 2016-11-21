package com.dudaizhong.news.modules.zhihu.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.dudaizhong.news.R;
import com.dudaizhong.news.base.BaseActivity;
import com.dudaizhong.news.modules.zhihu.adapter.ViewPagerAdapter;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuDetailZip;
import com.dudaizhong.news.modules.zhihu.fragment.CommentFragment;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by Markable on 2016/11/20.
 */

public class ZhihuCommentActivity extends BaseActivity {

    @Bind(R.id.tab_comment)
    TabLayout mTabComment;
    @Bind(R.id.vp_comment)
    ViewPager mVpComment;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    public static final String COMMENT = "comment";
    public ZhihuDetailZip mZhihuDetailZip;
    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    ArrayList<String> titles = new ArrayList<>();

    @Override
    protected void initInject() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_comment;
    }

    @Override
    protected void initEventAndData(Bundle savedInstanceState) {
        mZhihuDetailZip = (ZhihuDetailZip) getIntent().getSerializableExtra(COMMENT);
        setToolBar(mToolbar, mZhihuDetailZip.mZhihuCommentData.comments + "条评论");

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        fragmentList.add(CommentFragment.newInstance(mZhihuDetailZip.mZhihuDetail.id,0));
        fragmentList.add(CommentFragment.newInstance(mZhihuDetailZip.mZhihuDetail.id,1));

        viewPagerAdapter.setData(fragmentList);

        titles.add("短评论" + "(" + mZhihuDetailZip.mZhihuCommentData.shortComments + ")");
        titles.add("长评论" + "(" + mZhihuDetailZip.mZhihuCommentData.longComments + ")");
        viewPagerAdapter.setTitles(titles);

        mVpComment.setAdapter(viewPagerAdapter);
        mTabComment.setupWithViewPager(mVpComment);
        mTabComment.setTabsFromPagerAdapter(viewPagerAdapter);
    }

    public static Intent getZhihuCommentIntent(Context context, ZhihuDetailZip zhihuDetailZip) {
        Intent intent = new Intent(context, ZhihuCommentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(COMMENT, zhihuDetailZip);
        intent.putExtras(bundle);
        return intent;
    }

}
