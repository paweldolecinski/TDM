package com.tdm.server.persistance.jdo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tdm.domain.model.user.LocalUser;
import com.tdm.domain.model.user.Role;
import com.tdm.server.persistance.jdo.entities.UserEntity;

public class UserEntityAssembler {

    /**
     * Simply creates {@code RequestDTO} based on JPA Request entity.
     */
    public LocalUser fromEntity(UserEntity entity) {
	LocalUser localUser = new LocalUser();
	localUser.setName(entity.getName());
	localUser.setUsername(entity.getUsername());
	localUser.setEmail(entity.getEmail());
	localUser.setEnabled(entity.isEnabled());
	Set<String> auth = entity.getAuthorities();
	List<Role> authorities = new ArrayList<Role>();
	for (String roleEntity : auth) {
	    authorities.add(new Role(roleEntity));
	}
	localUser.setAuthorities(authorities);
	return localUser;
    }

    public UserEntity toEntity(LocalUser user) {
	UserEntity entity = new UserEntity();
	entity.setUsername(user.getUsername());
	entity.setEmail(user.getEmail());
	entity.setName(user.getName());
	entity.setEnabled(user.isEnabled());
	List<Role> auth = user.getAuthorities();
	Set<String> authorities = new HashSet<String>();
	for (Role role : auth) {
	    authorities.add(role.getAuthority());
	}
	entity.setAuthorities(authorities);
	return entity;
    }
}
