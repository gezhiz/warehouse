package com.worthto.dao.impl;

import com.worthto.bean.ItemExitOrder;
import com.worthto.bean.service.ItemExitOrderQuery;
import com.worthto.dao.ItemExitOrderDao;
import com.worthto.dao.base.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gezz on 2017/3/18.
 */
@Repository("itemExitOrderDao")
public class ItemExitOrderDaoImpl extends BaseDaoImpl implements ItemExitOrderDao {
    @Override
    public int deleteByPrimaryKey(Long id) {
        return execute("ItemExitOrderMapper.deleteByPrimaryKey",id);
    }

    @Override
    public int insert(ItemExitOrder record) {
        return execute("ItemExitOrderMapper.insert",record);
    }

    @Override
    public int insertSelective(ItemExitOrder record) {
        return execute("ItemExitOrderMapper.insertSelective",record);
    }

    @Override
    public ItemExitOrder selectByPrimaryKey(Long id) {
        return executeForObject("ItemExitOrderMapper.selectByPrimaryKey",id,ItemExitOrder.class);
    }

    @Override
    public int updateByPrimaryKeySelective(ItemExitOrder record) {
        return execute("ItemExitOrderMapper.updateByPrimaryKeySelective",record);
    }

    @Override
    public int updateByPrimaryKey(ItemExitOrder record) {
        return execute("ItemExitOrderMapper.updateByPrimaryKey",record);
    }

    @Override
    public Integer countByQuery(ItemExitOrder query) {
        return executeForObject("ItemExitOrderMapper.countByQuery",query,Integer.class);
    }

    @Override
    public List<ItemExitOrder> selectByItemExitOrderQuery(ItemExitOrderQuery itemExitOrderQuery) {
        return executeForObjectList("ItemExitOrderMapper.selectByItemExitOrderQuery",itemExitOrderQuery,itemExitOrderQuery.getSkip(),itemExitOrderQuery.getPageSize());
    }

    @Override
    public ItemExitOrder selectOneByQuery(ItemExitOrderQuery itemExitOrderQuery) {
        return executeForObject("ItemExitOrderMapper.selectByItemExitOrderQuery", itemExitOrderQuery,ItemExitOrder.class);
    }

}
