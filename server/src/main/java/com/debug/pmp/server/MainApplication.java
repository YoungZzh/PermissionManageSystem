package com.debug.pmp.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Author:Young
 * Date:2020/6/23-16:12
 */
@SpringBootApplication
@MapperScan(basePackages = "com.debug.pmp.model.mapper")
public class MainApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return super.configure(builder);
    }

    public static void main(String[] args) throws Exception{
        ConfigurableApplicationContext run = new SpringApplication().run(MainApplication.class,args);
    }
}
