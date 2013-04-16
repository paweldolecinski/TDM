package com.tdm.server.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@ImportResource("classpath:META-INF/security.xml")
@ComponentScan(basePackages = { "com.tdm.server.security",
	"com.tdm.server.persistance" }, excludeFilters = @Filter(value = Configuration.class))
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
	return new StandardPasswordEncoder();
    }

    @Bean
    public TextEncryptor textEncryptor() {
	return Encryptors.noOpText();
    }

}
