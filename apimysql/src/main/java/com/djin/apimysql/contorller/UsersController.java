package com.djin.apimysql.contorller;

import com.alibaba.fastjson.JSON;
import com.djin.apimysql.entity.User;
import com.djin.apimysql.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @auther dj
 * @date 2022/8/4
 * @note:
 */
@RestController
@RequestMapping("/api")
public class UsersController {
    @Autowired
    UsersService usersService;

    @RequestMapping("/users")
    public JSON getUsers() {
        List<User> users = usersService.getUsers();

        return (JSON) JSON.toJSON(users);
    }
}
