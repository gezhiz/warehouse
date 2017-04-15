package com.worthto.controller;

import com.mvp01.common.bean.ResultBean;
import com.mvp01.common.exception.LoginException;
import com.mvp01.common.exception.ParamException;
import com.mvp01.common.utils.LoginUtils;
import com.worthto.bean.User;
import com.worthto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by gezz on 16/1/7.
 */
@Controller
@RequestMapping("/sysops")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        if (LoginUtils.isLogin(request)) {
            return "sysops/item/itemList";
        }
        return "/sysops/login";
    }
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        return "/sysops/item/itemList";
    }

    @RequestMapping(value = "/do_login", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean doLogin(User user, HttpServletRequest request, HttpServletResponse response) {
        if (LoginUtils.isLogin(request)) {
            throw new LoginException("您已经登录过了");
        }
        User dbUser = userService.findByUsernameAndPasswd(user);
        if (dbUser == null) {
            throw new ParamException("用户名或密码错误");
        }
        LoginUtils.handleLoginSession(request,response,dbUser);
        return ResultBean.buildOKResult();
    }

    @RequestMapping(value = "/do_logout", method = RequestMethod.GET)
    public String doLogout(HttpServletRequest request, HttpServletResponse response) {
        LoginUtils.handleLogoutSession(request,response);
        return "redirect:/sysops/index";
    }

}
