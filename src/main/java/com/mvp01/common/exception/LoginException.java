package com.mvp01.common.exception;

/**
 * Created by wenjie on 16/1/7.
 */
public class LoginException extends CustomException {
    public LoginException() {
        super("登录已失效，请重新登录！");
    }

    public LoginException(String message) {
        super(message);
    }
}
