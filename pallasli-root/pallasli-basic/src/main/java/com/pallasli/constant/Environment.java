package com.pallasli.constant;

public class Environment {

	// Java 运行时环境版本
	public static final String JAVA_VERSION = System
			.getProperty("java.version");
	// Java 运行时环境供应商
	public static final String JAVA_VENDOR = System.getProperty("java.vendor");
	// Java 供应商的 URL

	public static final String JAVA_VENDOR_URL = System
			.getProperty("java.vendor.url");
	// Java 安装目录
	public static final String JAVA_HOME = System.getProperty("java.home");
	// Java 虚拟机规范版本
	public static final String JAVA_VM_SPECIFICATION_VERSION = System
			.getProperty("java.vm.specification.version");
	// Java 虚拟机规范供应商
	public static final String JAVA_VM_SPECIFICATION_VENDOR = System
			.getProperty("java.vm.specification.vendor");
	// Java 虚拟机规范名称
	public static final String JAVA_VM_SPECIFICATION_NAME = System
			.getProperty("java.vm.specification.name");
	// Java 虚拟机实现版本
	public static final String JAVA_VM_VERSION = System
			.getProperty("java.vm.version");
	// Java 虚拟机实现供应商
	public static final String JAVA_VM_VENDOR = System
			.getProperty("java.vm.vendor");
	// Java 虚拟机实现名称
	public static final String JAVA_VM_NAME = System
			.getProperty("java.vm.name");
	// Java 运行时环境规范版本
	public static final String JAVA_SPECIFICATION_VERSION = System
			.getProperty("java.specification.version");
	// Java 运行时环境规范供应商
	public static final String JAVA_SPECIFICATION_VENDOR = System
			.getProperty("java.specification.vendor");
	// Java 运行时环境规范名称
	public static final String JAVA_SPECIFICATION_NAME = System
			.getProperty("java.specification.name");
	// Java 类格式版本号
	public static final String JAVA_CLASS_VERSION = System
			.getProperty("java.class.version");
	// Java 类路径
	public static final String JAVA_CLASS_PATH = System
			.getProperty("java.class.path");
	// 加载库时搜索的路径列表
	public static final String JAVA_LIBRARY_PATH = System
			.getProperty("java.library.path");
	// 默认的临时文件路径
	public static final String JAVA_IO_TMPDIR = System
			.getProperty("java.io.tmpdir");
	// 要使用的 JIT 编译器的名称
	public static final String JAVA_COMPILER = System
			.getProperty("java.compiler");
	// 一个或多个扩展目录的路径
	public static final String JAVA_EXT_DIRS = System
			.getProperty("java.ext.dirs");
	// 操作系统的名称
	public static final String OS_NAME = System.getProperty("os.name");
	// 操作系统的架构
	public static final String OS_ARCH = System.getProperty("os.arch");
	// 操作系统的版本
	public static final String OS_VERSION = System.getProperty("os.version");
	// 文件分隔符（在 UNIX 系统中是“/”）
	public static final String FILE_SEPARATOR = System
			.getProperty("file.separator");
	// 路径分隔符（在 UNIX 系统中是“:”）
	public static final String PATH_SEPARATOR = System
			.getProperty("path.separator");
	// 行分隔符（在 UNIX 系统中是“/n”）
	public static final String LINE_SEPARATOR = System
			.getProperty("line.separator");
	// 用户的账户名称
	public static final String USER_NAME = System.getProperty("user.name");
	// 用户的主目录
	public static final String USER_HOME = System.getProperty("user.home");
	// 用户的当前工作目录
	public static final String USER_DIR = System.getProperty("user.dir");

}
