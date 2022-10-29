package com.djin.apimysql.entity;

import lombok.Data;

/**
 * @auther dj
 * @date 2022/8/4
 * @note:
 */
@Data
public class User {
    private int id;
    private String username;
    private String pwd;
    private String phone;
    private String email;
}
