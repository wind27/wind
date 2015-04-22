package com.noriental.utils;


import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 *
 * 字符串工具集合
 *
 * @author 肖城
 */

public class StringUtil {

    private static final String PASSWORD_CRYPT_KEY = "__jDlog_";

    private static final String DES = "DES";

    /**
     *
     * 加密
     *
     * @param src
     *            数据源
     *
     * @param key
     *            密钥，长度必须是8的倍数
     *
     * @return 返回加密后的数据
     *
     * @throws Exception
     */

    public static byte[] encrypt(byte[] src, byte[] key) throws Exception {

        // DES算法要求有一个可信任的随机数源

        SecureRandom sr = new SecureRandom();

        // 从原始密匙数据创建DESKeySpec对象

        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密匙工厂，然后用它把DESKeySpec转换成

        // 一个SecretKey对象

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);

        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成加密操作

        Cipher cipher = Cipher.getInstance(DES);

        // 用密匙初始化Cipher对象

        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

        // 现在，获取数据并加密

        // 正式执行加密操作

        return cipher.doFinal(src);

    }

    /**
     *
     * 解密
     *
     * @param src
     *            数据源
     *
     * @param key
     *            密钥，长度必须是8的倍数
     *
     * @return返回解密后的原始数据
     *
     * @throws Exception
     */

    public static byte[] decrypt(byte[] src, byte[] key) throws Exception {

        // DES算法要求有一个可信任的随机数源

        SecureRandom sr = new SecureRandom();

        // 从原始密匙数据创建一个DESKeySpec对象

        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成

        // 一个SecretKey对象

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);

        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成解密操作

        Cipher cipher = Cipher.getInstance(DES);

        // 用密匙初始化Cipher对象

        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

        // 现在，获取数据并解密

        // 正式执行解密操作

        return cipher.doFinal(src);

    }

    /**
     *
     * 密码解密
     *
     * @param data
     *
     * @return
     *
     * @throws Exception
     */

    public final static String decrypt(String data) {

        try {

            return new String(decrypt(hex2byte(data.getBytes()),

                    PASSWORD_CRYPT_KEY.getBytes()));

        } catch (Exception e) {

        }

        return null;

    }

    public final static String decrypt(String data, String key) {

        try {

            return new String(decrypt(hex2byte(data.getBytes()),

                    key.getBytes()));

        } catch (Exception e) {

        }

        return null;

    }

    /**
     *
     * 密码加密
     *
     * @param password
     *
     * @return
     *
     * @throws Exception
     */

    public final static String encrypt(String password) {

        try {

            return byte2hex(encrypt(password.getBytes(),
                    PASSWORD_CRYPT_KEY.getBytes()));
        } catch (Exception e) {

        }

        return null;

    }

    public final static String encrypt(String password, String key) {

        try {

            return byte2hex(encrypt(password.getBytes(),
                    key.getBytes()));
        } catch (Exception e) {

        }

        return null;

    }

    /**
     *
     * 二行制转字符串
     *
     * @param b
     *
     * @return
     */

    public static String byte2hex(byte[] b) {

        String hs = "";

        String stmp = "";

        for (int n = 0; n < b.length; n++) {

            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));

            if (stmp.length() == 1)

                hs = hs + "0" + stmp;

            else

                hs = hs + stmp;

        }

        return hs.toUpperCase();

    }

    public static byte[] hex2byte(byte[] b) {

        if ((b.length % 2) != 0)

            throw new IllegalArgumentException("长度不是偶数");

        byte[] b2 = new byte[b.length / 2];

        for (int n = 0; n < b.length; n += 2) {

            String item = new String(b, n, 2);

            b2[n / 2] = (byte) Integer.parseInt(item, 16);

        }

        return b2;
    }

    public static boolean regex(String str, String regEx) {
        return Pattern.compile(regEx).matcher(str).find();
    }
    public static String formatURI(String uri, String contextPath) {
        // 去应用名
        uri = uri.substring(contextPath.length());

        // 去参数
        if (uri.indexOf("?") > -1) {
            String[] temp = uri.split("?");
            if (temp != null && temp.length > 1) {
                uri = temp[0];
            }
        }

        // 结尾补/
        if (!uri.endsWith("/")) {
            uri += "/";
        }

        return uri;
    }
    public static String formatJSON(String str) {
        return str.replace("\"", "\\\"").replace("[", "\"[").replace("]", "]\"");
    }
    
    public static String subFileName(String fileName,int length){
    	if(null!=fileName&&fileName.length()>0){
    		if(fileName.length()<=length){
    			return fileName;
    		}
    		int dotPos=fileName.lastIndexOf(".");
    		if(dotPos>0){
    			String suffix=fileName.substring(dotPos);
    			int fileLength=length-suffix.length();
    			String prefix=fileName.substring(0,fileLength);
    			return prefix+suffix;
    		}else{
    			return fileName.substring(0,length);
    		}
    	}
    	return "";
    }
    public static void main(String[] args) {
       /* String regx = "/qust/[\\w]";
        String str = "/qust/123ad___QQ!sf/";
        System.out.println(regex(str, regx));*/
    	System.out.println(subFileName("aaaaaab.",5));
    }
}