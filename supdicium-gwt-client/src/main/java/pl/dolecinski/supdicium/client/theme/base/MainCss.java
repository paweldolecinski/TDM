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
package pl.dolecinski.supdicium.client.theme.base;

import com.google.gwt.resources.client.CssResource;

/**
 * <p>
 * MainCss interface.
 * </p>
 * 
 * @author Paweł Doleciński
 * 
 */
public interface MainCss extends CssResource {
	String verticalBorder();

	String top();

	String leftContainer();

	String container();

	String lfloat();

	String rfloat();

	String fcg();

	String fwn();

	String fsm();

	String tar();

	String header();

	String pageLogoHref();

	String footer();

	String footerHref();

	String pageLogo();

	String section();

	String globalContainer();

	String contentAbsolute();

	String problemDescription();

	String infoSidebar();

	String stackLayoutPanelHeader();

}
