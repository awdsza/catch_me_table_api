package com.app.catchmetable.config;

import com.app.catchmetable.converter.StringToEnumConverterFactory;
import com.app.catchmetable.interceptor.AuthcheckInterceptor;
import com.app.catchmetable.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final RedisService redisService;
    @Value("${external.interceptors.exclude.patterns}")
    private String excludePatterns;
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new StringToEnumConverterFactory());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludeList = Arrays.asList(excludePatterns.split(","));
        registry.addInterceptor(new AuthcheckInterceptor(redisService))
        .excludePathPatterns(excludeList);

    }
}
