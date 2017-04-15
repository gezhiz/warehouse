package com.worthto.dao.impl;

import com.mvp01.common.exception.ParamException;
import com.mvp01.common.utils.CommonUtil;
import com.worthto.bean.ItemSize;
import com.worthto.bean.User;
import com.worthto.dao.UserDao;
import com.worthto.dao.base.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by gezz on 2017/3/11.
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    public int deleteByPrimaryKey(Long id) {
        return execute("UserMapper.deleteByPrimaryKey",id);
    }

    public int insert(User record) {
        User query = new User();
        query.setUsername(record.getUsername());
        if (countByQuery(query) > 0) {
            throw new ParamException(CommonUtil.combineString("用户名为",record.getUsername(),"的用户已经存在"));
        }
        return execute("UserMapper.insert",record);
    }

    public Integer countByQuery(User query) {
        return executeForObject("UserMapper.countByQuery",query,Integer.class);
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

    @Override
    public User selectByUsernameAndPasswd(User record) {
        return executeForObject("UserMapper.selectByUsernameAndPasswd", record, User.class);
    }
}
