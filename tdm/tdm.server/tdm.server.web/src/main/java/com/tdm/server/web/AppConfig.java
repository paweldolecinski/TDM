package com.tdm.server.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan(basePackages = { "com.tdm.server.application",
		"com.tdm.server.persistance" }, excludeFilters = @Filter(value = Configuration.class))
@ImportResource("classpath:META-INF/persistance-config.xml")
public class AppConfig {

	public AppConfig() {
		System.out
				.println("AppConfigAppConfigAppConfigAppConfigAppConfigAppConfig");
	}

}
