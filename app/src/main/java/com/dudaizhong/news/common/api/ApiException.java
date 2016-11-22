package com.dudaizhong.news.common.api;


public class ApiException extends RuntimeException {

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

    public ApiException(int resultCode, String msg) {
        this(handleErrorCode(resultCode, msg));
    }

    private static String handleErrorCode(int code, String s) {
        String msg;
        switch (code) {
            default:
                msg = s;
                break;
        }
        return msg;
    }
}