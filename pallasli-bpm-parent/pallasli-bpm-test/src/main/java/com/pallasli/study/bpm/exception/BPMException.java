package com.pallasli.study.bpm.exception;

public class BPMException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8890528860122644054L;

	public BPMException() {
		super("流程内部发生错误");
	}

	public BPMException(String message) {
		super(message);
	}

	public BPMException(String message, Throwable t) {
		super(message, t);
	}

}
