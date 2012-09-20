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
package pl.dolecinski.supdicium.server.users;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class InMemoryUserRegistry implements UserRegistry {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Map<String, GaeUser> users = Collections.synchronizedMap(new HashMap<String, GaeUser>());

    public GaeUser findUser(String userId) {
        return users.get(userId);
    }

    public void registerUser(GaeUser newUser) {
        logger.debug("Attempting to create new user " + newUser);

        Assert.state(!users.containsKey(newUser.getUserId()));

        users.put(newUser.getUserId(), newUser);
    }

    public void removeUser(String userId) {
        users.remove(userId);
    }
}