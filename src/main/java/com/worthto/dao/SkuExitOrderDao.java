package com.worthto.dao;

import com.worthto.bean.SkuExitOrder;
import com.worthto.bean.service.SkuExitOrderQuery;
import com.worthto.dao.base.BaseDao;

import java.util.List;

public interface SkuExitOrderDao extends BaseDao {
    int deleteByPrimaryKey(Long id);

    int insert(SkuExitOrder record);

    int insertSelective(SkuExitOrder record);

    SkuExitOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SkuExitOrder record);

    int updateByPrimaryKey(SkuExitOrder record);


    Integer countByQuery(SkuExitOrderQuery query);

    List<SkuExitOrder> selectBySkuExitOrderQuery(SkuExitOrderQuery skuExitOrderQuery);

    SkuExitOrder selectOneByQuery(SkuExitOrderQuery skuExitOrderQuery);
}