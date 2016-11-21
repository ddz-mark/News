package com.dudaizhong.news.modules.zhihu.adapter.viewholder;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.dudaizhong.news.R;
import com.dudaizhong.news.base.BaseViewHolder;
import com.dudaizhong.news.base.utils.Util;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuCommentData;
import com.dudaizhong.news.modules.zhihu.domain.ZhihuShortCommentData;

import butterknife.Bind;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Markable on 2016/11/21.
 */

public class CommentViewHolder extends BaseViewHolder {

    @Bind(R.id.photo_image)
    ImageView mPhotoImage;
    @Bind(R.id.tv_comment_name)
    TextView mTvCommentName;
    @Bind(R.id.tv_comment_content)
    TextView mTvCommentContent;
    @Bind(R.id.tv_comment_reply)
    TextView mTvCommentReply;
    @Bind(R.id.tv_comment_time)
    TextView mTvCommentTime;
    @Bind(R.id.tv_comment_expand)
    TextView mTvCommentExpand;
    @Bind(R.id.tv_comment_like)
    TextView mTvCommentLike;

    public CommentViewHolder(Context context, ViewGroup root, OnRecyclerViewListener onRecyclerViewListener) {
        super(context, root, R.layout.item_short_comment, onRecyclerViewListener);
    }

    @Override
    public void bindData(Object o) {

        ZhihuShortCommentData.CommentsBean data = (ZhihuShortCommentData.CommentsBean) o;

        Glide.with(getContext())
                .load(Util.safeText(data.avatar))
                .bitmapTransform(new CropCircleTransformation(getContext()), new FitCenter(getContext()))
                .placeholder(R.mipmap.ic_launcher)
                .into(mPhotoImage);
        mTvCommentName.setText(Util.safeText(data.author));
        mTvCommentContent.setText(Util.safeText(data.content));
        if (null != data.replyTo)
            mTvCommentReply.setText(Util.safeText(data.replyTo.content));
        mTvCommentTime.setText(Util.formatDate("HH:mm:ss",data.time));
        mTvCommentLike.setText(Util.safeText(data.likes));
    }

    @OnClick(R.id.tv_comment_expand)
    public void onClick() {
    }
}
