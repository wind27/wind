package com.noriental.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Encrypt {

    private static final Logger logger = LoggerFactory.getLogger(Encrypt.class);

    public Encrypt() {
    }

    public String encrypt(String strSrc, String encName) {
        // parameter strSrc is a string will be encrypted,
        // parameter encName is the algorithm name will be used.
        // encName dafault to "MD5"
        MessageDigest md = null;
        String strDes = null;

        byte[] bt = strSrc.getBytes();
        try {
            if (encName == null || encName.equals("")) {
                encName = "MD5";
            }
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        }
        catch (NoSuchAlgorithmException e) {
            logger.info("Invalid algorithm.");
            return null;
        }
        return strDes;
    }

    public String encrypt(String strSrc, String strSrcEnc, String encName)
            throws UnsupportedEncodingException {
        // parameter strSrc is a string will be encrypted,
        // parameter encName is the algorithm name will be used.
        // encName dafault to "MD5"
        MessageDigest md = null;
        String strDes = null;
        if (StringUtils.isEmpty(strSrcEnc)) {
            strSrcEnc = "utf-8";
        }

        byte[] bt = strSrc.getBytes(strSrcEnc);
        try {
            if (encName == null || encName.equals("")) {
                encName = "MD5";
            }
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        }
        catch (NoSuchAlgorithmException e) {
            logger.info("Invalid algorithm.");
            return null;
        }
        return strDes;
    }

    public String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

    public static void main(String[] args) {
        Encrypt te = new Encrypt();
        String strSrc = "可以加密汉字.Oh,and english 33333333333333333333a";
        System.out.println("Source String:" + strSrc);
        System.out.println("Encrypted String:");
        System.out.println("Use Def:" + te.encrypt(strSrc, null));
        System.out.println("Use MD5:" + te.encrypt(strSrc, "MD5"));
        System.out.println("Use SHA:" + te.encrypt(strSrc, "SHA-1"));
        System.out.println("Use SHA-256:" + te.encrypt(strSrc, "SHA-256"));
    }
}
