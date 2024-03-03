package com.xjt.httpclient.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 采用原生的Http请求
 */
@ResponseBody
@Controller
public class HttpClientsController {
    @GetMapping("httpclients/post")
    public String toIndex() {
        //此处将要发送的数据转换为json格式字符串
        String jsonText = "{id: 1}";
        JSONObject json = (JSONObject) JSONObject.parse(jsonText);
        JSONObject sr = doPost(json);
        System.out.println("返回参数: " + sr);
        return sr.toString();
    }

    public JSONObject doPost(JSONObject date) {
        HttpClient client = HttpClients.createDefault();
        // 要调用的接口方法
        String url = "https://www.httpbin.org/post";
        HttpPost post = new HttpPost(url);
        JSONObject jsonObject = null;
        try {
            StringEntity s = new StringEntity(date.toString());
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");
            post.setEntity(s);
            post.addHeader("content-type", "application/json");
            HttpResponse res = client.execute(post);
            String response1 = EntityUtils.toString(res.getEntity());
            System.out.println(response1);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(res.getEntity());      // 返回json格式:
                jsonObject = JSONObject.parseObject(result);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return jsonObject;
    }
}
