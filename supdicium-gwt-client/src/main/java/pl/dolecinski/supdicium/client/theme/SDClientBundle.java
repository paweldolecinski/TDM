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
package pl.dolecinski.supdicium.client.theme;

import pl.dolecinski.supdicium.client.theme.base.AddCommentBoxCss;
import pl.dolecinski.supdicium.client.theme.base.CommentListCss;
import pl.dolecinski.supdicium.client.theme.base.CommentWidgetCss;
import pl.dolecinski.supdicium.client.theme.base.ListCss;
import pl.dolecinski.supdicium.client.theme.base.MainCss;
import pl.dolecinski.supdicium.client.theme.base.UtilCss;

import com.google.gwt.resources.client.ImageResource;

/**
 * @author Paweł Doleciński
 *
 */
public interface SDClientBundle {
	
	UtilCss getUtilCss();
	
	ListCss getListCss();
	
	CommentListCss getCommentListCss();
	
	MainCss getMainCss();
	
	AddCommentBoxCss getAddCommentBoxCss();
	
	CommentWidgetCss getCommentWidgetCss();
	
	ImageResource idea();
	
	ImageResource hbackground();
}
