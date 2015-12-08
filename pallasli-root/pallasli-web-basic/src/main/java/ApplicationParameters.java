
public class ApplicationParameters {
	private static ApplicationParameters singleton;

	/* System-Runtime-Configuration */
	private String applicationName;
	private String applicationServerType;
	private String fileUploadPath;
	private String resourcePath;
	private boolean useIdentifyCode;
	private String loginPath;
	private String homePath;
	private String headPath;
	private String runMode;
	private boolean jsCompress;
	private String userConfigFolder;
	private String handSignFolder;
	private String sncode;
	private String applicationCode;
	private String host;
	private String caiss;

	public String getCaiss() {
		return caiss;
	}

	public void setCaiss(String caiss) {
		this.caiss = caiss;
	}

	private boolean clusterSyncWithDb;
	private boolean unitSync;

	public boolean isUnitSync() {
		return unitSync;
	}

	public void setUnitSync(boolean unitSync) {
		this.unitSync = unitSync;
	}

	private String OAWSIP; // OA ws IP
	private String OAWSPort; // OA ws 端口
	private String hrWSIP; // 人力资源 ws IP
	private String hrWSPort; // 人力资源 ws 端口

	private boolean caflag;
	private String certIP; // certCA证书IP地址
	private String cerPort; // certCA证书port端口

	public boolean isCaflag() {
		return caflag;
	}

	public void setCaflag(boolean caflag) {
		this.caflag = caflag;
	}

	public String getCertIP() {
		return certIP;
	}

	public void setCertIP(String certIP) {
		this.certIP = certIP;
	}

	public String getCerPort() {
		return cerPort;
	}

	public void setCerPort(String cerPort) {
		this.cerPort = cerPort;
	}

	/* Database-Configuration */

	private ApplicationParameters() {
		clusterSyncWithDb = false;
	}

	public static ApplicationParameters instance() {
		if (singleton == null) {
		}
		return singleton;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getRelateiveFileUploadPath() {
		return fileUploadPath;
	}

	public String getFileUploadPath() {
		return "";
	}

	public void setFileUploadPath(String fileUploadPath) {
		this.fileUploadPath = fileUploadPath;
	}

	public String getApplicationServerType() {
		return applicationServerType;
	}

	public void setApplicationServerType(String applicationServerType) {
		this.applicationServerType = applicationServerType;
	}

	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	public boolean isUseIdentifyCode() {
		return useIdentifyCode;
	}

	public void setUseIdentifyCode(boolean useIdentifyCode) {
		this.useIdentifyCode = useIdentifyCode;
	}

	public String getApplicationLoginPath() {
		return loginPath;
	}

	public void setApplicationLoginPath(String loginPath) {
		this.loginPath = loginPath;
	}

	public String getApplicationHomePath() {
		return homePath;
	}

	public void setApplicationHomePath(String homePath) {
		this.homePath = homePath;
	}

	public String getApplicationHeadPath() {
		return headPath;
	}

	public void setApplicationHeadPath(String headPath) {
		this.headPath = headPath;
	}

	public String getApplicationRunMode() {
		return runMode;
	}

	public void setApplicationRunMode(String runMode) {
		this.runMode = runMode;
	}

	public String getUserConfigFolder() {
		return userConfigFolder;
	}

	public void setUserConfigFolder(String userConfigFolder) {
		this.userConfigFolder = userConfigFolder;
	}

	public boolean getJsCompress() {
		return jsCompress;
	}

	public void setJsCompress(boolean jsCompress) {
		this.jsCompress = jsCompress;
	}

	public String getHandSignFolder() {
		return handSignFolder;
	}

	public void setHandSignFolder(String handSignFolder) {
		this.handSignFolder = handSignFolder;
	}

	public String getSncode() {
		return sncode;
	}

	public void setSncode(String sncode) {
		this.sncode = sncode;
	}

	public String getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getHost() {
		return host;
	}

	public boolean isClusterSyncWithDb() {
		return clusterSyncWithDb;
	}

	public void setClusterSyncWithDb(boolean clusterSyncWithDb) {
		this.clusterSyncWithDb = clusterSyncWithDb;
	}

	public String getHrWSIP() {
		return hrWSIP;
	}

	public void setHrWSIP(String hrWSIP) {
		this.hrWSIP = hrWSIP;
	}

	public String getHrWSPort() {
		return hrWSPort;
	}

	public void setHrWSPort(String hrWSPort) {
		this.hrWSPort = hrWSPort;
	}

	public String getOAWSIP() {
		return OAWSIP;
	}

	public void setOAWSIP(String oawsip) {
		OAWSIP = oawsip;
	}

	public String getOAWSPort() {
		return OAWSPort;
	}

	public void setOAWSPort(String port) {
		OAWSPort = port;
	}
}