package com.djin.apikylin2.controller;

import com.alibaba.fastjson.JSONObject;
import com.djin.apikylin2.service.DataService;
import com.djin.apikylin2.util.IndexProp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther dj
 * @date 2022/9/7
 * @note:
 */
@RestController
@RequestMapping("/api")
public class DataController {


    @Autowired
    DataService DataService;
    private String sql;
    IndexProp prop = new IndexProp();

    @RequestMapping("/{index-name}")
    public JSONObject getResult(@PathVariable("index-name") String indexName) {
        sql = prop.getProperty(indexName);
        return DataService.getDatas(sql);
    }
}
