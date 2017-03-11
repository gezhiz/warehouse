package com.worthto.interceptor;

import com.mvp01.common.exception.ErrcodeException;
import com.mvp01.common.exception.LoginException;
import com.mvp01.common.exception.PermissionException;
import com.mvp01.common.utils.CommonUtil;
import com.mvp01.common.utils.LoginUtils;
import com.worthto.bean.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wenjie on 16/1/6.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    public final static String LOGININTERCEPTOR_LOGIN_DEST = "logininterceptor_login_dest";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        // 获得请求路径的uri
        String uri = CommonUtil.getRequestUri(request);
        User user = LoginUtils.getLoginUser(request);

        if (StringUtils.isBlank(uri) || "/".equals(uri)) {
            throw new PermissionException();
        }

        if (uri.startsWith("/index")) {
            throw new LoginException();
        }

        if (uri.startsWith("/platform/login")) {
            if (user != null) {
                throw new LoginException();
            }
            return true;
        }

        if(uri.startsWith("/platform/do_login")) {
            //已登录
            if (user != null) {
                throw new ErrcodeException("您已经登录过，请勿重复登录");
            }
            return true;
        }

        if(user == null) {
            throw new LoginException();
        }

        return true;
    }
}
