package com.web.demo.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * @Author: qushiwen
 * @Date: 19-1-4
 * @version: 1.0
 */
public class HttpDemo {

    public static void main(String[] args) throws IOException, InterruptedException {

/*        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofMillis(5000))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();


        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://www.baidu.com"))
                .build();


        HttpResponse response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        System.out.println(response);*/


        String sql = "EXPLAIN SELECT * FROM CITY";
        String lowerSql = "explain select * from city";

        String explain = "EXPLAIN";

        System.out.println(lowerSql.toUpperCase().startsWith("EXPLAIN"));

        System.out.println(lowerSql);

        lowerSql = lowerSql.toUpperCase().replaceFirst(explain, "");

        System.out.println(lowerSql);

    }


}
