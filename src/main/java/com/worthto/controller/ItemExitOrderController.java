package com.worthto.controller;

import com.mvp01.common.bean.ResultBean;
import com.mvp01.common.exception.ErrcodeException;
import com.mvp01.common.exception.ParamException;
import com.mvp01.common.utils.LoginUtils;
import com.worthto.bean.ItemExitOrder;
import com.worthto.bean.User;
import com.worthto.bean.service.ItemExitOrderDetail;
import com.worthto.bean.service.ItemExitOrderDetailQuery;
import com.worthto.bean.service.ItemExitOrderQuery;
import com.worthto.bean.service.enums.ItemExitOrderStatusEnum;
import com.worthto.dao.base.PageBean;
import com.worthto.service.ItemExitOrderService;
import com.worthto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by gezz on 16/1/7.
 */
@Controller
@RequestMapping("/sysops/itemExitOrder")
public class ItemExitOrderController {

    @Autowired
    private UserService userService;

    @Autowired
    private ItemExitOrderService itemExitOrderService;

    @RequestMapping(value = "/itemExitOrderList", method = RequestMethod.GET)
    public String itemExitOrderList(HttpServletRequest request) {
        return "/sysops/itemExitOrder/itemExitOrderList";
    }

    @RequestMapping(value = "/edit_item_exit_order", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean addItemExitOrder(HttpServletRequest request, ItemExitOrder itemExitOrder) {
        User user = LoginUtils.getLoginUser(request);
        itemExitOrder.setUserId(user.getId());
        int insertNum = itemExitOrderService.editItemExitOrder(itemExitOrder);
        if (insertNum <= 0) {
            throw new ErrcodeException("添加失败,请联系系统管理员");
        }
        return ResultBean.buildOKResult();
    }

    /**
     * 操作：已发货
     * @param request
     * @param itemExitOrderId
     * @return
     */
    @RequestMapping(value = "/shipped", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean shipped(HttpServletRequest request, Long itemExitOrderId) {
        User user = LoginUtils.getLoginUser(request);
        int resultCount = itemExitOrderService.shipped(itemExitOrderId, user.getId());
        if (resultCount == 0) {
            throw new ErrcodeException("出库失败，请联系系统管理员");
        }
        return ResultBean.buildOKResult();
    }

    @RequestMapping(value = "/item_exit_order_page_list", method = RequestMethod.GET)
    @ResponseBody
    public ResultBean itemExitOrderPageList(HttpServletRequest request, ItemExitOrderQuery itemExitOrderQuery) {
        User user = LoginUtils.getLoginUser(request);
        PageBean<ItemExitOrder> itemExitOrderPageBean = itemExitOrderService.itemExitOrderPageList(itemExitOrderQuery);
        for (ItemExitOrder itemExitOrder : itemExitOrderPageBean.getList()) {
            ItemExitOrderStatusEnum statusEnum = ItemExitOrderStatusEnum.getEnumByInt(itemExitOrder.getStatus());
            if (statusEnum != null) {
                itemExitOrder.setExtValue("statusEnumShow",statusEnum.getText());
            }
        }
        return ResultBean.buildOKResultWithObj(itemExitOrderPageBean);
    }

    @RequestMapping(value = "/item_exit_order_info", method = RequestMethod.GET)
    @ResponseBody
    public ResultBean itemExitOrderInfo(HttpServletRequest request, Long itemExitOrderId) {
        User user = LoginUtils.getLoginUser(request);
        ItemExitOrder itemExitOrder = itemExitOrderService.findById(itemExitOrderId);
        return ResultBean.buildOKResultWithObj(itemExitOrder);
    }

    /**
     * 商品出库单详情
     * @param request
     * @param itemExitOrderId
     * @return
     */
    @RequestMapping(value = "/item_exit_order_detail", method = RequestMethod.GET)
    @ResponseBody
    public ResultBean itemExitOrderDetail(HttpServletRequest request, Long itemExitOrderId) {
        User user = LoginUtils.getLoginUser(request);
        ItemExitOrder itemExitOrder = itemExitOrderService.findById(itemExitOrderId);
        if (!itemExitOrder.getUserId().equals(user.getId())) {
            throw new ParamException("未找到您的出库单");
        }
        ItemExitOrderDetailQuery itemExitOrderDetailQuery = new ItemExitOrderDetailQuery();
        itemExitOrderDetailQuery.setItemExitOrderDetailId(itemExitOrderId);
        List<ItemExitOrderDetail> itemExitOrderDetails = itemExitOrderService.findByItemExitOrderQuery(itemExitOrderDetailQuery);
        itemExitOrder.setExtValue("itemExitOrderDetails", itemExitOrderDetails);
        return ResultBean.buildOKResultWithObj(itemExitOrder);
    }

    @RequestMapping(value = "/itemExitOrderDetail/{itemExitOrderId}", method = RequestMethod.GET)
    public String itemExitOrderDetail(Model model, HttpServletRequest request, @PathVariable Long itemExitOrderId) {
        if (itemExitOrderId == null) {
            throw new ParamException("orderId不能为空");
        }
        User user = LoginUtils.getLoginUser(request);
        ItemExitOrder itemExitOrder = itemExitOrderService.findById(itemExitOrderId);
        if (!itemExitOrder.getUserId().equals(user.getId())) {
            throw new ParamException("未找到您的出库单");
        }
        ItemExitOrderDetailQuery itemExitOrderDetailQuery = new ItemExitOrderDetailQuery();
        itemExitOrderDetailQuery.setItemExitOrderDetailId(itemExitOrderId);
        List<ItemExitOrderDetail> itemExitOrderDetails = itemExitOrderService.findByItemExitOrderQuery(itemExitOrderDetailQuery);
        itemExitOrder.setExtValue("itemExitOrderDetails", itemExitOrderDetails);
        model.addAttribute("itemExitOrder",itemExitOrder);
        return "/sysops/itemExitOrder/itemExitOrderDetail";
    }

}
