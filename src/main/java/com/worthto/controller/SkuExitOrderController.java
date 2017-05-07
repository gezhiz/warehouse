package com.worthto.controller;

import com.mvp01.common.bean.ResultBean;
import com.mvp01.common.exception.ErrcodeException;
import com.mvp01.common.utils.LoginUtils;
import com.worthto.bean.SkuExitOrder;
import com.worthto.bean.User;
import com.worthto.bean.service.SkuExitOrderQuery;
import com.worthto.dao.base.PageBean;
import com.worthto.service.SkuExitOrderService;
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
@RequestMapping("/sysops/skuExitOrder")
public class SkuExitOrderController {

    @Autowired
    private UserService userService;

    @Autowired
    private SkuExitOrderService skuExitOrderService;

    @RequestMapping(value = "/skuExitOrderList", method = RequestMethod.GET)
    public String skuExitOrderList(HttpServletRequest request) {
        return "/sysops/skuExitOrder/skuExitOrderList";
    }

    /**
     * 加入出库车、编辑出库车的某个sku
     * @param request
     * @param skuExitOrder
     * @return
     */
    @RequestMapping(value = "/edit_sku_exit_order", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean addSkuExitOrder(HttpServletRequest request, SkuExitOrder skuExitOrder) {
        User user = LoginUtils.getLoginUser(request);
        skuExitOrder.setUserId(user.getId());
        int insertNum = skuExitOrderService.editSkuExitOrder(skuExitOrder);
        if (insertNum <= 0) {
            throw new ErrcodeException("添加失败,请联系系统管理员");
        }
        return ResultBean.buildOKResult();
    }

    @RequestMapping(value = "/sku_exit_order_page_list", method = RequestMethod.GET)
    @ResponseBody
    public ResultBean skuExitOrderPageList(HttpServletRequest request, SkuExitOrderQuery skuExitOrderQuery) {
        PageBean<SkuExitOrder> skuExitOrderPageBean = skuExitOrderService.skuExitOrderPageList(skuExitOrderQuery);
        return ResultBean.buildOKResultWithObj(skuExitOrderPageBean);
    }

    @RequestMapping(value = "/sku_exit_order_info", method = RequestMethod.GET)
    @ResponseBody
    public ResultBean skuExitOrderInfo(HttpServletRequest request, Long skuExitOrderId) {
        SkuExitOrder skuExitOrder = skuExitOrderService.findById(skuExitOrderId);
        return ResultBean.buildOKResultWithObj(skuExitOrder);
    }



}
