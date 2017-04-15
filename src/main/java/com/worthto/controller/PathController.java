package com.worthto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by gezz on 16/1/7.
 */
@Controller
public class PathController {

    @RequestMapping(value = "/err", method = {RequestMethod.GET})
    public String err(HttpServletRequest request) {
        return "error";
    }

    @RequestMapping(value = "/", method = {RequestMethod.GET})
    public String toHome() {
        return "sysops/item/itemList";
    }

    @RequestMapping(value = "/**", method = {RequestMethod.GET})
    public String to(HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getServletPath();
        return uri;
    }

}
