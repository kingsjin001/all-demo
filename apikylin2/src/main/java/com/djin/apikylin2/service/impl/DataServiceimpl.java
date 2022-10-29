package com.djin.apikylin2.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.djin.apikylin2.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @auther dj
 * @date 2022/9/6
 * @note:
 */
@Service
@Slf4j
public class DataServiceimpl implements DataService {
    @Value("${kylin.query.url}")
    String kylinQueryUrl;
    @Value("${kylin.query.project}")
    String kylinProject;
    @Value("${kylin.query.authorization}")
    String kylinAuthorization;

    @Override
    public JSONObject getDatas(String sql) {

        JSONObject result = new JSONObject();
        ArrayList columns = new ArrayList<String>();
        JSONObject resultData = new JSONObject();
        String duration = "";
        String code = "";
        //开始时间
        //设置时间格式，为了 能转换成 字符串
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginTime = new Date();
        Long begin = beginTime.getTime();
        try {
            URL url = new URL(kylinQueryUrl);
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setConnectTimeout(30000);
            httpConnection.setReadTimeout(3600000);

            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Authorization", kylinAuthorization);
            httpConnection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            httpConnection.setRequestProperty("Accept", "application/vnd.apache.kylin-v4-public+json");

            OutputStream outputStream = httpConnection.getOutputStream();
            String queryData = "{\"sql\":\"" + sql + "\", \"project\":\"" + kylinProject + "\"}";
            log.info("执行SQL: " + sql);
            outputStream.write(queryData.getBytes());
            outputStream.flush();

            if (httpConnection.getResponseCode() != 200) {
                log.error("Failed : HTTP error code : " + httpConnection.getResponseCode());
            }

            BufferedReader responseBuffer = new BufferedReader(new InputStreamReader((httpConnection.getInputStream())));
            String resJsonStr = responseBuffer.readLine();

            if (resJsonStr != null) {
                JSONObject resJson = JSONObject.parseObject(resJsonStr);
                //System.out.println(resJson);
                code = resJson.getString("code");
                BigInteger rowCount = resJson.getJSONObject("data").getBigInteger("resultRowCount");
                Object resData = resJson.getJSONObject("data").get("results");
                JSONArray columnMetas = resJson.getJSONObject("data").getJSONArray("columnMetas");

                for (int i = 0; i < columnMetas.size(); i++) {
                    JSONObject columnJson = columnMetas.getJSONObject(i);
                    String columnName = columnJson.getString("name").toLowerCase();
                    columns.add(columnName);
                }

                resultData.put("resultRowCount", rowCount);
                resultData.put("results", resData);
            }

            httpConnection.disconnect();

        } catch (MalformedURLException e) {
            log.error(e.toString());
        } catch (IOException e) {
            log.error(e.toString());
        }
        //结束时间
        Date finishTime = new Date();
        Long end = finishTime.getTime();
        // 时间差 = 结束时间 - 开始时间，这样得到的差值是毫秒级别
        long timeLag = end - begin;
        //天
        long day = timeLag / (24 * 60 * 60 * 1000);
        //小时
        long hour = (timeLag / (60 * 60 * 1000) - day * 24);
        //分钟
        long minute = ((timeLag / (60 * 1000)) - day * 24 * 60 - hour * 60);
        //秒
        long s = (timeLag / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - minute * 60);
        //毫秒
        long ms = (timeLag % 1000);
        duration = day + "天 " + hour + "时 " + minute + "分 " + s + "秒" + ms + "毫秒";
        String finishTimestr = df.format(finishTime);
        result.put("code", code);
        result.put("duration", duration);
        result.put("columns", columns);
        result.put("data", resultData);
        return result;
    }
}
