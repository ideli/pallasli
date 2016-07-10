package cn.shineyue.wx.business;

import cn.shineyue.wx.ApplicationParameters;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxMenu;
import me.chanjar.weixin.common.bean.WxMenu.WxMenuButton;

public class BusinessMessage {	
	
	private static String atwaMobileUrl = ApplicationParameters.instance().getDeployedUrl();

	/**
	 * 欢迎信息，主菜单
	 * @return
	 */
	public static String getWelcomeText(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("欢迎关注伟奥软件官方微信,伟奥软件为您提供完整的住房公积金服务.").append("/:share").append("\n");
		buffer.append("请输入以下数字办理业务：").append("\n");
		buffer.append("0：查看 帮助菜单").append("\n");
		buffer.append("1：查看 服务流程").append("\n");
		buffer.append("2：查看 政策须知").append("\n");
		buffer.append("3：查询 公积金余额").append("\n");
		buffer.append("4：贷款额度计算器").append("\n");
		buffer.append("感谢您的关注.").append(atwaMobileUrl).append("/::)");
		return buffer.toString();		
	}
	
	/**
	 * 服务流程 菜单
	 * @return
	 */
	public static String getFwlcText(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("请回复以下数字办理业务：").append("\n");
		buffer.append("11：查看 缴存登记 流程").append("\n");
		buffer.append("12：查看 单位汇缴 流程").append("\n");
		buffer.append("13：查看 账户转移 流程").append("\n");
		buffer.append("14：查看 基数调整 流程").append("\n");
		buffer.append("15：查看 提取业务 流程").append("\n");
		buffer.append("16：查看 贷款业务 流程").append("\n");
		return buffer.toString();	
	}
	
	/**
	 * 政策须知 菜单
	 * @return
	 */
	public static String getZcxzText(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("请回复以下数字办理业务：").append("\n");
		buffer.append("21：查看 汇缴业务 政策").append("\n");
		buffer.append("22：查看 提取业务 政策").append("\n");
		buffer.append("23：查看 贷款业务 政策").append("\n");
		buffer.append("24：查看 还款业务 政策").append("\n");
		return buffer.toString();	
	}	
	
	public static WxMenu getMenu(){
		//第一个菜单的5个子菜单
		WxMenuButton btn11 = new WxMenuButton();  
        btn11.setName("基本信息");  
        btn11.setType(WxConsts.BUTTON_CLICK);  
        btn11.setKey("11");  
  
        WxMenuButton btn12 = new WxMenuButton();  
        btn12.setName("缴存余额");  
        btn12.setType(WxConsts.BUTTON_CLICK);  
        btn12.setKey("12");
        
        WxMenuButton btn13 = new WxMenuButton();  
        btn13.setName("缴存明细");  
        btn13.setType(WxConsts.BUTTON_CLICK);  
        btn13.setKey("13");
        
        WxMenuButton btn14 = new WxMenuButton();  
        btn14.setName("还款明细");  
        btn14.setType(WxConsts.BUTTON_CLICK);  
        btn14.setKey("14");
        
        WxMenuButton btn15 = new WxMenuButton();  
        btn15.setName("还款计划");  
        btn15.setType(WxConsts.BUTTON_CLICK);  
        btn15.setKey("15");
    
        //第2个菜单的4个子菜单
        WxMenuButton btn21 = new WxMenuButton();  
        btn21.setName("绑定账户");  
        btn21.setType(WxConsts.BUTTON_CLICK);  
        btn21.setKey("21");  
  
        WxMenuButton btn22 = new WxMenuButton();  
        btn22.setName("还款转账");  
        btn22.setType(WxConsts.BUTTON_CLICK);  
        btn22.setKey("22");  
        
        WxMenuButton btn23 = new WxMenuButton();  
        btn23.setName("提取预约");  
        btn23.setType(WxConsts.BUTTON_CLICK);  
        btn23.setKey("23");  
        
        WxMenuButton btn24 = new WxMenuButton();  
        btn24.setName("贷款预约");  
        btn24.setType(WxConsts.BUTTON_CLICK);  
        btn24.setKey("24");  
        
        //第3个菜单
        WxMenuButton btn31 = new WxMenuButton();  
        btn31.setName("中心政策");  
        btn31.setType(WxConsts.BUTTON_VIEW);  
        btn31.setUrl(atwaMobileUrl + "/html/zxzcIndex.html"); 
  
        //5个子菜单加到主菜单1中
        WxMenuButton mainBtn1 = new WxMenuButton();  
        mainBtn1.setName("我要查询");  
        mainBtn1.getSubButtons().add(btn11);  
        mainBtn1.getSubButtons().add(btn12);  
        mainBtn1.getSubButtons().add(btn13);  
        mainBtn1.getSubButtons().add(btn14);  
        mainBtn1.getSubButtons().add(btn15);  
  
        //4个子菜单加到主菜单2中
        WxMenuButton mainBtn2 = new WxMenuButton();  
        mainBtn2.setName("我要办理");  
        mainBtn2.getSubButtons().add(btn21); 
        mainBtn2.getSubButtons().add(btn22); 
        mainBtn2.getSubButtons().add(btn23); 
        mainBtn2.getSubButtons().add(btn24); 
        
        //3个主菜单 加到 整个菜单中
        WxMenu menu = new WxMenu();  
        menu.getButtons().add(mainBtn1);
        menu.getButtons().add(mainBtn2);
        menu.getButtons().add(btn31);		
		
		return menu;
	}
	
	/**
	 * 绑定微信 返回文本
	 * @return
	 */
	public static String getRegisterText(String openid){
		StringBuffer buffer = new StringBuffer();
		buffer.append("欢迎使用伟奥微信公众平台绑定功能,请点击<a href=\"").append(atwaMobileUrl).append("/html/register.html?openid=").append(openid).append("\">此处</a>");
		buffer.append("进行绑定.");
		return buffer.toString();	
	}
	
	/**
	 * 不识别指令
	 * @return
	 */
	public static String getUnKnowText(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("不识别的指令,您可以输入 0 查看帮助,也可以通过菜单操作,多谢您的支持.");
		return buffer.toString();	
	}
	
	
	/**
	 * 查询基本信息 返回文本
	 * @return
	 */
	public static String getSearchInfoText(String openid){
		StringBuffer buffer = new StringBuffer();
		buffer.append("欢迎使用伟奥微信公众平台查询功能,请点击<a href=\"").append(atwaMobileUrl).append("/html/searchInfo.html?openid=").append(openid).append("\">此处</a>");
		buffer.append("查询个人基本信息.");
		return buffer.toString();	
	}
	
	/**
	 * 查询缴存余额 返回文本
	 * @return
	 */
	public static String getSearchGzYeText(String openid){
		StringBuffer buffer = new StringBuffer();
		buffer.append("欢迎使用伟奥微信公众平台查询功能,请点击<a href=\"").append(atwaMobileUrl).append("/html/searchGzYe.html?openid=").append(openid).append("\">此处</a>");
		buffer.append("查询缴存余额.");
		return buffer.toString();	
	}
	
	/**
	 * 查询缴存明细 返回文本
	 * @return
	 */
	public static String getSearchGzMx(String openid){
		StringBuffer buffer = new StringBuffer();
		buffer.append("欢迎使用伟奥微信公众平台查询功能,请点击<a href=\"").append(atwaMobileUrl).append("/html/searchGzMxStatic.html?openid=").append(openid).append("\">此处</a>");
		buffer.append("查询缴存明细信息.");
		return buffer.toString();	
	}
	
	/**
	 * 查询还款明细 返回文本
	 * @return
	 */
	public static String getSearchGdMx(String openid,String atwaMobileUrl){
		StringBuffer buffer = new StringBuffer();
		buffer.append("欢迎使用伟奥微信公众平台查询功能,请点击<a href=\"").append(atwaMobileUrl).append("/html/searchGdHkMxStatic.html?openid=").append(openid).append("\">此处</a>");
		buffer.append("查询还款明细信息.");
		return buffer.toString();	
	}
	
	/**
	 * 查询还款计划 返回文本
	 * @return
	 */
	public static String getSearchGdJh(String openid){
		StringBuffer buffer = new StringBuffer();
		buffer.append("欢迎使用伟奥微信公众平台查询功能,请点击<a href=\"").append(atwaMobileUrl).append("/html/searchGdHkJhStatic.html?openid=").append(openid).append("\">此处</a>");
		buffer.append("查询还款明细信息.");
		return buffer.toString();	
	}
	
	/**
	 * 绑定微信 返回文本
	 * @return
	 */
	public static String getCalcDkjsText(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("请点击<a href=\"").append(atwaMobileUrl).append("/html/calcDkjs.html").append("\">此处</a>使用贷款额度计算器");
		return buffer.toString();	
	}
	
	/**
	 * 提取预约 返回文本
	 * @return
	 */
	public static String getDoTqsq(String openid){
		StringBuffer buffer = new StringBuffer();
		buffer.append("欢迎使用伟奥微信公众平台预约功能,请点击<a href=\"").append(atwaMobileUrl).append("/html/doTqsq.html?openid=").append(openid).append("\">此处</a>");
		buffer.append("进行提取预约.");
		return buffer.toString();	
	}
	
	/**
	 * 贷款预约 返回文本
	 * @return
	 */
	public static String getDoDksq(String openid){
		StringBuffer buffer = new StringBuffer();
		buffer.append("欢迎使用伟奥微信公众平台预约功能,请点击<a href=\"").append(atwaMobileUrl).append("/html/doDksq.html?openid=").append(openid).append("\">此处</a>");
		buffer.append("进行贷款预约.");
		return buffer.toString();	
	}

}
