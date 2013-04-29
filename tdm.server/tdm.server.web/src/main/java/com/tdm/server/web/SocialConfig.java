package com.tdm.server.web;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.UserIdSource;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.appengine.AppEngineUsersConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplateExt;
import org.springframework.social.security.SocialAuthenticationServiceLocator;
import org.springframework.social.security.SocialAuthenticationServiceRegistry;
import org.springframework.social.twitter.security.TwitterAuthenticationService;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.tdm.server.security.facebook.FacebookAuthenticationService;
import com.tdm.server.security.google.GoogleAuthenticationService;

@Configuration
public class SocialConfig {
    @Inject
    private Environment environment;
    @Inject
    private UsersConnectionRepository usersConnectionRepository;
    @Inject
    private UserIdSource userIdSource;

    @Bean
    @Scope(value = "singleton", proxyMode = ScopedProxyMode.INTERFACES)
    public SocialAuthenticationServiceLocator authenticationServiceLocator() {
	SocialAuthenticationServiceRegistry registry = new SocialAuthenticationServiceRegistry();
	registry.addAuthenticationService(new FacebookAuthenticationService(
		environment.getProperty("facebook.clientId"), environment
			.getProperty("facebook.clientSecret"), environment
			.getProperty("facebook.namespace")));

	registry.addAuthenticationService(new GoogleAuthenticationService(
		environment.getProperty("google.clientId"), environment
			.getProperty("google.clientSecret")));

	registry.addAuthenticationService(new TwitterAuthenticationService(
		environment.getProperty("twitter.clientId"), environment
			.getProperty("twitter.clientSecret")));

	// registry.addConnectionFactory(new
	// FacebookConnectionFactory(environment
	// .getProperty("facebook.clientId"), environment
	// .getProperty("facebook.clientSecret")));
	return registry;
    }

    @Bean
    @Scope(value = "singleton", proxyMode = ScopedProxyMode.INTERFACES)
    public UsersConnectionRepository usersConnectionRepository(
	    SocialAuthenticationServiceLocator authenticationServiceLocator,
	    TextEncryptor textEncryptor, ConnectionSignUp connectionSignUp) {
	AppEngineUsersConnectionRepository repository = new AppEngineUsersConnectionRepository(
		authenticationServiceLocator, textEncryptor,
		DatastoreServiceFactory.getDatastoreService());
	repository.setConnectionSignUp(connectionSignUp);
	return repository;
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public ConnectionRepository connectionRepository() {
	return usersConnectionRepository
		.createConnectionRepository(userIdSource.getUserId());
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public Facebook facebook() {
	Connection<Facebook> facebook = connectionRepository()
		.findPrimaryConnection(Facebook.class);
	return facebook != null ? facebook.getApi() : new FacebookTemplateExt();
    }
}
