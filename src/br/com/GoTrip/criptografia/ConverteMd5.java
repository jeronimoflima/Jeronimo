package br.com.GoTrip.criptografia;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class ConverteMd5 {
	
	public static String converteMd5(String senha) throws NoSuchAlgorithmException{
		
		MessageDigest md = MessageDigest.getInstance("MD5"); 
		
		String salt="5a6b7c";
	       
	       BigInteger hash = new BigInteger(1, md.digest((salt+senha).getBytes()));
	       String senhaMd5 = String.format("%32x", hash);
	       return senhaMd5;
	    }
	}
