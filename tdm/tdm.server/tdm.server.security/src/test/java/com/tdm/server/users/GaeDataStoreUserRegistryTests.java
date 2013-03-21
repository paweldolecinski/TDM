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
package com.tdm.server.users;

import static org.junit.Assert.assertEquals;

import java.util.EnumSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.tdm.server.security.AppRole;
import com.tdm.server.users.GaeDatastoreUserRegistry;
import com.tdm.server.users.GaeUser;

public class GaeDataStoreUserRegistryTests {
    private final LocalServiceTestHelper helper =
        new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    @Before
    public void setUp() throws Exception {
        helper.setUp();
    }

    @After
    public void tearDown() throws Exception {
        helper.tearDown();
    }

    @Test
    public void correctDataIsRetrievedAfterInsert() {
        GaeDatastoreUserRegistry registry = new GaeDatastoreUserRegistry();

        Set<AppRole> roles = EnumSet.of(AppRole.ADMIN, AppRole.USER);
        String userId = "someUserId";

        GaeUser origUser = new GaeUser(userId, "nick", "nick@blah.com", "Forename", "Surname", roles, true);

        registry.registerUser(origUser);

        GaeUser loadedUser = registry.findUser(userId);

        assertEquals(loadedUser.getUserId(), origUser.getUserId());
        assertEquals(true, loadedUser.isEnabled());
        assertEquals(roles, loadedUser.getAuthorities());
        assertEquals("nick", loadedUser.getNickname());
        assertEquals("nick@blah.com", loadedUser.getEmail());
        assertEquals("Forename", loadedUser.getForename());
        assertEquals("Surname", loadedUser.getSurname());
    }
}
