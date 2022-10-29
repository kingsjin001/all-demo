package com.djin.apikylin.controller;

import com.alibaba.fastjson.JSON;
import com.djin.apikylin.service.CarNumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther dj
 * @date 2022/8/5
 * @note:
 */
@RestController
@RequestMapping("/api")
public class CarNumController {
    @Autowired
    CarNumService carNumService;
    @RequestMapping("/carnum")
    public JSON getCarnumbers() {
        return carNumService.getCarNumbers();
    }
}
