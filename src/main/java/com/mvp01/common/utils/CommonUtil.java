package com.mvp01.common.utils;


import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by gezz on 2016/12/28.
 */
public class CommonUtil {

    /**
     * 记录异常
     * @param errmsg
     */
    public static void markException(String errmsg) {
        try {
            throw new Exception(errmsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String combineString(String ...args) {
        StringBuilder sb = new StringBuilder();
        for (String arg : args) {
            sb.append(arg);
        }
        return sb.toString();
    }

    public static String getRequestUri(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        String path = request.getRequestURI();
        if (StringUtils.isBlank(contextPath)) {
            return path;
        }
        return path.replace(contextPath, "");
    }


}
