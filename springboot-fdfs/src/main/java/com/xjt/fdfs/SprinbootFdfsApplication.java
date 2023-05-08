package com.xjt.fdfs;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

// 解决jmx重复注册bean的问题
@EnableMBeanExport(registration= RegistrationPolicy.IGNORE_EXISTING)
@Import(FdfsClientConfig.class)
@SpringBootApplication
@MapperScan("com.xjt.fdfs.mapper")
public class SprinbootFdfsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SprinbootFdfsApplication.class, args);
    }

}
