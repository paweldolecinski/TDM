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
package com.tdm.client.resources;

import com.google.gwt.resources.client.CssResource;

/**
 * <p>
 * MainCss interface.
 * </p>
 * 
 * @author Paweł Doleciński
 * 
 */
public interface ContentCss extends CssResource {

	@ClassName("row-fluid")
	String rowFluid();

	@ClassName("index-main")
	String indexMain();

	@ClassName("index-right")
	String indexRight();

	@ClassName("index-left")
	String indexLeft();

	@ClassName("index-center")
	String indexCenter();

	String span1();

	String span2();

	String span3();

	String span4();

	String span5();

	String span6();

	String span7();

	String span8();

	@ClassName("info-panel")
	String infoPanel();

	@ClassName("tab-well")
	String tabWell();
	
	@ClassName("tab-content")
	String tabContent();
	
	@ClassName("empty-placeholder")
	String emptyPlaceholder();
	
	@ClassName("print_and_settings")
	String printAndSettings();
}
