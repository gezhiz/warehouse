package com.worthto.controller;

import com.mvp01.common.bean.ResultBean;
import com.mvp01.common.exception.ErrcodeException;
import com.mvp01.common.exception.ParamException;
import com.mvp01.common.utils.LoginUtils;
import com.sun.tracing.dtrace.Attributes;
import com.worthto.bean.*;
import com.worthto.bean.service.ItemColorQuery;
import com.worthto.bean.service.ItemSizeQuery;
import com.worthto.bean.service.ItemSkuQuery;
import com.worthto.dao.base.PageBean;
import com.worthto.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by gezz on 16/1/7.
 */
@Controller
@RequestMapping("/sysops/sku")
public class ItemSkuController {

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemSkuService itemSkuService;

    @Autowired
    private ItemSizeService itemSizeService;

    @Autowired
    private ItemColorService itemColorService;

    @Autowired
    private ItemExitOrderService itemExitOrderService;

    @RequestMapping(value = "/itemSkuList/{itemId}", method = RequestMethod.GET)
    public String skuList(HttpServletRequest request, Model model, @PathVariable Long itemId) {
        User loginUser = LoginUtils.getLoginUser(request);
        Item item = itemService.findById(itemId);
        model.addAttribute("item",item);
        //尺寸
        ItemSizeQuery itemSizeQuery = new ItemSizeQuery();
        itemSizeQuery.setPage(1);
        itemSizeQuery.setPageSize(Integer.MAX_VALUE);
        PageBean<ItemSize> itemSizePageBean = itemSizeService.itemSizePageList(itemSizeQuery);
        model.addAttribute("itemSizePageBean",itemSizePageBean);
        //颜色
        ItemColorQuery itemColorQuery = new ItemColorQuery();
        itemColorQuery.setPage(1);
        itemColorQuery.setPageSize(Integer.MAX_VALUE);
        PageBean<ItemColor> itemColorPageBean = itemColorService.itemColorPageList(itemColorQuery);
        model.addAttribute("itemColorPageBean",itemColorPageBean);

        //新出库单列表
        PageBean<ItemExitOrder> itemExitOrderPageBean = itemExitOrderService.itemExitOrderNewList(loginUser.getId());
        model.addAttribute("itemExitOrderPageBean", itemExitOrderPageBean);
        return "/sysops/sku/itemSkuList";
    }

    @RequestMapping(value = "/edit_item_sku", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean addItemSku(HttpServletRequest request, ItemSku itemSku, Long initStock, Double doublePrice) {
        User loginUser = LoginUtils.getLoginUser(request);
        itemSku.setUserId(loginUser.getId());
        int insertNum = itemSkuService.editItemSku(itemSku, initStock, doublePrice);
        if (insertNum <= 0) {
            throw new ErrcodeException("添加失败,请联系系统管理员");
        }
        return ResultBean.buildOKResult();
    }

    @RequestMapping(value = "/item_sku_page_list", method = RequestMethod.GET)
    @ResponseBody
    public ResultBean itemSkuPageList(HttpServletRequest request, ItemSkuQuery itemSkuQuery) {
        User user = LoginUtils.getLoginUser(request);
        PageBean itemSkuPageBean = itemSkuService.itemSkuPageList(itemSkuQuery);
        return ResultBean.buildOKResultWithObj(itemSkuPageBean);
    }

    @RequestMapping(value = "/item_sku_info", method = RequestMethod.GET)
    @ResponseBody
    public ResultBean itemSkuInfo(HttpServletRequest request, Long itemSkuId) {
        User user = LoginUtils.getLoginUser(request);
        ItemSku itemSku = itemSkuService.findById(itemSkuId);
        return ResultBean.buildOKResultWithObj(itemSku);
    }



}
