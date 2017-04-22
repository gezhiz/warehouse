package com.worthto.controller;

import com.mvp01.common.bean.ResultBean;
import com.mvp01.common.exception.ErrcodeException;
import com.mvp01.common.exception.ParamException;
import com.mvp01.common.utils.LoginUtils;
import com.worthto.bean.*;
import com.worthto.bean.service.ItemColorQuery;
import com.worthto.bean.service.ItemSizeQuery;
import com.worthto.bean.service.ItemSkuQuery;
import com.worthto.dao.base.PageBean;
import com.worthto.service.*;
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
@RequestMapping("/sysops/inStock")
public class InStockController {

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemSkuService itemSkuService;

    @Autowired
    private InStockOrderService inStockOrderService;

    //入库
    @RequestMapping(value = "/in_stock", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean inStock(HttpServletRequest request, Long skuId, Long stockCount) {
        if (stockCount <= 0) {
            throw new ParamException("stockCount必须大于0");
        }
        ItemSku itemSku = itemSkuService.findById(skuId);
        inStockOrderService.inStock(itemSku, stockCount);
        return ResultBean.buildOKResult();
    }
}
