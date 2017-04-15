package com.worthto.service;

import com.worthto.bean.ItemSize;
import com.worthto.bean.service.ItemSizeQuery;
import com.worthto.dao.base.PageBean;

/**
 * Created by gezz on 2017/3/18.
 */
public interface ItemSizeService extends BaseService {

    int editItemSize(ItemSize itemSize);

    PageBean itemSizePageList(ItemSizeQuery itemSizeQuery);

    ItemSize findById(Long itemSizeId);

}
