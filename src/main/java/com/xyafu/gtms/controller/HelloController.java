package com.xyafu.gtms.controller;

import com.xyafu.gtms.entity.Student;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author 刘高伟
 * @Classname HelloController
 * @Description 将login.html设置为默认访问页面
 * @Date 2019/9/12 15:03
 */
@Configuration
@SuppressWarnings("WebMvcConfigurerAdapter")
public class HelloController extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers( ViewControllerRegistry registry )
    {
        registry.addViewController( "/" ).setViewName( "forward:/page/login.html" );
        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
        super.addViewControllers( registry );
    }
}

