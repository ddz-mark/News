package com.dudaizhong.news.modules.zhihu.adapter.viewholder;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;

import com.dudaizhong.news.R;
import com.dudaizhong.news.base.BaseViewHolder;
import com.dudaizhong.news.common.widget.AutoPlayViewPager;
import com.dudaizhong.news.modules.zhihu.adapter.BannerAdapter;

import butterknife.Bind;

/**
 * Created by Markable on 2016/10/18.
 */

public class BannerViewHolder extends BaseViewHolder {

    private BannerAdapter bannerAdapter;
    @Bind(R.id.banner)
    AutoPlayViewPager banner;

    public BannerViewHolder(Context context, ViewGroup root, BannerAdapter bannerAdapter, OnRecyclerViewListener onRecyclerViewListener) {
        super(context, root, R.layout.item_banner_zhihu_daily, onRecyclerViewListener);
        this.bannerAdapter = bannerAdapter;
    }

    @Override
    public void bindData(Object o) {
        banner.setAdapter(bannerAdapter);
        banner.setDirection(AutoPlayViewPager.Direction.LEFT);// 设置播放方向
        banner.setCurrentItem(200); // 设置每个Item展示的时间
        banner.start(); // 开始轮播
    }
}
