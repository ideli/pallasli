package com.pallasli.website.encrypt.sha;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {
	public String encrypt(String data) throws NoSuchAlgorithmException {
		byte[] bytes = data.getBytes();
		MessageDigest md = MessageDigest.getInstance("sha");
		md.update(bytes);
		byte[] ret = md.digest();
		return ret.toString();
	}

	public static void main(String[] args) {
		try {
			System.out.println(new Encrypt().encrypt("daf"));
			System.out.println(new Encrypt().encrypt("daf"));
			System.out.println(new Encrypt().encrypt("daf"));
			System.out.println(new Encrypt().encrypt("daf"));
			System.out.println(new Encrypt().encrypt("daf"));
			System.out.println(new Encrypt().encrypt("daf"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
