package com.pallas.ws.startservice;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;

public class DataServer {

	protected ServerSocket serverSocket;
	protected ExecutorService executorService;

	public DataServer() {
		StartService.start();
	}

	public static void main(String[] args) throws IOException {
		new DataServer();
	}

}