package dharma.github.io.imageloader.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5 Digital Summary Tool
 */
public class MD5Utils {

	/**
	 * Get 32-bit MD5 digest value
	 * @param content The text content to be calculated
	 * @return MD5 value
	 */
	public static String getMD5String(String content) {
		byte[] digestBytes = null;
		try {
			digestBytes = MessageDigest.getInstance("md5").digest(
					content.getBytes());
		} catch (NoSuchAlgorithmException e) {
			//can not reach
		}
		String md5Code = new BigInteger(1, digestBytes).toString(16);
		//Completion of insufficient digits
		for (int i = 0; i < 32 - md5Code.length(); i++) {
			md5Code = "0" + md5Code;
		}
		return md5Code;
	}
}