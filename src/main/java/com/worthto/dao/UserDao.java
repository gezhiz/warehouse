package com.worthto.dao;

import com.worthto.bean.User;
import com.worthto.dao.base.BaseDao;

public interface UserDao extends BaseDao {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}