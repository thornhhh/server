package com.call110.common.util;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
public class RSACoder extends PEMEncoder {
	public static final String KEY_ALGORITHM = "RSA";  
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";  
    private static final String KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCCLCHnW224gxhLnf0ISB871gYy2C+9h+dkYkyxoaI/mqEGleXjnrq9TZqJ6vlFQs77JRgwOAOBxB2gq1T911STBWj3BHvCsKwI+iVI7jSgN+3y6Ukadddy549ho3ybC+jZawjpq5hp8N51Oa+ON6FrVY0ANl3s3KkLJsAaaNJl7QIDAQAB";  
    public static String sign(byte[] data, String privateKey) throws Exception {  
        // 解密由base64编码的私钥  
        byte[] keyBytes = decryptBASE64(privateKey);  
        // 构造PKCS8EncodedKeySpec对象  
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);  
        // KEY_ALGORITHM 指定的加密算法  
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
        // 取私钥匙对象  
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);  
        // 用私钥对信息生成数字签名  
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);  
        signature.initSign(priKey);  
        signature.update(data);  
        return encryptBASE64(signature.sign());  
    } 
    
  
    public synchronized static boolean verify(byte[] data, String publicKey, String sign)  
            throws Exception {  
        // 解密由base64编码的公钥  
        byte[] keyBytes = decryptBASE64(publicKey);  
        // 构造X509EncodedKeySpec对象  
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);  
        // KEY_ALGORITHM 指定的加密算法  
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
        // 取公钥匙对象  
        PublicKey pubKey = keyFactory.generatePublic(keySpec);  
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);  
        signature.initVerify(pubKey);  
        signature.update(data);  
        // 验证签名是否正常  
        return signature.verify(decryptBASE64(sign));  
    }  
  
    public static String decrypt(String str){
    	try{
    		byte[] data = decryptBASE64(str);
            byte[] keyBytes = decryptBASE64(KEY);  
            // 
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);  
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
            Key publicKey = keyFactory.generatePublic(x509KeySpec);  
            // 对数据解密  
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return new String(cipher.doFinal(data));  
    	}catch(Exception e){
    		throw (e instanceof AuthException) ? (AuthException) e : new AuthException("Encode is error");
    	}
    }  
  
    public static String encrypt(String str){
    	try{
	    	byte[] data = str.getBytes("UTF-8");
	        // 对密钥解密  
	        byte[] keyBytes = decryptBASE64(KEY);  
	        // 取得私钥  
	        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);  
	        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
	        Key publicKey = keyFactory.generatePublic(x509KeySpec);  
	        // 对数据加密  
	        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
	        cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
	        return encryptBASE64Url(cipher.doFinal(data));
    	}catch(Exception e){
    		throw (e instanceof AuthException) ? (AuthException) e : new AuthException("Encode is error");
    	}
    }
}
