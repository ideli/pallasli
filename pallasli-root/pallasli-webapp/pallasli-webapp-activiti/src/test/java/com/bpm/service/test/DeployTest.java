package com.bpm.service.test;

import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.junit.Ignore;

@Ignore
public class DeployTest extends PluggableActivitiTestCase {

	public String bpmnJsonFile = "test.json";

	@Override
	protected void setUp() throws Exception {

		super.setUp();
	}

	// public void testDeploy() {
	// ClassLoader classloader = this.getClass().getClassLoader();
	// InputStream jsonStream = classloader.getResourceAsStream(bpmnJsonFile);
	//
	// BpmnJsonConverterEx jsonConverter = new BpmnJsonConverterEx();
	//
	// ObjectMapper objectMapper = new ObjectMapper();
	//
	// JsonNode jsonNode = null;
	// BpmnModel bpmnModel = null;
	// try {
	// jsonNode = objectMapper.readTree(jsonStream);
	// } catch (JsonProcessingException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	//
	// bpmnModel = jsonConverter.convertToBpmnModel(jsonNode);
	// BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
	//
	// byte[] xmlData = xmlConverter.convertToXML(bpmnModel, "utf-8");
	//
	// Model model = new ModelEntity();
	//
	// repositoryService.saveModel(model);
	// repositoryService.addModelEditorSource(model.getId(),
	// "<afllfal>".getBytes());
	//
	// Deployment deployment = repositoryService.createDeployment()
	// .addString("lyttest.bpmn", new String(xmlData)).deploy();
	// assertNotNull(deployment.getId());
	// log.info(new String(xmlData));
	//
	// // List<ProcessDefinition> definitionList = repositoryService
	// // .createProcessDefinitionQuery().list();
	// //
	// // Model model = repositoryService.createModelQuery()
	// // .deploymentId(deployment.getId()).latestVersion()
	// // .singleResult();
	// // List<Model> modelList = repositoryService.createNativeModelQuery()
	// // .list();
	// // byte[] exportjson = repositoryService.getModelEditorSource(deployment
	// // .getId());
	// List<Model> modelList = repositoryService.createModelQuery().list();
	// log.info("----->{}", modelList.size());
	// log.info(
	// "----->{}",
	// new String(repositoryService.getModelEditorSource(modelList
	// .get(0).getId())));
	// repositoryService.deleteModel(model.getId());
	//
	// ProcessDefinition processDef = repositoryService
	// .createProcessDefinitionQuery().processDefinitionKey("lyttest")
	// .latestVersion().singleResult();
	//
	// BpmnModel bpmnModel2 = repositoryService.getBpmnModel(processDef
	// .getId());
	//
	// log.info(
	// "----->{}",
	// bpmnModel2.getFlowElement(
	// "sid-7953A70D-19D6-42DA-AA19-2E1DD131964A")
	// .getAttributeValue(BaseBpmnJsonConverter.NAMESPACE,
	// UserTaskJsonConverterEx.INITUSER));
	// InputStream processBpmn = repositoryService.getResourceAsStream(
	// deployment.getId(), "lyttest.bpmn");
	// try {
	// FileUtils.copyInputStreamToFile(processBpmn, new File(
	// "D://process.bpmn.xml"));
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// // log.info(new String(exportjson));
	// repositoryService.deleteDeployment(deployment.getId());
	// }
}
