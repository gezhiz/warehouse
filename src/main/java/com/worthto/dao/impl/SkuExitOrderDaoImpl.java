package com.worthto.dao.impl;

import com.worthto.bean.SkuExitOrder;
import com.worthto.bean.service.SkuExitOrderQuery;
import com.worthto.dao.SkuExitOrderDao;
import com.worthto.dao.base.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gezz on 2017/3/18.
 */
@Repository("skuExitOrderDao")
public class SkuExitOrderDaoImpl extends BaseDaoImpl implements SkuExitOrderDao {
    @Override
    public int deleteByPrimaryKey(Long id) {
        return execute("SkuExitOrderMapper.deleteByPrimaryKey",id);
    }

    @Override
    public int insert(SkuExitOrder record) {
        return execute("SkuExitOrderMapper.insert",record);
    }

    @Override
    public int insertSelective(SkuExitOrder record) {
        return execute("SkuExitOrderMapper.insertSelective",record);
    }

    @Override
    public SkuExitOrder selectByPrimaryKey(Long id) {
        return executeForObject("SkuExitOrderMapper.selectByPrimaryKey",id,SkuExitOrder.class);
    }

    @Override
    public int updateByPrimaryKeySelective(SkuExitOrder record) {
        return execute("SkuExitOrderMapper.updateByPrimaryKeySelective",record);
    }

    @Override
    public int updateByPrimaryKey(SkuExitOrder record) {
        return execute("SkuExitOrderMapper.updateByPrimaryKey",record);
    }

    @Override
    public Integer countByQuery(SkuExitOrderQuery query) {
        return executeForObject("SkuExitOrderMapper.countByQuery",query,Integer.class);
    }

    @Override
    public List<SkuExitOrder> selectBySkuExitOrderQuery(SkuExitOrderQuery skuExitOrderQuery) {
        return executeForObjectList("SkuExitOrderMapper.selectBySkuExitOrderQuery",skuExitOrderQuery,skuExitOrderQuery.getSkip(),skuExitOrderQuery.getPageSize());
    }

    @Override
    public SkuExitOrder selectOneByQuery(SkuExitOrderQuery skuExitOrderQuery) {
        return executeForObject("SkuExitOrderMapper.selectBySkuExitOrderQuery", skuExitOrderQuery,SkuExitOrder.class);
    }

}
