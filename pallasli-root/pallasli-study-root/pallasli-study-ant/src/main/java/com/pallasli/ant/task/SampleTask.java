package com.pallasli.ant.task;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class SampleTask extends Task {
	private String msg;

	// The method executing the task
	@Override
	public void execute() throws BuildException {
		System.out.println(msg);
	}

	// The setter for the "message" attribute
	public void setMessage(String msg) {
		this.msg = msg;
	}
}