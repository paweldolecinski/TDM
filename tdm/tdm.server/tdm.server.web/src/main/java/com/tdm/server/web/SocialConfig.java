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
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.appengine.AppEngineUsersConnectionRepository;
import org.springframework.social.facebook.security.FacebookAuthenticationService;
import org.springframework.social.security.SocialAuthenticationServiceLocator;
import org.springframework.social.security.SocialAuthenticationServiceRegistry;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.tdm.server.security.ConnectionFactoryLocatorPseudoProxy;

@Configuration
// @EnableFacebook(appId = "242235915795558", appSecret =
// "1c130bf0dbe937f7217c2e852e8fcf90")
public class SocialConfig {
    @Inject
    private Environment environment;

    static {
	try {
	    Class.forName("org.springframework.social.connect.support.OAuth2Connection");
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	}
    }

    @Bean
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
    public SocialAuthenticationServiceLocator authenticationServiceLocator() {
	return new ConnectionFactoryLocatorPseudoProxy(
		"connectionFactoryLocatorTarget");
    }

    @Bean
    public ConnectionFactoryLocator connectionFactoryLocatorTarget() {
	SocialAuthenticationServiceRegistry registry = new SocialAuthenticationServiceRegistry();
	registry.addAuthenticationService(new FacebookAuthenticationService(
		environment.getProperty("facebook.clientId"), environment
			.getProperty("facebook.clientSecret")));

	// registry.addConnectionFactory(new
	// FacebookConnectionFactory(environment
	// .getProperty("facebook.clientId"), environment
	// .getProperty("facebook.clientSecret")));
	return registry;
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public ConnectionRepository connectionRepository(
	    UsersConnectionRepository usersConnectionRepository,
	    UserIdSource userIdSource) {
	return usersConnectionRepository
		.createConnectionRepository(userIdSource.getUserId());
    }

}
