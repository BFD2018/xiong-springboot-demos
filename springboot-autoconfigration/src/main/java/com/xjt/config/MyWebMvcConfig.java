package com.xjt.config;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

import static com.alibaba.fastjson.serializer.SerializerFeature.*;

@Configuration
public class MyWebMvcConfig extends WebMvcConfigurerAdapter implements EnvironmentAware {
    // 当前的环境对象
    protected Environment environment;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        //1.需要先定义一个 convert 转换消息的对象;
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        //2、添加fastJson 的配置信息，比如：是否要格式化返回的json数据;
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        // 不忽略对象属性中的null值
        fastJsonConfig.setSerializerFeatures(
                PrettyFormat,
                WriteNullListAsEmpty,
                WriteNullStringAsEmpty);
        //3、在convert中添加配置信息.
        fastConverter.setFastJsonConfig(fastJsonConfig);
        //4、将convert添加到converters当中.
        converters.add(fastConverter);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
