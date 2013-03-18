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
package pl.dolecinski.subdicium.server.datastore.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import pl.dolecinski.subdicium.server.datastore.jpa.entities.Problem;

/**
 *
 * @author Paweł Doleciński
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
    "classpath*:testsContexts/JPATest-context.xml"
})
public class JPATest {

    private static final String RESOURCE_NAME = "testResourceName";
    private static final String USER_NAME = "testUserName";

    public JPATest() {
    }

    @Test
    @Transactional
    public void testPersistAndFind() {
        //given
        Problem persistedRequest = new Problem();
        persistedRequest.setAuthorUsername(USER_NAME);
        persistedRequest.setResourceName(RESOURCE_NAME);
//        em.persist(persistedRequest);
        //when
//        Problem foundRequest = null; //em.find(Request.class, persistedRequest.getId());
        //then
//        assertEquals(persistedRequest.getId(), foundRequest.getId());
//        assertEquals(persistedRequest.getCreationDate(), foundRequest.getCreationDate());
//        assertEquals(persistedRequest.getSentDate(), foundRequest.getSentDate());
//        assertEquals(USER_NAME, foundRequest.getAuthorUsername());
//        assertEquals(RESOURCE_NAME, foundRequest.getResourceName());
    }

    @Test
    @Transactional
    public void testPersistManyAndFindAll() {
        final int quantity = 100;
        //given
        for (int i = 0; i < quantity; i++) {
            Problem persistedRequest = new Problem();
            persistedRequest.setAuthorUsername(USER_NAME+i);
            persistedRequest.setResourceName(RESOURCE_NAME+i);
//            em.persist(persistedRequest);
        }
        //when
//        List<Problem> result = null; //em.createQuery("SELECT r From Request r", Request.class).getResultList();
//        assertEquals(quantity, result.size());
    }
}
