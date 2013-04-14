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

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;

public interface AppResources extends ClientBundle {

	@Source({ "css/list.css" })
	ListCss getListCss();

	@Source({ "css/add_comment_box.css" })
	AddCommentBoxCss getAddCommentBoxCss();

	@Source({ "css/comment_widget.css" })
	CommentWidgetCss getCommentWidgetCss();

	@Source({ "css/list.css", "css/comment_list.css" })
	CommentListCss getCommentListCss();

	@Source("css/main.css")
	MainCss mainCss();

	@Source("css/naviBar.css")
	NaviBarCss naviBar();

	@Source("css/welcome.css")
	WelcomeCss welcome();

	@Source("resources/idea.png")
	ImageResource idea();

	@Source("resources/separator-footer.png")
	@ImageOptions(repeatStyle = RepeatStyle.None)
	ImageResource separator();

	@Source("resources/logo.png")
	@ImageOptions(repeatStyle = RepeatStyle.None)
	ImageResource logo();

	@Source("resources/which_way.jpg")
	@ImageOptions(repeatStyle = RepeatStyle.None)
	ImageResource problemDefaultImage();

	@Source("resources/solution_avatar.jpg")
	ImageResource solutionAvatar();
}
