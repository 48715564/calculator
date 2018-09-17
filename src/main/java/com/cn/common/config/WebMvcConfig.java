package com.cn.common.config;

import com.cn.common.infrastructure.AuthResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mobile.device.DeviceHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private AuthResolver authResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(authResolver);
        argumentResolvers.add(deviceHandlerMethodArgumentResolver());
    }

    @Bean
    public DeviceHandlerMethodArgumentResolver deviceHandlerMethodArgumentResolver() {
        return new DeviceHandlerMethodArgumentResolver();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    }

//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
//        /**
//         * 序列换成json时,将所有的long变成string
//         * 因为js中得数字类型不能包含所有的java long值
//         */
//        JsonComponentModule module = new JsonComponentModule();
//        module.addSerializer(Long.class, ToStringSerializer.instance);
//        module.addSerializer(Long.TYPE, ToStringSerializer.instance);
//        jackson2HttpMessageConverter.getObjectMapper().registerModule(module);
//        converters.add(jackson2HttpMessageConverter);
//        ObjectMapper objectMapper = new ObjectMapper();
//        System.out.println(objectMapper);
//    }

}
