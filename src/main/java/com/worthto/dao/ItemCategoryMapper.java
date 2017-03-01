package com.worthto.dao;

import com.generator.bean.ItemCategory;

public interface ItemCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ItemCategory record);

    int insertSelective(ItemCategory record);

    ItemCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ItemCategory record);

    int updateByPrimaryKey(ItemCategory record);
}