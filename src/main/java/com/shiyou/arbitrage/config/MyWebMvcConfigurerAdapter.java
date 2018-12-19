package com.shiyou.arbitrage.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shiyou.arbitrage.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Package: com.bloex.web.config
 * FileName: MyWebMvcConfigurerAdapter.java
 * Description: WebMVC配置适配器
 * Author: xinjl
 * Date:  2017/8/2
 */
@Configuration
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurationSupport {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }

    /**
     * Method：configureMessageConverters
     * Description: HTTP请求返回内容格式开启自定义，LONG转STRING
     * Author: ZhangYX
     * Date: 2017/9/18
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        //builder.serializationInclusion(JsonInclude.Include.NON_EMPTY);
        builder.indentOutput(true).dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));


        //自定义Long类型转换 超过12个数字用String格式返回，由于js的number只能表示15个数字
        builder.serializerByType(Long.class, new CustomLongConverter());
        builder.serializerByType(Long.TYPE, new CustomLongConverter());
        converters.add(0, new MappingJackson2HttpMessageConverter(builder.build()));
    }

    /**
     * Method：addCorsMappings
     * Description: 跨域请求配置
     * Author: ZhangYX
     * Date: 2017/8/21
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .exposedHeaders("token");
    }

    /**
     * Method：addInterceptors
     * Description: 添加拦截器
     * Author: xinjl
     * Date: 2017/8/2
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
