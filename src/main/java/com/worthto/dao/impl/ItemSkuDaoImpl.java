package com.worthto.dao.impl;

import com.mvp01.common.exception.ParamException;
import com.mvp01.common.utils.CommonUtil;
import com.worthto.bean.ItemSku;
import com.worthto.bean.service.ItemSkuQuery;
import com.worthto.bean.service.ItemSkuStockUpdate;
import com.worthto.dao.ItemSkuDao;
import com.worthto.dao.base.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gezz on 2017/3/11.
 */
@Repository("itemSkuDao")
public class ItemSkuDaoImpl extends BaseDaoImpl implements ItemSkuDao {

    @Override
    public int deleteByPrimaryKey(Long id) {
        return execute("ItemSkuMapper.deleteByPrimaryKey",id);
    }

    @Override
    public int insert(ItemSku record) {
        return execute("ItemSkuMapper.insert",record);
    }

    @Override
    public int insertSelective(ItemSku record) {
        return execute("ItemSkuMapper.insertSelective",record);
    }

    @Override
    public ItemSku selectByPrimaryKey(Long id) {
        return executeForObject("ItemSkuMapper.selectByPrimaryKey",id,ItemSku.class);
    }

    @Override
    public int updateByPrimaryKeySelective(ItemSku record) {
        return execute("ItemSkuMapper.updateByPrimaryKeySelective",record);
    }

    @Override
    public int updateByPrimaryKey(ItemSku record) {
        return execute("ItemSkuMapper.updateByPrimaryKey",record);
    }

    @Override
    public Integer countByQuery(ItemSku query) {
        return executeForObject("ItemSkuMapper.countByQuery",query,Integer.class);
    }

    @Override
    public List<ItemSku> selectByItemSkuQuery(ItemSkuQuery itemSkuQuery) {
        return executeForObjectList("ItemSkuMapper.selectByItemSkuQuery",itemSkuQuery,itemSkuQuery.getSkip(),itemSkuQuery.getPageSize());
    }

    @Override
    public ItemSku selectOneByQuery(ItemSkuQuery itemSkuQuery) {
        return executeForObject("ItemSkuMapper.selectByItemSkuQuery", itemSkuQuery,ItemSku.class);
    }

    @Override
    public void inStockById(ItemSkuStockUpdate itemSkuStockUpdate, ItemSku dbItemSku) {
        execute("ItemSkuMapper.inStockById", itemSkuStockUpdate);
    }

    @Override
    public void updateStockById(ItemSkuStockUpdate itemSkuStockUpdate, ItemSku dbItemSku) {
        if (itemSkuStockUpdate.getAddStock() < 0) {
            //减库存,需要判定库存是否充足
            if (dbItemSku == null) {
                throw new ParamException("dbItemSku不能为空");
            }
            //判定库存是否充足
            if (dbItemSku.getId() == null) {
                throw new ParamException("dbItemSku必须含有id");
            }
            if (dbItemSku.getStock() + itemSkuStockUpdate.getAddStock() < 0) {
                //库存不足
                throw new ParamException(CommonUtil.combineString("库存不足，sku【"+dbItemSku.getStock()+"】、【"+dbItemSku.getItemSize()+"】目前库存量为"+dbItemSku.getStock()));
            }
            execute("ItemSkuMapper.updateStockById", itemSkuStockUpdate);
        } else {
            execute("ItemSkuMapper.updateStockById", itemSkuStockUpdate);
        }
    }
}
