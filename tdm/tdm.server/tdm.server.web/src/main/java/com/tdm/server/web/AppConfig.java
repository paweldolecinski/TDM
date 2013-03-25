package com.tdm.server.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ComponentScan.Filter;

@Configuration
@ComponentScan(basePackages = { "com.tdm.server.application" }, excludeFilters = @Filter(value = Configuration.class))
@Import({ JdoPersistanceConfig.class })
// @EnableAsync
// @EnableAspectJAutoProxy
public class AppConfig {

	public AppConfig() {
		System.out
				.println("AppConfigAppConfigAppConfigAppConfigAppConfigAppConfig");
	}

}
