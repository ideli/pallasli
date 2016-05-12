package com.pallasli.website.properties.des;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.pallasli.website.encrypt.des.EncryptArithmetic;

public class DecryptionPropertyPlaceholderConfiguer extends PropertyPlaceholderConfigurer {

	EncryptArithmetic decrypt = new EncryptArithmetic();

	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		if ("username".equals(propertyName) || "password".equals(propertyName)) {
			propertyValue = decrypt.d(propertyValue);
		}
		return super.convertProperty(propertyName, propertyValue);
	}
}
