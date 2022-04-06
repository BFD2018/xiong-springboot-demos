package com.xjt;

import com.xjt.config.JavaConfig;
import com.xjt.domain.Food;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

//@SpringBootApplication
public class SpringbootConditionalApplication {

    public static void main(String[] args) {
//        SpringApplication.run(SpringbootConditionalApplication.class, args);

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        Map<String, Object> systemProperties = ctx.getEnvironment().getSystemProperties();
        System.out.println("systemProperties------------>");
        System.out.println(systemProperties);

        ctx.getEnvironment().getSystemProperties().put("people", "南方人");
        ctx.register(JavaConfig.class);
        ctx.refresh();
        Food food = (Food) ctx.getBean("food");
        System.out.println(food.showName());
    }

}
