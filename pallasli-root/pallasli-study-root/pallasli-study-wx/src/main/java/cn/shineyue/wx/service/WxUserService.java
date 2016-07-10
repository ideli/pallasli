package cn.shineyue.wx.service;

public interface WxUserService {
	
	public boolean searchWxUserExist(String uname,String cardNum,String grbh) throws Exception;
	
	public void insertWxUser(String grbh,String openId) throws Exception;
	
	public void deleteWxUser(String grbh,String openId) throws Exception;

}
