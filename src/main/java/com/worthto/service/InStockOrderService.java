package com.worthto.service;

import com.worthto.bean.InStockOrder;
import com.worthto.bean.ItemSku;
import com.worthto.bean.service.InStockOrderQuery;
import com.worthto.dao.base.PageBean;

/**
 * Created by gezz on 2017/3/18.
 */
public interface InStockOrderService extends BaseService {

    int addInStockOrder(InStockOrder inStockOrder);

    PageBean inStockOrderPageList(InStockOrderQuery inStockOrderQuery);

    InStockOrder findById(Long inStockOrderId);

    /**
     * 入库操作
     * @param itemSku
     */
    void inStock(ItemSku itemSku);

}
