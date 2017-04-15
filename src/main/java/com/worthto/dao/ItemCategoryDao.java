package com.worthto.dao;

import com.worthto.bean.ItemCategory;
import com.worthto.bean.ItemCategory;
import com.worthto.bean.service.ItemCategoryQuery;
import com.worthto.dao.base.BaseDao;

import java.util.List;

public interface ItemCategoryDao extends BaseDao {
    int deleteByPrimaryKey(Long id);

    int insert(ItemCategory record);

    int insertSelective(ItemCategory record);

    ItemCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ItemCategory record);

    int updateByPrimaryKey(ItemCategory record);

    Integer countByQuery(ItemCategory query);

    List<ItemCategory> selectByItemCategoryQuery(ItemCategoryQuery itemCategoryQuery);

    ItemCategory selectOneByQuery(ItemCategoryQuery itemCategoryQuery);
}