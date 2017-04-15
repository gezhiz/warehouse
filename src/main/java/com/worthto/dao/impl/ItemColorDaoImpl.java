package com.worthto.dao.impl;

import com.worthto.bean.ItemColor;
import com.worthto.bean.service.ItemColorQuery;
import com.worthto.dao.ItemColorDao;
import com.worthto.dao.base.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gezz on 2017/4/15.
 */
@Repository("itemColorDao")
public class ItemColorDaoImpl extends BaseDaoImpl implements ItemColorDao {
    @Override
    public int deleteByPrimaryKey(Long id) {
        return execute("ItemColorMapper.deleteByPrimaryKey",id);
    }

    @Override
    public int insert(ItemColor record) {
        return execute("ItemColorMapper.insert",record);
    }

    @Override
    public int insertSelective(ItemColor record) {
        return execute("ItemColorMapper.insertSelective",record);
    }

    @Override
    public ItemColor selectByPrimaryKey(Long id) {
        return executeForObject("ItemColorMapper.selectByPrimaryKey",id,ItemColor.class);
    }

    @Override
    public int updateByPrimaryKeySelective(ItemColor record) {
        return execute("ItemColorMapper.updateByPrimaryKeySelective",record);
    }

    @Override
    public int updateByPrimaryKey(ItemColor record) {
        return execute("ItemColorMapper.updateByPrimaryKey",record);
    }

    @Override
    public Integer countByQuery(ItemColor query) {
        return executeForObject("ItemColorMapper.countByQuery",query,Integer.class);
    }

    @Override
    public List<ItemColor> selectByItemColorQuery(ItemColorQuery itemColorQuery) {
        return executeForObjectList("ItemColorMapper.selectByQuery",itemColorQuery,itemColorQuery.getSkip(),itemColorQuery.getPageSize());
    }

    @Override
    public ItemColor selectOneByQuery(ItemColorQuery itemColorQuery) {
        return executeForObject("ItemColorMapper.selectByQuery", itemColorQuery,ItemColor.class);
    }
}
