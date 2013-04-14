package com.tdm.server.security;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.security.provider.OAuth2AuthenticationService;

public class FacebookAuthenticationService extends
		OAuth2AuthenticationService<Facebook> {

	public FacebookAuthenticationService(String apiKey, String appSecret,
			String namespace) {
		super(new FacebookConnectionFactory(apiKey, appSecret, namespace));
	}

}
