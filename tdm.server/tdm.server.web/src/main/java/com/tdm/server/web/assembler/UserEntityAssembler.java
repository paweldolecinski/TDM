package com.tdm.server.web.assembler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.tdm.domain.model.user.dto.LocalUser;
import com.tdm.domain.model.user.dto.Role;
import com.tdm.server.security.GaeSocialUser;

public class UserEntityAssembler {

	/**
	 * Simply creates {@code RequestDTO} based on JPA Request entity.
	 */
	public LocalUser fromEntity(GaeSocialUser entity) {
		LocalUser localUser = new LocalUser();
		localUser.setName(entity.getName());
		localUser.setUsername(entity.getUsername());
		localUser.setEmail(entity.getEmail());
		localUser.setImageUrl(entity.getImageUrl());
		localUser.setEnabled(entity.isEnabled());
		Collection<GrantedAuthority> auth = entity.getAuthorities();
		List<Role> authorities = new ArrayList<Role>();
		for (GrantedAuthority roleEntity : auth) {
			authorities.add(new Role(roleEntity.getAuthority()));
		}
		localUser.setAuthorities(authorities);
		return localUser;
	}

	public GaeSocialUser toEntity(LocalUser user) {
		List<Role> auth = user.getAuthorities();
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		for (Role role : auth) {
			authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
		}

		GaeSocialUser entity = new GaeSocialUser(user.getUsername(),
				user.getName(), user.getEmail(), user.getImageUrl(),
				authorities);

		return entity;
	}
}
