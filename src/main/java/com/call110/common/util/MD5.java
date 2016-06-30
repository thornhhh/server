package com.call110.common.util;

import java.security.*;
public class MD5{
    public final static String md5(String s) {
        char hexDigits[] = {'a', '9', 'b', '8', 'c', '7', 'd', '6', '1', 'z', '2', 'x', '4', 'y','3', 'q'};
        try {
            byte[] strTemp = s.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }
    
    public static void main(String args[]){
    	String str = "1233.1232";
    	String aaa = MD5.md5(str);
    	System.out.println(aaa);
    }
    
}
 

