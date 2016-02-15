package com.pallasli.json;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.JsonElement;

public class JsonUtilsTest {

	@Test
	public void fromStringToJsonElement() {
		JsonElement json = JsonUtils.fromStringToJsonElement("{'ds':34}");
		Assert.assertEquals("{\"ds\":34}", json.toString());
	}

	@Test
	public void fromJsonToObject() {
		JsonElement json = JsonUtils
				.fromStringToJsonElement("{'arg0':34,'arg1':'Feb 13, 2016 9:15:23 PM','arg2':{arg0:23,arg1:false}}");
		TestBean bean = JsonUtils.fromJsonToObject(json.getAsJsonObject(), TestBean.class);
		Assert.assertEquals(23, bean.getArg2().getArg0());
	}

	@Test
	public void fromJsonArrayToList() {

	}

	@Test
	public void fromObjectToJson() {
		TestBean t = new TestBean();
		t.setArg1(new Date());
		JsonElement json = JsonUtils.fromObjectToJson(t);
	}

	@Test
	public void fromListToJsonArray() {

	}

	@Test
	public void getJsonContent() {

	}

	@Test
	public void parseJsonToStr() {

	}

	@Test
	public void parseStrToJson() {

	}

	@Test
	public void parseJsonToObject() {

	}

	@Test
	public void parseObjectToJson() {

	}

	@Test
	public void parseJsonArrayToList() {

	}

	@Test
	public void parseListToJsonArray() {

	}

}

class TestBean {
	private String arg0;
	private Date arg1;
	private TestChildBean arg2;

	public String getArg0() {
		return arg0;
	}

	public void setArg0(String arg0) {
		this.arg0 = arg0;
	}

	public Date getArg1() {
		return arg1;
	}

	public void setArg1(Date arg1) {
		this.arg1 = arg1;
	}

	public TestChildBean getArg2() {
		return arg2;
	}

	public void setArg2(TestChildBean arg2) {
		this.arg2 = arg2;
	}
}

class TestChildBean {
	private int arg0;
	private boolean arg1;

	public int getArg0() {
		return arg0;
	}

	public void setArg0(int arg0) {
		this.arg0 = arg0;
	}

	public boolean isArg1() {
		return arg1;
	}

	public void setArg1(boolean arg1) {
		this.arg1 = arg1;
	}
}
