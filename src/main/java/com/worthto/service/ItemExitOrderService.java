package com.worthto.service;

import com.worthto.bean.ItemExitOrder;
import com.worthto.bean.service.ItemExitOrderDetail;
import com.worthto.bean.service.ItemExitOrderDetailQuery;
import com.worthto.bean.service.ItemExitOrderQuery;
import com.worthto.dao.base.PageBean;

import java.util.List;

/**
 * Created by gezz on 2017/3/18.
 */
public interface ItemExitOrderService extends BaseService {

    int editItemExitOrder(ItemExitOrder itemExitOrder);

    PageBean itemExitOrderPageList(ItemExitOrderQuery itemExitOrderQuery);

    ItemExitOrder findById(Long itemExitOrderId);

    PageBean<ItemExitOrder> itemExitOrderNewList(Long userId);

    int shipped(Long itemExitOrderId, Long userId);

    List<ItemExitOrderDetail> findByItemExitOrderQuery(ItemExitOrderDetailQuery itemExitOrderDetailQuery);

}
