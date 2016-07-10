package cn.shineyue.wx.controller.mobile;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.shineyue.wx.po.custom.RegisterBean;
import cn.shineyue.wx.service.WxUserService;

@Controller
public class RegisterController {
	
	@Autowired
	private WxUserService wxUserService;

	@RequestMapping(value = "/register")
	@ResponseBody
	public RegisterBean registerWeiXin(HttpServletRequest req) {
		System.out.println("openid:" + req.getParameter("openid"));
		String uname = req.getParameter("uname");
		String cardNum = req.getParameter("cardNumber");
		String openId = req.getParameter("openid").trim();
		String grbh = req.getParameter("grbh").trim();
		RegisterBean bean = new RegisterBean();

		boolean userExits = false;
		try{
			userExits = wxUserService.searchWxUserExist(uname, cardNum, grbh);
		}catch(Exception e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			bean.setSuccess("false");
			bean.setMsg("在公积金系统中未查询异常：" + e.getMessage());
		}
		if(userExits){
			try{
				wxUserService.insertWxUser(grbh, openId);
				bean.setSuccess("true");
				bean.setMsg("成功绑定！");
			}catch(Exception e){
				// TODO Auto-generated catch block
				e.printStackTrace();
				bean.setSuccess("false");
				bean.setMsg("在公积金系统中插入异常：" + e.getMessage());
			}

		}else{
			bean.setSuccess("false");
			bean.setMsg("在公积金系统中未查到相关信息！可能是您不是缴存人或者输入内容错误。");
		}
		return bean;
	}
}
