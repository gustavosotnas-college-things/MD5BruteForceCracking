package sequencial;

import java.math.BigInteger;
import java.security.MessageDigest;

public class QuebraSenhaMD5 {

	public boolean Cracking(String combinacao, String hash) throws Exception {

		String senhaMD5 = criptografar(combinacao);
		boolean quebra = comparar(senhaMD5, hash);
		boolean quebrado = false;
		
		if(quebra){
			System.err.println("Hash MD5 quebrada !!\n");
			System.out.println("Hash: " + senhaMD5);
			System.out.println("Senha: " + combinacao);
			quebrado = true;
		}
		
		return quebrado;
	}

	private String criptografar(String senha) throws Exception {

		String hashMD5 = "";
		MessageDigest m = MessageDigest.getInstance("MD5");
		BigInteger hash = new BigInteger(1, m.digest(senha.getBytes()));
		hashMD5 = hash.toString(16);

		return hashMD5;

	}

	private boolean comparar(String hashcomparar, String hash) {

		if (hashcomparar.equals(hash)) {
			return true;
		} else
			return false;
	}
}
