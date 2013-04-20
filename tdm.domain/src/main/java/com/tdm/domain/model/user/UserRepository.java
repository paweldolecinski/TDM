package com.tdm.domain.model.user;

public interface UserRepository {

	User findByUsername(String username);

	User store(User user);
}
