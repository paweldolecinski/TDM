package com.tdm.server.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.tdm.server.web.controller" }, excludeFilters = @Filter(value = Configuration.class))
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class WebConfiguration extends WebMvcConfigurerAdapter {

}
