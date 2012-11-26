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
package pl.dolecinski.supdicium.client;

import pl.dolecinski.supdicium.client.theme.SDTheme;
import pl.dolecinski.supdicium.client.theme.SDThemeBaseThemeStandardImpl;

/**
 * @author Paweł Doleciński
 * 
 */
public class SDStyle {
	private static SDTheme theme;

	/**
	 * get the default bundle of this SubDicium app
	 * 
	 * @return the default bundle
	 */
	public static final SDTheme getTheme() {
		if (theme == null) {
			theme = new SDThemeBaseThemeStandardImpl();
			theme.getSDClientBundle().getMainCss().ensureInjected();

		}
		return theme;
	}

	/**
	 * Set the default bundle of this SubDicium app
	 * 
	 * <p>
	 * can only be called once at startup
	 * <p>
	 * 
	 * 
	 * @param bundle
	 *            the default bundle to use
	 */
	public static final void setTheme(SDTheme newTheme) {
		if (theme != null) {
			throw new IllegalStateException(
					"can not change default theme if theres already an instance...");
		}
		theme = newTheme;
		theme.getSDClientBundle().getMainCss().ensureInjected();
	}

}
