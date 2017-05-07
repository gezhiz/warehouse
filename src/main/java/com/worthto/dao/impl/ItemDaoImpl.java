package com.worthto.dao.impl;

import com.mvp01.common.exception.ParamException;
import com.mvp01.common.utils.CommonUtil;
import com.worthto.bean.Item;
import com.worthto.bean.service.ItemCountUpdate;
import com.worthto.bean.service.ItemQuery;
import com.worthto.bean.service.ItemSkuStockUpdate;
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

    @Override
    public void inStockById(ItemCountUpdate itemCountUpdate, Item dbItem) {
        execute("ItemMapper.inStockById", itemCountUpdate);
    }

    @Override
    public void updateStockById(ItemCountUpdate itemCountUpdate, Item dbItem) {
        if (itemCountUpdate.getAddCount() < 0) {
            //减库存
            if (dbItem == null) {
                throw new ParamException("dbItem不能为空");
            }
            //判定库存是否充足
            if (dbItem.getId() == null) {
                throw new ParamException("dbItem必须含有id");
            }
            if (dbItem.getTotalCount() + itemCountUpdate.getAddCount() < 0) {
                //库存不足
                throw new ParamException(CommonUtil.combineString("库存不足，商品【"+dbItem.getName()+"】目前库存量为"+dbItem.getTotalCount()));
            }
            execute("ItemMapper.updateStockById", itemCountUpdate);
        } else {
            //增加库存
            execute("ItemMapper.updateStockById", itemCountUpdate);
        }
    }
}
