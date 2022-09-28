## 沙箱地址
https://open.alipay.com/develop/sandbox/app

## 官方文档
https://opendocs.alipay.com/open/270/01didh

## 支付账号
xjlugv6874@sandbox.com

111111

## natapp地址
官网：https://natapp.cn/

下载：https://pan.baidu.com/s/1L99Ibawylnck4b4c0NtmXg?pwd=685x

## 完整版sdk
```xml
<dependency>
    <groupId>com.alipay.sdk</groupId>
    <artifactId>alipay-sdk-java</artifactId>
    <version>4.22.110.ALL</version>
</dependency>

<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-all</artifactId>
    <version>5.7.20</version>
</dependency>
```

## 在 application.yml 里面进行配置
```yaml
alipay:
    appId: 2016102600763473
    appPrivateKey: MIIEwAIBADANBgkqhkiG9w0BAQEFAASCBKowggSmAgEAAoIBAQDDVLnhmbCGYwRfe+8K0KPVyxeZIXlK095HAW9Aiuq9tb3ikDh4zlxoJzmv+rUZtRQestoo7PCYNUTb2vaDOv9WaJ+vfkPSHl7+rx0PsG0fFPeIRyTHTQtjpLBEq1kuXojcnSCO41rhygmb3MJNYEZTLY+BdeaTMzWLSaL0O/ivmM4OvvhWdEhHUL7kotWL5b+jXAxu4bfJvlijAHt/7M7S3XPs70WfZpv6bYtnsz2tS0J7p3rVXVeYHIClIMpCgDUKzg2+O6CNoQBURLMjUNgcd8dYPU/i3ZbmDN2tAbm4qAaZAFNvvixkiQ8GmbXfQjQdrHRXsndWhaXsrIZrFHTHAgMBAAECggEBAK+QgrZUZkaTzwVnpnZpCykJLv+zB4sLHVli37T6Z3z2UmLGUAu9J29x+jbDN22QxTHGfbGVLbcRAgA+MZ3INGJdDVI2Qg0kwStXB97nK11pggcaMuNzy03XY5uFC1ZTH95y2CW2EiSpbLOL25SiePMgG0E4UHNz7qdwGyg8kwx3zgIKgp4GEbqCwAn+bXyI2svvql53nBzE0x6OPmUNWu+8OuysEtWLwjjDff9ft9pGb4+QUPdxbp0rqE3dEe5FwirsLeEodg2R/Yi3m2192pMyMeLf3rd38trJ8nmb6JlnKGzST23BtEB2ia6gVOtQHS703WrrCx6iICCVUYHwDCECgYEA+oA/rikrDi6pe76rtMZ5rVDRPaha/xfcW9vMLsoRLb7YuEP/BwbmUNq5TtM4BgSCSrTD6XUg0wPSX4BijvgeqFpBJAvQ90kTEfXGZnr2r2f635Bfo02mQr5IhyhPIKMJaM5wQVW1Op+Lx+eaa4j2BwcWNzt7F6AV/qPBwvPYHLECgYEAx55vn7z92fBfa7yNMm3XKNFxXo6uNN70iD5MyzdcTPuqz9R9UlCo1XIJGgV3C1bK4X2f+zFijtm8tqor+Q1N0j1hZKFW/YlF21/FPP6JPjniax/phegRwZ5jEuI/YF44YvkMbO6yPNqxsR+JO9lhgiW/8gnnmVDQIYRNjTfWpvcCgYEAuj6cGgr1vgenbx+mXjOqx9dsmqEPdtXwukNDHg6SkyZvzyCO/lR87OSSHi8gWikEDjMz7eFt1DXlNagonw+PC7B++iPm69Ri31mSdyM5QdTXS1z2Hl5fHQSIvCSWIDfXiRrjj0///GQe8zQZNZaRBUyZkdshe8FEWRy7tQzDQyECgYEAgYYQL7nVjUAm6iUiCQK0hUvBH/W8m5m/WVfzRDjbryftIYVi+7JSmoyv0y6Qm87pPX7h+3+Dz+UAShYJCkTTpgMl2sHFTCVyKnHt7THLo5CzlYbTY4u9WcCH0Iz1SnZYZ//pTBVlmY7dlWw9A5R9bJFKBqbem+CP6++I0oTUxukCgYEApmooMXYoXPIbEHiWxGzUEOFaOsGJaYEOIcJjbUqB+FoS2wFdPdKyomjwOF83+j3orPEL5SSXbMIRyxZNcuJAvXeEwJ6byx6d6txli1vfVfcfgn8YIaCZdYTgRTNGYYC4c6WrQ5Rt1kWO4oTVPPAsk2WT7sD99PpIFer5y3ICbkw=
    alipayPublicKey: MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmydAaVLYbtvWKdaZhN0zDAi8sym6nfCwO18hQfZZOtwIFoR90+CkxNCOtoLOPC3+ksB6wwoqzhn33v4cTkzmHJjhlN9MFVhCYWIw4z0RkjvE1snlMi8F+lynPIv9kRUDnv5N9tabagfmouuhJZ1Ly145yT+2MBOy20Jueaqj01xFak+kzgjqK4K/8Cid4kfLCj7t8btiOFWexfXy5ZLJHOsOvaiyrwkI7+pDe9eKiEwZQ7ixqwO6PSQsVf1swOjZMi30Lj8RcfsfrH9XAx7X2t1Qj945QEcCnT725gBqEJUmUb9bCpD9ioSas/USNqFMgS/iJ8n0gnAK1N8vueSykQIDAQAB
    notifyUrl: http://muaqx9.natappfree.cc/alipay/notify
```


## AlipayConfig.java 

只需要获取配置alipay的参数即可
```java
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "alipay")
public class AliPayConfig {
    private String appId;
    private String appPrivateKey;
    private String alipayPublicKey;
    private String notifyUrl;

}
```

## 参数AliPay.java
```java
import lombok.Data;

@Data
public class AliPay {
    private String traceNo;
    private double totalAmount;
    private String subject;
    private String alipayTraceNo;
}
```

## AliPayController.java
```java
import cn.hutool.json.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.alipay.common.AlipayConfig;
import com.example.alipay.dao.OrdersMapper;
import com.example.alipay.entity.Orders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// xjlugv6874@sandbox.com
// 9428521.24 - 30 = 9428491.24 + 30 = 9428521.24
@RestController
@RequestMapping("/alipay")
public class AliPayController {

    private static final String GATEWAY_URL = "https://openapi.alipaydev.com/gateway.do";
    private static final String FORMAT = "JSON";
    private static final String CHARSET = "UTF-8";
    //签名方式
    private static final String SIGN_TYPE = "RSA2";

    @Resource
    private AlipayConfig aliPayConfig;

    @Resource
    private OrdersMapper ordersMapper;

    @GetMapping("/pay") // &subject=xxx&traceNo=xxx&totalAmount=xxx
    public void pay(AliPay aliPay, HttpServletResponse httpResponse) throws Exception {
        // 1. 创建Client，通用SDK提供的Client，负责调用支付宝的API
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, aliPayConfig.getAppId(),
                aliPayConfig.getAppPrivateKey(), FORMAT, CHARSET, aliPayConfig.getAlipayPublicKey(), SIGN_TYPE);

        // 2. 创建 Request并设置Request参数
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();  // 发送请求的 Request类
        request.setNotifyUrl(aliPayConfig.getNotifyUrl());
        JSONObject bizContent = new JSONObject();
        bizContent.set("out_trade_no", aliPay.getTraceNo());  // 我们自己生成的订单编号
        bizContent.set("total_amount", aliPay.getTotalAmount()); // 订单的总金额
        bizContent.set("subject", aliPay.getSubject());   // 支付的名称
        bizContent.set("product_code", "FAST_INSTANT_TRADE_PAY");  // 固定配置
        request.setBizContent(bizContent.toString());

        // 执行请求，拿到响应的结果，返回给浏览器
        String form = "";
        try {
            form = alipayClient.pageExecute(request).getBody(); // 调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

    @PostMapping("/notify")  // 注意这里必须是POST接口
    public String payNotify(HttpServletRequest request) throws Exception {
        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
            System.out.println("=========支付宝异步回调========");

            Map<String, String> params = new HashMap<>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (String name : requestParams.keySet()) {
                params.put(name, request.getParameter(name));
                // System.out.println(name + " = " + request.getParameter(name));
            }

            String outTradeNo = params.get("out_trade_no");
            String gmtPayment = params.get("gmt_payment");
            String alipayTradeNo = params.get("trade_no");

            String sign = params.get("sign");
            String content = AlipaySignature.getSignCheckContentV1(params);
            boolean checkSignature = AlipaySignature.rsa256CheckContent(content, sign, aliPayConfig.getAlipayPublicKey(), "UTF-8"); // 验证签名
            // 支付宝验签
            if (checkSignature) {
                // 验签通过
                System.out.println("交易名称: " + params.get("subject"));
                System.out.println("交易状态: " + params.get("trade_status"));
                System.out.println("支付宝交易凭证号: " + params.get("trade_no"));
                System.out.println("商户订单号: " + params.get("out_trade_no"));
                System.out.println("交易金额: " + params.get("total_amount"));
                System.out.println("买家在支付宝唯一id: " + params.get("buyer_id"));
                System.out.println("买家付款时间: " + params.get("gmt_payment"));
                System.out.println("买家付款金额: " + params.get("buyer_pay_amount"));

                // 查询订单
                QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("order_id", outTradeNo);
                Orders orders = ordersMapper.selectOne(queryWrapper);

                if (orders != null) {
                    orders.setAlipayNo(alipayTradeNo);
                    orders.setPayTime(new Date());
                    orders.setState("已支付");
                    ordersMapper.updateById(orders);
                }
            }
        }
        return "success";
    }
}
```


## 实现回调功能
- 加配置：alipay.returnUrl=http://localhost:8000/orders
- AlipayConfig.java 加一个属性：returnUrl
- AliPayController.java的pay接口设置回调的url：request.setReturnUrl(aliPayConfig.getReturnUrl());

以上即全部的内容，配置完之后就可以实现了

