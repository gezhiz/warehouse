package com.worthto.service;

import com.worthto.bean.ItemCategory;
import com.worthto.bean.service.ItemCategoryQuery;
import com.worthto.dao.base.PageBean;

/**
 * Created by gezz on 2017/3/18.
 */
public interface ItemCategoryService extends BaseService {

    int editItemCategory(ItemCategory itemCategory);

    PageBean itemCategoryPageList(ItemCategoryQuery itemCategoryQuery);

    ItemCategory findById(Long itemCategoryId);

}
