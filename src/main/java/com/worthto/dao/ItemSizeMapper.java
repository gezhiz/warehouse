package com.worthto.dao;

import com.generator.bean.ItemSize;

public interface ItemSizeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ItemSize record);

    int insertSelective(ItemSize record);

    ItemSize selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ItemSize record);

    int updateByPrimaryKey(ItemSize record);
}