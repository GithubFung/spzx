package com.spzx.manager.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置跨域请求
 * Created by 小冯 on 2023/11/7 22:59
 */
@Component
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")      //添加路由规则
                .allowCredentials(true)         //是否允许在跨域的情况下传递Cookie
                .allowedOriginPatterns("*")     //允许请求来源的域规则
                .allowedHeaders("*");
    }
}
