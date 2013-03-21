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
package com.tdm.server.datastore.exception;

/**
 * Occurs when object with given id cannot be retrieved from datasource.
 * e.g. when entity with given id doesn't exist in database
 * 
 * @author Paweł Doleciński
 */
public class ObjectNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -6753679774934598382L;

    public ObjectNotFoundException(String objectName, Object id) {
        super(objectName + " with id: " + id.toString() + " not found");
    }

    public ObjectNotFoundException(String objectName, Object id, Throwable cause) {
        super(objectName + " with id: " + id.toString() + " not found", cause);
    }

    public ObjectNotFoundException(Class<?> objectClass, Object id) {
        this(objectClass.getName(), id);
    }

    public ObjectNotFoundException(Class<?> objectClass, Object id, Throwable cause) {
        this(objectClass.getName(), id, cause);
    }
}
