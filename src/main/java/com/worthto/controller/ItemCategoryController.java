package com.worthto.controller;

import com.mvp01.common.bean.ResultBean;
import com.mvp01.common.exception.ErrcodeException;
import com.mvp01.common.utils.LoginUtils;
import com.worthto.bean.ItemCategory;
import com.worthto.bean.User;
import com.worthto.bean.service.ItemCategoryQuery;
import com.worthto.dao.base.PageBean;
import com.worthto.service.ItemCategoryService;
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
@RequestMapping("/sysops/category")
public class ItemCategoryController {

    @Autowired
    private UserService userService;

    @Autowired
    private ItemCategoryService itemCategoryService;

    @RequestMapping(value = "/itemCategoryList", method = RequestMethod.GET)
    public String itemCategoryList(HttpServletRequest request) {
        return "/sysops/category/itemCategoryList";
    }

    @RequestMapping(value = "/edit_item_category", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean addItemCategory(HttpServletRequest request, ItemCategory itemCategory) {
        User user = LoginUtils.getLoginUser(request);
        int insertNum = itemCategoryService.editItemCategory(itemCategory);
        if (insertNum <= 0) {
            throw new ErrcodeException("添加失败,请联系系统管理员");
        }
        return ResultBean.buildOKResult();
    }

    @RequestMapping(value = "/item_category_page_list", method = RequestMethod.GET)
    @ResponseBody
    public ResultBean itemCategoryPageList(HttpServletRequest request, ItemCategoryQuery itemCategoryQuery) {
        User user = LoginUtils.getLoginUser(request);
        PageBean itemCategoryPageBean = itemCategoryService.itemCategoryPageList(itemCategoryQuery);
        return ResultBean.buildOKResultWithObj(itemCategoryPageBean);
    }

    @RequestMapping(value = "/item_category_info", method = RequestMethod.GET)
    @ResponseBody
    public ResultBean itemCategoryInfo(HttpServletRequest request, Long itemCategoryId) {
        User user = LoginUtils.getLoginUser(request);
        ItemCategory itemCategory = itemCategoryService.findById(itemCategoryId);
        return ResultBean.buildOKResultWithObj(itemCategory);
    }



}
