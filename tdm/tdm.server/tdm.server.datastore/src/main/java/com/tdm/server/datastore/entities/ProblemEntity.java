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
package com.tdm.server.datastore.entities;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.tdm.server.datastore.exception.ConstraintsViolationException;


/**
 * Simple Request entity. Representes Requested posted by end user. Contains
 * information about user, resource type, status, etc.
 * 
 * @author Paweł Doleciński
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class ProblemEntity implements Serializable {

	public static final String QUERY_FIND_SENT_BEFORE = "Request.findSentBefore";
	public static final String PARAMETER_SENT_DATE = "sentDate";
	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	@Persistent
	private Date creationDate = new Date();
	@Persistent
	private Date sentDate = null;
	@Persistent
	private String resourceName;
	@Persistent
	private String authorUserName;
	@Persistent
	private String authorMessage;

	/**
	 * Creates Request entity, sets actual Date as creationDate sets Status as a
	 * UNSENT and updates lastModificationDate
	 */
	public ProblemEntity() {
		this.creationDate = new Date();
		// this.status = Status.UNSENT;
	}

	/**
	 * Gets uniqe id of Request entity
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Gets date of creation. Automatically initialized when this entity was
	 * created
	 * 
	 * @return creation Date
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * Gets date when request was sent i.e. user done his work on request and
	 * passes it to administrators to review and accept or not. It's set
	 * automatically when status is changed from {@link Status#UNSENT} to
	 * {@link Status#UNREAD}
	 * 
	 * @return date of sent or null if not sent yet
	 */
	public Date getSentDate() {
		return sentDate;
	}

	/**
	 * Gets status of request: For more informations read {@link Status} javadoc
	 * 
	 * @return status of request
	 */
	// public Status getStatus() {
	// return status;
	// }

	/**
	 * Sets status of request: For more informations read {@link Status} javadoc
	 * 
	 * @param status
	 *            status to set
	 * @throws ConstraintsViolationException
	 *             when trying to set status back to UNSENT
	 */
	// public void setStatus(Status status) {
	// throwIfNull(status);
	// if (this.status != Status.UNSENT && status == Status.UNSENT) {
	// throw new
	// ConstraintsViolationException("Cannot change status of request back to UNSENT");
	// }
	//
	// if (this.status == Status.UNSENT && status != Status.UNSENT) {
	// this.sentDate = new Date();
	// }
	// this.status = status;
	//
	// }

	/**
	 * Returns resource name ("SVN", "VirtualMachine")
	 * 
	 * @return resource name
	 */
	public String getResourceName() {
		return resourceName;
	}

	/**
	 * Sets resource name ("SVN", "VirtualMachine")
	 * 
	 * @param resourceName
	 *            resource name, cannot be null
	 */
	public void setResourceName(String resourceName) {
		throwIfNull(resourceName);
		this.resourceName = resourceName;
	}

	/**
	 * Gets username of this request author
	 * 
	 * @return
	 */
	public String getAuthorUsername() {
		return authorUserName;
	}

	/**
	 * Sets authors (of this request) username
	 * 
	 * @param userName
	 *            , username, cannto be null
	 */
	public void setAuthorUsername(String userName) {
		throwIfNull(userName);
		this.authorUserName = userName;
	}

	/**
	 * Gets message filled in by author of this request, it can modified till
	 * sent.
	 * 
	 * @return String, can be null
	 */
	public String getAuthorMessage() {
		return authorMessage;
	}

	/**
	 * Sets message filled in by author of this request, it can modified till
	 * sent.
	 * 
	 * @param authorMessage
	 *            message to set
	 */
	public void setAuthorMessage(String authorMessage) {
		// if(this.status!=Status.UNSENT){
		// throw new
		// ConstraintsViolationException("Authors message can't be modified after sending request");
		// }
		this.authorMessage = authorMessage;
	}

	@Override
	public String toString() {
		return "pl.edu.amu.wmi.kino.rrr.server.datastore.entities.Request[id="
				+ getId() + "]";
	}

	private void throwIfNull(Object obj) {
		if (obj == null) {
			throw new ConstraintsViolationException("cannot be null");
		}
	}
}
