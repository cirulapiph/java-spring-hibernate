package com.sciencom.InterceptorsSpring.config;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jupiterzhuo on 03/05/19.
 */
public class LogInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        long startTime = System.currentTimeMillis();
        System.out.println("Log Interceptor Pre Handle");
        System.out.println("=======================");
        System.out.println("Request URL : " +  request.getRequestURL());
        System.out.println("Start Time : " + System.currentTimeMillis());

        request.setAttribute("startTime",startTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("Log Interceptor Post Handle");
        System.out.println("Request URL : " +  request.getRequestURL());

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        System.out.println("Log Interceptor After Completion");
        long startTime = (long)request.getAttribute("startTime");
        long endTime  = System.currentTimeMillis();
        System.out.println("Request URL : " +  request.getRequestURL());
        System.out.println("End Time : " + endTime);
        System.out.println("Time Taken :" + (endTime-startTime));
    }
}
