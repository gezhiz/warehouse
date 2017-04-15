package com.worthto.dao.impl;

import com.worthto.bean.Item;
import com.worthto.bean.service.ItemQuery;
import com.worthto.dao.ItemDao;
import com.worthto.dao.base.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gezz on 2017/4/15.
 */
@Repository("itemDao")
public class ItemDaoImpl extends BaseDaoImpl implements ItemDao {
    @Override
    public int deleteByPrimaryKey(Long id) {
        return execute("ItemMapper.deleteByPrimaryKey",id);
    }

    @Override
    public int insert(Item record) {
        return execute("ItemMapper.insert",record);
    }

    @Override
    public int insertSelective(Item record) {
        return execute("ItemMapper.insertSelective",record);
    }

    @Override
    public Item selectByPrimaryKey(Long id) {
        return executeForObject("ItemMapper.selectByPrimaryKey",id,Item.class);
    }

    @Override
    public int updateByPrimaryKeySelective(Item record) {
        return execute("ItemMapper.updateByPrimaryKeySelective",record);
    }

    @Override
    public int updateByPrimaryKey(Item record) {
        return execute("ItemMapper.updateByPrimaryKey",record);
    }

    @Override
    public Integer countByQuery(Item query) {
        return executeForObject("ItemMapper.countByQuery",query,Integer.class);
    }

    @Override
    public List<Item> selectByItemQuery(ItemQuery itemQuery) {
        return executeForObjectList("ItemMapper.selectByQuery",itemQuery,itemQuery.getSkip(),itemQuery.getPageSize());
    }

    @Override
    public Item selectOneByQuery(ItemQuery itemQuery) {
        return executeForObject("ItemMapper.selectByQuery", itemQuery,Item.class);
    }
}
