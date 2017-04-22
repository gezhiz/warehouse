package com.worthto.dao;

import com.worthto.bean.ItemSku;
import com.worthto.bean.ItemSku;
import com.worthto.bean.service.ItemSkuQuery;
import com.worthto.bean.service.ItemSkuStockUpdate;
import com.worthto.dao.base.BaseDao;

import java.util.List;

public interface ItemSkuDao extends BaseDao {
    int deleteByPrimaryKey(Long id);

    int insert(ItemSku record);

    int insertSelective(ItemSku record);

    ItemSku selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ItemSku record);

    int updateByPrimaryKey(ItemSku record);


    Integer countByQuery(ItemSku query);

    List<ItemSku> selectByItemSkuQuery(ItemSkuQuery itemSizeQuery);

    ItemSku selectOneByQuery(ItemSkuQuery itemSizeQuery);

    void updateStockById(ItemSkuStockUpdate itemSkuStockUpdate);
}