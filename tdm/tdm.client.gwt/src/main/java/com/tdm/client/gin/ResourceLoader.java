package com.tdm.client.gin;

import com.google.inject.Inject;
import com.tdm.client.resources.AppResources;

public class ResourceLoader {
	@Inject
	public ResourceLoader(AppResources resources) {
		resources.getMainCss().ensureInjected();

		// ... Inject more css into the document here on boot
	}
}
