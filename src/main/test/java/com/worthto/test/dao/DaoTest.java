package com.worthto.test.dao;

import com.mvp01.common.utils.CipherUtil;
import com.worthto.bean.ItemSize;
import com.worthto.bean.User;
import com.worthto.bean.service.ItemSizeQuery;
import com.worthto.dao.ItemSizeDao;
import com.worthto.dao.UserDao;
import com.worthto.dao.base.PageBean;
import com.worthto.service.ItemSizeService;
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

    @Autowired
    private ItemSizeService itemSizeService;

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

    @Test
    public void testItemSizePageList() {
        ItemSizeQuery itemSizeQuery = new ItemSizeQuery();
        itemSizeQuery.setPage(2);
        itemSizeQuery.setPageSize(5);
        PageBean<ItemSize> pageBean = itemSizeService.itemSizePageList(itemSizeQuery);
        System.out.println(pageBean.getList());
    }
}
