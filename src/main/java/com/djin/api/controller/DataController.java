package com.djin.api.controller;

import com.djin.api.service.DataService;
import com.djin.api.service.DataServiceImpl;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @auther dj
 * @date 2022/7/19
 * @note:查询数据接口及返回参数处理
 */

@RestController
@RequestMapping("/api/djin")
public class DataController {
    @Autowired
    DataService dataService;
    /*
    {
    "status": 0,
    "msg": "",
    "data": 1201081.1632389291
    }
    */
    @RequestMapping("/gmv")
    public String getGMV(@RequestParam(value = "date",defaultValue = "0") Integer date) {
        if(date==0){
            date=now();
        }
        //BigDecimal gmv = dataService.getGMV(date);
        int gmv = 123456;
        String json = "{ \"status\": 0, \"data\":" + gmv + "}";
        return json;
    }
    private int now(){
        String yyyyMMdd = DateFormatUtils.format(new Date(), "yyyyMMdd");
        return Integer.valueOf(yyyyMMdd);
    }
}
