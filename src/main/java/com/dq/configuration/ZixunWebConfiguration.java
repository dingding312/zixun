package com.dq.configuration;

import com.dq.interceptor.LoginRequiredInterceptor;
import com.dq.interceptor.PassportInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by DQ on 2016/8/22.
 */
@Component
public class ZixunWebConfiguration extends WebMvcConfigurerAdapter{
    @Autowired
    PassportInterceptor passportInterceptor;

    @Autowired
    LoginRequiredInterceptor loginRequiredInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginRequiredInterceptor).addPathPatterns("/not*");
        registry.addInterceptor(passportInterceptor);
        super.addInterceptors(registry);
    }
}
