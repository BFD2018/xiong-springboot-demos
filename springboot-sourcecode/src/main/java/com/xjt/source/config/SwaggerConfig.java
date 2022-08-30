package com.xjt.source.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2        //开启swagger2
public class SwaggerConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 解决静态资源无法访问
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");

        // 解决swagger无法访问
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        // 解决swagger的js文件无法访问
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(xiaomingApiInfo())
                .groupName("xiaoxiong")
                .select()
                //为任何请求处理生成API文档
                .apis(RequestHandlerSelectors.any())
                //过滤路径
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo xiaomingApiInfo() {
        //作者联系方式
        Contact contact = new Contact("xiaoxiong", "https://github.com/BFD2018", "1351655382@qq.com");
        return new ApiInfoBuilder()
                .title("xiong-SwaggerUI演示")
                .description("接口文档，描述词省略200字")
                .contact(contact)
                .version("v1.0")
                .build();
    }
}
