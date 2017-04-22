package com.worthto.service;

import com.worthto.bean.ItemSku;
import com.worthto.bean.service.ItemSkuQuery;
import com.worthto.dao.base.PageBean;

/**
 * Created by gezz on 2017/3/18.
 */
public interface ItemSkuService extends BaseService {

    int editItemSku(ItemSku itemSku);

    PageBean itemSkuPageList(ItemSkuQuery itemSkuQuery);

    ItemSku findById(Long itemSkuId);

}
