package com.dudaizhong.news.modules.zhihu.adapter.viewholder;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dudaizhong.news.R;
import com.dudaizhong.news.base.BaseViewHolder;
import com.dudaizhong.news.base.utils.DensityUtil;
import com.dudaizhong.news.common.widget.AutoPlayViewPager;
import com.dudaizhong.news.modules.zhihu.adapter.BannerAdapter;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuList;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by Markable on 2016/10/18.
 */

public class BannerViewHolder extends BaseViewHolder {

    @Bind(R.id.banner)
    AutoPlayViewPager banner;
    @Bind(R.id.banner_bottom)
    RelativeLayout bannerBottom;

    TextView desc;
    LinearLayout pointGroup;
    private BannerAdapter bannerAdapter;
    private Context context;
    private int prePoint = 0;
    private ArrayList<ZhihuList.TopStoriesBean> datas;

    public BannerViewHolder(Context context, ViewGroup root, OnRecyclerViewListener onRecyclerViewListener, BannerAdapter bannerAdapter) {
        super(context, root, R.layout.item_banner_zhihu_daily, onRecyclerViewListener);
        this.bannerAdapter = bannerAdapter;
        this.context = context;

        //设置banner的图片比例9:16
        RelativeLayout.LayoutParams switcherParams =
                (RelativeLayout.LayoutParams) banner.getLayoutParams();
        switcherParams.width = DensityUtil.getWindowWidth(context);
        switcherParams.height = DensityUtil.getWindowWidth(context) * 9 / 16;
        banner.setLayoutParams(switcherParams);

        //banner的文本提示
        RelativeLayout titleLayout =
                (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.item_banner_zhihu_daily_title, null, false);
        desc = (TextView) titleLayout.findViewById(R.id.banner_desc);
        pointGroup = (LinearLayout) titleLayout.findViewById(R.id.point_group);

        RelativeLayout.LayoutParams params =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(context, 40));
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        titleLayout.setLayoutParams(params);
        bannerBottom.addView(titleLayout);
    }

    @Override
    public void bindData(Object o) {
        datas = (ArrayList<ZhihuList.TopStoriesBean>) o;
        if (null != datas && datas.size() != 0) {
            pointGroup.removeAllViews();
            for (int i = 0; i < datas.size(); i++) {
                ImageView point = new ImageView(context);
                point.setImageResource(R.drawable.ic_page_indicator);

                //设置间距
                int pointSize = context.getResources().getDimensionPixelSize(R.dimen.point_size);
                LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(pointSize, pointSize);
                if (i > 0) {
                    params2.leftMargin = context.getResources().getDimensionPixelSize(R.dimen.point_leftmargin);
                }
                point.setLayoutParams(params2);

                pointGroup.addView(point, i);
            }

            desc.setText(datas.get(0).getTitle());
            ((ImageView) pointGroup.getChildAt(0)).setImageResource(R.drawable.ic_page_indicator_focused);

            banner.setAdapter(bannerAdapter);
            banner.setDirection(AutoPlayViewPager.Direction.LEFT);// 设置播放方向
            banner.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % datas.size());
            banner.start(); // 开始轮播
            banner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {

                    final int nowPoint = position % datas.size();
                    desc.setText(datas.get(nowPoint).getTitle());
                    ((ImageView) pointGroup.getChildAt(prePoint)).setImageResource(R.drawable.ic_page_indicator);
                    ((ImageView) pointGroup.getChildAt(nowPoint)).setImageResource(R.drawable.ic_page_indicator_focused);
                    prePoint = nowPoint;

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
    }
}
