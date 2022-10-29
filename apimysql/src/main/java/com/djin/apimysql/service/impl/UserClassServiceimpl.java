package com.djin.apimysql.service.impl;

import com.djin.apimysql.entity.UserClass;
import com.djin.apimysql.mapper.UserClassMapper;
import com.djin.apimysql.service.UserClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * @auther dj
 * @date 2022/8/4
 * @note:
 */
@Service
public class UserClassServiceimpl implements UserClassService {
    @Autowired
    UserClassMapper userClassMapper;
    @Override
    public List<UserClass> getUserClasses() {
        return userClassMapper.getUserClasses();
    }
}
