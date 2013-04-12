/**
 * Copyright 2011 Paweł Doleciński.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.tdm.server.persistance.jdo;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.tdm.domain.model.user.LocalUser;
import com.tdm.domain.model.user.UserRepository;
import com.tdm.server.persistance.jdo.assembler.UserEntityAssembler;
import com.tdm.server.persistance.jdo.entities.UserEntity;

/**
 * @author Paweł Doleciński
 * 
 */
@Repository
public class GaeUserRepository implements UserRepository {

    @Autowired
    @Qualifier("proxy")
    private PersistenceManagerFactory persistenceManagerFactory;

    public LocalUser findByUsername(String username) {
	PersistenceManager pm = getPersistenceManager();
	Query q = pm.newQuery(UserEntity.class);
	q.setFilter("username == usernameParam");
	q.declareParameters("String usernameParam");
	try {
	    @SuppressWarnings("unchecked")
	    List<UserEntity> results = (List<UserEntity>) q.execute(username);
	    if (!results.isEmpty() && results.size() == 1) {
		UserEntityAssembler assembler = new UserEntityAssembler();
		UserEntity detachCopy = pm.detachCopy(results.get(0));
		return assembler.fromEntity(detachCopy);
	    }
	} finally {
	    pm.close();
	}
	return null;
    }

    @Override
    public LocalUser store(LocalUser user) {
	PersistenceManager pm = getPersistenceManager();
	UserEntityAssembler assembler = new UserEntityAssembler();
	try {
	    UserEntity entity = assembler.toEntity(user);
	    UserEntity createdPersistent = pm.makePersistent(entity);
	    createdPersistent = pm.detachCopy(createdPersistent);
	    return assembler.fromEntity(createdPersistent);
	} finally {
	    pm.close();
	}

    }

    public UserEntity update(UserEntity user) {
	// TODO Auto-generated method stub
	return null;
    }

    private PersistenceManager getPersistenceManager() {
	return persistenceManagerFactory.getPersistenceManager();
    }

}
