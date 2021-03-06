package com.xjt.httpclient;

import com.alibaba.fastjson.JSONObject;
import com.xjt.httpclient.utils.MyHttpClientUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class HttpclientDemoApplicationTests {

    @Test
    void contextLoads() throws URISyntaxException, IOException {
        // 1.??????HttpClient??????
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // ?????????????????????: http://yun.itheima.com/search?keys=Java
        // ??????uriBuilder
        URIBuilder uriBuilder = new URIBuilder("http://yun.itheima.com/search");
        // ?????????????????????????????????
        uriBuilder.setParameter("keys","Java");

        // 2.??????HttpGet??????, ??????url???????????????
        HttpGet httpGet = new HttpGet(uriBuilder.build());

        System.out.println("????????????????????????");

        // 3.?????? HttpClient ????????????, ?????? response
        CloseableHttpResponse response = httpClient.execute(httpGet);
        // 4.????????????
        if (response.getStatusLine().getStatusCode() == 200){
            String content = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(content.length());
            System.out.println(content);
        }
    }

    @Test
    public void testPost(){
        // 1.??????HttpClient??????
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 2.??????HttpPost??????, ??????url????????????
        HttpPost httpPost = new HttpPost("http://www.itcast.cn");


        // 3.?????? HttpClient ????????????, ?????? response
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            // 4.????????????
            if (response.getStatusLine().getStatusCode() == 200){
                String content = EntityUtils.toString(response.getEntity(), "utf-8");
                System.out.println(content.length());
                System.out.println(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            // 5.????????????
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test03(){
        HashMap<String, String> headers = new HashMap<>();
        headers.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.54 Safari/537.36");
        headers.put("Referer","https://api.vvhan.com/");
        headers.put("Cookie","__51vcke__JLvourAu9qIUtWYT=63db2934-cd21-5322-8a7f-d5565a8d2dd1; __51vuft__JLvourAu9qIUtWYT=1652106097504; __gads=ID=22807b6ec3dfc152-22ba0bc61dd300b4:T=1652106153:RT=1652106153:S=ALNI_Ma6JbUrrd1xAqGABqjk9ISAs3ii5A; __vtins__JLvourAu9qIUtWYT=%7B%22sid%22%3A%20%22402fb187-a4ff-5faf-9152-21b1bfa1ba70%22%2C%20%22vd%22%3A%201%2C%20%22stt%22%3A%200%2C%20%22dr%22%3A%200%2C%20%22expires%22%3A%201652195504298%2C%20%22ct%22%3A%201652193704298%7D; __51uvsct__JLvourAu9qIUtWYT=4");

        HashMap<String, String> params = new HashMap<>();
        params.put("type", "zhihuHot");

        String ret = MyHttpClientUtils.sendGet("https://api.vvhan.com/api/hotlist", params, headers);
        System.out.println(ret);
    }

    /**
     * post???????????????
     */
    @Test
    public void test05() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://localhost:8080/api/upload/img");
//        HttpPost httpPost = new HttpPost("https://sm.ms/api/v2/upload");
        CloseableHttpResponse httpResponse = null;
        try {
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();

            //RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(200000).setSocketTimeout(20000).build();
//            httpPost = new HttpPost("https://sm.ms/api/v2/upload");

            httpPost.setHeader("Content-Type","multipart/form-data");
            httpPost.setHeader("Authorization","CWQjdeMixpUUeSeu4f0tg98OeaTZuToD");
            //httpPost.setConfig(requestConfig);

            File file = new File("D:\\??????\\logo_xiong.png");
            HttpEntity httpEntity = multipartEntityBuilder.addBinaryBody("smfile", file, ContentType.MULTIPART_FORM_DATA, URLEncoder.encode(file.getName(), "utf-8")).build();

//            File file = new File("D:\\??????\\logo_xiong.png");
//            multipartEntityBuilder.addBinaryBody("smfile",file);
//            multipartEntityBuilder.addTextBody("format", "json");
//            multipartEntityBuilder.addTextBody("username", "xiongjt");
//            multipartEntityBuilder.addTextBody("password", "xr112358");
//            HttpEntity httpEntity = multipartEntityBuilder.build();

            httpPost.setEntity(httpEntity);

            httpResponse = httpClient.execute(httpPost);
            HttpEntity responseEntity = httpResponse.getEntity();
            System.out.println(EntityUtils.toString(responseEntity, StandardCharsets.UTF_8));

//        int statusCode= httpResponse.getStatusLine().getStatusCode();
//        if(statusCode == 200){
//            String ret = EntityUtils.toString(responseEntity);
//
//            System.out.println(ret);
//        }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpResponse != null) {
                httpResponse.close();
            }

            if (httpPost!= null){
                httpPost.releaseConnection();
            }

            if (httpClient != null){
                httpClient.close();
            }
        }
    }

    /**
     * ????????????
     * multipart/form-data????????????(???????????????)
     * ???:????????????????????????????????????????????????
     *    ????????????org.apache.httpcomponents?????????httpclient?????????
     *    ?????????org.apache.httpcomponents???httpmime?????????
     *    ??????:???????????????httpmime???????????????????????????????????????????????????????????????
     */
    @Test
    public void test4() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://localhost:8080/api/testupload");
        CloseableHttpResponse response = null;
        try {
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            // ???????????????
            String filesKey = "files";
            multipartEntityBuilder.addBinaryBody(filesKey, new File("D:\\??????\\5.jpg"));
            // ???????????????(????????????????????????????????????key???????????????????????????????????????????????????)
            File file2 = new File("D:\\??????\\2.jpg");
            // ?????????????????????????????????????????? ?????????????????????????????????URLEncode???????????????????????????????????????URLDecode??????????????????????????????
            // ????????????????????????????????????Content-Disposition???????????????????????????????????? form-data; name="files"; filename="??????.jpg"
            multipartEntityBuilder.addBinaryBody(filesKey, file2, ContentType.DEFAULT_BINARY, URLEncoder.encode(file2.getName(), "utf-8"));
            // ????????????(???:?????????contentType?????????UTF-8???????????????????????????????????????????????????)
            ContentType ct = ContentType.create("text/plain", Charset.forName("UTF-8"));
            multipartEntityBuilder.addTextBody("name", "????????????", ct);
            multipartEntityBuilder.addTextBody("age", "25", ct);

            HttpEntity httpEntity = multipartEntityBuilder.build();
            httpPost.setEntity(httpEntity);

            response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            System.out.println("HTTPS???????????????:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("HTTPS?????????????????????:" + responseEntity.getContentLength());
                // ??????????????????????????????????????????
                String responseStr = EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);
                System.out.println("HTTPS???????????????:" + responseStr);
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // ????????????
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * ??????????????????  https://sm.ms/api/v2/upload
     */
    @Test
    public void test5() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("https://sm.ms/api/v2/upload");
        CloseableHttpResponse response = null;
        try {
            httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.54 Safari/537.36");
            httpPost.setHeader("referer","https://sm.ms/");
            httpPost.setHeader("Authorization","CWQjdeMixpUUeSeu4f0tg98OeaTZuToD");
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            multipartEntityBuilder.addBinaryBody("smfile", new File("D:\\??????\\??????.png"));

            HttpEntity httpEntity = multipartEntityBuilder.build();
            httpPost.setEntity(httpEntity);

            response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();

            System.out.println("HTTPS???????????????:" + response.getStatusLine());
            System.out.println(response.getStatusLine().getStatusCode());
            String responseStr = EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);
            System.out.println(responseStr);

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // ????????????
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * ??????????????????  https://sm.ms/api/v2/upload_history
     */
    @Test
    public void testUploadHistory(){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();;
        HttpGet httpGet = new HttpGet("https://sm.ms/api/v2/upload_history");
        CloseableHttpResponse response = null;

        try {
            httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.54 Safari/537.36");
            httpGet.setHeader("referer","https://sm.ms/");
            httpGet.setHeader("Authorization","CWQjdeMixpUUeSeu4f0tg98OeaTZuToD");
            httpGet.setHeader("Content-Type","multipart/form-data");

            response = httpClient.execute(httpGet);

            String result = EntityUtils.toString(response.getEntity(),"utf-8");

            System.out.println(result);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                // ????????????
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * ??????????????????  https://sm.ms/api/v2//delete/:hash   ??????hash???
     */
    @Test
    public void testDelete(){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();;
        HttpGet httpGet = new HttpGet("https://sm.ms/api/v2/delete/zwhnYW2XeuxIdaSQqvpDbKisoZ");
        CloseableHttpResponse response = null;

        try {
            httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.54 Safari/537.36");
            httpGet.setHeader("referer","https://sm.ms/");
            httpGet.setHeader("Authorization","CWQjdeMixpUUeSeu4f0tg98OeaTZuToD");

            response = httpClient.execute(httpGet);

            String result = EntityUtils.toString(response.getEntity(),"utf-8");

            System.out.println(result);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                // ????????????
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
