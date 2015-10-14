package com.pallasli.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomUtils {
	private static String range = "0123456789abcdefjhijklmnopqrstuvwxyz";

	public static synchronized String getRandomString(int length) {
		Random random = new Random();
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < length; i++) {
			result.append(range.charAt(random.nextInt(range.length())));
		}
		return result.toString();
	}

	// private static DefaultKaptcha producer;
	private static final Map<String, String> captchaMap = new HashMap<String, String>();

	public static void generateCaptchaText(String captchaKey) {
		if (captchaMap != null && !captchaMap.containsKey(captchaKey)) {
			captchaMap.remove(captchaKey);

		}
		captchaMap.put(captchaKey, getRandomString(4));
	}

	// public static byte[] generateCaptchaImage(String captchaKey) {
	//
	// String text = captchaMap.get(captchaKey);
	// producer = new DefaultKaptcha();
	// producer.setConfig(new Config(new Properties()));
	// BufferedImage image = producer.createImage(text);
	// ByteArrayOutputStream out = new ByteArrayOutputStream();
	// try {
	// ImageIO.write(image, "jpg", out);
	//
	// } catch (IOException e) {
	//
	// }
	// return out.toByteArray();
	// }

	public static boolean validateCaptcha(String captchaKey, String captchaValue) {
		String text = captchaMap.get(captchaKey);
		if (text.equals(captchaValue)) {
			captchaMap.remove(captchaKey);
			return true;
		}
		return false;
	}

}
