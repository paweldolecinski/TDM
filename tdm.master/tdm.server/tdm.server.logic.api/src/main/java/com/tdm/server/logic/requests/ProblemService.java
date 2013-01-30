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
package com.tdm.server.logic.requests;

import java.util.List;

import com.tdm.server.logic.model.Problem;



/**
 * @author Paweł Doleciński
 *
 */
public interface ProblemService {

	
	 public Problem getProblemInfo(long id);
	 
    /**
     * Returns list of latest pending{@code RequestSummaryDTO}s
     * Pending requests are requests with {@code RequestStatus == READ} or {@code UNREAD}
     * If there is less results than {@code maxResultSize} result list is shorter(it can be
     * empty too)
     * <p>
     * Results are ordered by {@link ProblemDTO#sentDate} descending
     * If there are two requests with the same sentDate, they are ordered by
     * {@code requestId} descending
     * @param maxResultSize maximum number of Requests that will be returned
     * @return list of request summaries, may be empty
     */
    public List<Problem> getPendingRequests(int maxResultSize);

    /**
     * Returns list of {@code RequestSummaryDTO} older than request with given id
     * Pending requests are requests with {@code RequestStatus == READ} or {@code UNREAD}
     * If there is less results than {@code maxResultSize} result list is shorter(it can be
     * empty too)
     * <p>
     * Results are ordered by {@link ProblemDTO#sentDate} descending
     * If there are two requests with the same sentDate, they are ordered by 
     * {@code requestId} descending
     * @param sentBefore requests older than this date will be returned
     * @param maxResultSize maximum number of Requests that will be returned
     * @return list of request summaries, may be empty
     */
    List<Problem> getPendingRequestsOlderThan(long requestId, int maxResultSize);

    /**
     * Returns list of {@code RequestSummaryDTO} newer than request with given id
     * Pending requests are requests with {@code RequestStatus == READ} or {@code UNREAD}
     * If there is less results than {@code maxResultSize} result list is shorter(it can be
     * empty too)
     * <p>
     * Results are ordered by {@link ProblemDTO#sentDate} descending
     * If there are two requests with the same sentDate, they are ordered by
     * {@code requestId} descending
     * @param sentBefore requests older than this date will be returned
     * @param maxResultSize maximum number of Requests that will be returned
     * @return list of request summaries, may be empty
     */
    List<Problem> getPendingRequestsNewerThan(long requestId, int maxResultSize);
}
