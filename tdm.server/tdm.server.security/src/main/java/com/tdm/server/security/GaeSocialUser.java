package com.tdm.server.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.security.SocialUser;

public class GaeSocialUser extends SocialUser {

	private static final long serialVersionUID = -3626293967555419341L;

	private String name;
	private String email;
	private String imageUrl;

	public GaeSocialUser(String username, String name, String email,
			String imageUrl, Collection<? extends GrantedAuthority> authorities) {
		super(username, "", true, true, true, true, authorities);
		this.name = name;
		this.email = email;
		this.imageUrl = imageUrl;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getImageUrl() {
		return imageUrl;
	}

}
