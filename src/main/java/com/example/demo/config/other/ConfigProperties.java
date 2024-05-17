package com.example.demo.config.other;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 测试配置逻辑
 */
@Component
@ConfigurationProperties(prefix = "security")
public class ConfigProperties {

    List<String> ignoreUrl;

    public List<String> getIgnoreUrl() {
        return ignoreUrl;
    }

    public void setIgnoreUrl(List<String> ignoreUrl) {
        this.ignoreUrl = ignoreUrl;
    }
}
