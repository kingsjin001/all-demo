package com.djin.apimysql.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

/**
 * @auther dj
 * @date 2022/8/4
 * @note:
 */
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserClass {
    private int id;
    private String className;
    private String studentName;
    private String gender;
}
