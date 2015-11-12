package com.pallasli.run.mac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import org.junit.Test;

public class Command {
	@Test
	public void find() {
		String command = "ps -u lyt1987 | grep sts";
		// command = "./jenkinsStart.sh";
		Runtime run = Runtime.getRuntime();
		try {
			InputStream is = null;
			InputStreamReader isr = null;
			BufferedReader brStat = null;
			StringTokenizer tokenStat = null;
			// Process process = run.exec("cd ~");
			Process process = run.exec(command);
			// Process
			// process = Runtime.getRuntime().exec("top -b -n 1");
			is = process.getInputStream();
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
