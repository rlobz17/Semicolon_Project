package account;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordManager {
	// hashing algotithm
	private final String hashingAlgorithm = "SHA-512";
	
	/**
	 * returns hash of given password with pre defined hashing algotihm
	 * 
	 * */
	
	public String hashPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest messDig = MessageDigest.getInstance(hashingAlgorithm);
		messDig.update(password.getBytes());
		return hexToString(messDig.digest());
	}
	
	
	/*
	 Given a byte[] array, produces a hex String,
	 such as "234a6f". with 2 chars for each byte in the array.
	*/
	private String hexToString(byte[] bytes) {
		StringBuffer bf = new StringBuffer();
		for (int i=0; i<bytes.length; i++) {
			int value = bytes[i];
			value = value & 0xff;  
			if (value<16) bf.append('0');
			bf.append(Integer.toString(value, 16));
		}
		return bf.toString();
	}
	
}
