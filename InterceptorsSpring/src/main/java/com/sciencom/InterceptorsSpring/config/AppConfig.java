package com.sciencom.InterceptorsSpring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by jupiterzhuo on 04/05/19.
 */
@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //Apply All URL
        registry.addInterceptor(new LogInterceptor());

        registry.addInterceptor(new OldPageInterceptor())
                .addPathPatterns("/old-page");

        registry.addInterceptor(new NewPageInterceptor())
                .addPathPatterns("/new-page")
                .excludePathPatterns("/old-page");
                //.addPathPatterns("/page/*");

    }
}
