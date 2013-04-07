package com.tdm.client.gin;

import com.google.inject.Inject;
import com.tdm.client.resources.AppResources;

public class ResourceLoader {
	@Inject
	public ResourceLoader(AppResources resources) {

		resources.mainCss().ensureInjected();
		resources.naviBar().ensureInjected();
		// ... Inject more css into the document here on boot
	}
}
