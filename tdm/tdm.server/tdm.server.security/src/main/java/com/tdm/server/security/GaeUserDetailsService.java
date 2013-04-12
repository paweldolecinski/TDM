package com.tdm.server.security;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tdm.domain.model.user.LocalUser;
import com.tdm.domain.model.user.Role;
import com.tdm.domain.model.user.UserRepository;

@Service("gaeUserDetailsService")
public class GaeUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username)
	    throws UsernameNotFoundException {
	LocalUser user = userRepository.findByUsername(username);

	if (user == null) {

	    throw new UsernameNotFoundException("User for username " + username
		    + "was not found.");
	}

	List<Role> permissions = user.getAuthorities();

	if (permissions.isEmpty()) {

	    throw new UsernameNotFoundException(username
		    + "has no permissions.");

	}

	Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();

	for (Role permission : permissions) {

	    authorities.add(new SimpleGrantedAuthority(permission
		    .getAuthority()));

	}

	return new User(user.getUsername(), "", true, true, true, true,
		authorities);
    }

}
