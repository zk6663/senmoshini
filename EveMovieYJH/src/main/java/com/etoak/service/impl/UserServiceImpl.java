package com.etoak.service.impl;

import com.etoak.bean.Page;
import com.etoak.bean.User;
import com.etoak.mapper.UserMapper;
import com.etoak.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

   /* @Override
    public Page<User> queryList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userMapper.queryList();
        PageInfo<User> pageInfo = new PageInfo<>(userList);

        Page<User> result = new Page<>(pageInfo.getPageNum(),
            pageInfo.getPageSize(),
            userList,
            pageInfo.getTotal(),
            pageInfo.getPages(),
            pageInfo.getPrePage(),
            pageInfo.getNextPage(),
            pageInfo.isIsFirstPage(),
            pageInfo.isIsLastPage());
        return result;
    }*/

    @Override
    public User queryUser(String name, String password) {
        return userMapper.queryByNameAndPass(name, password);
    }

}
