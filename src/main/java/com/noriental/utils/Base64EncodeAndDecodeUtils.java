package com.noriental.utils;

public class Base64EncodeAndDecodeUtils {

	// 将 encodeStr 进行 BASE64 编码 
	public static String encoderBASE64(String encodeStr) { 
		if (encodeStr == null) return null; 
		
		sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
		
		return encoder.encode( encodeStr.getBytes() ); 
	} 

	// 将 BASE64 编码的字符串  encoder 进行解码 
	public static String decoderBASE64(String decoderStr) { 
		if (decoderStr == null) return null; 

		sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder(); 
		
		try { 
			byte[] b = decoder.decodeBuffer(decoderStr); 
			return new String(b); 
		} catch (Exception e) { 
			return null; 
		} 
	}
}
