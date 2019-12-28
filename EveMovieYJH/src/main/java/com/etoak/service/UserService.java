package com.etoak.service;

import com.etoak.bean.Page;
import com.etoak.bean.User;

public interface UserService {

    //Page<User> queryList(int pageNum, int pageSize);

    /**
     * 根据用户名和密码查询用户
     *
     * @param name
     * @param password
     * @return
     */
    User queryUser(String name, String password);
}
