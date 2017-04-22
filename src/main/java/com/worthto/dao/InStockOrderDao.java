package com.worthto.dao;

import com.worthto.bean.InStockOrder;
import com.worthto.bean.service.InStockOrderQuery;
import com.worthto.dao.base.BaseDao;

import java.util.List;

public interface InStockOrderDao extends BaseDao {
    int deleteByPrimaryKey(Long id);

    int insert(InStockOrder record);

    int insertSelective(InStockOrder record);

    InStockOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InStockOrder record);

    int updateByPrimaryKey(InStockOrder record);

    Integer countByQuery(InStockOrderQuery query);

    List<InStockOrder> selectByInStockOrderQuery(InStockOrderQuery itemCategoryQuery);

    InStockOrder selectOneByQuery(InStockOrderQuery itemCategoryQuery);
}