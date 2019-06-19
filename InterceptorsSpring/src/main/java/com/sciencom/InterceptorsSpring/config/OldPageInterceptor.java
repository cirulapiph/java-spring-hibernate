package com.sciencom.InterceptorsSpring.config;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jupiterzhuo on 04/05/19.
 */
public class OldPageInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Old page Interceptor Pre Handle");
        System.out.println("=======================");
        System.out.println("Request URL : " +  request.getRequestURL());
        System.out.println("Sorry this page not longer used, please use new page");
        response.sendRedirect(request.getContextPath()+"/new-page");
        return false;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //never print out
        System.out.println("Old page Interceptor post Handle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //never print out
        System.out.println("Old page Interceptor After Completion");
    }
}
