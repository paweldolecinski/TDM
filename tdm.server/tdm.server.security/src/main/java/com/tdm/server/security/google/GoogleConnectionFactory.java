package com.tdm.server.security.google;

import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.connect.GoogleAdapter;
import org.springframework.social.oauth2.AccessGrant;

public class GoogleConnectionFactory extends OAuth2ConnectionFactory<Google> {
    public GoogleConnectionFactory(String clientId, String clientSecret) {
	super("google", new GoogleServiceProvider(clientId, clientSecret),
		new GoogleAdapter());
    }

    @Override
    protected String extractProviderUserId(AccessGrant accessGrant) {
	Google api = ((GoogleServiceProvider) getServiceProvider())
		.getApi(accessGrant.getAccessToken());
	UserProfile userProfile = getApiAdapter().fetchUserProfile(api);
	return userProfile.getUsername();
    }
}
