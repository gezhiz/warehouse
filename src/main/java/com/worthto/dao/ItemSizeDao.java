package com.worthto.dao;

import com.worthto.bean.ItemSize;
import com.worthto.bean.User;
import com.worthto.bean.service.ItemSizeQuery;
import com.worthto.dao.base.BaseDao;

import java.util.List;

public interface ItemSizeDao extends BaseDao {
    int deleteByPrimaryKey(Long id);

    int insert(ItemSize record);

    int insertSelective(ItemSize record);

    ItemSize selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ItemSize record);

    int updateByPrimaryKey(ItemSize record);


    Integer countByQuery(ItemSize query);

    List<ItemSize> selectByItemSizeQuery(ItemSizeQuery itemSizeQuery);

    ItemSize selectOneByQuery(ItemSizeQuery itemSizeQuery);
}