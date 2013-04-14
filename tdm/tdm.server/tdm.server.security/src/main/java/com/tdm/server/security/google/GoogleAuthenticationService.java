package com.tdm.server.security.google;

import org.springframework.social.google.api.Google;
import org.springframework.social.security.provider.OAuth2AuthenticationService;


public class GoogleAuthenticationService extends
	OAuth2AuthenticationService<Google> {

    public GoogleAuthenticationService(String apiKey, String appSecret) {
	super(new GoogleConnectionFactory(apiKey, appSecret));
	setScope("https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email");
    }
}
