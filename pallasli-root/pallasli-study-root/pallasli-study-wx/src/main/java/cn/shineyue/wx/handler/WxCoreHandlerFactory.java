package cn.shineyue.wx.handler;

import java.util.Map;

import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutNewsMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutTextMessage;

import cn.shineyue.wx.business.BusinessMessage;
import cn.shineyue.wx.ApplicationParameters;

public class WxCoreHandlerFactory {

	private static WxCoreHandlerFactory singleton;

	private WxCoreHandlerFactory() {
	}

	public static WxCoreHandlerFactory getInstance() {
		if(singleton == null){
			singleton = new WxCoreHandlerFactory();
		}
		return singleton;
	}

	/**
	 * 欢迎信息
	 * @return
	 */
	public WxMpMessageHandler getWelcomeHandler() {
		WxMpMessageHandler handler = new WxMpMessageHandler() {
			public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
			        WxMpService wxMpService, WxSessionManager sessionManager) {
				WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT().content(BusinessMessage.getWelcomeText()).fromUser(
				        wxMessage.getToUserName()).toUser(wxMessage.getFromUserName()).build();
				return m;
			}

		};

		return handler;
	}

	/**
	 * 1 -> 服务流程 文本
	 * @return
	 */
	public WxMpMessageHandler getFwlcHandler() {
		WxMpMessageHandler handler = new WxMpMessageHandler() {
			public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
			        WxMpService wxMpService, WxSessionManager sessionManager) {
				WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT().content(BusinessMessage.getFwlcText()).fromUser(
				        wxMessage.getToUserName()).toUser(wxMessage.getFromUserName()).build();
				return m;
			}
		};

		return handler;
	}

	/**
	 * 2 -> 政策须知 文本
	 * @return
	 */
	public WxMpMessageHandler getZcxzHandler() {
		WxMpMessageHandler handler = new WxMpMessageHandler() {
			public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
			        WxMpService wxMpService, WxSessionManager sessionManager) {
				WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT().content(BusinessMessage.getZcxzText()).fromUser(
				        wxMessage.getToUserName()).toUser(wxMessage.getFromUserName()).build();
				return m;
			}
		};

		return handler;
	}

	/**
	 * 绑定文本
	 * @return
	 */
	public WxMpMessageHandler getRegisterHandler() {
		WxMpMessageHandler handler = new WxMpMessageHandler() {
			public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
			        WxMpService wxMpService, WxSessionManager sessionManager) {
				WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT().content(
				        BusinessMessage.getRegisterText(wxMessage.getFromUserName())).fromUser(
				        wxMessage.getToUserName()).toUser(wxMessage.getFromUserName()).build();
				return m;
			}
		};

		return handler;
	}

	/**
	 * 不识别指令
	 * @return
	 */
	public WxMpMessageHandler getUnKnownHandler() {
		WxMpMessageHandler handler = new WxMpMessageHandler() {
			public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
			        WxMpService wxMpService, WxSessionManager sessionManager) {
				WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT().content(BusinessMessage.getUnKnowText()).fromUser(
				        wxMessage.getToUserName()).toUser(wxMessage.getFromUserName()).build();
				return m;
			}
		};

		return handler;
	}

	/**
	 * 查询基本信息
	 * @return
	 */
	public WxMpMessageHandler getSearchInfoHandler() {
		WxMpMessageHandler handler = new WxMpMessageHandler() {
			public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
			        WxMpService wxMpService, WxSessionManager sessionManager) {
				WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT().content(
				        BusinessMessage.getSearchInfoText(wxMessage.getFromUserName())).fromUser(
				        wxMessage.getToUserName()).toUser(wxMessage.getFromUserName()).build();
				return m;
			}
		};

		return handler;
	}

	/**
	 * 查询缴存额
	 * @return
	 */
	public WxMpMessageHandler getGzYeHandler() {
		WxMpMessageHandler handler = new WxMpMessageHandler() {
			public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
			        WxMpService wxMpService, WxSessionManager sessionManager) {
				WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT().content(
				        BusinessMessage.getSearchGzYeText(wxMessage.getFromUserName())).fromUser(wxMessage.getToUserName()).toUser(
				        wxMessage.getFromUserName()).build();
				return m;
			}
		};

		return handler;
	}

	/**
	 * 查询缴存明细
	 * @return
	 */
	public WxMpMessageHandler getGzMxHandler() {
		WxMpMessageHandler handler = new WxMpMessageHandler() {
			public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
			        WxMpService wxMpService, WxSessionManager sessionManager) {
				WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT().content(
				        BusinessMessage.getSearchGzMx(wxMessage.getFromUserName())).fromUser(wxMessage.getToUserName()).toUser(
				        wxMessage.getFromUserName()).build();
				return m;
			}
		};

		return handler;
	}

	/**
	 * 查询还款明细
	 * @return
	 */
	public WxMpMessageHandler getGdMxHandler() {
		WxMpMessageHandler handler = new WxMpMessageHandler() {
			public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
			        WxMpService wxMpService, WxSessionManager sessionManager) {
				WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT().content(
				        BusinessMessage.getSearchGdMx(wxMessage.getFromUserName(), ApplicationParameters.instance()
				                .getDeployedUrl())).fromUser(wxMessage.getToUserName()).toUser(
				        wxMessage.getFromUserName()).build();
				return m;
			}
		};

		return handler;
	}

	/**
	 * 查询还款计划
	 * @return
	 */
	public WxMpMessageHandler getGdJhHandler() {
		WxMpMessageHandler handler = new WxMpMessageHandler() {
			public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
			        WxMpService wxMpService, WxSessionManager sessionManager) {
				WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT().content(
				        BusinessMessage.getSearchGdJh(wxMessage.getFromUserName())).fromUser(wxMessage.getToUserName()).toUser(
				        wxMessage.getFromUserName()).build();
				return m;
			}
		};

		return handler;
	}

	/**
	 * 贷款额度计算器
	 * @return
	 */
	public WxMpMessageHandler getCalcDkjsHandler() {
		WxMpMessageHandler handler = new WxMpMessageHandler() {
			public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
			        WxMpService wxMpService, WxSessionManager sessionManager) {
				WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT().content(
				        BusinessMessage.getCalcDkjsText()).fromUser(
				        wxMessage.getToUserName()).toUser(wxMessage.getFromUserName()).build();
				return m;
			}
		};

		return handler;
	}

	/**
	 * 提取 预约 申请 
	 * @return
	 */
	public WxMpMessageHandler getTqsqHandler() {
		WxMpMessageHandler handler = new WxMpMessageHandler() {
			public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
			        WxMpService wxMpService, WxSessionManager sessionManager) {
				WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT().content(
				        BusinessMessage.getDoTqsq(wxMessage.getFromUserName())).fromUser(wxMessage.getToUserName()).toUser(
				        wxMessage.getFromUserName()).build();
				return m;
			}
		};

		return handler;
	}

	/**
	 * 贷款 预约 申请
	 * @return
	 */
	public WxMpMessageHandler getDksqHandler() {
		WxMpMessageHandler handler = new WxMpMessageHandler() {
			public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
			        WxMpService wxMpService, WxSessionManager sessionManager) {
				WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT().content(
				        BusinessMessage.getDoDksq(wxMessage.getFromUserName())).fromUser(wxMessage.getToUserName()).toUser(
				        wxMessage.getFromUserName()).build();
				return m;
			}
		};

		return handler;
	}

	/**
	 * 11-缴存登记流程
	 * @return
	 */
	public WxMpMessageHandler getJcdjLcHandler() {
		WxMpMessageHandler handler = new WxMpMessageHandler() {
			public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
			        WxMpService wxMpService, WxSessionManager sessionManager) {
				WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();
				item.setDescription("1、贷款申请。申请人申请住房公积金贷款的，应持相关贷款证明材料原件和复印件，向公积金中心提出申请");
				item.setPicUrl(ApplicationParameters.instance().getDeployedUrl() + "/images/bmmenu2.jpg");
				item.setTitle("缴存登记流程");
				item.setUrl(ApplicationParameters.instance().getDeployedUrl() + "/html/Lc_dkyw.html");
				WxMpXmlOutNewsMessage m = WxMpXmlOutMessage.NEWS().fromUser(wxMessage.getToUserName()).toUser(
				        wxMessage.getFromUserName()).addArticle(item).build();
				return m;
			}
		};

		return handler;
	}

	/**
	 * 12-单位汇缴流程
	 * @return
	 */
	public WxMpMessageHandler getDwhjLcHandler() {
		WxMpMessageHandler handler = new WxMpMessageHandler() {
			public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
			        WxMpService wxMpService, WxSessionManager sessionManager) {
				WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();
				item.setDescription("1、贷款申请。申请人申请住房公积金贷款的，应持相关贷款证明材料原件和复印件，向公积金中心提出申请");
				item.setPicUrl(ApplicationParameters.instance().getDeployedUrl() + "/images/bmmenu3.jpg");
				item.setTitle("单位汇缴流程");
				item.setUrl(ApplicationParameters.instance().getDeployedUrl() + "/html/Lc_dkyw.html");
				WxMpXmlOutNewsMessage m = WxMpXmlOutMessage.NEWS().fromUser(wxMessage.getToUserName()).toUser(
				        wxMessage.getFromUserName()).addArticle(item).build();
				return m;
			}
		};

		return handler;
	}

	/**
	 * 13-账户转移流程
	 * @return
	 */
	public WxMpMessageHandler getZhzyLcHandler() {
		WxMpMessageHandler handler = new WxMpMessageHandler() {
			public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
			        WxMpService wxMpService, WxSessionManager sessionManager) {
				WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();
				item.setDescription("1、贷款申请。申请人申请住房公积金贷款的，应持相关贷款证明材料原件和复印件，向公积金中心提出申请");
				item.setPicUrl(ApplicationParameters.instance().getDeployedUrl() + "/images/bmmenu4.jpg");
				item.setTitle("账户转移流程");
				item.setUrl(ApplicationParameters.instance().getDeployedUrl() + "/html/Lc_dkyw.html");
				WxMpXmlOutNewsMessage m = WxMpXmlOutMessage.NEWS().fromUser(wxMessage.getToUserName()).toUser(
				        wxMessage.getFromUserName()).addArticle(item).build();
				return m;
			}
		};

		return handler;
	}

	/**
	 * 14-基数调整流程
	 * @return
	 */
	public WxMpMessageHandler getJstzLcHandler() {
		WxMpMessageHandler handler = new WxMpMessageHandler() {
			public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
			        WxMpService wxMpService, WxSessionManager sessionManager) {
				WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();
				item.setDescription("1、贷款申请。申请人申请住房公积金贷款的，应持相关贷款证明材料原件和复印件，向公积金中心提出申请");
				item.setPicUrl(ApplicationParameters.instance().getDeployedUrl() + "/images/bmmenu5.jpg");
				item.setTitle("基数调整流程");
				item.setUrl(ApplicationParameters.instance().getDeployedUrl() + "/html/Lc_dkyw.html");
				WxMpXmlOutNewsMessage m = WxMpXmlOutMessage.NEWS().fromUser(wxMessage.getToUserName()).toUser(
				        wxMessage.getFromUserName()).addArticle(item).build();
				return m;
			}
		};

		return handler;
	}

	/**
	 * 15-提取业务流程
	 * @return
	 */
	public WxMpMessageHandler getTqywLcHandler() {
		WxMpMessageHandler handler = new WxMpMessageHandler() {
			public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
			        WxMpService wxMpService, WxSessionManager sessionManager) {
				WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();
				item.setDescription("1、贷款申请。申请人申请住房公积金贷款的，应持相关贷款证明材料原件和复印件，向公积金中心提出申请");
				item.setPicUrl(ApplicationParameters.instance().getDeployedUrl() + "/images/bmmenu6.jpg");
				item.setTitle("提取业务流程");
				item.setUrl(ApplicationParameters.instance().getDeployedUrl() + "/html/Lc_dkyw.html");
				WxMpXmlOutNewsMessage m = WxMpXmlOutMessage.NEWS().fromUser(wxMessage.getToUserName()).toUser(
				        wxMessage.getFromUserName()).addArticle(item).build();
				return m;
			}
		};

		return handler;
	}

	/**
	 * 16-贷款业务流程
	 * @return
	 */
	public WxMpMessageHandler getDkywLcHandler() {
		WxMpMessageHandler handler = new WxMpMessageHandler() {
			public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
			        WxMpService wxMpService, WxSessionManager sessionManager) {
				WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();
				item.setDescription("1、贷款申请。申请人申请住房公积金贷款的，应持相关贷款证明材料原件和复印件，向公积金中心提出申请");
				item.setPicUrl(ApplicationParameters.instance().getDeployedUrl() + "/images/bmmenu6.jpg");
				item.setTitle("贷款业务流程");
				item.setUrl(ApplicationParameters.instance().getDeployedUrl() + "/html/Lc_dkyw.html");
				WxMpXmlOutNewsMessage m = WxMpXmlOutMessage.NEWS().fromUser(wxMessage.getToUserName()).toUser(
				        wxMessage.getFromUserName()).addArticle(item).build();
				return m;
			}
		};

		return handler;
	}

	/**
	 * 21-汇缴业务 政策
	 * @return
	 */
	public WxMpMessageHandler getHjywZcHandler() {
		WxMpMessageHandler handler = new WxMpMessageHandler() {
			public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
			        WxMpService wxMpService, WxSessionManager sessionManager) {
				WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();
				item.setDescription("1、贷款申请。申请人申请住房公积金贷款的，应持相关贷款证明材料原件和复印件，向公积金中心提出申请");
				item.setPicUrl(ApplicationParameters.instance().getDeployedUrl() + "/images/bmmenu8.jpg");
				item.setTitle("汇缴业务-缴存职工 政策");
				item.setUrl(ApplicationParameters.instance().getDeployedUrl() + "/html/Lc_dkyw.html");
				WxMpXmlOutNewsMessage.Item item2 = new WxMpXmlOutNewsMessage.Item();
				item2.setDescription("1、贷款申请。申请人申请住房公积金贷款的，应持相关贷款证明材料原件和复印件，向公积金中心提出申请");
				item2.setPicUrl(ApplicationParameters.instance().getDeployedUrl() + "/images/bmmenu9.jpg");
				item2.setTitle("汇缴业务-月缴存额 政策");
				item2.setUrl(ApplicationParameters.instance().getDeployedUrl() + "/html/Lc_dkyw.html");
				WxMpXmlOutNewsMessage m = WxMpXmlOutMessage.NEWS().fromUser(wxMessage.getToUserName()).toUser(
				        wxMessage.getFromUserName()).addArticle(item).addArticle(item2).build();
				return m;
			}
		};

		return handler;
	}

	/**
	 * 22-提取业务 政策
	 * @return
	 */
	public WxMpMessageHandler getTqywZcHandler() {
		WxMpMessageHandler handler = new WxMpMessageHandler() {
			public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
			        WxMpService wxMpService, WxSessionManager sessionManager) {
				WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();
				item.setDescription("1、贷款申请。申请人申请住房公积金贷款的，应持相关贷款证明材料原件和复印件，向公积金中心提出申请");
				item.setPicUrl(ApplicationParameters.instance().getDeployedUrl() + "/images/bmmenu1.jpg");
				item.setTitle("提取业务-提取条件 政策");
				item.setUrl(ApplicationParameters.instance().getDeployedUrl() + "/html/Lc_dkyw.html");
				WxMpXmlOutNewsMessage.Item item2 = new WxMpXmlOutNewsMessage.Item();
				item2.setDescription("1、贷款申请。申请人申请住房公积金贷款的，应持相关贷款证明材料原件和复印件，向公积金中心提出申请");
				item2.setPicUrl(ApplicationParameters.instance().getDeployedUrl() + "/images/bmmenu2.jpg");
				item2.setTitle("提取业务-委托提取 政策");
				item2.setUrl(ApplicationParameters.instance().getDeployedUrl() + "/html/Lc_dkyw.html");
				WxMpXmlOutNewsMessage m = WxMpXmlOutMessage.NEWS().fromUser(wxMessage.getToUserName()).toUser(
				        wxMessage.getFromUserName()).addArticle(item).addArticle(item2).build();
				return m;
			}
		};

		return handler;
	}

	/**
	 * 23-贷款业务 政策
	 * @return
	 */
	public WxMpMessageHandler getDkywZcHandler() {
		WxMpMessageHandler handler = new WxMpMessageHandler() {
			public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
			        WxMpService wxMpService, WxSessionManager sessionManager) {
				WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();
				item.setDescription("1、贷款申请。申请人申请住房公积金贷款的，应持相关贷款证明材料原件和复印件，向公积金中心提出申请");
				item.setPicUrl(ApplicationParameters.instance().getDeployedUrl() + "/images/bmmenu3.jpg");
				item.setTitle("贷款业务-开设还款账户 政策");
				item.setUrl(ApplicationParameters.instance().getDeployedUrl() + "/html/Lc_dkyw.html");
				WxMpXmlOutNewsMessage.Item item2 = new WxMpXmlOutNewsMessage.Item();
				item2.setDescription("1、贷款申请。申请人申请住房公积金贷款的，应持相关贷款证明材料原件和复印件，向公积金中心提出申请");
				item2.setPicUrl(ApplicationParameters.instance().getDeployedUrl() + "/images/bmmenu4.jpg");
				item2.setTitle("贷款业务-贷款计息结息 政策");
				item2.setUrl(ApplicationParameters.instance().getDeployedUrl() + "/html/Lc_dkyw.html");
				WxMpXmlOutNewsMessage m = WxMpXmlOutMessage.NEWS().fromUser(wxMessage.getToUserName()).toUser(
				        wxMessage.getFromUserName()).addArticle(item).addArticle(item2).build();
				return m;
			}
		};

		return handler;
	}

	/**
	 * 24-还款业务 政策
	 * @return
	 */
	public WxMpMessageHandler getHkywZcHandler() {
		WxMpMessageHandler handler = new WxMpMessageHandler() {
			public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
			        WxMpService wxMpService, WxSessionManager sessionManager) {
				WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();
				item.setDescription("1、贷款申请。申请人申请住房公积金贷款的，应持相关贷款证明材料原件和复印件，向公积金中心提出申请");
				item.setPicUrl(ApplicationParameters.instance().getDeployedUrl() + "/images/bmmenu5.jpg");
				item.setTitle("还款业务-开设还款账户 政策");
				item.setUrl(ApplicationParameters.instance().getDeployedUrl() + "/html/Lc_dkyw.html");
				WxMpXmlOutNewsMessage.Item item2 = new WxMpXmlOutNewsMessage.Item();
				item2.setDescription("1、贷款申请。申请人申请住房公积金贷款的，应持相关贷款证明材料原件和复印件，向公积金中心提出申请");
				item2.setPicUrl(ApplicationParameters.instance().getDeployedUrl() + "/images/bmmenu6.jpg");
				item2.setTitle("还款业务-贷款计息结息 政策");
				item2.setUrl(ApplicationParameters.instance().getDeployedUrl() + "/html/Lc_dkyw.html");
				WxMpXmlOutNewsMessage m = WxMpXmlOutMessage.NEWS().fromUser(wxMessage.getToUserName()).toUser(
				        wxMessage.getFromUserName()).addArticle(item).addArticle(item2).build();
				return m;
			}
		};

		return handler;
	}

	/**
	 * 图片消息
	 * @return
	 */
	public WxMpMessageHandler getPictureHandler() {
		WxMpMessageHandler handler = new WxMpMessageHandler() {
			public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
			        WxMpService wxMpService, WxSessionManager sessionManager) {
				WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT().content("图片消息!").toUser(wxMessage.getFromUserName())
				        .build();
				return m;
			}
		};

		return handler;
	}

	/**
	 * 地理位置消息
	 * @return
	 */
	public WxMpMessageHandler getLocationHandler() {
		WxMpMessageHandler handler = new WxMpMessageHandler() {
			public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
			        WxMpService wxMpService, WxSessionManager sessionManager) {
				WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT().content("地理位置信息!").toUser(
				        wxMessage.getFromUserName()).build();
				return m;
			}
		};

		return handler;
	}

	/**
	 * 链接消息
	 * @return
	 */
	public WxMpMessageHandler getLinkHandler() {
		WxMpMessageHandler handler = new WxMpMessageHandler() {
			public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
			        WxMpService wxMpService, WxSessionManager sessionManager) {
				WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT().content("链接信息!").toUser(wxMessage.getFromUserName())
				        .build();
				return m;
			}
		};

		return handler;
	}

	/**
	 * 音频消息
	 * @return
	 */
	public WxMpMessageHandler getVoiceHandler() {
		WxMpMessageHandler handler = new WxMpMessageHandler() {
			public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
			        WxMpService wxMpService, WxSessionManager sessionManager) {
				WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT().content("音频信息!").toUser(wxMessage.getFromUserName())
				        .build();
				return m;
			}
		};

		return handler;
	}

	/**
	 * 订阅取消
	 * @return
	 */
	public WxMpMessageHandler getUnSubscribeHandler() {
		WxMpMessageHandler handler = new WxMpMessageHandler() {
			public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
			        WxMpService wxMpService, WxSessionManager sessionManager) {
				WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT().content("取消了订阅!")
				        .toUser(wxMessage.getFromUserName()).build();
				return m;
			}
		};

		return handler;
	}

	/**
	 * 22-还款转账 菜单
	 * @return
	 */
	public WxMpMessageHandler getHkzzMenuHandler() {
		WxMpMessageHandler handler = new WxMpMessageHandler() {
			public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
			        WxMpService wxMpService, WxSessionManager sessionManager) {
				WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT().content("点击了 还款转账 菜单,功能建设中,敬请期待!").toUser(
				        wxMessage.getFromUserName()).build();
				return m;
			}
		};
		return handler;
	}

	/**
	 * 未知 菜单
	 * @return
	 */
	public WxMpMessageHandler getOtherMenuHandler() {
		WxMpMessageHandler handler = new WxMpMessageHandler() {
			public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
			        WxMpService wxMpService, WxSessionManager sessionManager) {
				WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT().content("点击了 点击了自定义菜单 " + wxMessage.getEventKey())
				        .toUser(wxMessage.getFromUserName()).build();
				return m;
			}
		};
		return handler;
	}

}
