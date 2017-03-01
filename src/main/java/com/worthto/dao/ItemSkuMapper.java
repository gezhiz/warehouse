package com.worthto.dao;

import com.generator.bean.ItemSku;

public interface ItemSkuMapper {
    int insert(ItemSku record);

    int insertSelective(ItemSku record);
}