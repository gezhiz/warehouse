package com.worthto.controller;

import com.mvp01.common.bean.ResultBean;
import com.mvp01.common.exception.ErrcodeException;
import com.mvp01.common.exception.ParamException;
import com.mvp01.common.utils.LoginUtils;
import com.sun.tracing.dtrace.Attributes;
import com.worthto.bean.Item;
import com.worthto.bean.ItemSku;
import com.worthto.bean.User;
import com.worthto.bean.service.ItemSkuQuery;
import com.worthto.dao.base.PageBean;
import com.worthto.service.ItemService;
import com.worthto.service.ItemSkuService;
import com.worthto.service.UserService;
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

    @RequestMapping(value = "/itemSkuList/{itemId}", method = RequestMethod.GET)
    public String skuList(HttpServletRequest request, Model model, @PathVariable Long itemId) {
        Item item = itemService.findById(itemId);
        model.addAttribute("item",item);
        return "/sysops/sku/itemSkuList";
    }

    @RequestMapping(value = "/edit_item_sku", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean addItemSku(HttpServletRequest request, ItemSku itemSku) {
        User user = LoginUtils.getLoginUser(request);
        int insertNum = itemSkuService.editItemSku(itemSku);
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
