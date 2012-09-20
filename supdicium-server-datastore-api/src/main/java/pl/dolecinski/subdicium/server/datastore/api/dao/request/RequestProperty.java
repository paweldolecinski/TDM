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
package pl.dolecinski.subdicium.server.datastore.api.dao.request;
/**
 *
 * @author Paweł Doleciński
 */
public enum RequestProperty {
    ID(RequestPropertyType.NUMBER),
    SENT_DATE(RequestPropertyType.DATE),
    CREATION_DATE(RequestPropertyType.DATE),
    AUTHOR_USERNAME(RequestPropertyType.STRING),
    AUTHOR_MESSAGE(RequestPropertyType.STRING),
    RESOURCE_NAME(RequestPropertyType.STRING),
    STATUS(RequestPropertyType.STATUS);
    
    private final RequestPropertyType propertyType;

    RequestProperty(RequestPropertyType propertyType){
        this.propertyType = propertyType;
    }

    public RequestPropertyType getPropertyType() {
        return propertyType;
    }

    public enum RequestPropertyType{
        NUMBER, DATE, STRING, STATUS;
    }
}
