package com.atwasoft.bpm.image;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import org.activiti.bpmn.model.Artifact;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.FlowElementsContainer;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.GraphicInfo;
import org.activiti.bpmn.model.Lane;
import org.activiti.bpmn.model.Pool;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.HistoricActivityInstanceQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.cmd.GetBpmnModelCmd;
import org.activiti.engine.impl.cmd.GetDeploymentProcessDefinitionCmd;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;

public class CustomProcessDiagramGenerator {
	public static final int OFFSET_SUBPROCESS = 5;
	public static final int OFFSET_TASK = 20;
	private static List<String> taskType = new ArrayList<String>();
	private static List<String> eventType = new ArrayList<String>();
	private static List<String> gatewayType = new ArrayList<String>();
	private static List<String> subProcessType = new ArrayList<String>();
	private static Color RUNNING_COLOR = Color.RED;
	private static Color HISTORY_COLOR = Color.GREEN;
	private static Color SKIP_COLOR = Color.GRAY;
	private static Stroke THICK_BORDER_STROKE = new BasicStroke(3.0F);
	private int minX;
	private int minY;

	public CustomProcessDiagramGenerator() {
		init();
	}

	protected static void init() {
		taskType.add("manualTask");
		taskType.add("receiveTask");
		taskType.add("scriptTask");
		taskType.add("sendTask");
		taskType.add("serviceTask");
		taskType.add("userTask");

		gatewayType.add("exclusiveGateway");
		gatewayType.add("inclusiveGateway");
		gatewayType.add("eventBasedGateway");
		gatewayType.add("parallelGateway");

		eventType.add("intermediateTimer");
		eventType.add("intermediateMessageCatch");
		eventType.add("intermediateSignalCatch");
		eventType.add("intermediateSignalThrow");
		eventType.add("messageStartEvent");
		eventType.add("startTimerEvent");
		eventType.add("error");
		eventType.add("startEvent");
		eventType.add("errorEndEvent");
		eventType.add("endEvent");

		subProcessType.add("subProcess");
		subProcessType.add("callActivity");
	}

	public InputStream generateDiagram(String processInstanceId)
			throws IOException {
		HistoricProcessInstance historicProcessInstance = Context
				.getCommandContext().getHistoricProcessInstanceEntityManager()
				.findHistoricProcessInstance(processInstanceId);

		String processDefinitionId = historicProcessInstance
				.getProcessDefinitionId();

		GetBpmnModelCmd getBpmnModelCmd = new GetBpmnModelCmd(
				processDefinitionId);

		BpmnModel bpmnModel = getBpmnModelCmd.execute(Context
				.getCommandContext());
		ProcessDefinitionEntity processDefinition = new GetDeploymentProcessDefinitionCmd(
				processDefinitionId).execute(Context.getCommandContext());
		String diagramResourceName = processDefinition.getDiagramResourceName();
		String deploymentId = processDefinition.getDeploymentId();
		ProcessEngineConfiguration processEngineConfiguration = Context
				.getProcessEngineConfiguration();

		byte[] bytes = Context
				.getCommandContext()
				.getResourceEntityManager()
				.findResourceByDeploymentIdAndResourceName(deploymentId,
						diagramResourceName).getBytes();

		InputStream is = new ByteArrayInputStream(bytes);

		BufferedImage image = ImageIO.read(is);
		Point point = getMinXAndMinY(bpmnModel);
		this.minX = point.x;
		this.minY = point.y;
		this.minX = ((this.minX <= 5) ? 5 : this.minX);
		this.minY = ((this.minY <= 5) ? 5 : this.minY);
		this.minX -= 5;
		this.minY -= 5;

		ProcessDefinitionEntity definition = new GetDeploymentProcessDefinitionCmd(
				processDefinitionId).execute(Context.getCommandContext());

		HistoricActivityInstanceQueryImpl historicActivityInstanceQueryImpl = new HistoricActivityInstanceQueryImpl();
		historicActivityInstanceQueryImpl.processInstanceId(processInstanceId)
				.orderByHistoricActivityInstanceStartTime().asc();

		Page page = new Page(0, 100);
		List<HistoricActivityInstance> activityInstances = Context
				.getCommandContext()
				.getHistoricActivityInstanceEntityManager()
				.findHistoricActivityInstancesByQueryCriteria(
						historicActivityInstanceQueryImpl, page);

		drawHistoryFlow(image, processInstanceId);

		for (HistoricActivityInstance historicActivityInstance : activityInstances) {
			String historicActivityId = historicActivityInstance
					.getActivityId();

			ActivityImpl activity = definition.findActivity(historicActivityId);

			if (activity != null) {
				if (historicActivityInstance.getEndTime() == null) {
					signRunningNode(image, activity.getX() - this.minX,
							activity.getY() - this.minY, activity.getWidth(),
							activity.getHeight(),
							historicActivityInstance.getActivityType());
				} else {
					String deleteReason = null;

					if (historicActivityInstance.getTaskId() != null) {
						deleteReason = Context
								.getCommandContext()
								.getHistoricTaskInstanceEntityManager()
								.findHistoricTaskInstanceById(
										historicActivityInstance.getTaskId())
								.getDeleteReason();
					}

					if ("跳过".equals(deleteReason)) {
						signSkipNode(image, activity.getX() - this.minX,
								activity.getY() - this.minY,
								activity.getWidth(), activity.getHeight(),
								historicActivityInstance.getActivityType());
					} else {
						signHistoryNode(image, activity.getX() - this.minX,
								activity.getY() - this.minY,
								activity.getWidth(), activity.getHeight(),
								historicActivityInstance.getActivityType());
					}

				}

			}

		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		String formatName = getDiagramExtension(diagramResourceName);
		ImageIO.write(image, formatName, out);
		return new ByteArrayInputStream(out.toByteArray());
	}

	private static String getDiagramExtension(String diagramResourceName) {
		String ext = diagramResourceName.substring(diagramResourceName
				.lastIndexOf("."));
		if (ext != null && ext.length() > 0) {
			return ext.substring(1);
		}
		return "";
	}

	private static void signRunningNode(BufferedImage image, int x, int y,
			int width, int height, String activityType) {
		Color nodeColor = RUNNING_COLOR;
		Graphics2D graphics = image.createGraphics();
		try {
			drawNodeBorder(x, y, width, height, graphics, nodeColor,
					activityType);
		} finally {
			graphics.dispose();
		}
	}

	private static void signHistoryNode(BufferedImage image, int x, int y,
			int width, int height, String activityType) {
		Color nodeColor = HISTORY_COLOR;
		Graphics2D graphics = image.createGraphics();
		try {
			drawNodeBorder(x, y, width, height, graphics, nodeColor,
					activityType);
		} finally {
			graphics.dispose();
		}
	}

	private static void signSkipNode(BufferedImage image, int x, int y,
			int width, int height, String activityType) {
		Color nodeColor = SKIP_COLOR;
		Graphics2D graphics = image.createGraphics();
		try {
			drawNodeBorder(x, y, width, height, graphics, nodeColor,
					activityType);
		} finally {
			graphics.dispose();
		}
	}

	protected static void drawNodeBorder(int x, int y, int width, int height,
			Graphics2D graphics, Color color, String activityType) {
		graphics.setPaint(color);
		graphics.setStroke(THICK_BORDER_STROKE);

		if (taskType.contains(activityType))
			drawTask(x, y, width, height, graphics);
		else if (gatewayType.contains(activityType))
			drawGateway(x, y, width, height, graphics);
		else if (eventType.contains(activityType))
			drawEvent(x, y, width, height, graphics);
		else if (subProcessType.contains(activityType))
			drawSubProcess(x, y, width, height, graphics);
	}

	protected static void drawTask(int x, int y, int width, int height,
			Graphics2D graphics) {
		RoundRectangle2D rect = new RoundRectangle2D.Double(x, y, width,
				height, 20.0D, 20.0D);

		graphics.draw(rect);
	}

	protected static void drawGateway(int x, int y, int width, int height,
			Graphics2D graphics) {
		Polygon rhombus = new Polygon();
		rhombus.addPoint(x, y + height / 2);
		rhombus.addPoint(x + width / 2, y + height);
		rhombus.addPoint(x + width, y + height / 2);
		rhombus.addPoint(x + width / 2, y);
		graphics.draw(rhombus);
	}

	protected static void drawEvent(int x, int y, int width, int height,
			Graphics2D graphics) {
		Ellipse2D.Double circle = new Ellipse2D.Double(x, y, width, height);
		graphics.draw(circle);
	}

	protected static void drawSubProcess(int x, int y, int width, int height,
			Graphics2D graphics) {
		RoundRectangle2D rect = new RoundRectangle2D.Double(x + 1, y + 1,
				width - 2, height - 2, 5.0D, 5.0D);

		graphics.draw(rect);
	}

	protected Point getMinXAndMinY(BpmnModel bpmnModel) {
		Iterator<GraphicInfo> i$;
		GraphicInfo graphicInfo;
		double theMinX = 1.7976931348623157E+308D;
		double theMaxX = 0.0D;
		double theMinY = 1.7976931348623157E+308D;
		double theMaxY = 0.0D;

		for (Pool pool : bpmnModel.getPools()) {
			GraphicInfo tmp = bpmnModel.getGraphicInfo(pool.getId());
			theMinX = tmp.getX();
			theMaxX = tmp.getX() + tmp.getWidth();
			theMinY = tmp.getY();
			theMaxY = tmp.getY() + tmp.getHeight();
		}

		List<FlowNode> flowNodes = gatherAllFlowNodes(bpmnModel);

		for (FlowNode flowNode : flowNodes) {
			GraphicInfo flowNodeGraphicInfo = bpmnModel.getGraphicInfo(flowNode
					.getId());

			if (flowNodeGraphicInfo.getX() + flowNodeGraphicInfo.getWidth() > theMaxX) {
				theMaxX = flowNodeGraphicInfo.getX()
						+ flowNodeGraphicInfo.getWidth();
			}

			if (flowNodeGraphicInfo.getX() < theMinX) {
				theMinX = flowNodeGraphicInfo.getX();
			}

			if (flowNodeGraphicInfo.getY() + flowNodeGraphicInfo.getHeight() > theMaxY) {
				theMaxY = flowNodeGraphicInfo.getY()
						+ flowNodeGraphicInfo.getHeight();
			}

			if (flowNodeGraphicInfo.getY() < theMinY) {
				theMinY = flowNodeGraphicInfo.getY();
			}

			for (SequenceFlow sequenceFlow : flowNode.getOutgoingFlows()) {
				List<GraphicInfo> graphicInfoList = bpmnModel
						.getFlowLocationGraphicInfo(sequenceFlow.getId());

				for (GraphicInfo tmp : graphicInfoList) {
					if (tmp.getX() > theMaxX) {
						theMaxX = tmp.getX();
					}

					if (tmp.getX() < theMinX) {
						theMinX = tmp.getX();
					}

					if (tmp.getY() > theMaxY) {
						theMaxY = tmp.getY();
					}

					if (tmp.getY() < theMinY)
						theMinY = tmp.getY();
				}
			}

		}

		List<Artifact> artifacts = gatherAllArtifacts(bpmnModel);

		for (Artifact artifact : artifacts) {
			GraphicInfo artifactGraphicInfo = bpmnModel.getGraphicInfo(artifact
					.getId());

			if (artifactGraphicInfo != null) {
				if (artifactGraphicInfo.getX() + artifactGraphicInfo.getWidth() > theMaxX) {
					theMaxX = artifactGraphicInfo.getX()
							+ artifactGraphicInfo.getWidth();
				}

				if (artifactGraphicInfo.getX() < theMinX) {
					theMinX = artifactGraphicInfo.getX();
				}

				if (artifactGraphicInfo.getY()
						+ artifactGraphicInfo.getHeight() > theMaxY) {
					theMaxY = artifactGraphicInfo.getY()
							+ artifactGraphicInfo.getHeight();
				}

				if (artifactGraphicInfo.getY() < theMinY)
					theMinY = artifactGraphicInfo.getY();

			}

			List<GraphicInfo> graphicInfoList = bpmnModel
					.getFlowLocationGraphicInfo(artifact.getId());

			if (graphicInfoList != null)
				for (i$ = graphicInfoList.iterator(); i$.hasNext();) {
					graphicInfo = (GraphicInfo) i$.next();

					if (graphicInfo.getX() > theMaxX) {
						theMaxX = graphicInfo.getX();
					}

					if (graphicInfo.getX() < theMinX) {
						theMinX = graphicInfo.getX();
					}

					if (graphicInfo.getY() > theMaxY) {
						theMaxY = graphicInfo.getY();
					}

					if (graphicInfo.getY() < theMinY)
						theMinY = graphicInfo.getY();
				}

		}

		int nrOfLanes = 0;

		for (Process process : bpmnModel.getProcesses()) {
			for (Lane l : process.getLanes()) {
				++nrOfLanes;

				graphicInfo = bpmnModel.getGraphicInfo(l.getId());

				if (graphicInfo.getX() + graphicInfo.getWidth() > theMaxX) {
					theMaxX = graphicInfo.getX() + graphicInfo.getWidth();
				}

				if (graphicInfo.getX() < theMinX) {
					theMinX = graphicInfo.getX();
				}

				if (graphicInfo.getY() + graphicInfo.getHeight() > theMaxY) {
					theMaxY = graphicInfo.getY() + graphicInfo.getHeight();
				}

				if (graphicInfo.getY() < theMinY)
					theMinY = graphicInfo.getY();

			}

		}

		if ((flowNodes.size() == 0) && (bpmnModel.getPools().size() == 0)
				&& (nrOfLanes == 0)) {
			theMinX = 0.0D;
			theMinY = 0.0D;
		}

		return new Point((int) theMinX, (int) theMinY);
	}

	protected static List<Artifact> gatherAllArtifacts(BpmnModel bpmnModel) {
		List<Artifact> artifacts = new ArrayList<Artifact>();

		for (Process process : bpmnModel.getProcesses()) {
			artifacts.addAll(process.getArtifacts());
		}

		return artifacts;
	}

	protected static List<FlowNode> gatherAllFlowNodes(BpmnModel bpmnModel) {
		List<FlowNode> flowNodes = new ArrayList<FlowNode>();

		for (Process process : bpmnModel.getProcesses()) {
			flowNodes.addAll(gatherAllFlowNodes(process));
		}

		return flowNodes;
	}

	protected static List<FlowNode> gatherAllFlowNodes(
			FlowElementsContainer flowElementsContainer) {
		List<FlowNode> flowNodes = new ArrayList<FlowNode>();

		for (FlowElement flowElement : flowElementsContainer.getFlowElements()) {
			if (flowElement instanceof FlowNode) {
				flowNodes.add((FlowNode) flowElement);
			}

			if (flowElement instanceof FlowElementsContainer) {
				flowNodes
						.addAll(gatherAllFlowNodes((FlowElementsContainer) flowElement));
			}

		}

		return flowNodes;
	}

	public void drawHistoryFlow(BufferedImage image, String processInstanceId) {
		HistoricProcessInstance historicProcessInstance = Context
				.getCommandContext().getHistoricProcessInstanceEntityManager()
				.findHistoricProcessInstance(processInstanceId);

		String processDefinitionId = historicProcessInstance
				.getProcessDefinitionId();

		Graph graph = new ActivitiHistoryGraphBuilder(processInstanceId)
				.build();

		for (Edge edge : graph.getEdges())
			drawSequenceFlow(image, processDefinitionId, edge.getName());
	}

	public void drawSequenceFlow(BufferedImage image,
			String processDefinitionId, String sequenceFlowId) {
		GetBpmnModelCmd getBpmnModelCmd = new GetBpmnModelCmd(
				processDefinitionId);

		BpmnModel bpmnModel = getBpmnModelCmd.execute(Context
				.getCommandContext());

		Graphics2D graphics = image.createGraphics();
		graphics.setPaint(HISTORY_COLOR);
		graphics.setStroke(new BasicStroke(2.0F));
		try {
			List<GraphicInfo> graphicInfoList = bpmnModel
					.getFlowLocationGraphicInfo(sequenceFlowId);

			int[] xPoints = new int[graphicInfoList.size()];
			int[] yPoints = new int[graphicInfoList.size()];

			for (int i = 1; i < graphicInfoList.size(); ++i) {
				GraphicInfo graphicInfo = (GraphicInfo) graphicInfoList.get(i);
				GraphicInfo previousGraphicInfo = (GraphicInfo) graphicInfoList
						.get(i - 1);

				if (i == 1) {
					xPoints[0] = ((int) previousGraphicInfo.getX() - this.minX);
					yPoints[0] = ((int) previousGraphicInfo.getY() - this.minY);
				}

				xPoints[i] = ((int) graphicInfo.getX() - this.minX);
				yPoints[i] = ((int) graphicInfo.getY() - this.minY);
			}

			int radius = 15;

			Path2D path = new Path2D.Double();

			for (int i = 0; i < xPoints.length; ++i) {
				Integer anchorX = Integer.valueOf(xPoints[i]);
				Integer anchorY = Integer.valueOf(yPoints[i]);

				double targetX = anchorX.intValue();
				double targetY = anchorY.intValue();

				double ax = 0.0D;
				double ay = 0.0D;
				double bx = 0.0D;
				double by = 0.0D;
				double zx = 0.0D;
				double zy = 0.0D;

				if ((i > 0) && (i < xPoints.length - 1)) {
					Integer cx = anchorX;
					Integer cy = anchorY;

					double lineLengthY = yPoints[i] - yPoints[(i - 1)];

					double lineLengthX = xPoints[i] - xPoints[(i - 1)];
					double lineLength = Math.sqrt(Math.pow(lineLengthY, 2.0D)
							+ Math.pow(lineLengthX, 2.0D));

					double dx = lineLengthX * radius / lineLength;
					double dy = lineLengthY * radius / lineLength;
					targetX -= dx;
					targetY -= dy;

					if ((lineLength < 2 * radius) && (i > 1)) {
						targetX = xPoints[i] - lineLengthX / 2.0D;
						targetY = yPoints[i] - lineLengthY / 2.0D;
					}

					lineLengthY = yPoints[(i + 1)] - yPoints[i];
					lineLengthX = xPoints[(i + 1)] - xPoints[i];
					lineLength = Math.sqrt(Math.pow(lineLengthY, 2.0D)
							+ Math.pow(lineLengthX, 2.0D));

					if (lineLength < radius) {
						lineLength = radius;
					}

					dx = lineLengthX * radius / lineLength;
					dy = lineLengthY * radius / lineLength;

					double nextSrcX = xPoints[i] + dx;
					double nextSrcY = yPoints[i] + dy;

					if ((lineLength < 2 * radius) && (i < xPoints.length - 2)) {
						nextSrcX = xPoints[i] + lineLengthX / 2.0D;
						nextSrcY = yPoints[i] + lineLengthY / 2.0D;
					}

					double dx0 = (cx.intValue() - targetX) / 3.0D;
					double dy0 = (cy.intValue() - targetY) / 3.0D;
					ax = cx.intValue() - dx0;
					ay = cy.intValue() - dy0;

					double dx1 = (cx.intValue() - nextSrcX) / 3.0D;
					double dy1 = (cy.intValue() - nextSrcY) / 3.0D;
					bx = cx.intValue() - dx1;
					by = cy.intValue() - dy1;

					zx = nextSrcX;
					zy = nextSrcY;
				}

				if (i == 0)
					path.moveTo(targetX, targetY);
				else {
					path.lineTo(targetX, targetY);
				}

				if ((i > 0) && (i < xPoints.length - 1)) {
					path.curveTo(ax, ay, bx, by, zx, zy);
				}
			}

			graphics.draw(path);

			Line2D.Double line = new Line2D.Double(
					xPoints[(xPoints.length - 2)],
					yPoints[(xPoints.length - 2)],
					xPoints[(xPoints.length - 1)],
					yPoints[(xPoints.length - 1)]);

			int ARROW_WIDTH = 5;
			int doubleArrowWidth = 2 * ARROW_WIDTH;
			Polygon arrowHead = new Polygon();
			arrowHead.addPoint(0, 0);
			arrowHead.addPoint(-ARROW_WIDTH, -doubleArrowWidth);
			arrowHead.addPoint(ARROW_WIDTH, -doubleArrowWidth);

			AffineTransform transformation = new AffineTransform();
			transformation.setToIdentity();

			double angle = Math.atan2(line.y2 - line.y1, line.x2 - line.x1);
			transformation.translate(line.x2, line.y2);
			transformation.rotate(angle - 1.570796326794897D);

			AffineTransform originalTransformation = graphics.getTransform();
			graphics.setTransform(transformation);
			graphics.fill(arrowHead);
			graphics.setTransform(originalTransformation);
		} finally {
			graphics.dispose();
		}
	}
}
