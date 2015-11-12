package com.atwasoft.bpm.image;

import java.io.InputStream;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

public class HistoryProcessInstanceDiagramCmd implements Command<InputStream> {
	protected String historyProcessInstanceId;

	public HistoryProcessInstanceDiagramCmd(String historyProcessInstanceId) {
		this.historyProcessInstanceId = historyProcessInstanceId;
	}

	public InputStream execute(CommandContext commandContext) {
		CustomProcessDiagramGenerator customProcessDiagramGenerator;
		try {
			customProcessDiagramGenerator = new CustomProcessDiagramGenerator();

			return customProcessDiagramGenerator
					.generateDiagram(this.historyProcessInstanceId);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}
}
