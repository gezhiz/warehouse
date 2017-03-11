package com.worthto.dao.impl;

import com.worthto.bean.User;
import com.worthto.dao.UserDao;
import com.worthto.dao.base.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by gezz on 2017/3/11.
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    public UserDaoImpl() {
        System.out.println("userDaoImpl initial");
    }

    public int deleteByPrimaryKey(Long id) {
        return execute("UserMapper.deleteByPrimaryKey",id);
    }

    public int insert(User record) {
        return execute("UserMapper.insert",record);
    }

    public int insertSelective(User record) {
        return execute("UserMapper.insertSelective",record);
    }

    public User selectByPrimaryKey(Long id) {
        return executeForObject("UserMapper.insertSelective",id,User.class);
    }

    public int updateByPrimaryKeySelective(User record) {
        return execute("UserMapper.updateByPrimaryKeySelective",record);
    }

    public int updateByPrimaryKey(User record) {
        return execute("UserMapper.updateByPrimaryKey",record);
    }
}
