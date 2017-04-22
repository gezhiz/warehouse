package com.worthto.dao.impl;

import com.worthto.bean.InStockOrder;
import com.worthto.bean.service.InStockOrderQuery;
import com.worthto.dao.InStockOrderDao;
import com.worthto.dao.base.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gezz on 2017/4/22.
 */
@Repository("inStockOrderDao")
public class InStockOrderDaoImpl  extends BaseDaoImpl implements InStockOrderDao {
    @Override
    public int deleteByPrimaryKey(Long id) {
        return execute("InStockOrderMapper.deleteByPrimaryKey",id);
    }

    @Override
    public int insert(InStockOrder record) {
        return execute("InStockOrderMapper.insert",record);
    }

    @Override
    public int insertSelective(InStockOrder record) {
        return execute("InStockOrderMapper.insertSelective",record);
    }

    @Override
    public InStockOrder selectByPrimaryKey(Long id) {
        return executeForObject("InStockOrderMapper.selectByPrimaryKey",id,InStockOrder.class);
    }

    @Override
    public int updateByPrimaryKeySelective(InStockOrder record) {
        return execute("InStockOrderMapper.updateByPrimaryKeySelective",record);
    }

    @Override
    public int updateByPrimaryKey(InStockOrder record) {
        return execute("InStockOrderMapper.updateByPrimaryKey",record);
    }

    @Override
    public Integer countByQuery(InStockOrderQuery query) {
        return executeForObject("InStockOrderMapper.countByQuery",query,Integer.class);
    }

    @Override
    public List<InStockOrder> selectByInStockOrderQuery(InStockOrderQuery itemCategoryQuery) {
        return executeForObjectList("InStockOrderMapper.selectByQuery",itemCategoryQuery,itemCategoryQuery.getSkip(),itemCategoryQuery.getPageSize());
    }

    @Override
    public InStockOrder selectOneByQuery(InStockOrderQuery itemCategoryQuery) {
        return executeForObject("InStockOrderMapper.selectByQuery", itemCategoryQuery,InStockOrder.class);
    }
}
