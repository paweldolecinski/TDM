package com.tdm.server.web;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.UserIdSource;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.appengine.AppEngineUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

import com.google.appengine.api.datastore.DatastoreServiceFactory;

@Configuration
public class SocialConfig {
	@Inject
	private Environment environment;
	@Inject
	private TextEncryptor textEncryptor;
	@Inject
	private UserIdSource userIdSource;

	@Bean
	public ConnectionFactoryLocator connectionFactoryLocator() {
		ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
		registry.addConnectionFactory(new FacebookConnectionFactory(environment
				.getProperty("facebook.clientId"), environment
				.getProperty("facebook.clientSecret")));
		return registry;
	}

	@Bean
	public UsersConnectionRepository usersConnectionRepository() {
		AppEngineUsersConnectionRepository repository = new AppEngineUsersConnectionRepository(
				connectionFactoryLocator(), textEncryptor,
				DatastoreServiceFactory.getDatastoreService());
		return repository;
	}

	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public ConnectionRepository connectionRepository() {
		return usersConnectionRepository().createConnectionRepository(
				userIdSource.getUserId());
	}

}
