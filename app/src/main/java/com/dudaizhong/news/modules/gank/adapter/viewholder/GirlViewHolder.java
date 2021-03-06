package com.dudaizhong.news.modules.gank.adapter.viewholder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dudaizhong.news.R;
import com.dudaizhong.news.base.BaseViewHolder;
import com.dudaizhong.news.base.utils.DensityUtil;
import com.dudaizhong.news.base.utils.ImageLoader;
import com.dudaizhong.news.modules.gank.activity.MeiziActivity;
import com.dudaizhong.news.modules.gank.domain.AIList;

import butterknife.Bind;

/**
 * Created by Markable on 2016/11/23.
 */

public class GirlViewHolder extends BaseViewHolder {

    @Bind(R.id.image)
    ImageView mImage;
    private Context mContext;

    public GirlViewHolder(Context context, ViewGroup root, OnRecyclerViewListener onRecyclerViewListener) {
        super(context, root, R.layout.item_gril, onRecyclerViewListener);
        mContext = context;
    }

    @Override
    public void bindData(Object o) {
        final AIList data = (AIList) o;

        ImageLoader.loadWithNoBg(getContext(),data.url,mImage);
//        mImage.setMaxHeight(200 + );

        ViewGroup.LayoutParams params = mImage.getLayoutParams();
        //(数据类型)(最小值+Math.random()*(最大值-最小值+1))
        params.height = (int) (((int) (Math.random() * (100))) + DensityUtil.getWindowheight(mContext) / 3.0);
        mImage.setLayoutParams(params);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContext().startActivity(MeiziActivity.getMeiziActivityIntent(getContext(),data.url));
            }
        });

    }
}
