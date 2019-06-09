package edu.hebeu.util;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;
 
public class CryptoUtil {
 
    /**
	 * 密码加密处理（MD5）
	 * @param src 原密码
	 * @return 加密后的内容
	 */
    public static String md5(String src) {
		try {// 采用MD5处理
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] output = md.digest(src.getBytes());// 加密处理
			// 将加密结果output利用Base64转成字符串输出
			String ret = Base64.encodeBase64String(output);
			return ret;
		} catch (Exception e) {
			return "";
		}
	}
    
 
    public static void main(String[] args) {
        /* String a = "root";
//        String key = "yang测试";
        System.out.println(a);
        // 加密
        String m = encode(a);
        System.out.println(m);
        // 解密
        String n = decode(m);
        System.out.println(n);
        
*/        
        String s = "Q5GU3FhtjBE";
        System.out.println(md5(s));
    }
}
