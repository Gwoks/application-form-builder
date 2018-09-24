package com.bermanfaat.formbuilder.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class EncodingUtils {

	public static String generateMD5(String n) throws NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(n.getBytes());

		byte byteData[] = md.digest();

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();

	}

	public static String generateBCrypt(String n) {

		String generatedSecuredPasswordHash = BCrypt.hashpw(n, BCrypt.gensalt(10));

		return generatedSecuredPasswordHash;
	}

	public static boolean generateBCrypt_Decrypt(String a, String b) {

		boolean matched = BCrypt.checkpw(a, b);

		System.out.println(matched);

		return matched;
	}

	/*
	 * 1/23/2014 Intel Core i7-2700K CPU @ 3.50 GHz
	 * 
	 * | Cost | Iterations | Duration | |------|-------------------|-------------| |
	 * 8 | 256 iterations | 38.2 ms | <-- minimum allowed by BCrypt | 9 | 512
	 * iterations | 74.8 ms | | 10 | 1,024 iterations | 152.4 ms | <-- current
	 * default (BCRYPT_COST=10) | 11 | 2,048 iterations | 296.6 ms | | 12 | 4,096
	 * iterations | 594.3 ms | | 13 | 8,192 iterations | 1,169.5 ms | | 14 | 16,384
	 * iterations | 2,338.8 ms | | 15 | 32,768 iterations | 4,656.0 ms | | 16 |
	 * 65,536 iterations | 9,302.2 ms |
	 */

}

/*
 * 
 * public static void main(String[] args) { for(int i=0; i<1000; i++) { String
 * password = "password";
 * org.springframework.security.crypto.password.PasswordEncoder encoder = new
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
 * BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); String
 * hashedPassword = passwordEncoder.encode(password);
 * System.out.println(hashedPassword); System.out.println(
 * encoder.matches(password,
 * "$2a$10$/XAM2mKfwHC2ribTOg5vUeV7fbJU3eC5ExMY.HzUrY0nKq0meJRwq")); } }
 * 
 */
