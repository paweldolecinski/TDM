package com.tdm.server.security.facebook;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplateExt;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

public class FacebookServiceProvider extends
		AbstractOAuth2ServiceProvider<Facebook> {

	private String appNamespace;

	/**
	 * Creates a FacebookServiceProvider for the given application ID, secret,
	 * and namespace.
	 * 
	 * @param appId
	 *            The application's App ID as assigned by Facebook
	 * @param appSecret
	 *            The application's App Secret as assigned by Facebook
	 * @param appNamespace
	 *            The application's App Namespace as configured with Facebook.
	 *            Enables use of Open Graph operations.
	 */
	public FacebookServiceProvider(String appId, String appSecret,
			String appNamespace) {
		super(new FacebookOAuth2Template(appId, appSecret));
		this.appNamespace = appNamespace;
	}

	public Facebook getApi(String accessToken) {
		return new FacebookTemplateExt(accessToken, appNamespace);
	}

}
