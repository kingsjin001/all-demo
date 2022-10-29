package com.djin.apimysql.mapper;

import com.djin.apimysql.entity.UserClass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @auther dj
 * @date 2022/8/4
 * @note:
 */
@Mapper
public interface UserClassMapper {
    @Select("select a.id," +
            "b.class as class_name," +
            "a.username as student_name," +
            "a.gender " +
            "from users a left join students b " +
            "on a.username = b.name")
    public List<UserClass> getUserClasses();
}
