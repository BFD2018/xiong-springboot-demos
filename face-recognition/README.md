目前存在的问题：

BASE64编码字符串太长了，

解决方案：将图片传入到aliyun-oss 获得URL，以URL方式上传图片

```java
@Test
void test02(){
    AipFace client = new AipFace("15391850", "FVnP4fAmGymppljHZZ6leenG", "YT1BH5yETk370RoR85xuNoFjiT6bB56Q");
    JSONObject jsonObject = client.search("https://xiong-test-srt.oss-cn-shenzhen.aliyuncs.com/typoraimages/20220629223736.png", "URL","face_recognition_test", null);
    System.out.println(jsonObject);
}
```

