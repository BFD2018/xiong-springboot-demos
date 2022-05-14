package com.xjt.httpclient.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyHttpClientUtils {
    /**
     * get请求
     * @param url 请求地址
     * @return
     */
    public static String sendGet(String url){
        CloseableHttpClient httpClient = null;
        HttpGet httpGet = null;
        CloseableHttpResponse response = null;
        String result = "";

        try {
            httpClient = HttpClientBuilder.create().build();
            httpGet = new HttpGet(url);
            response = httpClient.execute(httpGet);
            result = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            close(response,httpGet,httpClient);
        }
        return result;
    }

    /**
     * get请求
     * @param url 请求地址
     * @param params 请求参数（以 & 连接）  https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&wd=httpClient
     * @param headers 请求头
     * @return
     */
    public static String sendGet(String url, Map<String,String> params,Map<String,String> headers){
        CloseableHttpClient httpClient = null;
        HttpGet httpGet = null;
        CloseableHttpResponse response = null;
        String result = "";

        try {
            httpClient = HttpClientBuilder.create().build();
            response.getEntity();
            // 设置请求头
            if(null != headers && headers.size() > 0){
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    httpGet.setHeader(key,value);
                }
            }
            // 设置参数
            if (null != params && params.size() > 0){
                List<BasicNameValuePair> pairList = new ArrayList<>();
                params.forEach((x,y) -> pairList.add(new BasicNameValuePair(x,y)));
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(pairList,"utf-8");
                // 将参数转成page=1&limit=5格式
                String param = EntityUtils.toString(urlEncodedFormEntity, "utf-8");
                httpGet = new HttpGet(url+"?"+param);
            }
            response = httpClient.execute(httpGet);
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity,"utf-8");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            close(response,httpGet,httpClient);
        }
        return result;
    }

    public static String sendGet(String url, HashMap<String, String> headers){
        CloseableHttpClient httpClient = null;
        HttpGet httpGet = null;
        CloseableHttpResponse response = null;
        String result = "";

        try {
            httpClient = HttpClientBuilder.create().build();
            response.getEntity();
            // 设置请求头
            if(null != headers && headers.size() > 0){
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    httpGet.setHeader(key,value);
                }
            }

            response = httpClient.execute(httpGet);
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                result = EntityUtils.toString(response.getEntity(),"utf-8");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            close(response,httpGet,httpClient);
        }
        return result;
    }

    public static String sendPost(String url, Map<String,String> params,Map<String,String> headers){
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = null;
        CloseableHttpResponse response = null;
        String result = "";
        try {
            httpClient = HttpClientBuilder.create().build();
            httpPost = new HttpPost(url);
            // 设置请求头
            if(null != headers && headers.size() > 0){
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    httpPost.setHeader(key,value);
                }
            }
            // 设置参数
            if (null != params && params.size() > 0){
                List<NameValuePair> pairList = new ArrayList<>();

                // 注意BasicNameValuePair是NameValuePair唯一的实现类
                pairList.add(new BasicNameValuePair("keys","Java"));

                // 创建表单的Entity对象, 第一个参数就是封装好的表单数据, 第二个参数就是编码
                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(pairList,"utf-8");
                params.forEach((x,y) -> pairList.add(new BasicNameValuePair(x,y)));
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(pairList,"utf-8");
                httpPost.setEntity(urlEncodedFormEntity);
            }
            response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            close(response,httpPost,httpClient);
        }
        return result;
    }

    /**
     * 向sm.ms上传图片
     * @param url
     * @param params
     * @param headers
     * @param multipartFiles
     * @return
     */
    public static CloseableHttpResponse sendPostFiles(String url, Map<String,String> params, Map<String,String> headers, MultipartFile[] multipartFiles){
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = null;
        CloseableHttpResponse response = null;
        try {
            
            httpClient = HttpClientBuilder.create().build();
            httpPost = new HttpPost(url);
            // 设置请求头
            if(null != headers && headers.size() > 0){
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    httpPost.setHeader(key,value);
                }
            }
            // 设置参数
            if (null != params && params.size() > 0){
                List<BasicNameValuePair> pairList = new ArrayList<>();
                params.forEach((x,y) -> pairList.add(new BasicNameValuePair(x,y)));
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(pairList,"utf-8");
                httpPost.setEntity(urlEncodedFormEntity);
            }

            //文件
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            for (MultipartFile file : multipartFiles) {
                multipartEntityBuilder.addBinaryBody("smfile", (File) file);
            }

            HttpEntity httpEntity = multipartEntityBuilder.build();
            httpPost.setEntity(httpEntity);

            response = httpClient.execute(httpPost);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            close(response,httpPost,httpClient);
        }
        return response;
    }

    public static String sendPostJson(String url, Map<String,String> params){
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = null;
        CloseableHttpResponse response = null;
        String result = "";
        try {
            httpClient = HttpClientBuilder.create().build();
            httpPost = new HttpPost(url);
            // 设置参数
            if (null != params && params.size() > 0){
                String paramJson = JSONObject.toJSONString(params);
                StringEntity stringEntity = new StringEntity(paramJson,"utf-8");
                stringEntity.setContentType("application/json;charset=utf-8");
                httpPost.setEntity(stringEntity);
            }
            response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            close(response,httpPost,httpClient);
        }
        return result;
    }

    public static void close(CloseableHttpResponse response, HttpRequestBase httpRequestBase, CloseableHttpClient httpClient){
        try {
            if (response != null) {
                response.close();
            }

            if (httpRequestBase!= null){
                httpRequestBase.releaseConnection();
            }

            if (httpClient != null){
                httpClient.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
