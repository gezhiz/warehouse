package com.worthto.interceptor;

import com.mvp01.common.exception.LoginException;
import com.mvp01.common.utils.CommonUtil;
import com.mvp01.common.utils.LoginUtils;
import com.worthto.bean.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wenjie on 16/1/6.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        // 获得请求路径的uri
        String uri = CommonUtil.getRequestUri(request);
        if (uri.endsWith("index.js.map")) {
            return false;
        }
        User user = LoginUtils.getLoginUser(request);

        if(user == null) {
            throw new LoginException();
        }

        return true;
    }
}
