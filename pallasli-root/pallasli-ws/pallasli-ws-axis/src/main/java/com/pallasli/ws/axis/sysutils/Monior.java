package com.pallasli.ws.axis.sysutils;

import java.io.IOException;

public class Monior {
	public static void main(String[] args) throws IOException {
		// AdminClient.main(null);

		// AdminClient
		// .main(new String[] {
		// "/Users/lyt1987/Desktop/GitHub/pallasli/pallasli-root/pallasli-ws/pallasli-ws-axis/src/main/resources/deploy.wsdd",
		// "–p8081" });
		// AdminClient
		// .main(new String[] {
		// "-lhttp://localhost:8080/axiswebProject/services/AdminService",
		// "/Users/lyt1987/Desktop/GitHub/pallasli/pallasli-root/pallasli-ws/pallasli-ws-axis/src/main/resources/deploy.wsdd"

		org.apache.axis.utils.tcpmon.main(null);
		System.in.read();

	}
}
