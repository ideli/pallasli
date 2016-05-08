package com.pallasli.encrypt.des;

import java.io.IOException;
import java.math.BigInteger;
import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.NoSuchAlgorithmException;

public class Encrypt {
	// public String encrypt(String data) throws NoSuchAlgorithmException,
	// IOException {
	// AlgorithmParameters des = AlgorithmParameters.getInstance("des");
	// des.init(new BigInteger(data).toByteArray());
	// byte[] ret = des.getEncoded();
	// return new BigInteger(ret).toString();
	// }
	public String encrypt(String data) throws NoSuchAlgorithmException, IOException {
		AlgorithmParameterGenerator apg = AlgorithmParameterGenerator.getInstance("DES");
		apg.init(180);
		AlgorithmParameters ap = apg.generateParameters();
		byte[] ret = ap.getEncoded();
		return new BigInteger(ret).toString();
	}

	public static void main(String[] args) {
		try {
			System.out.println(new Encrypt().encrypt("289897858455454758745857854732"));
		} catch (NoSuchAlgorithmException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
