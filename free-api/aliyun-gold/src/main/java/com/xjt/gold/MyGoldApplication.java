package com.xjt.gold;

import com.alibaba.fastjson.JSONObject;
import com.xjt.gold.config.HttpUtils;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyGoldApplication {
    public static void main(String[] args) {
        String host = "https://jisugold.market.alicloudapi.com";
        String path = "/gold/shgold";
        String method = "GET";
        String appcode = "e14279f5e73d4381bcf479c237e0c1f6";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            org.apache.http.HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
            JSONObject jsonObject = JSONObject.parseObject(result);
            JSONObject result1 = JSONObject.parseObject(jsonObject.get("result").toString());
            HashMap hashMap1 = JSONObject.parseObject(result1.toJSONString(), HashMap.class);
            HashMap hashMap = JSONObject.parseObject(result, HashMap.class);
            System.out.println(hashMap);
            System.out.println(hashMap.get("result"));
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity(),"utf-8"));

//            hashMap.get("result")
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
