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
package pl.dolecinski.supdicium.server.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;

public class SuspendRealTimeVoter implements AccessDecisionVoter {

	private Set<String> revokedUsers = new HashSet<String>();

	/**
	 * Will vote based on existence of a particular user in the "revokedUsers"
	 * set;
	 */
	@Override
	public int vote(Authentication authentication, Object object,
			Collection<ConfigAttribute> config) {
		String userName = authentication.getName();
		return revokedUsers.contains(userName) ? ACCESS_DENIED : ACCESS_GRANTED;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}
}
