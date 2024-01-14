package com.baiduai.face_recognition.utils;

import cn.hutool.core.util.IdUtil;
import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.MatchRequest;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;

@Component
public class BaiduAiUtils {
    private String APP_ID = "15391850";
    private String API_KEY = "FVnP4fAmGymppljHZZ6leenG";
    private String SECRET_KEY = "YT1BH5yETk370RoR85xuNoFjiT6bB56Q";

    private String groupID = "face_recognition_test";

    private String Quality_Control = "NONE";
    private String Image_Type = "BASE64";
    private String Liveness_Control = "NONE";

    private AipFace client;


    @PostConstruct
    public void initAipFace() {
        client = new AipFace("15391850", "FVnP4fAmGymppljHZZ6leenG", "YT1BH5yETk370RoR85xuNoFjiT6bB56Q");
    }

    /**
     * 人脸注册
     *
     * @param image    base64编码的图片
     * @param userInfo 用户信息（可以为空）
     * @return
     */
    public RespBean faceRegister(String image, String userInfo) {
        String userid = IdUtil.randomUUID().replace("-", "");

        HashMap<String, String> options = new HashMap<>();
        if (StringUtils.hasText(userInfo)) {
            options.put("user_info", userInfo);
        }
        JSONObject jsonObject = client.addUser(image, Image_Type, groupID, userid, options);

        return RespBean.ok("ok", jsonObject.toString());
    }

    /**
     * 人脸更新
     *
     * @param userId
     * @param image
     * @param userInfo
     * @return
     */
    public RespBean faceUpdate(String userId, String image, String userInfo) {
        HashMap<String, String> options = new HashMap<>();
        if (StringUtils.hasText(userInfo)) {
            options.put("user_info", userInfo);
        }
        JSONObject jsonObject = client.updateUser(image, Image_Type, groupID, userId, options);

        return RespBean.ok("ok", jsonObject.toString());
    }

    /**
     * 人脸删除
     *
     * @param userId
     * @return
     */
    public RespBean faceDelete(String userId) {
        JSONObject jsonObject = client.deleteUser(groupID, userId, null);
        return RespBean.ok("ok", jsonObject.toString());
    }

    /**
     * 人脸搜索
     *
     * @param imagebase64
     * @return
     */
    public RespBean faceSearch(String imagebase64) {
        imagebase64 = imagebase64.substring(imagebase64.indexOf(",") + 1, imagebase64.length());

        JSONObject jsonObject = client.search(imagebase64, Image_Type, groupID, null);
        System.out.println("imagebase64----------->");
        System.out.println(imagebase64);
        System.out.println("jsonObject----------->");
        System.out.println(jsonObject);

        return RespBean.ok("ok", jsonObject.toString());
    }

    public RespBean faceSearch(String imgUrl, String image_Type) {
        JSONObject jsonObject = client.search(imgUrl, image_Type, groupID, null);
        System.out.println("jsonObject----------->");
        System.out.println(jsonObject);

        return RespBean.ok("ok", jsonObject.toString());
    }

    /**
     * 人脸对比
     *
     * @param imagebase64
     * @return
     */
    public RespBean faceMatch(String imagebase64) {
        MatchRequest matchRequest = new MatchRequest(imagebase64, Image_Type);
        JSONObject jsonObject = client.match(Arrays.asList(matchRequest));

        return RespBean.ok("ok", jsonObject.toString());
    }

    /**
     * 人脸检测
     *
     * @param imagebase64
     * @return
     */
    public RespBean faceDetect(String imagebase64) {
        JSONObject jsonObject = client.detect(imagebase64, Image_Type, null);
        return RespBean.ok("ok", jsonObject.toString());
    }
}
