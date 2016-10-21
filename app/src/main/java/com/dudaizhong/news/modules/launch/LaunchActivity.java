package com.dudaizhong.news.modules.launch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dudaizhong.news.R;
import com.dudaizhong.news.base.BaseActivity;
import com.dudaizhong.news.base.utils.SharedPreferencesUtil;
import com.dudaizhong.news.modules.main.MainActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;

/**
 * Created by Dudaizhong on 2016/9/27.
 */

public class LaunchActivity extends BaseActivity<LaunchPresenter> implements LaunchContract.View {


    @Bind(R.id.viewPager_launch)
    ViewPager viewPagerLaunch;
    @Bind(R.id.textView_launch)
    TextView textViewLaunch;
    @Bind(R.id.point_group)
    LinearLayout pointGroup;
    @Bind(R.id.white_point)
    ImageView whitePoint;
    @Bind(R.id.re_point)
    RelativeLayout rePoint;

    private ArrayList<ImageView> views = new ArrayList<>();
    private int mPointMargin;
    private LaunchAdapter launchAdapter;

    // 引导页图片资源
    private static final int[] pics = {R.mipmap.view1,
            R.mipmap.view2, R.mipmap.view3};

//    @Override
//    protected LaunchContract.Presenter createPresenter() {
//        return new LaunchPresenter();
//    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_launch;
    }

    @Override
    protected void initEventAndData(Bundle savedInstanceState) {

        //设置pf成第一次进入
        SharedPreferencesUtil.setFirst(true);

        for (int i = 0; i < pics.length; i++) {
            // 准备好显示的图片
            ImageView image = new ImageView(this);
            image.setBackgroundResource(pics[i]);
            views.add(image);

            // 设置底部小圆点
            ImageView point = new ImageView(this);
            point.setImageResource(R.drawable.shape_point_normal);

            // 设置白点的布局参数
            int pointSize = getResources().getDimensionPixelSize(R.dimen.point_size);
            RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(pointSize, pointSize);
            whitePoint.setLayoutParams(params1);

            // 设置灰色点的布局参数
            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(pointSize, pointSize);
            if (i > 0) {
                params2.leftMargin = getResources().getDimensionPixelSize(R.dimen.point_margin);
            }
            point.setLayoutParams(params2);

            pointGroup.addView(point);
        }

        launchAdapter = new LaunchAdapter(views);
        viewPagerLaunch.setAdapter(launchAdapter);
        setListener();
    }

    private void setListener() {

        // 获取视图树对象，通过监听白点布局的显示，然后获取两个圆点之间的距离
        whitePoint.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                // 此时layout布局已经显示出来了，可以获取小圆点之间的距离了
                mPointMargin = pointGroup.getChildAt(1).getLeft() - pointGroup.getChildAt(0).getLeft();

                // 将自己移除掉
                whitePoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });


        /**
         * 对View Pager添加监听
         */
        viewPagerLaunch.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // 页面滑动的时候，动态的获取小圆点的左边距
                int leftMargin = (int) (mPointMargin * (position + positionOffset));

                // 获取布局参数，然后设置布局参数
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) whitePoint.getLayoutParams();
                // 修改参数
                params.leftMargin = leftMargin;
                // 重新设置布局参数
                whitePoint.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                // 最后一页
                if (position == pics.length - 1) {
                    textViewLaunch.setVisibility(View.VISIBLE);
                } else {
                    textViewLaunch.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

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

    @OnClick(R.id.textView_launch)
    public void onClick() {
        mPresenter.jumpToMain();
    }

}
