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
package pl.dolecinski.subdicium.server.datastore.dto;

import java.util.Date;

/**
 *
 * @author Paweł Doleciński
 */
public class ProblemDTO {

    private long id;
    private Date sentDate;
    private Date creationDate;
    private String resourceName;
    private String requesterUsername;
    private String requesterMessage;
    private RequestStatus status;

    public ProblemDTO(long id, Date sentDate, Date creationDate, String resourceName, String requesterUsername, String requesterMessage, RequestStatus status) {
        this.id = id;
        this.sentDate = sentDate;
        this.creationDate = creationDate;
        this.resourceName = resourceName;
        this.requesterUsername = requesterUsername;
        this.requesterMessage = requesterMessage;
        this.status = (RequestStatus)status;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public long getId() {
        return id;
    }

    public String getRequesterMessage() {
        return requesterMessage;
    }

    public String getRequesterUsername() {
        return requesterUsername;
    }

    public String getResourceName() {
        return resourceName;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public RequestStatus getStatus() {
        return status;
    }

    /**
     * RequestStatus meaning:
     * <ul>
     * <li>{@link RequestStatus#UNSENT} - at default when request entity is created, visible
     * only to its author, can be edited & deleted by end user
     * <li>{@link RequestStatus#UNREAD} - unread by administrator, should be e.g. bolded in
     * administators user interfase
     * <li>{@link RequestStatus#READ} - read by administrator, but decission hasn't been maded
     * <li>{@link RequestStatus#ACCEPTED} - accepted by administrator, resource created
     * <li>{@link RequestStatus#REJECTED} - rejected by administrator, resource not created
     */
    public enum RequestStatus {

        /**
         * At default when request entity is created, visible
         * only to its author, can be edited & deleted by end user
         */
        UNSENT,
        /**
         * Posted by end user, byt unread by any administrator, should be e.g. bolded in
         * administators user interfase
         */
        UNREAD,
        /**
         * Read by administrator, but decission hasn't been maded yet
         */
        READ,
        /**
         * Accepted by administrator, resource created
         */
        ACCEPTED,
        /**
         * Rejected by administrator, resource not created
         */
        REJECTED
    }
}
