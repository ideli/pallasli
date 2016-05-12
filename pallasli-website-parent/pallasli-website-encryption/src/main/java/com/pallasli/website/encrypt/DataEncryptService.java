package com.pallasli.website.encrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pallasli.website.encrypt.des.EncryptArithmetic;

public class DataEncryptService {

	private static DataEncryptService singleton;
	private final EncryptArithmetic encryptArthmeticer;
	private final String defaultKey;
	private static final Logger logger = LoggerFactory
			.getLogger(DataEncryptService.class);

	private DataEncryptService() {
		encryptArthmeticer = new EncryptArithmetic();
		defaultKey = "78ueo7-33f-jinckour-8yu3";
	}

	public static DataEncryptService instance() {
		if (singleton == null) {
			singleton = new DataEncryptService();
		}
		return singleton;
	}

	public String encryptIt(String source, String key) {
		if (encryptArthmeticer != null) {
			if (key == null || key.equals("")) {
				key = defaultKey;
			}
			try {
				return encryptArthmeticer.encryptIt(source,
						key.getBytes("ISO-8859-1"));
			} catch (Exception e) {
			}
		}
		logger.error("无法完成串加密");
		return null;
	}

	public String decryptIt(String encoded, String key) {
		if (encryptArthmeticer != null) {
			if (key == null || key.equals("")) {
				key = defaultKey;
			}
			try {
				return encryptArthmeticer.decryptIt(encoded,
						key.getBytes("ISO-8859-1"));
			} catch (Exception e) {
			}
		}
		logger.error("无法完成串解密");
		return null;
	}

}
