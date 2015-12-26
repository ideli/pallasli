package com.pallasli.dubbo.config;

public interface DynamicDubboPortReader {

	void updateProtocolMessage(String protocolConfig, int port);

}
