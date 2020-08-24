package com.ypgly.yipin.config;


import com.ypgly.yipin.servlet.interceptor.SessionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author yzy
 */
//@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {

    @Resource
    private SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        InterceptorRegistration rgistry = registry.addInterceptor(sessionInterceptor);
        rgistry.addPathPatterns("/**");
        rgistry.excludePathPatterns(
                "/favicon.ico",
                "/webjars/**",
                "/v2/**",
                "/swagger-ui.html/**",
                "/swagger-resources/**"
                );
        rgistry.excludePathPatterns(
                "/static/**",
                "/static/"
        );
        rgistry.excludePathPatterns(
                "/index",
                "/",
                "/view/index",
                "/transFileController/downLoadTransFile",
                "/transFileController/uploadFile",
                "/userControll/userLogin",
                "/userControll/userIsLogin",
                "/transTextController/postTranslation",
                "/static/**",
                "/static/js/**",
                "/static/css/**",
                "/static/fonts/**",
                "/static/img/**",
                "/static/image/**"
                );   //对应的不拦截的请求

    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        //第一个方法设置访问路径前缀，第二个方法设置资源路径
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
