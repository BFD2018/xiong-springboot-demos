package com.xjt.httpclient.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 上传图片到SM.MS图床
 */
@RestController
@RequestMapping("rest")
public class RestTemplateController {
    /*
        {
        "args": {},
        "data": "{\"name\":\"xiong\",\"addr\":\"shenzhen\",\"age\":\"18\"}",
        "files": {},
        "form": {},
        "headers": {
            "Accept": "application/json, application/*+json",
            "Content-Length": "45",
            "Content-Type": "application/json",
            "Host": "www.httpbin.org",
            "User-Agent": "Java/14.0.1",
            "X-Amzn-Trace-Id": "Root=1-65e467b3-7c0ac27f709241bf61d48c44"
        },
        "json": {
            "addr": "shenzhen",
            "age": "18",
            "name": "xiong"
        },
        "origin": "205.185.221.206",
        "url": "https://www.httpbin.org/post"
    }
    * */

    @GetMapping("/post")
    public Object toPost(){
        RestTemplate restTemplate=new RestTemplate();

        Map<String,String> data = new HashMap<>();
        data.put("name","xiong");
        data.put("age","18");
        data.put("addr","shenzhen");

        ResponseEntity<Map> responseEntity = restTemplate.postForEntity("https://www.httpbin.org/post", data, Map.class);

        Map body = responseEntity.getBody();

        return body;
    }

    @GetMapping("/get")
    public Map toGet(){
        RestTemplate restTemplate=new RestTemplate();

        ResponseEntity<Map> responseEntity = restTemplate.getForEntity("https://www.httpbin.org/get", Map.class);
        return responseEntity.getBody();
    }
}
