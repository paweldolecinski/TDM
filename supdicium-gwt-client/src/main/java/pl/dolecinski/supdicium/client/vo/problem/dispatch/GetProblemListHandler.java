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
package pl.dolecinski.supdicium.client.vo.problem.dispatch;

import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonWriter;
import pl.dolecinski.subdicium.common.vo.problem.ProblemInfoList;
import pl.dolecinski.supdicium.client.dispatch.AbstractRequestBuilderClientActionHandler;
import pl.dolecinski.supdicium.client.util.UrlBuilder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.Response;

/**
 * @author Paweł Doleciński
 * 
 */
public class GetProblemListHandler
		extends
		AbstractRequestBuilderClientActionHandler<GetProblemListAction, GetProblemListResult> {


	public interface LocationReader extends JsonReader<ProblemInfoList> {
	}

	public interface LocationWriter extends JsonWriter<ProblemInfoList> {
	}

	public transient static final LocationReader jsonr = GWT
			.create(LocationReader.class);

	public transient static final LocationWriter jsonw = GWT
			.create(LocationWriter.class);
	
	// @Inject
	protected GetProblemListHandler() {
		super(GetProblemListAction.class);
	}

	@Override
	protected GetProblemListResult extractResult(final Response response) {
		// TODO parse error handling
		ProblemInfoList location = jsonr.read(response.getText());

		return new GetProblemListResult(location);
	}

	@Override
	protected RequestBuilder getRequestBuilder(final GetProblemListAction action) {
		UrlBuilder urlBuilder = new UrlBuilder().setModule("api").setVersion(
				"v1");
		urlBuilder.addResourcePath("locations");

		RequestBuilder rb = new RequestBuilder(RequestBuilder.POST,
				urlBuilder.toUrl());

		return rb;
	}

}
