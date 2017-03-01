package com.worthto.dao;

import com.generator.bean.ItemColor;

public interface ItemColorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ItemColor record);

    int insertSelective(ItemColor record);

    ItemColor selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ItemColor record);

    int updateByPrimaryKey(ItemColor record);
}