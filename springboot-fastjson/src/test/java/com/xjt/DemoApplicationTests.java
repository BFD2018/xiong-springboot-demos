package com.xjt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.xjt.domain.User;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        // 对象
        User user1 = new User(11, "lisi");
        User user2 = new User(12, "zhangsan");
        String s = JSON.toJSONString(user1);
        System.out.println("对象变为json字符串" + s);
        User user = JSON.parseObject(s, User.class);
        System.out.println("json字符串变为对象" + user);

        JSONObject jsonObject = (JSONObject) JSON.toJSON(user1);
        System.out.println("java对象变为json对象" + jsonObject);
        User user3 = JSON.toJavaObject(jsonObject, User.class);
        System.out.println("json对象变为java对象" + user3);


        // list
        List list = new ArrayList();
        list.add(user1);
        list.add(user2);
        String s1 = JSON.toJSONString(list);
        System.out.println("list变为json字符串" + s1);// []是个数组
        List<User> users = JSON.parseArray(s1, User.class);
        System.out.println("json字符串变为list" + users);

        // map
        Map map = new HashMap();
        map.put("user1", user1);
        map.put("user2", user2);
        String smap = JSON.toJSONString(map);
        System.out.println("map变为json字符串" + smap);
        JSONObject jsonObject1 = JSON.parseObject(smap);

        JSONObject user11 = jsonObject1.getJSONObject("user1");
        System.out.println(user11.getString("id"));
    }

    @Test
    void testHttpClient1() {
        CloseableHttpClient httpClient = null;
        HttpGet httpGet = null;
        CloseableHttpResponse response = null;
        String result = "";

        try {
            //1、创建HttpClient对象
            httpClient = HttpClientBuilder.create().build();
            //2、创建HttpGet对象
            String url = "http://152.136.185.210:8000/api/w6/home/data?type=pop&page=1";            //请求到的json数据： 20220623095020.json
            httpGet = new HttpGet(url);

            //3、执行get请求
            response = httpClient.execute(httpGet);

            //4、得到响应结果
            result = EntityUtils.toString(response.getEntity());
            System.out.println(result);

            JSONArray jsonArray = JSON.parseObject(result).getJSONObject("data").getJSONArray("list");
            for (Object o : jsonArray) {
                System.out.println(o);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }

                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
