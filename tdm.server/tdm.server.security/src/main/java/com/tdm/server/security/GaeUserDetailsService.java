package com.tdm.server.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tdm.domain.model.user.User;
import com.tdm.domain.model.user.UserRepository;

@Service("gaeUserDetailsService")
public class GaeUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);

		if (user == null) {

			throw new UsernameNotFoundException("User for username " + username
					+ "was not found.");
		}

		Set<String> permissions = user.getAuthorities();

		if (permissions.isEmpty()) {

			throw new UsernameNotFoundException(username
					+ "has no permissions.");

		}

		Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();

		for (String permission : permissions) {

			authorities.add(new SimpleGrantedAuthority(permission));

		}

		return new org.springframework.security.core.userdetails.User(user.getUsername(), "", true, true, true, true,
				authorities);
	}

}
