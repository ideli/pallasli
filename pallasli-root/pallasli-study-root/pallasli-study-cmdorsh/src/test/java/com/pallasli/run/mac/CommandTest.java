package com.pallasli.run.mac;

import java.net.URI;

import org.junit.Test;

public class CommandTest {
	@Test
	public void find() {
		// try {
		// Runtime.getRuntime().exec(
		// new String[] { "open", "http://www.baidu.com" });
		try {
			Class<?> desktopClass = Class.forName("java.awt.Desktop");
			// Desktop.isDesktopSupported()
			Boolean supported = (Boolean) desktopClass.getMethod(
					"isDesktopSupported").invoke(null, new Object[0]);
			URI uri = new URI("http://www.baidu.com");
			if (supported) {
				// Desktop.getDesktop();
				Object desktop = desktopClass.getMethod("getDesktop").invoke(
						null, new Object[0]);
				// desktop.browse(uri);
				desktopClass.getMethod("browse", URI.class)
						.invoke(desktop, uri);
				return;
			}
		} catch (Exception e) {
			// ignore
		}
		String[] browsers = { "google-chrome", "firefox", "mozilla-firefox",
				"mozilla", "konqueror", "netscape", "opera", "midori" };
		boolean ok = false;
		for (String b : browsers) {
			try {
				Runtime.getRuntime().exec(
						new String[] { b, "http://www.baidu.com" });
				ok = true;
				break;
			} catch (Exception e) {
				// ignore and try the next
			}
		}
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// new Command().find();
		// new Command().start("/Users/lyt1987/nexusStart.sh");
	}
}
