package com.xjt.fdfs.config;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

@Configuration
@Import(FdfsClientConfig.class)
@EnableMBeanExport(registration= RegistrationPolicy.IGNORE_EXISTING)
public class FastClient {       //类名无特定限制

}
