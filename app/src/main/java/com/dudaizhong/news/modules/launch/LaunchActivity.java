package com.dudaizhong.news.modules.launch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dudaizhong.news.R;
import com.dudaizhong.news.base.BaseActivity;
import com.dudaizhong.news.modules.main.MainActivity;

import java.util.ArrayList;

import butterknife.Bind;
import rx.Observable;

/**
 * Created by Dudaizhong on 2016/9/27.
 */

public class LaunchActivity extends BaseActivity<LaunchContract.Presenter> implements LaunchContract.View {

    @Bind(R.id.viewPager_launch)
    ViewPager viewPagerLaunch;
    @Bind(R.id.img_vp_page1)
    ImageView imgVpPage1;
    @Bind(R.id.img_vp_page2)
    ImageView imgVpPage2;
    @Bind(R.id.img_vp_page3)
    ImageView imgVpPage3;
    @Bind(R.id.textView_launch)
    TextView textViewLaunch;

    private ArrayList<View> views;
    private LaunchAdapter launchAdapter;

    // 引导页图片资源
    private static final int[] pics = {R.layout.guid_view_1,
            R.layout.guid_view_2, R.layout.guid_view_3};

    @Override
    protected LaunchContract.Presenter createPresenter() {
        return new LaunchPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_launch;
    }

    @Override
    protected void initEventAndData(Bundle savedInstanceState) {
        views = new ArrayList<>();
        for (int i = 0; i < pics.length; i++) {
            View view = LayoutInflater.from(this).inflate(pics[i], null);
            views.add(view);
        }

        launchAdapter = new LaunchAdapter(views);
        viewPagerLaunch.setAdapter(launchAdapter);
        viewPagerLaunch.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case 0:
                        textViewLaunch.setVisibility(View.INVISIBLE);
                        imgVpPage1.setImageResource(R.mipmap.circle_selected);
                        imgVpPage2.setImageResource(R.mipmap.circle_unselected);
                        imgVpPage3.setImageResource(R.mipmap.circle_unselected);
                        break;
                    case 1:
                        textViewLaunch.setVisibility(View.INVISIBLE);
                        imgVpPage1.setImageResource(R.mipmap.circle_unselected);
                        imgVpPage2.setImageResource(R.mipmap.circle_selected);
                        imgVpPage3.setImageResource(R.mipmap.circle_unselected);
                        break;
                    case 2:
                        textViewLaunch.setVisibility(View.VISIBLE);
                        imgVpPage1.setImageResource(R.mipmap.circle_unselected);
                        imgVpPage2.setImageResource(R.mipmap.circle_unselected);
                        imgVpPage3.setImageResource(R.mipmap.circle_selected);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        textViewLaunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().jumpToMain();
            }
        });
    }

    @Override
    public <V> Observable.Transformer<V, V> bind() {
        return bindToLifecycle();
    }

    @Override
    public void jumpToMain() {
        startActivity(new Intent(LaunchActivity.this, MainActivity.class));
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
}
