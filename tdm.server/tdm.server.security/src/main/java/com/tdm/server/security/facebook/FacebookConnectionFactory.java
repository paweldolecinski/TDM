package com.tdm.server.security.facebook;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookAdapter;

public class FacebookConnectionFactory extends
		OAuth2ConnectionFactory<Facebook> {

	/**
	 * Creates a FacebookConnectionFactory for the given application ID and
	 * secret. Using this constructor, no application namespace is set (and
	 * therefore Facebook's Open Graph operations cannot be used).
	 * 
	 * @param appId
	 *            The application's App ID as assigned by Facebook
	 * @param appSecret
	 *            The application's App Secret as assigned by Facebook
	 */
	public FacebookConnectionFactory(String appId, String appSecret) {
		this(appId, appSecret, null);
	}

	/**
	 * Creates a FacebookConnectionFactory for the given application ID, secret,
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
	public FacebookConnectionFactory(String appId, String appSecret,
			String appNamespace) {
		super("facebook", new FacebookServiceProvider(appId, appSecret,
				appNamespace), new FacebookAdapter());
	}

}
