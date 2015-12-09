package com.mixky.common.json;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MixkyDesignObjectTypes implements BeanPostProcessor {
	private Log logger;
	private Map<String, String> serializedClasses;
	private static MixkyDesignObjectTypes singleton;

	public MixkyDesignObjectTypes() {
		this.logger = LogFactory.getLog(super.getClass());
	}

	public static MixkyDesignObjectTypes instance() {
		if (singleton == null)
			singleton = new MixkyDesignObjectTypes();

		return singleton;
	}

	public void setSerializedClasses(Map<String, String> serializedClasses) {
		this.serializedClasses = serializedClasses;
	}

	public Class<?> getRegisteredClass(String classpath) {
		if ((this.serializedClasses != null)
				&& (this.serializedClasses.containsKey(classpath))) {
			String classname = this.serializedClasses.get(classpath);
			if ((classname != null) && (!("".equals(classname))))
				try {
					return Class.forName(classname);
				} catch (ClassNotFoundException e) {
					if (this.logger.isDebugEnabled())
						e.printStackTrace();
					else
						this.logger.warn("查询指定类型[" + classpath
								+ "]对应类时出错，ClassNotFoundException for "
								+ classname);
				}

		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public <T> T createDesignObject(String classpath) {
		Class<T> classType = (Class<T>) getRegisteredClass(classpath);
		if (classType != null)
			try {
				return classType.newInstance();
			} catch (InstantiationException e) {
				this.logger.warn("创建类型为[" + classpath
						+ "]的设计对象失败, InstantiationException." + e.getCause());
				if (!(this.logger.isDebugEnabled()))
					return null;
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				this.logger.warn("创建类型为[" + classpath
						+ "]的设计对象失败, IllegalAccessException." + e.getCause());
				if (this.logger.isDebugEnabled())
					e.printStackTrace();
			}

		return null;
	}

	@Override
	public Object postProcessAfterInitialization(Object arg0, String arg1)
			throws BeansException {
		singleton = this;
		return arg0;
	}

	@Override
	public Object postProcessBeforeInitialization(Object arg0, String arg1)
			throws BeansException {
		singleton = this;
		return arg0;
	}
}