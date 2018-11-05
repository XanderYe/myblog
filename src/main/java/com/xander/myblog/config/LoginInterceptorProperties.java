package com.xander.myblog.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Xander on 2018-11-05.
 */
@ConfigurationProperties(prefix = "com.xander.myblog.login-interceptor")
public class LoginInterceptorProperties {
    private List<String> includePatterns;
    private List<String> excludePatterns;

    public List<String> getIncludePatterns() {
        return this.includePatterns;
    }

    public List<String> getExcludePatterns() {
        return this.excludePatterns;
    }


    public void setIncludePatterns(List<String> includePatterns) {
        this.includePatterns = includePatterns;
    }

    public void setExcludePatterns(List<String> excludePatterns) {
        this.excludePatterns = excludePatterns;
    }

}