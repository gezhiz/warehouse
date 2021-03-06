package com.worthto.dao;

import com.worthto.bean.Item;
import com.worthto.bean.service.ItemCountUpdate;
import com.worthto.bean.service.ItemQuery;
import com.worthto.bean.service.ItemSkuStockUpdate;
import com.worthto.dao.base.BaseDao;

import java.util.List;

public interface ItemDao extends BaseDao {
    int deleteByPrimaryKey(Long id);

    int insert(Item record);

    int insertSelective(Item record);

    Item selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKey(Item record);

    Integer countByQuery(Item query);

    List<Item> selectByItemQuery(ItemQuery itemQuery);

    Item selectOneByQuery(ItemQuery itemQuery);

    void inStockById(ItemCountUpdate itemCountUpdate, Item dbItem);//入库

    void updateStockById(ItemCountUpdate itemCountUpdate, Item dbItem);


}