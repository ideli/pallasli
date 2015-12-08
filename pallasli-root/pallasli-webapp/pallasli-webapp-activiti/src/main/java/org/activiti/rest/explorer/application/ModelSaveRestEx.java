package org.activiti.rest.explorer.application;

import org.activiti.editor.constants.ModelDataJsonConstants;
import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModelSaveRestEx extends ServerResource implements
		ModelDataJsonConstants {

	protected static final Logger LOGGER = LoggerFactory
			.getLogger(ModelSaveRestEx.class);

	@Put
	public void saveModel(Form modelForm) {

		try {

			String modelId = (String) getRequest().getAttributes().get(
					"modelId");

			// ProcessEditorModel model = new ProcessEditorModel();
			// model.setName(modelForm.getFirstValue("name"));
			// model.setCategory(modelForm.getFirstValue("category"));
			// model.setDescription(modelForm.getFirstValue("description"));
			// model.setType(modelForm.getFirstValue("type"));
			// model.setParent(modelForm.getFirstValue("parent"));
			// model.setGlossaryXml(modelForm.getFirstValue("glossary_xml"));
			// model.setNamespace(modelForm.getFirstValue("namespace"));
			// model.setViews(modelForm.getFirstValue("views"));
			// String gbk = modelForm.getFirstValue("json_xml");
			// String iso = new String(gbk.getBytes("UTF-8"), "ISO-8859-1");
			// String utf8 = new String(iso.getBytes("ISO-8859-1"), "UTF-8");
			// model.setJsonXml(utf8.getBytes("UTF-8"));
			// model.setProcesskey(modelForm.getFirstValue("processkey"));
			//
			// InputStream svgStream = new ByteArrayInputStream(modelForm
			// .getFirstValue("svg_xml").getBytes("utf-8"));
			// TranscoderInput input = new TranscoderInput(svgStream);
			//
			// PNGTranscoder transcoder = new PNGTranscoder();
			// // Setup output
			// ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			// TranscoderOutput output = new TranscoderOutput(outStream);
			// // Do the transformation
			// transcoder.transcode(input, output);
			// final byte[] result = outStream.toByteArray();
			// outStream.close();
			//
			// model.setSvgXml(result);
			//
			// BPMNewServiceFacade bpmService = (BPMNewServiceFacade)
			// DataServicePool
			// .instance().getService("bpmnew");
			// bpmService.saveProcessModel(modelId, model);

		} catch (Exception e) {
			LOGGER.error("Error saving model", e);
			setStatus(Status.SERVER_ERROR_INTERNAL);
		}
	}
}
