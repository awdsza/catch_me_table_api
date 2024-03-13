package com.app.catchmetable.interceptor;

import com.app.catchmetable.service.RedisService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
public class AuthcheckInterceptor implements HandlerInterceptor {
    private final RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        String requestMethod = request.getMethod();
        HttpSession session = request.getSession();
        if((requestURI.contains("restaurants") && requestMethod.equalsIgnoreCase("POST"))
        || (requestURI.contains("members") && requestMethod.equalsIgnoreCase("POST"))){
            return true;
        }
        return session.getAttribute("userInfo") != null;
    }
}
