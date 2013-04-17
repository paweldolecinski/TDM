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
public interface MainCss extends CssResource {

	String container();

	@ClassName("content-main")
	String content();

	String footer();

	String navSub();

	String row();

	String dropdownMenu();

	String btn();

	String btnDiscreet();

	String btnPrimary();

	String btnLarge();

	String caret();

	String span1();

	String span2();

	String span4();

	String span5();

	String span6();

	String span7();

	String span8();

	String separator();

	String copyright();

	String problemList();

	String problemListItem();

	String thumbnails();

	String thumbnail();

	String caption();

	String title();

	String meta();

	String description();

	String create();

	String problemTitle();

	String clearfix();

	String well();

	String toolbar();

	String right();

	String blockShare();

	String problemSolutions();

	String solutionsHeader();

	String solutionList();

	String solution();

	String solutionTitle();

	String solutionSubTitle();

	String solutionAvatar();

	String solutionComment();
}
