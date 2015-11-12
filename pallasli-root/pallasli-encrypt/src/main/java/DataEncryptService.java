

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DataEncryptService {

	private static DataEncryptService singleton;
	private final EncryptArithmetic encryptArthmeticer;
	private final String defaultKey;
	private final Log logger;

	private DataEncryptService() {
		encryptArthmeticer = new EncryptArithmetic();
		defaultKey = "78ueo7-33f-jinckour-8yu3";
		logger = LogFactory.getLog(this.getClass());
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
