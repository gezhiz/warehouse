package com.worthto.service.impl;

import com.worthto.bean.User;
import com.worthto.dao.UserDao;
import com.worthto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gezz on 2017/3/18.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findByUsernameAndPasswd(User user) {
        return userDao.selectByUsernameAndPasswd(user);
    }
}
