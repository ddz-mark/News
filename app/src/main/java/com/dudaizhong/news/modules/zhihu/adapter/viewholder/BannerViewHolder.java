package com.dudaizhong.news.modules.zhihu.adapter.viewholder;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dudaizhong.news.R;
import com.dudaizhong.news.base.BaseViewHolder;
import com.dudaizhong.news.base.utils.DensityUtil;
import com.dudaizhong.news.common.widget.AutoPlayViewPager;
import com.dudaizhong.news.modules.zhihu.adapter.BannerAdapter;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuList;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Markable on 2016/10/18.
 */

public class BannerViewHolder extends BaseViewHolder {

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.point_group)
    LinearLayout pointGroup;
    @Bind(R.id.titleLayout)
    RelativeLayout titleLayout;
    @Bind(R.id.banner)
    AutoPlayViewPager banner;

    private BannerAdapter bannerAdapter;
    private Context context;

    public BannerViewHolder(Context context, ViewGroup root, BannerAdapter bannerAdapter, OnRecyclerViewListener onRecyclerViewListener) {
        super(context, root, R.layout.item_banner_zhihu_daily, onRecyclerViewListener);
        this.bannerAdapter = bannerAdapter;
        this.context = context;

        //设置banner的图片比例9:16
        RelativeLayout.LayoutParams switcherParams =
                (RelativeLayout.LayoutParams) banner.getLayoutParams();
        switcherParams.width = DensityUtil.getWindowWidth(context);
        switcherParams.height = DensityUtil.getWindowWidth(context) * 9 / 16;
        banner.setLayoutParams(switcherParams);

        //设置小圆点
        RelativeLayout.LayoutParams paramsDot = (RelativeLayout.LayoutParams) pointGroup.getLayoutParams();
        paramsDot.setMargins(0, 0, DensityUtil.dip2px(context, 15), DensityUtil.dip2px(context, 15));
        pointGroup.setLayoutParams(paramsDot);

        //设置整个导航栏的高度
        RelativeLayout.LayoutParams params =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(context, 40));
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        titleLayout.setLayoutParams(params);
    }

    @Override
    public void bindData(Object o) {
        banner.setAdapter(bannerAdapter);
        banner.setDirection(AutoPlayViewPager.Direction.LEFT);// 设置播放方向
        banner.setCurrentItem(200); // 设置每个Item展示的时间
        banner.start(); // 开始轮播

        ZhihuList.TopStoriesBean datas = (ZhihuList.TopStoriesBean) o;
        title.setText(datas.getTitle());
    }
}
