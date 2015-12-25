package com.pallasli.study.h2;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;

import org.h2.constant.SysProperties;
import org.h2.tools.Server;
import org.h2.util.StringUtils;
import org.h2.util.Utils;
import org.junit.Test;

public class StartH2 {
	@Test
	public void start() {
		try {
			// 默认端口8082
			Server server = Server.createWebServer("-webPort", "8083");
			server.start();
			// Connection conn=DriverManager.getConnection(url);
			// Server.startWebServer(conn);
			openBrowser("http://localhost:8083");
			System.in.read();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void openBrowser(String url) throws Exception {
		try {
			String osName = StringUtils.toLowerEnglish(Utils.getProperty(
					"os.name", "linux"));
			Runtime rt = Runtime.getRuntime();
			String browser = Utils.getProperty(SysProperties.H2_BROWSER, null);
			if (browser != null) {
				if (browser.startsWith("call:")) {
					browser = browser.substring("call:".length());
					Utils.callStaticMethod(browser, url);
				} else if (browser.indexOf("%url") >= 0) {
					String[] args = StringUtils.arraySplit(browser, ',', false);
					for (int i = 0; i < args.length; i++) {
						args[i] = StringUtils.replaceAll(args[i], "%url", url);
					}
					rt.exec(args);
				} else if (osName.indexOf("windows") >= 0) {
					rt.exec(new String[] { "cmd.exe", "/C", browser, url });
				} else {
					rt.exec(new String[] { browser, url });
				}
				return;
			}
			try {
				Class<?> desktopClass = Class.forName("java.awt.Desktop");
				// Desktop.isDesktopSupported()
				Boolean supported = (Boolean) desktopClass.getMethod(
						"isDesktopSupported").invoke(null, new Object[0]);
				URI uri = new URI(url);
				if (supported) {
					// Desktop.getDesktop();
					Object desktop = desktopClass.getMethod("getDesktop")
							.invoke(null, new Object[0]);
					// desktop.browse(uri);
					desktopClass.getMethod("browse", URI.class).invoke(desktop,
							uri);
					return;
				}
			} catch (Exception e) {
				// ignore
			}
			if (osName.indexOf("windows") >= 0) {
				rt.exec(new String[] { "rundll32",
						"url.dll,FileProtocolHandler", url });
			} else if (osName.indexOf("mac") >= 0
					|| osName.indexOf("darwin") >= 0) {
				// Mac OS: to open a page with Safari, use "open -a Safari"
				Runtime.getRuntime().exec(new String[] { "open", url });
			} else {
				String[] browsers = { "google-chrome", "firefox",
						"mozilla-firefox", "mozilla", "konqueror", "netscape",
						"opera", "midori" };
				boolean ok = false;
				for (String b : browsers) {
					try {
						rt.exec(new String[] { b, url });
						ok = true;
						break;
					} catch (Exception e) {
						// ignore and try the next
					}
				}
				if (!ok) {
					// No success in detection.
					throw new Exception(
							"Browser detection failed and system property "
									+ SysProperties.H2_BROWSER + " not set");
				}
			}
		} catch (Exception e) {
			throw new Exception("Failed to start a browser to open the URL "
					+ url + ": " + e.getMessage());
		}
	}
}
