package com.pallas.data;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.Picture;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.ProcessEngineImpl;
import org.activiti.engine.impl.util.IoUtil;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.task.Task;
import org.activiti.explorer.demo.DemoDataGenerator;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class DataGenerator implements ModelDataJsonConstants {

	protected static final Logger LOGGER = LoggerFactory
			.getLogger(DemoDataGenerator.class);

	protected transient ProcessEngine processEngine;
	protected transient IdentityService identityService;
	protected transient RepositoryService repositoryService;

	protected boolean createDemoUsersAndGroups;
	protected boolean createDemoProcessDefinitions;
	protected boolean createDemoModels;
	protected boolean generateReportData;

	public void init() {
		this.identityService = processEngine.getIdentityService();
		this.repositoryService = processEngine.getRepositoryService();

		if (createDemoUsersAndGroups) {
			LOGGER.info("Initializing demo groups");
			initDemoGroups();
			LOGGER.info("Initializing demo users");
			initDemoUsers();
		}

		if (createDemoProcessDefinitions) {
			LOGGER.info("Initializing demo process definitions");
			initProcessDefinitions();
		}

		if (createDemoModels) {
			LOGGER.info("Initializing demo models");
			initModelData();
		}

		if (generateReportData) {
			LOGGER.info("Initializing demo report data");
			generateReportData();
		}
	}

	public void setProcessEngine(ProcessEngine processEngine) {
		this.processEngine = processEngine;
	}

	public void setCreateDemoUsersAndGroups(boolean createDemoUsersAndGroups) {
		this.createDemoUsersAndGroups = createDemoUsersAndGroups;
	}

	public void setCreateDemoProcessDefinitions(
			boolean createDemoProcessDefinitions) {
		this.createDemoProcessDefinitions = createDemoProcessDefinitions;
	}

	public void setCreateDemoModels(boolean createDemoModels) {
		this.createDemoModels = createDemoModels;
	}

	public void setGenerateReportData(boolean generateReportData) {
		this.generateReportData = generateReportData;
	}

	protected void initDemoGroups() {
		String[] assignmentGroups = new String[] { "management", "sales",
				"marketing", "engineering" };
		for (String groupId : assignmentGroups) {
			createGroup(groupId, "assignment");
		}

		String[] securityGroups = new String[] { "user", "admin" };
		for (String groupId : securityGroups) {
			createGroup(groupId, "security-role");
		}
	}

	protected void createGroup(String groupId, String type) {
		if (identityService.createGroupQuery().groupId(groupId).count() == 0) {
			Group newGroup = identityService.newGroup(groupId);
			newGroup.setName(groupId.substring(0, 1).toUpperCase()
					+ groupId.substring(1));
			newGroup.setType(type);
			identityService.saveGroup(newGroup);
		}
	}

	protected void initDemoUsers() {
		createUser("kermit", "Kermit", "The Frog", "kermit",
				"kermit@activiti.org", "images/kermit.jpg", Arrays.asList(
						"management", "sales", "marketing", "engineering",
						"user", "admin"), Arrays.asList("birthDate",
						"10-10-1955", "jobTitle", "Muppet", "location",
						"Hollywoord", "phone", "+123456789", "twitterName",
						"alfresco", "skype", "activiti_kermit_frog"));

		createUser("gonzo", "Gonzo", "The Great", "gonzo",
				"gonzo@activiti.org", "images/gonzo.jpg",
				Arrays.asList("management", "sales", "marketing", "user"), null);
		createUser("fozzie", "Fozzie", "Bear", "fozzie", "fozzie@activiti.org",
				"images/fozzie.jpg",
				Arrays.asList("marketing", "engineering", "user"), null);
	}

	protected void createUser(String userId, String firstName, String lastName,
			String password, String email, String imageResource,
			List<String> groups, List<String> userInfo) {

		if (identityService.createUserQuery().userId(userId).count() == 0) {

			// Following data can already be set by demo setup script

			User user = identityService.newUser(userId);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setPassword(password);
			user.setEmail(email);
			identityService.saveUser(user);

			if (groups != null) {
				for (String group : groups) {
					identityService.createMembership(userId, group);
				}
			}
		}

		// Following data is not set by demo setup script

		// image
		if (imageResource != null) {
			byte[] pictureBytes = IoUtil.readInputStream(this.getClass()
					.getClassLoader().getResourceAsStream(imageResource), null);
			Picture picture = new Picture(pictureBytes, "image/jpeg");
			identityService.setUserPicture(userId, picture);
		}

		// user info
		if (userInfo != null) {
			for (int i = 0; i < userInfo.size(); i += 2) {
				identityService.setUserInfo(userId, userInfo.get(i),
						userInfo.get(i + 1));
			}
		}

	}

	protected void initProcessDefinitions() {

		String deploymentName = "Demo processes";
		List<Deployment> deploymentList = repositoryService
				.createDeploymentQuery().deploymentName(deploymentName).list();

		if (deploymentList == null || deploymentList.isEmpty()) {
			repositoryService
					.createDeployment()
					.name(deploymentName)
					.addClasspathResource(
							"diagram/createTimersProcess.bpmn20.xml")
					.addClasspathResource("diagram/VacationRequest.bpmn20.xml")
					.addClasspathResource("diagram/VacationRequest.png")
					.addClasspathResource(
							"diagram/FixSystemFailureProcess.bpmn20.xml")
					.addClasspathResource("diagram/FixSystemFailureProcess.png")
					.addClasspathResource("diagram/simple-approval.bpmn20.xml")
					.addClasspathResource("diagram/Helpdesk.bpmn20.xml")
					.addClasspathResource("diagram/Helpdesk.png")
					.addClasspathResource("diagram/reviewSalesLead.bpmn20.xml")
					.deploy();
		}

		String reportDeploymentName = "Demo reports";
		deploymentList = repositoryService.createDeploymentQuery()
				.deploymentName(reportDeploymentName).list();
		if (deploymentList == null || deploymentList.isEmpty()) {
			repositoryService
					.createDeployment()
					.name(reportDeploymentName)
					.addClasspathResource(
							"diagram/reports/taskDurationForProcessDefinition.bpmn20.xml")
					.addClasspathResource(
							"diagram/reports/processInstanceOverview.bpmn20.xml")
					.addClasspathResource(
							"diagram/reports/helpdeskFirstLineVsEscalated.bpmn20.xml")
					.addClasspathResource(
							"diagram/reports/employeeProductivity.bpmn20.xml")
					.deploy();
		}

	}

	protected void generateReportData() {
		if (generateReportData) {

			// Report data is generated in background thread

			Thread thread = new Thread(new Runnable() {

				public void run() {

					// We need to temporarily disable the job executor or it
					// would interfere with the process execution
					((ProcessEngineImpl) processEngine)
							.getProcessEngineConfiguration().getJobExecutor()
							.shutdown();

					Random random = new Random();

					Date now = new Date(new Date().getTime()
							- (24 * 60 * 60 * 1000));
					((ProcessEngineImpl) processEngine)
							.getProcessEngineConfiguration().getClock()
							.setCurrentTime(now);

					for (int i = 0; i < 50; i++) {

						if (random.nextBoolean()) {
							processEngine.getRuntimeService()
									.startProcessInstanceByKey(
											"fixSystemFailure");
						}

						if (random.nextBoolean()) {
							processEngine.getIdentityService()
									.setAuthenticatedUserId("kermit");
							Map<String, Object> variables = new HashMap<String, Object>();
							variables.put("customerName", "testCustomer");
							variables.put("details", "Looks very interesting!");
							variables.put("notEnoughInformation", false);
							processEngine.getRuntimeService()
									.startProcessInstanceByKey(
											"reviewSaledLead", variables);
						}

						if (random.nextBoolean()) {
							processEngine.getRuntimeService()
									.startProcessInstanceByKey(
											"escalationExample");
						}

						if (random.nextInt(100) < 20) {
							now = new Date(
									now.getTime()
											- ((24 * 60 * 60 * 1000) - (60 * 60 * 1000)));
							((ProcessEngineImpl) processEngine)
									.getProcessEngineConfiguration().getClock()
									.setCurrentTime(now);
						}
					}

					List<Job> jobs = processEngine.getManagementService()
							.createJobQuery().list();
					for (int i = 0; i < jobs.size() / 2; i++) {
						((ProcessEngineImpl) processEngine)
								.getProcessEngineConfiguration().getClock()
								.setCurrentTime(jobs.get(i).getDuedate());
						processEngine.getManagementService().executeJob(
								jobs.get(i).getId());
					}

					List<Task> tasks = processEngine.getTaskService()
							.createTaskQuery().list();
					while (!tasks.isEmpty()) {
						for (Task task : tasks) {

							if (task.getAssignee() == null) {
								String assignee = random.nextBoolean() ? "kermit"
										: "fozzie";
								processEngine.getTaskService().claim(
										task.getId(), assignee);
							}

							((ProcessEngineImpl) processEngine)
									.getProcessEngineConfiguration()
									.getClock()
									.setCurrentTime(
											new Date(
													task.getCreateTime()
															.getTime()
															+ random.nextInt(60 * 60 * 1000)));

							processEngine.getTaskService().complete(
									task.getId());
						}

						tasks = processEngine.getTaskService()
								.createTaskQuery().list();
					}

					((ProcessEngineImpl) processEngine)
							.getProcessEngineConfiguration().getClock().reset();

					((ProcessEngineImpl) processEngine)
							.getProcessEngineConfiguration().getJobExecutor()
							.start();
					LOGGER.info("Demo report data generated");
				}

			});
			thread.start();

		}
	}

	protected void initModelData() {
		createModelData("Demo model", "This is a demo model",
				"model/test.model.json");
	}

	protected void createModelData(String name, String description,
			String jsonFile) {
		List<Model> modelList = repositoryService.createModelQuery()
				.modelName("Demo model").list();

		if (modelList == null || modelList.isEmpty()) {

			Model model = repositoryService.newModel();
			model.setName(name);

			ObjectNode modelObjectNode = new ObjectMapper().createObjectNode();
			modelObjectNode.put(MODEL_NAME, name);
			modelObjectNode.put(MODEL_DESCRIPTION, description);
			model.setMetaInfo(modelObjectNode.toString());

			repositoryService.saveModel(model);

			try {
				InputStream svgStream = this.getClass().getClassLoader()
						.getResourceAsStream("model/test.svg");
				repositoryService.addModelEditorSourceExtra(model.getId(),
						IOUtils.toByteArray(svgStream));
			} catch (Exception e) {
				LOGGER.warn("Failed to read SVG", e);
			}

			try {
				InputStream editorJsonStream = this.getClass().getClassLoader()
						.getResourceAsStream(jsonFile);
				repositoryService.addModelEditorSource(model.getId(),
						IOUtils.toByteArray(editorJsonStream));
			} catch (Exception e) {
				LOGGER.warn("Failed to read editor JSON", e);
			}
		}
	}

}
