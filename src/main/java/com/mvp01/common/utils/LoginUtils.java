package com.mvp01.common.utils;

import com.worthto.bean.User;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by wenjie on 16/1/7.
 */
public class LoginUtils {

    private final static String SESSION_KEY_USER = "session_freelancer_user";

    public static void handleLoginSession(HttpServletRequest request, HttpServletResponse response, User user) {
        HttpSession session = request.getSession(true);
        session.setAttribute(SESSION_KEY_USER, user);
    }

    public static void handleLogoutSession(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    public static boolean isLogin(HttpServletRequest request) {
        return getLoginUser(request) != null;
    }

    public static User getLoginUser(HttpServletRequest request) {
        return request.getSession().getAttribute(SESSION_KEY_USER) == null ? null : (User) request.getSession().getAttribute(SESSION_KEY_USER);
    }

    private static <T> T getSessionObj(HttpServletRequest request, String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        return request.getSession(false) != null ? (T) request.getSession(false).getAttribute(key) : null;
    }

}
