package com.tdm.domain.model.user;


public interface UserRepository {
    
    LocalUser findByUsername(String username);

    LocalUser store(LocalUser user);
}
