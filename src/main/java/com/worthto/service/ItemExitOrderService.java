package com.worthto.service;

import com.worthto.bean.ItemExitOrder;
import com.worthto.bean.service.ItemExitOrderQuery;
import com.worthto.dao.base.PageBean;

/**
 * Created by gezz on 2017/3/18.
 */
public interface ItemExitOrderService extends BaseService {

    int editItemExitOrder(ItemExitOrder itemExitOrder);

    PageBean itemExitOrderPageList(ItemExitOrderQuery itemExitOrderQuery);

    ItemExitOrder findById(Long itemExitOrderId);

}
