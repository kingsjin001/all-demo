package com.djin.apikylin.service.impl;

import com.alibaba.fastjson.JSON;
import com.djin.apikylin.mapper.CarNumMapper;
import com.djin.apikylin.service.CarNumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @auther dj
 * @date 2022/8/5
 * @note:
 */
@Service
@Slf4j
public class CarNumServiceimpl implements CarNumService {
    @Autowired
    CarNumMapper carNumMapper;

    @Override
    public JSON getCarNumbers() {
        return (JSON) JSON.toJSON(carNumMapper.getCarNumbers());
    }
}
