package com.pallasli.dubbo.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

import com.alibaba.dubbo.config.ProtocolConfig;

public class DynamicDubboPortReaderImpl implements DynamicDubboPortReader,
		ApplicationContextAware {

	private ConfigurableApplicationContext applicationContext = null;

	private static final String PROTOCOL_NAME = "dubbo";

	private static int RPOTOCOL_PORT = 20880;

	// @Autowired
	// private IComputePortService computePortService;// 获取端口服务

	/* 初始化方法 */

	public void init() {

		// RPOTOCOL_PORT = computePortService.getAddressPort();

		updateProtocolMessage(PROTOCOL_NAME, RPOTOCOL_PORT);

	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {

		this.applicationContext = (ConfigurableApplicationContext) applicationContext;

	}

	@Override
	public void updateProtocolMessage(String protocolConfig, int port) {

		// 判断初始化

		if (!applicationContext.containsBean(protocolConfig)) {

			System.out.println("没有【" + protocolConfig + "】协议");

		}

		// 获取协议

		ProtocolConfig protocolConfigSource = (ProtocolConfig) applicationContext
				.getBean(protocolConfig);

		// 修改协议暴露端口信息

		protocolConfigSource.setPort(port);

	}

}
