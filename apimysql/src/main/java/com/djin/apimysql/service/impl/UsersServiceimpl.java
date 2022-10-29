package com.djin.apimysql.service.impl;

import com.djin.apimysql.entity.User;
import com.djin.apimysql.mapper.UsersMapper;
import com.djin.apimysql.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther dj
 * @date 2022/8/4
 * @note:
 */
@Service
public class UsersServiceimpl implements UsersService {
    @Autowired
    UsersMapper usersMapper;

    @Override
    public List<User> getUsers() {
        return usersMapper.getUsers();
    }
}
