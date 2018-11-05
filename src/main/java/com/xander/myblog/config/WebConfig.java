package com.xander.myblog.config;

import com.xander.myblog.interceptor.LoginInterceptor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Xander on 2018-11-05.
 */
@Configuration
@EnableConfigurationProperties({LoginInterceptorProperties.class})
public class WebConfig implements WebMvcConfigurer {
    private LoginInterceptor loginInterceptor;
    private LoginInterceptorProperties loginInterceptorProperties;

    WebConfig(LoginInterceptor loginInterceptor, LoginInterceptorProperties loginInterceptorProperties) {
        this.loginInterceptor = loginInterceptor;
        this.loginInterceptorProperties = loginInterceptorProperties;
    }

    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(this.loginInterceptor).addPathPatterns(this.loginInterceptorProperties.getIncludePatterns())
                .excludePathPatterns(this.loginInterceptorProperties.getExcludePatterns());
    }
}