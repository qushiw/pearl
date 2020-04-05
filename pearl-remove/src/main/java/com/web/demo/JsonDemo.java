package com.web.demo;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;
import java.util.Set;

/**
 * @Author: qushiwen
 * @Date: 18-11-27
 * @version: 1.0
 */
public class JsonDemo {

    public static void main(String[] args) {

        String jsonString = "{'127.0.0.1':'3306', '127.0.0.2':'3307'}";

        JSONObject jsonObject = JSONObject.parseObject(jsonString);

        System.out.println(jsonObject);

        Set<Map.Entry<String, Object>> set = jsonObject.entrySet();
        for (Map.Entry<String, Object> entry : set) {
            System.out.println("key:" + entry.getKey() + ",value:" + entry.getValue());
        }
    }


}
