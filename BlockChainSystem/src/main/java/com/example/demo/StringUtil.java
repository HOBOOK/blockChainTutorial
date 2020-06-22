package com.example.demo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtil {
    public static String getSha256(String str) {
        String SHA;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(str.getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte data : byteData) {
                sb.append(Integer.toString((data & 0xff) + 0x100, 16).substring(1));
            }
            SHA = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            SHA = null;
        }
        return SHA;
    }
}
