package com.mvp01.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Ajax Util
 * Created by wenjie on 16/5/4.
 */
public class AjaxUtil {

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String xmlHttpRequest = request.getHeader("X-Requested-With");
        if (xmlHttpRequest != null) {
            if (xmlHttpRequest.equalsIgnoreCase("XMLHttpRequest")) {
                return true;
            }
        }
        return false;
    }
}
