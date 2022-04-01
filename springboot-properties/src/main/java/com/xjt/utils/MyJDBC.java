package com.xjt.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = {"classpath:props/datasource.properties"})
@ConfigurationProperties(prefix = "xxxmysql.datasource")
@Data
public class MyJDBC {
    private String driver;
    private String url;
    private String username;
    private String password;
}
