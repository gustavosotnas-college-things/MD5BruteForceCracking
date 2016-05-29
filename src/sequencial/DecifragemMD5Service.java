package sequencial;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DecifragemMD5Service {

	public static boolean quebrarHashMD5(String combinacao, String hash) throws NoSuchAlgorithmException {

		String hashMD5compare = criptografar(combinacao);
		boolean quebra = comparar(hashMD5compare, hash);
		boolean quebrado = false;
		
		if(quebra) {
			quebrado = true;
		}
		
		return quebrado;
	}

	private static String criptografar(String senha) throws NoSuchAlgorithmException {

		String hashMD5 = "";
		MessageDigest m = MessageDigest.getInstance("MD5");
		BigInteger hash = new BigInteger(1, m.digest(senha.getBytes()));
		hashMD5 = hash.toString(16);
		return hashMD5;
	}

	private static boolean comparar(String hashTocompare, String originalMD5Hash) {

		if (hashTocompare.equals(originalMD5Hash)) {
			return true;
		} else {
			return false;
		}
	}
}
