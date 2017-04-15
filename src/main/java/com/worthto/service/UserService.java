package com.worthto.service;

import com.worthto.bean.User;

/**
 * Created by gezz on 2017/3/18.
 */
public interface UserService extends BaseService {
    User findByUsernameAndPasswd(User user);
}
