package com.pallasli.bpm.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.GraphicInfo;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.pallasli.bpm.service.ProcessDiagramService;

public class DefaultProcessDiagramServiceImpl implements ProcessDiagramService {

	@Autowired
	RepositoryService repositoryService;

	@Override
	public void getDiagram(String proDefId) {

		ProcessDefinition pd = ((RepositoryServiceImpl) repositoryService).createProcessDefinitionQuery()
				.deploymentId(proDefId).singleResult();

		BpmnModel bpmnModel = repositoryService.getBpmnModel(pd.getId());
		System.out.println(bpmnModel);
		InputStream in = new DefaultProcessDiagramGenerator().generatePngDiagram(bpmnModel);
		try {
			File f = new File("test");
			if (!f.exists())
				f.mkdir();
			f = new File("test/tmp.png");
			if (!f.exists())
				f.createNewFile();

			FileUtils.copyInputStreamToFile(in, new File("test/tmp.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void getDiagramWithCoords(String proDefId) {
		ProcessDefinition pd = ((RepositoryServiceImpl) repositoryService).createProcessDefinitionQuery()
				.deploymentId(proDefId).singleResult();
		ProcessDefinitionEntity pde = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(pd.getId());

		Map<String, String> coordsMap = new HashMap<String, String>();
		List<ActivityImpl> actList = pde.getActivities();

		BpmnModel bpmnModel = repositoryService.getBpmnModel(pd.getId());
		for (ActivityImpl act : actList) {

			coordsMap.put(act.getId(), "{x:" + act.getX() + ",y:" + act.getY() + ",height:" + act.getHeight()
					+ ",width:" + act.getWidth());
			List<GraphicInfo> flowList = bpmnModel.getFlowLocationGraphicInfo(act.getId());
			// for (GraphicInfo flow : flowList) {
			// System.out.println("flow");
			// }
			List<PvmTransition> outs = act.getOutgoingTransitions();
			for (PvmTransition out : outs) {
				System.out.println("outs");
			}
		}
		System.out.println(coordsMap.toString());
	}

}
