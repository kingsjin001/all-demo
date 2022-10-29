package com.djin.apimysql.contorller;

import com.alibaba.fastjson.JSON;
import com.djin.apimysql.entity.UserClass;
import com.djin.apimysql.service.UserClassService;
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
public class UserClassController {
    @Autowired
    UserClassService userClassService;
    @RequestMapping("/userclass")
    public JSON getUserClass() {
        List<UserClass> userClasses = userClassService.getUserClasses();
        return (JSON) JSON.toJSON(userClasses);
    }
}
