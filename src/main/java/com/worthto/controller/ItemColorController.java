package com.worthto.controller;

import com.mvp01.common.bean.ResultBean;
import com.mvp01.common.exception.ErrcodeException;
import com.mvp01.common.utils.LoginUtils;
import com.worthto.bean.ItemColor;
import com.worthto.bean.User;
import com.worthto.bean.service.ItemColorQuery;
import com.worthto.dao.base.PageBean;
import com.worthto.service.ItemColorService;
import com.worthto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by gezz on 16/1/7.
 */
@Controller
@RequestMapping("/sysops/color")
public class ItemColorController {

    @Autowired
    private UserService userService;

    @Autowired
    private ItemColorService itemColorService;

    @RequestMapping(value = "/itemColorList", method = RequestMethod.GET)
    public String itemColorList(HttpServletRequest request) {
        return "/sysops/color/itemColorList";
    }

    @RequestMapping(value = "/edit_item_color", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean addItemColor(HttpServletRequest request, ItemColor itemColor) {
        User user = LoginUtils.getLoginUser(request);
        int insertNum = itemColorService.editItemColor(itemColor);
        if (insertNum <= 0) {
            throw new ErrcodeException("添加失败,请联系系统管理员");
        }
        return ResultBean.buildOKResult();
    }

    @RequestMapping(value = "/item_color_page_list", method = RequestMethod.GET)
    @ResponseBody
    public ResultBean itemColorPageList(HttpServletRequest request, ItemColorQuery itemColorQuery) {
        User user = LoginUtils.getLoginUser(request);
        PageBean itemColorPageBean = itemColorService.itemColorPageList(itemColorQuery);
        return ResultBean.buildOKResultWithObj(itemColorPageBean);
    }

    @RequestMapping(value = "/item_color_info", method = RequestMethod.GET)
    @ResponseBody
    public ResultBean itemColorInfo(HttpServletRequest request, Long itemColorId) {
        User user = LoginUtils.getLoginUser(request);
        ItemColor itemColor = itemColorService.findById(itemColorId);
        return ResultBean.buildOKResultWithObj(itemColor);
    }



}
