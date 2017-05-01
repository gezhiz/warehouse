package com.worthto.controller;

import com.mvp01.common.bean.ResultBean;
import com.mvp01.common.exception.ErrcodeException;
import com.mvp01.common.utils.LoginUtils;
import com.worthto.bean.Item;
import com.worthto.bean.ItemCategory;
import com.worthto.bean.ItemColor;
import com.worthto.bean.User;
import com.worthto.bean.service.ItemCategoryQuery;
import com.worthto.bean.service.ItemQuery;
import com.worthto.bean.service.SortBy;
import com.worthto.dao.base.PageBean;
import com.worthto.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by gezz on 16/1/7.
 */
@Controller
@RequestMapping("/sysops/item")
public class ItemController {

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemCategoryService itemCategoryService;

    @Autowired
    private ItemColorService itemColorService;

    @Autowired
    private ItemSizeService itemSizeService;

    @RequestMapping(value = "/itemList", method = RequestMethod.GET)
    public String itemList(Model model, HttpServletRequest request) {
        ItemCategoryQuery itemCategoryQuery = new ItemCategoryQuery();
        itemCategoryQuery.setPage(1);
        itemCategoryQuery.setPageSize(Integer.MAX_VALUE);
        itemCategoryQuery.setSortBy(new SortBy("name", SortBy.DirectionEnum.ASC));
        PageBean itemCategoryPageBean = itemCategoryService.itemCategoryPageList(itemCategoryQuery);
        model.addAttribute("itemCategoryPageBean",itemCategoryPageBean);
        return "/sysops/item/itemList";
    }

    @RequestMapping(value = "/edit_item", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean addItem(HttpServletRequest request, Item item) {
        User user = LoginUtils.getLoginUser(request);
        item.setUserId(user.getId());
        int insertNum = itemService.editItem(item);
        if (insertNum <= 0) {
            throw new ErrcodeException("添加失败,请联系系统管理员");
        }
        return ResultBean.buildOKResult();
    }

    @RequestMapping(value = "/item_page_list", method = RequestMethod.GET)
    @ResponseBody
    public ResultBean itemPageList(HttpServletRequest request, ItemQuery itemQuery) {
        User user = LoginUtils.getLoginUser(request);
        PageBean itemPageBean = itemService.itemPageList(itemQuery);
        return ResultBean.buildOKResultWithObj(itemPageBean);
    }

    @RequestMapping(value = "/item_info", method = RequestMethod.GET)
    @ResponseBody
    public ResultBean itemInfo(HttpServletRequest request, Long itemId) {
        User user = LoginUtils.getLoginUser(request);
        Item item = itemService.findById(itemId);
        return ResultBean.buildOKResultWithObj(item);
    }



}
