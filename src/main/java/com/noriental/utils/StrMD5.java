package com.noriental.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;

public class StrMD5 {

//	public static void main(String[] args) {
//		String password = "root";
//		String md5 = new StrMD5(password).getResult();
//		System.out.println(password);
//		System.out.println(md5);
//	}

	private String m_result;
	private String m_key;
	private MessageDigest currentAlgorithm;

	public StrMD5(String key) {
		this.m_key = key;
		try {
			currentAlgorithm = MessageDigest.getInstance("MD5");
			this.m_result = computeDigest(loadBytes(this.m_key,null)); //+ "\r\n";
		} catch (Exception e) {
			System.out.println("MD5 algorithm not available.");
		}
	}
	
	public StrMD5(String key,String encoding) {
        this.m_key = key;
        try {
            currentAlgorithm = MessageDigest.getInstance("MD5");
            this.m_result = computeDigest(loadBytes(this.m_key,encoding)); //+ "\r\n";
        } catch (Exception e) {
            System.out.println("MD5 algorithm not available.");
        }
    }

	public String getResult() {
		return this.m_result;
	}
	static private byte[] loadBytes(String name,String encoding) throws UnsupportedEncodingException {
	    if( StringUtils.isEmpty(encoding) )
	    {
	        encoding = "utf8";
	    }
		byte[] buffer = name.getBytes(encoding);
		//System.out.println(name);
		//System.out.println("Begin...");
		return (buffer);
	}

	private String computeDigest(byte[] b) {
		currentAlgorithm.reset();
		currentAlgorithm.update(b);
		byte[] hash = currentAlgorithm.digest();
		String d = "";
		int usbyte = 0; // unsigned byte
		for (int i = 0;
			i < hash.length;
			i += 2) { // format with 2-byte words with spaces.
			usbyte = hash[i] & 0xFF;
			// byte-wise AND converts signed byte to unsigned.
			if (usbyte < 16)
				d += "0" + Integer.toHexString(usbyte);
			// pad on left if single hex digit.
			else
				d += Integer.toHexString(usbyte);
			usbyte = hash[i + 1] & 0xFF;
			// byte-wise AND converts signed byte to unsigned.
			if (usbyte < 16)
				d += "0" + Integer.toHexString(usbyte);
			//+ " "; // pad on left if single hex digit.
			else
				d += Integer.toHexString(usbyte); // + " ";
		}
		//return d.toUpperCase();
		return d.trim().toLowerCase();
	}
	
	public static String getStrMD5(String myStr) throws Exception{
		if(myStr.length()>0){
			StrMD5 myStrMD5 = new StrMD5(myStr);
			return (myStrMD5.getResult());
		} 
		else {
			return null;	
		}
	}


}

