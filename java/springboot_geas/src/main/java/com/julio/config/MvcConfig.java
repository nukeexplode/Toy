package com.julio.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Julio
 * @date 2020/3/14-11:50
 **/
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/ppsdFile/**").addResourceLocations("file:D:/ppsdFile/");//配置本地文件资源路径映射
//        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:10888") //调用者端口号
//                .allowedMethods("GET","HEAD","POST","PUT","DELETE","OPTIONS")
//                .allowCredentials(true)   //开启跨域
//                .allowedHeaders("*")
//                .maxAge(3600);
//    }
}
