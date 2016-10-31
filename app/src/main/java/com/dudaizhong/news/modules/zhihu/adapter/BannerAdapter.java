package com.dudaizhong.news.modules.zhihu.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dudaizhong.news.R;
import com.dudaizhong.news.base.utils.DensityUtil;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Markable on 2016/10/21.
 */

public class BannerAdapter extends PagerAdapter {

    private List<ZhihuList.TopStoriesBean> topdatas = new ArrayList<>();
    private Context context;

    TextView desc;
    LinearLayout pointGroup;

    public BannerAdapter(Context context, List<ZhihuList.TopStoriesBean> topdatas) {
        this.topdatas = topdatas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return topdatas == null ? 0 : Integer.MAX_VALUE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //图片
        View view = LayoutInflater.from(context).inflate(R.layout.item_top_banner, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);

        final int size = topdatas.size();
        if (size > 0) {
            Glide.with(context)
                    .load(topdatas.get(position % size).getImage())
                    .placeholder(R.mipmap.ic_launcher)
                    .into(imageView);
            container.addView(view);
        }
        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
