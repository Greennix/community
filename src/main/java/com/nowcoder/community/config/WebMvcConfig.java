package com.nowcoder.community.config;


import com.nowcoder.community.controller.interceptor.AlphaInterceptor;
import com.nowcoder.community.controller.interceptor.LoginTicketInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private AlphaInterceptor alphaInterceptor;

    @Autowired
    private LoginTicketInterceptor loginTicketInterceptor;

    //配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //什么都不加就默认全部拦截,可以加上excludePathPatterns排除一些不用拦截的,addPathPatterns来添加拦截的
       registry.addInterceptor(alphaInterceptor)
               .excludePathPatterns("/**/*.css","/**/*.js","/**/*.png","/**/*.jpg","/**/*.jpeg")
               .addPathPatterns("/register", "/login");//登陆后就不显示注册和登录了，所以将他们拦截

        registry.addInterceptor(loginTicketInterceptor)
                .excludePathPatterns("/**/*.css","/**/*.js","/**/*.png","/**/*.jpg","/**/*.jpeg");
                //.addPathPatterns("/register", "/login");//登陆后就不显示注册和登录了，所以将他们拦截
    }
}
