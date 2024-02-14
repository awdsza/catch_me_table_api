package com.app.catchmetable.config;

import com.app.catchmetable.converter.StringToEnumConverterFactory;
import com.app.catchmetable.interceptor.AuthcheckInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${external.interceptors.exclude.patterns}")
    private String excludePatterns;
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new StringToEnumConverterFactory());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludeList = Arrays.asList(excludePatterns.split(","));
        registry.addInterceptor(new AuthcheckInterceptor())
        .excludePathPatterns(excludeList);

    }
}
