package com.etoak.config;

import com.etoak.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration // AnnotationConfigApplicationContext、AnnotationConfigWebApplicationContext
public class MvcConfig implements WebMvcConfigurer {
    /**
     * 配置拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/js/**")
                .excludePathPatterns("/bootstrap/**")
                .excludePathPatterns("/user/to_login")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/logout")
                .excludePathPatterns("/user/to_admin");
    }

    /**
     * <mvc:resource location="" mapping=""/>
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pic/**") // mapping属性
           .addResourceLocations("file:d:/upload/"); // location

    }

    /**
     * <mvc:view-controller path="/index" view-name="index" />
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/")
                .setViewName("index");
    }
}
