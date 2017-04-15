package com.worthto.dao.impl;

import com.worthto.bean.ItemSize;
import com.worthto.bean.service.ItemSizeQuery;
import com.worthto.dao.ItemSizeDao;
import com.worthto.dao.base.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gezz on 2017/3/18.
 */
@Repository("itemSizeDao")
public class ItemSizeDaoImpl extends BaseDaoImpl implements ItemSizeDao {
    @Override
    public int deleteByPrimaryKey(Long id) {
        return execute("ItemSizeMapper.deleteByPrimaryKey",id);
    }

    @Override
    public int insert(ItemSize record) {
        return execute("ItemSizeMapper.insert",record);
    }

    @Override
    public int insertSelective(ItemSize record) {
        return execute("ItemSizeMapper.insertSelective",record);
    }

    @Override
    public ItemSize selectByPrimaryKey(Long id) {
        return executeForObject("ItemSizeMapper.insertSelective",id,ItemSize.class);
    }

    @Override
    public int updateByPrimaryKeySelective(ItemSize record) {
        return execute("ItemSizeMapper.updateByPrimaryKeySelective",record);
    }

    @Override
    public int updateByPrimaryKey(ItemSize record) {
        return execute("ItemSizeMapper.updateByPrimaryKey",record);
    }

    @Override
    public Integer countByQuery(ItemSize query) {
        return executeForObject("ItemSizeMapper.countByQuery",query,Integer.class);
    }

    @Override
    public List<ItemSize> selectByItemSizeQuery(ItemSizeQuery itemSizeQuery) {
        return executeForObjectList("ItemSizeMapper.selectByItemSizeQuery",itemSizeQuery,itemSizeQuery.getSkip(),itemSizeQuery.getPageSize());
    }


}
