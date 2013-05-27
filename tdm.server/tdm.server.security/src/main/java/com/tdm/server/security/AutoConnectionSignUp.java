package com.tdm.server.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Service;

import com.tdm.domain.model.user.Role;
import com.tdm.domain.model.user.User;
import com.tdm.domain.model.user.UserRepository;

@Service
public class AutoConnectionSignUp implements ConnectionSignUp {

	@Autowired
	private UserRepository userRepository;

	@Override
	public String execute(Connection<?> connection) {
		User user = new User();
		UserProfile userProfile = connection.fetchUserProfile();
		user.setName(userProfile.getName());
		user.setEmail(userProfile.getEmail());
		user.setImageUrl(connection.getImageUrl());
		String username = getUsernameFromProfile(userProfile);
		user.setUsername(username);
		Set<String> auth = new HashSet<String>();
		auth.add(Role.USER_ROLE);
		user.setAuthorities(auth);
		user.setEnabled(true);

		User createdUser = userRepository.store(user);
		return createdUser.getUsername();
	}

	private String getUsernameFromProfile(UserProfile profile) {
		String username;
		if (profile.getEmail() != null) {
			username = profile.getEmail();
		} else if (profile.getUsername() != null) {
			username = profile.getUsername();
		} else if (profile.getName() != null) {
			username = profile.getName();
		} else {
			username = profile.toString();
		}
		return username;
	}
}
