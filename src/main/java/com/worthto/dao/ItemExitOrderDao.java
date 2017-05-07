package com.worthto.dao;

import com.worthto.bean.ItemExitOrder;
import com.worthto.bean.ItemExitOrder;
import com.worthto.bean.service.ItemExitOrderDetail;
import com.worthto.bean.service.ItemExitOrderDetailQuery;
import com.worthto.bean.service.ItemExitOrderQuery;
import com.worthto.bean.service.ItemExitOrderUpdate;
import com.worthto.dao.base.BaseDao;

import java.util.List;

public interface ItemExitOrderDao extends BaseDao {
    int deleteByPrimaryKey(Long id);

    int insert(ItemExitOrder record);

    int insertSelective(ItemExitOrder record);

    ItemExitOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ItemExitOrder record);

    int updateByPrimaryKey(ItemExitOrder record);


    Integer countByQuery(ItemExitOrder query);

    List<ItemExitOrder> selectByItemExitOrderQuery(ItemExitOrderQuery itemExitOrderQuery);

    ItemExitOrder selectOneByQuery(ItemExitOrderQuery itemExitOrderQuery);

    void updateStockById(ItemExitOrderUpdate itemExitOrderUpdate);

    List<ItemExitOrderDetail> selectDetailByItemExitOrderQuery(ItemExitOrderDetailQuery itemExitOrderDetailQuery);

}