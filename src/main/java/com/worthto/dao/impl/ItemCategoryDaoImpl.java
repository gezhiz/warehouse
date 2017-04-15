package com.worthto.dao.impl;

import com.worthto.bean.ItemCategory;
import com.worthto.bean.service.ItemCategoryQuery;
import com.worthto.dao.ItemCategoryDao;
import com.worthto.dao.base.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gezz on 2017/4/15.
 */
@Repository("itemCategoryDao")
public class ItemCategoryDaoImpl extends BaseDaoImpl implements ItemCategoryDao {
    @Override
    public int deleteByPrimaryKey(Long id) {
        return execute("ItemCategoryMapper.deleteByPrimaryKey",id);
    }

    @Override
    public int insert(ItemCategory record) {
        return execute("ItemCategoryMapper.insert",record);
    }

    @Override
    public int insertSelective(ItemCategory record) {
        return execute("ItemCategoryMapper.insertSelective",record);
    }

    @Override
    public ItemCategory selectByPrimaryKey(Long id) {
        return executeForObject("ItemCategoryMapper.selectByPrimaryKey",id,ItemCategory.class);
    }

    @Override
    public int updateByPrimaryKeySelective(ItemCategory record) {
        return execute("ItemCategoryMapper.updateByPrimaryKeySelective",record);
    }

    @Override
    public int updateByPrimaryKey(ItemCategory record) {
        return execute("ItemCategoryMapper.updateByPrimaryKey",record);
    }

    @Override
    public Integer countByQuery(ItemCategory query) {
        return executeForObject("ItemCategoryMapper.countByQuery",query,Integer.class);
    }

    @Override
    public List<ItemCategory> selectByItemCategoryQuery(ItemCategoryQuery itemCategoryQuery) {
        return executeForObjectList("ItemCategoryMapper.selectByQuery",itemCategoryQuery,itemCategoryQuery.getSkip(),itemCategoryQuery.getPageSize());
    }

    @Override
    public ItemCategory selectOneByQuery(ItemCategoryQuery itemCategoryQuery) {
        return executeForObject("ItemCategoryMapper.selectByQuery", itemCategoryQuery,ItemCategory.class);
    }
}
