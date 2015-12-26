package com.pallasli.run.mac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Command {
	public void start(String path) {
		try {
			InputStream is = null;
			InputStreamReader isr = null;
			BufferedReader brStat = null;
			String command = "chmod 777 " + path;
			Runtime run = Runtime.getRuntime();
			Process process = run.exec(command);
			// int ret = process.exitValue();
			int ret = process.waitFor();
			System.out.println(ret);

			if (ret == 0 || false) {
				is = process.getInputStream();
			} else {

				is = process.getErrorStream();
			}
			isr = new InputStreamReader(is);
			brStat = new BufferedReader(isr);
			System.out.println(run.freeMemory());
			System.out.println(run.freeMemory());
			System.out.println(run.freeMemory());
			System.out.println(run.freeMemory());
			System.out.println(run.freeMemory());
			System.out.println(brStat.readLine());

			process.destroy();

			String cmd = "/bin/sh " + path;
			System.out.println(cmd);
			process = run.exec(cmd);
			ret = process.waitFor();
			// ret = process.exitValue();
			if (ret == 0 || false) {
				is = process.getInputStream();
			} else {

				is = process.getErrorStream();
			}
			isr = new InputStreamReader(is);
			brStat = new BufferedReader(isr);
			System.out.println(run.freeMemory());
			System.out.println(run.freeMemory());
			System.out.println(run.freeMemory());
			System.out.println(run.freeMemory());
			System.out.println(run.freeMemory());
			brStat.close();

			process.destroy();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void find() {
		String command[] = new String[] { "/bin/sh", "-c",
				"ps -u lyt1987 | grep sts" };
		// command = "./jenkinsStart.sh";
		Runtime run = Runtime.getRuntime();
		try {
			InputStream is = null;
			InputStreamReader isr = null;
			BufferedReader brStat = null;
			// StringTokenizer tokenStat = null;
			// Process process = run.exec("cd ~");
			Process process = run.exec(command);
			// int ret = process.exitValue();
			int ret = process.waitFor();
			System.out.println(ret);
			// Process
			// process = Runtime.getRuntime().exec("top -b -n 1");
			//
			if (ret == 0) {
				is = process.getInputStream();
			} else {

				is = process.getErrorStream();
			}
			isr = new InputStreamReader(is);
			brStat = new BufferedReader(isr);
			String line = "";
			System.out.println(run.freeMemory());
			System.out.println(brStat.readLine());
			System.out.println(brStat.readLine());
			System.out.println(brStat.readLine());
			System.out.println(brStat.readLine());
			System.out.println(brStat.readLine());
			while ((line = brStat.readLine()) != null) {
				System.out.println("1");
				System.out.println(line);
			}

			process.destroy();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
