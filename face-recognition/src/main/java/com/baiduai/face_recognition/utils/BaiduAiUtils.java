package com.baiduai.face_recognition.utils;

import cn.hutool.core.util.IdUtil;
import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.MatchRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;

@Component
public class BaiduAiUtils {
    @Value("${baiduai.face.appId}")
    private String APP_ID;
    @Value("${baiduai.face.apiKey}")
    private String API_KEY;
    @Value("${baiduai.face.secretKey}")
    private String SECRET_KEY;

    @Value("baiduai.face.groupId")
    private String groupID;
    @Value("baiduai.face.qualityControl")
    private String Quality_Control;
    @Value("baiduai.face.imageType")
    private String Image_Type;
    @Value("baiduai.face.livenessControl")
    private String Liveness_Control;

    private AipFace client;

    private HashMap<String,String> map = new HashMap<>();

    @PostConstruct
    public void initAipFace(){
        client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
    }

    /**
     * 人脸注册
     * @param image base64编码的图片
     * @param userInfo 用户信息（可以为空）
     * @return
     */
    public RespBean faceRegister(String image,String userInfo) {
        String userid = IdUtil.randomUUID().replace("-", "");

        HashMap<String, String> options = new HashMap<>();
        if(StringUtils.hasText(userInfo)){
            options.put("user_info",userInfo);
        }
        JSONObject jsonObject = client.addUser(image, Image_Type, groupID, userid, options);

        return RespBean.ok("ok",jsonObject);
    }

    /**
     * 人脸更新
     * @param userId
     * @param image
     * @param userInfo
     * @return
     */
    public RespBean faceUpdate(String userId,String image,String userInfo){
        HashMap<String, String> options = new HashMap<>();
        if(StringUtils.hasText(userInfo)){
            options.put("user_info",userInfo);
        }
        JSONObject jsonObject = client.updateUser(image, Image_Type, groupID, userId, options);

        return RespBean.ok("ok",jsonObject);
    }

    /**
     * 人脸删除
     * @param userId
     * @return
     */
    public RespBean faceDelete(String userId){
        JSONObject jsonObject = client.deleteUser(groupID, userId, null);
        return RespBean.ok("ok",jsonObject);
    }

    /**
     * 人脸搜索
     * @param imagebase64
     * @return
     */
    public RespBean faceSearch(String imagebase64){
        JSONObject jsonObject = client.search(imagebase64, Image_Type,groupID, null);

        return RespBean.ok("ok",jsonObject);
    }

    /**
     * 人脸对比
     * @param imagebase64
     * @return
     */
    public RespBean faceMatch(String imagebase64){
        MatchRequest matchRequest = new MatchRequest(imagebase64, Image_Type);
        JSONObject jsonObject = client.match(Arrays.asList(matchRequest));

        return RespBean.ok("ok",jsonObject);
    }

    /**
     * 人脸检测
     * @param imagebase64
     * @return
     */
    public RespBean faceDetect(String imagebase64){
        JSONObject jsonObject = client.detect(imagebase64, Image_Type, null);

        return RespBean.ok("ok",jsonObject);
    }
}
