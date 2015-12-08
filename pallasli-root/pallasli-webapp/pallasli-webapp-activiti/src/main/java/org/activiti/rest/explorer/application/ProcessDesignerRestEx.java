package org.activiti.rest.explorer.application;

import org.restlet.routing.Router;

public class ProcessDesignerRestEx {

	public static void attachResources(Router router) {
		router.attach("/atwasoft/model/{modelId}/json", ModelEditorJsonEx.class);
		router.attach("/atwasoft/model/{modelId}/save", ModelSaveRestEx.class);
	}
}
