package com.worthto.test.dao;

import com.mvp01.common.utils.CipherUtil;
import com.worthto.bean.User;
import com.worthto.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by gezz on 2017/3/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-root.xml")
public class DaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void initSys() {
        User user = new User();
        user.setEmail("gezhizheng");
        user.setMobile(18500865387L);
        user.setPassword(CipherUtil.generatePassword("wjsw240353236"));
        user.setUsername("gezz");
        user.setId(9304568295064l);
        userDao.insert(user);
    }
}
