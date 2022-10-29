package com.djin.apimysql.mapper;

import com.djin.apimysql.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @auther dj
 * @date 2022/8/4
 * @note:
 */
@Mapper
public interface UsersMapper {
    @Select("select id,username,pwd,phone,email from db_master.users")
    public List<User> getUsers();
}
