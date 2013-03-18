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
package com.tdm.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.gwtplatform.mvp.client.DelayedBindRegistry;
import com.tdm.client.gin.MainAppInjector;
import com.tdm.client.gin.wrapper.DesktopInjectorWrapper;
import com.tdm.client.gin.wrapper.InjectorWrapper;

public class TDMEntryPoint implements EntryPoint {

	final private InjectorWrapper injectorWrapper = GWT
			.create(DesktopInjectorWrapper.class);

	@Override
	public void onModuleLoad() {
		MainAppInjector injector = injectorWrapper.getInjector();
		DelayedBindRegistry.bind(injector);
		injector.getGaleriaApp().run(injector);

	}
}
