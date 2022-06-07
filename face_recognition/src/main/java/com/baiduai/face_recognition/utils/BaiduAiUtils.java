package com.baiduai.face_recognition.utils;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.face.AipFace;
import com.baiduai.face_recognition.config.BaiduFaceAIConfig;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BaiduAiUtils {
    @Autowired
    public BaiduFaceAIConfig baiduFaceAIConfig;

    private AipFace client;

    private HashMap<String,String> map = new HashMap<>();

    private String token = "";

    public BaiduAiUtils(){
        map.put("quality_control","NORMAL");//图片质量
        map.put("liveness_control","LOW");//活体检测
    }

    /**
     * 获取API访问token
     * 该token有一定的有效期，需要自行管理，当失效时需重新获取.
     *
     * @return assess_token 示例：
     * "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567"
     */
    public void getAuth() {
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + "FVnP4fAmGymppljHZZ6leenG"
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + "YT1BH5yETk370RoR85xuNoFjiT6bB56Q";
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.err.println(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            /**
             * 返回结果示例
             */
            System.err.println("result:" + result);
            JSONObject jsonObject = new JSONObject(result);
            if(jsonObject.getString("access_token") != null && jsonObject.getString("access_token") != ""){
                token = jsonObject.getString("access_token");
            }else{
                token = jsonObject.getString("refresh_token");
            }

            System.out.println(token);

        } catch (Exception e) {
            System.err.printf("获取token失败！");
            e.printStackTrace(System.err);
        }
    }

    //人脸注册
    public String faceRegister(String image) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add";
        String userid = UUID.randomUUID().toString().replace("-", "");

        try {
            map.put("image", image);
            map.put("image_type", "BASE64");
            map.put("group_id", "face_recognition_test");
            map.put("user_id", userid);

            String param = JSON.toJSONString(map);
            System.out.println("param===>" + param);
            System.out.println("token===>" + token);

            String result = HttpUtil.post(url, token, "application/json", param);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String faceSearch(String image) {
        getAuth();

        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/search";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", image);
            map.put("liveness_control", "NORMAL");
            map.put("group_id_list", "face_recognition_test");
            map.put("image_type", "BASE64");
            map.put("quality_control", "LOW");

            String param = GsonUtils.toJson(map);

            String result = HttpUtil.post(url, token, "application/json", param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
