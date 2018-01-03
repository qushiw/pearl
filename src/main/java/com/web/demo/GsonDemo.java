package com.web.demo;


import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.web.util.JsonUtil;

import java.util.Map;

/**
*
* @author qushiwen
* @create 2017-12-28
* @version 1.0
**/
public class GsonDemo {

    public static void main(String[] args) {
        String jsonData = "192.123.2.23";
        try {
            Map<String, Map<String, Map<String, Object>>> data =
                    JsonUtil.fromJson(jsonData, new TypeToken<Map<String, Map<String, Map<String, Object>>>>(){});


        } catch (JsonSyntaxException e) {
            System.out.println("json 串  解析出错");
        }


    }


}
