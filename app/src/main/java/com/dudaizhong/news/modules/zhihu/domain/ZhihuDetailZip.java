package com.dudaizhong.news.modules.zhihu.domain;

/**
 * Created by Markable on 2016/11/20.
 */

public class ZhihuDetailZip {

    public ZhihuDetail mZhihuDetail;
    public ZhihuCommentData mZhihuCommentData;

    public ZhihuDetailZip(ZhihuDetail zhihuDetail, ZhihuCommentData zhihuCommentData) {
        mZhihuDetail = zhihuDetail;
        mZhihuCommentData = zhihuCommentData;
    }
}
