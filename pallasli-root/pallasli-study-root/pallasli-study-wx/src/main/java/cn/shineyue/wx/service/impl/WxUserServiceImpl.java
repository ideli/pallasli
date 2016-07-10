package cn.shineyue.wx.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import cn.shineyue.wx.mapper.auto.CrGrMapper;
import cn.shineyue.wx.mapper.auto.TWaWxUserMapper;
import cn.shineyue.wx.po.auto.CrGrExample;
import cn.shineyue.wx.po.auto.TWaWxUser;
import cn.shineyue.wx.service.WxUserService;

public class WxUserServiceImpl implements WxUserService {

	@Autowired
	private CrGrMapper crGrMapper;
	
	@Autowired
	private TWaWxUserMapper tWaWxUserMapper;
	
	@Override
	public boolean searchWxUserExist(String uname, String cardNum, String grbh) throws Exception {
		// TODO Auto-generated method stub
		CrGrExample crGrExample = new CrGrExample();
		CrGrExample.Criteria criteria = crGrExample.createCriteria();
		criteria.andGrbhEqualTo(grbh).andGrxmEqualTo(uname).andZjhmEqualTo(cardNum);
		int count = crGrMapper.countByExample(crGrExample);
		if(count == 1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public void insertWxUser(String grbh, String openId) throws Exception {
		// TODO Auto-generated method stub
		TWaWxUser record = new TWaWxUser();
		record.setGrbh(grbh);
		record.setWxOpenid(openId);
		record.setRegDate(new Date());
		tWaWxUserMapper.insertSelective(record);

	}

	@Override
	public void deleteWxUser(String grbh, String openId) throws Exception {
		// TODO Auto-generated method stub

	}

}
