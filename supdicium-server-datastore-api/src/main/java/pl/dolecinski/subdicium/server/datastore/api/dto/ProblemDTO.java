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
package pl.dolecinski.subdicium.server.datastore.api.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Representation of Decision Problem.
 * 
 * @author Paweł Doleciński
 */
public class ProblemDTO implements Serializable {

    private static final long serialVersionUID = 3198851130163436193L;
    private long id;
    private Date creationDate;
    private Date sentDate;
    private Status status;
    private String title;
    private String authorUsername;
    private String description;

    public ProblemDTO(long id, Date creationDate, Date sentDate, Status status, String title, String userName, String description) {
        this.id = id;
        this.creationDate = creationDate;
        this.sentDate = sentDate;
        this.status = status;
        this.title = title;
        this.authorUsername = userName;
        this.description = description;
    }

    /**
     * Gets uniqe id of Request entity
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     * Gets request creation date.
     * @return creation Date
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Gets date when request was sent i.e. user done his work on request and passes
     * it to administrators to review and accept or not. It's set automatically when
     * status is changed from {@link Status#UNSENT} to {@link Status#UNREAD}
     * @return sent date
     */
    public Date getSentDate() {
        return sentDate;
    }

    /**
     * Gets resource name (e.g. "SVN", "VirtualMachine")
     * @return resource name
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets status of request:
     * For more informations read {@link Status} javadoc
     * @return status of request
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Gets username of this request author
     * @return
     */
    public String getAuthorUsername() {
        return authorUsername;
    }

    /**
     * Gets message filled in by author of this request, it can modified till
     * sent.
     * @return String, can be null
     */
    public String getDescription() {
        return description;
    }

    /**
     * Status meaning:
     * <ul>
     * <li>{@link Status#UNSENT} - at default when request entity is created, visible
     * only to its author, can be edited & deleted by end user
     * <li>{@link Status#UNREAD} - unread by administrator, should be e.g. bolded in
     * administators user interfase
     * <li>{@link Status#READ} - read by administrator, but decission hasn't been maded
     * <li>{@link Status#ACCEPTED} - accepted by administrator, resource created
     * <li>{@link Status#REJECTED} - rejected by administrator, resource not created
     */
    public enum Status {

        /**
         * At default when request entity is created, visible
         * only to its author, can be edited & deleted by end user
         */
        UNSENT,
        /**
         * Posted by end user, byt unread by any administrator, should be e.g. bolded in
         * administators user interfase
         */
        OPEN,
        /**
         * Read by administrator, but decission hasn't been maded yet
         */
        DONE,
    }
}
