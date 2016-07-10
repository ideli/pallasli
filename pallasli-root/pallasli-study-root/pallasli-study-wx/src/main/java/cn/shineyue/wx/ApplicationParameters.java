package cn.shineyue.wx;

import cn.shineyue.wx.context.WebContextUtil;

/**
 * @Description 系统参数spring注入
 * @author zhangchanglong
 * @created zhangchanglong  Aug 5, 2015
 * @version 1
 */
public class ApplicationParameters {
	
	private static ApplicationParameters singleton;
	
	private String  deployedUrl; //程序发布的地址，用于拼接一些链接
	private String  customerName; //客户名称，用于组装一些消息
	private String	customerAddress; //客户地址
	private String	customerTel; //客户联系电话
	private String	customerEmail; //客户邮箱
	private String	customerProfile; //客户简介
	private String[] wxTips; //消息提示数组，计划用于接收到不识别信息时 随机返回
	
	private ApplicationParameters(){
	}

	public static ApplicationParameters instance(){
		
		
		if(singleton == null){
			singleton = (ApplicationParameters)WebContextUtil.getBean("applicationParameters");
		}
		return singleton;
	}
	
	public String getDeployedUrl() {
    	return deployedUrl;
    }

	public void setDeployedUrl(String deployedUrl) {
    	this.deployedUrl = deployedUrl;
    }

	public String getCustomerName() {
    	return customerName;
    }

	public void setCustomerName(String customerName) {
    	this.customerName = customerName;
    }

	public String[] getWxTips() {
    	return wxTips;
    }

	public void setWxTips(String[] wxTips) {
    	this.wxTips = wxTips;
    }

	public String getCustomerAddress() {
    	return customerAddress;
    }

	public void setCustomerAddress(String customerAddress) {
    	this.customerAddress = customerAddress;
    }

	public String getCustomerTel() {
    	return customerTel;
    }

	public void setCustomerTel(String customerTel) {
    	this.customerTel = customerTel;
    }

	public String getCustomerEmail() {
    	return customerEmail;
    }

	public void setCustomerEmail(String customerEmail) {
    	this.customerEmail = customerEmail;
    }

	public String getCustomerProfile() {
    	return customerProfile;
    }

	public void setCustomerProfile(String customerProfile) {
    	this.customerProfile = customerProfile;
    }
	

}
