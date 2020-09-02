package com.tengzhi.base.tools.MD5;

import com.tengzhi.base.property.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;

@Component
public class MD5Util {

	private static Property property;

	public static String encodeBysalt(String password) {
		password = password + property.getSecurity().getSalt();
		return encode(password);
	}

	public static String encode(String password) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		char[] charArray = password.toCharArray();
		byte[] byteArray = new byte[charArray.length];
		for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	/**
	 * 对比
	 *
	 * @param aftval
	 * @param bftval
	 * @return
	 */
	public static boolean eq(Object aftval, Object bftval) {
		if (bftval != null) {
            return aftval.equals(bftval);
		}
		return false;
	}

	@Autowired
	public void init(Property property) {
		MD5Util.property = property;
	}

//	public static void main(String[] args) {
//		System.out.println(MD5Util.encode("abel"));
//	}
}
