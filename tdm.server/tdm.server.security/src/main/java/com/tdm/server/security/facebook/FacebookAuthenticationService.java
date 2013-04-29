package com.tdm.server.security.facebook;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.security.provider.OAuth2AuthenticationService;

public class FacebookAuthenticationService extends
		OAuth2AuthenticationService<Facebook> {

	public FacebookAuthenticationService(String apiKey, String appSecret,
			String namespace) {
		super(new FacebookConnectionFactory(apiKey, appSecret, namespace));
		setScope("email,offline_access");
	}
}
