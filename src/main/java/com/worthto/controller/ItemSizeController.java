package com.worthto.controller;

import com.mvp01.common.bean.ResultBean;
import com.mvp01.common.exception.ErrcodeException;
import com.mvp01.common.exception.ParamException;
import com.mvp01.common.utils.LoginUtils;
import com.worthto.bean.ItemSize;
import com.worthto.bean.User;
import com.worthto.bean.service.ItemSizeQuery;
import com.worthto.dao.base.PageBean;
import com.worthto.service.ItemSizeService;
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
@RequestMapping("/sysops/size")
public class ItemSizeController {

    @Autowired
    private UserService userService;

    @Autowired
    private ItemSizeService itemSizeService;

    @RequestMapping(value = "/itemSizeList", method = RequestMethod.GET)
    public String itemSizeList(HttpServletRequest request) {
        return "/sysops/size/itemSizeList";
    }

    @RequestMapping(value = "/add_item_size", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean addItemSize(HttpServletRequest request, ItemSize itemSize) {
        User user = LoginUtils.getLoginUser(request);
        itemSize.setUserId(user.getId());
        int insertNum = itemSizeService.addItemSize(itemSize);
        if (insertNum <= 0) {
            throw new ErrcodeException("添加失败,请联系系统管理员");
        }
        return ResultBean.buildOKResult();
    }

    @RequestMapping(value = "/item_size_page_list", method = RequestMethod.GET)
    @ResponseBody
    public ResultBean itemSizePageList(HttpServletRequest request, ItemSizeQuery itemSizeQuery) {
        User user = LoginUtils.getLoginUser(request);
        itemSizeQuery.setUserId(user.getId());
        PageBean itemSizePageBean = itemSizeService.itemSizePageList(itemSizeQuery);
        return ResultBean.buildOKResultWithObj(itemSizePageBean);
    }

}
