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

import pl.dolecinski.supdicium.client.theme.SDClientBundle;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;

public interface AppResources extends ClientBundle, SDClientBundle {

	@Source({ "css/util.css" })
	UtilCss getUtilCss();

	@Source({ "css/list.css" })
	ListCss getListCss();

	@Source({ "css/add_comment_box.css" })
	AddCommentBoxCss getAddCommentBoxCss();

	@Source({ "css/comment_widget.css" })
	CommentWidgetCss getCommentWidgetCss();
	
	@Source({ "css/list.css" , "css/comment_list.css" })
	CommentListCss getCommentListCss();

	@Source("css/main.css")
	MainCss getMainCss();

	@Source("resources/hbackground.png")
	@ImageOptions(repeatStyle = RepeatStyle.Horizontal)
	ImageResource hbackground();

	@Source("resources/idea.png")
	ImageResource idea();
}
