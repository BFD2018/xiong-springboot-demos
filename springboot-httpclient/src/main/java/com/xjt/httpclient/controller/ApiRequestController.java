package com.xjt.httpclient.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 第三方API接口调用
 */
@Controller
@RequestMapping("/smms")
public class ApiRequestController {
    //创建连接池管理器
    PoolingHttpClientConnectionManager cm = null;
    CloseableHttpClient httpClient = null;

    {
        //创建连接池管理器
        cm = new PoolingHttpClientConnectionManager();
        //设置最大的连接数
        cm.setMaxTotal(200);
        //设置每个主机的最大连接数，访问每一个网站指定的连接数，不会影响其他网站的访问
        cm.setDefaultMaxPerRoute(20);
        //使用连接池管理器获取连接并发起请求
        httpClient = HttpClients.custom().setConnectionManager(cm).build();
    }


    @PostMapping("/upload")
    @ResponseBody
    public String uploadImg(@RequestParam("smfile") List<MultipartFile> multipartFiles) throws IOException {
        //CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("https://sm.ms/api/v2/upload");
        CloseableHttpResponse response = null;
        String responseStr = "";
        try {
            httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.54 Safari/537.36");
            httpPost.setHeader("referer", "https://sm.ms/");
            httpPost.setHeader("Authorization", "CWQjdeMixpUUeSeu4f0tg98OeaTZuToD");
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();

            for (MultipartFile multipartFile : multipartFiles) {
                multipartEntityBuilder.addBinaryBody("smfile", multipartFile.getInputStream(), ContentType.MULTIPART_FORM_DATA, URLEncoder.encode(multipartFile.getOriginalFilename(), StandardCharsets.UTF_8));
            }


            HttpEntity httpEntity = multipartEntityBuilder.build();
            httpPost.setEntity(httpEntity);

            response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();

            responseStr = EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);
            System.out.println(responseStr);

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        } finally {
            HttpClientUtils.closeQuietly(response);
        }

        return responseStr;
    }

    @GetMapping("/upload_history")
    @ResponseBody
    public Object getImgUploadHistory() {
        System.out.println("httpClient----------->");
        System.out.println(httpClient);

        //CloseableHttpClient httpClient = HttpClientBuilder.create().build();;
        HttpGet httpGet = new HttpGet("https://sm.ms/api/v2/upload_history");
        CloseableHttpResponse response = null;

        String ret = "";

        try {
            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.54 Safari/537.36");
            httpGet.setHeader("referer", "https://sm.ms/");
            httpGet.setHeader("Authorization", "CWQjdeMixpUUeSeu4f0tg98OeaTZuToD");
            httpGet.setHeader("Content-Type", "multipart/form-data");

            // 设置配置请求参数
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 连接主机服务超时时间
                    .setConnectionRequestTimeout(35000)// 请求超时时间
                    .setSocketTimeout(60000)// 数据读取超时时间
                    .build();
            // 为httpGet实例设置配置
            httpGet.setConfig(requestConfig);

            response = httpClient.execute(httpGet);

            ret = EntityUtils.toString(response.getEntity(), "utf-8");

            System.out.println(ret);
            System.out.println(JSONObject.parse(ret));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            HttpClientUtils.closeQuietly(response);
        }

        return ret;
    }


    @GetMapping("/delete")
    @ResponseBody
    public Object deleteImage(@RequestParam("imghash") String imghash) {
        //CloseableHttpClient httpClient = HttpClientBuilder.create().build();;
        HttpGet httpGet = new HttpGet("https://sm.ms/api/v2/delete/" + imghash);
        CloseableHttpResponse response = null;

        String ret = "";

        try {
            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.54 Safari/537.36");
            httpGet.setHeader("referer", "https://sm.ms/");
            httpGet.setHeader("Authorization", "CWQjdeMixpUUeSeu4f0tg98OeaTZuToD");

            response = httpClient.execute(httpGet);

            ret = EntityUtils.toString(response.getEntity(), "utf-8");

            System.out.println(ret);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            HttpClientUtils.closeQuietly(response);
        }

        return ret;
    }
}
