package com.worthto.dao;

import com.worthto.bean.ItemColor;
import com.worthto.bean.service.ItemColorQuery;
import com.worthto.dao.base.BaseDao;

import java.util.List;

public interface ItemColorDao extends BaseDao {
    int deleteByPrimaryKey(Long id);

    int insert(ItemColor record);

    int insertSelective(ItemColor record);

    ItemColor selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ItemColor record);

    int updateByPrimaryKey(ItemColor record);

    Integer countByQuery(ItemColor query);

    List<ItemColor> selectByItemColorQuery(ItemColorQuery itemColorQuery);

    ItemColor selectOneByQuery(ItemColorQuery itemColorQuery);
}