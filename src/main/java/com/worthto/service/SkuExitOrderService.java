package com.worthto.service;

import com.worthto.bean.SkuExitOrder;
import com.worthto.bean.service.SkuExitOrderQuery;
import com.worthto.dao.base.PageBean;

/**
 * Created by gezz on 2017/3/18.
 */
public interface SkuExitOrderService extends BaseService {

    int editSkuExitOrder(SkuExitOrder skuExitOrder);

    PageBean skuExitOrderPageList(SkuExitOrderQuery skuExitOrderQuery);

    SkuExitOrder findById(Long skuExitOrderId);

}
