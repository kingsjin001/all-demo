package com.djin.apimysql.service;

import com.djin.apimysql.entity.User;

import java.util.List;

/**
 * @auther dj
 * @date 2022/8/4
 * @note:
 */
public interface UsersService {
    public List<User> getUsers();
}
