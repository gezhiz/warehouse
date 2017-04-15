package com.worthto.service;

import com.worthto.bean.ItemColor;
import com.worthto.bean.service.ItemColorQuery;
import com.worthto.dao.base.PageBean;

/**
 * Created by gezz on 2017/3/18.
 */
public interface ItemColorService extends BaseService {

    int editItemColor(ItemColor itemColor);

    PageBean itemColorPageList(ItemColorQuery itemColorQuery);

    ItemColor findById(Long itemColorId);

}
