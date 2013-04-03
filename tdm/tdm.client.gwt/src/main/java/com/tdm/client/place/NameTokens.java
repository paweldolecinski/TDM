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
package com.tdm.client.place;

public class NameTokens {

	public class Params {
		public static final String id = "id"; // non-i18n
	}

	public static final String login = "!login"; // non-i18n

	public static final String homePage = "!home"; // non-i18n

	public static final String inbox = "!inbox"; // non-i18n

	public static final String problem = "!problem"; // non-i18n

	public static final String error = "!error"; // non-i18n

	public static String getHomePage() {
		return homePage;
	}

	public static String getLogin() {
		return login;
	}

	public static String getInbox() {
		return inbox;
	}

	public static String getProblem() {
		return inbox;
	}
}