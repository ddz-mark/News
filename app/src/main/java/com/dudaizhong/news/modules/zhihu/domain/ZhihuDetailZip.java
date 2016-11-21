package com.dudaizhong.news.modules.zhihu.domain;

import java.io.Serializable;

/**
 * Created by Markable on 2016/11/20.
 */

public class ZhihuDetailZip implements Serializable{

    private static final long serialVersionUID = 3325004525379799589L;

    public ZhihuDetail mZhihuDetail;
    public ZhihuCommentData mZhihuCommentData;

    public ZhihuDetailZip(ZhihuDetail zhihuDetail, ZhihuCommentData zhihuCommentData) {
        mZhihuDetail = zhihuDetail;
        mZhihuCommentData = zhihuCommentData;
    }
}
