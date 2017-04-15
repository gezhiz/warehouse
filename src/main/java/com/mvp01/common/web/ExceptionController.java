package com.mvp01.common.web;

import com.mvp01.common.bean.ResultBean;
import com.mvp01.common.exception.*;
import com.mvp01.common.utils.AjaxUtil;
import com.mvp01.common.utils.CommonUtil;
import com.mvp01.common.utils.LoggerWrapper;
import com.mvp01.common.utils.LoginUtils;
import com.mvp01.common.utils.gson.AppGsonUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;


/**
 * Created by wenjie on 16/1/6.
 */
@ControllerAdvice
public class ExceptionController extends LoggerWrapper {

    public final static String ERROR_HANDLER_RESULT_MSG = "error_handler_result_msg";
    public final static Pattern JSON_ACCEPT_URI_PATTERN = Pattern.compile("^/v[0-9]*\\.[0-9]*/.*");

    @ExceptionHandler
    public void handleErrPage(ErrcodeException e, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        if (handleException(e, e.getObj(), request, response)) {
            return;
        }
        if (e.getObj() != null && !StringUtils.isBlank(e.getObj().getErrmsg())) {
            request.getSession().setAttribute(ERROR_HANDLER_RESULT_MSG, e.getObj().getErrmsg());
        }
        response.sendRedirect(request.getContextPath() + "/index");
    }

    @ExceptionHandler
    public void handleErrPage(LoginException e, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        if (handleException(e, -1000, "登录异常，请重新登录", request, response)) {
            return;
        }
        if (LoginUtils.getLoginUser(request) != null) {
            //已登录
            response.sendRedirect(request.getContextPath() + "/index");
        }else {
            response.sendRedirect(request.getContextPath() + "/sysops/login");
        }
    }

    @ExceptionHandler
    public void handleErrPage(ParamException e, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        if (handleException(e, -3000, e.getMessage(), request, response)) {
            return;
        }
        response.sendRedirect(request.getContextPath() + "/index");
    }

    private boolean handleException(CustomException e, int errcode, String msg, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        LOGGER.error("handleException errcode", e);
        e.printStackTrace();
        if ((e != null && !e.isCheckErrHandle()) || handleJsonAccept(request)) {
            handleJsonResponse(response, AppGsonUtil.toJson(ResultBean.buildResult(errcode, msg, null)));
            return true;
        }
        return false;
    }

    private boolean handleException(CustomException e, ResultBean resultBean, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        LOGGER.error("handleException", e);
        e.printStackTrace();
        if ((e != null && !e.isCheckErrHandle()) || handleJsonAccept(request)) {
            handleJsonResponse(response, AppGsonUtil.toJson(resultBean));
            return true;
        }
        return false;
    }

    private boolean handleJsonAccept(HttpServletRequest request) {
        if (isApiRequest(request)) {
            return true;
        }

        return AjaxUtil.isAjaxRequest(request);
//        String jsonAccept = request.getHeader("Accept");
//        if (!StringUtils.isBlank(jsonAccept) && jsonAccept.contains("application/json")) {
//            return true;
//        }
//        return false;
    }

    private void handleJsonResponse(HttpServletResponse response, String ret) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(ret);
        writer.flush();

    }

    public static boolean isApiRequest(HttpServletRequest request) {
        String uri = CommonUtil.getRequestUri(request);
//        String pathInfo = request.getServletPath();
        return JSON_ACCEPT_URI_PATTERN.matcher(uri).matches();
    }
}
