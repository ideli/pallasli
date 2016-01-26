package com.pallasli.bpm.api.exeption;

public class BpmExecption extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1771784946714113645L;

	public BpmExecption() {
		super("流程 内部发生错误");
	}

	public BpmExecption(String message) {
		super(message);
	}

	public BpmExecption(String message, Throwable t) {
		super(message, t);
	}

}