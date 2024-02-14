package com.app.catchmetable.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthcheckInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        String requestMethod = request.getMethod();
        HttpSession session = request.getSession(/**/);
        if((requestURI.contains("restaurants") && requestMethod.equalsIgnoreCase("POST"))
        || (requestURI.contains("members") && requestMethod.equalsIgnoreCase("POST"))){
            return true;
        }
        return session.getAttribute("loginID") != null;
    }
}
