package org.xjt;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.util.List;

@SpringBootTest
class SpringbootApplicationTests {

    @Test
    void contextLoads() throws FileNotFoundException {
        String property = System.getProperty("user.dir") + "\\src\\main\\resources\\myupload" ;
        System.out.println(property);

        String basePath= ClassUtils.getDefaultClassLoader().getResource("").getPath();
        System.out.println(basePath);

        String path = ResourceUtils.getURL("classpath:").getPath();
        System.out.println(path);
    }

}
