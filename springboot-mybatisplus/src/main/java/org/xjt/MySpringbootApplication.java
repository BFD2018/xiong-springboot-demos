package org.xjt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("org.xjt.mapper")
@SpringBootApplication
public class MySpringbootApplication {
    public static void main( String[] args ) {
        SpringApplication.run(MySpringbootApplication.class,args);
    }
}
