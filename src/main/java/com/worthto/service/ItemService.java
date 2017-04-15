package com.worthto.service;

import com.worthto.bean.Item;
import com.worthto.bean.service.ItemQuery;
import com.worthto.dao.base.PageBean;

/**
 * Created by gezz on 2017/3/18.
 */
public interface ItemService extends BaseService {

    int editItem(Item item);

    PageBean itemPageList(ItemQuery itemQuery);

    Item findById(Long itemId);

}
