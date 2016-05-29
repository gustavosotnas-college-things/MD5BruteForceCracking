package sequencial;

import java.math.BigInteger;
import java.security.MessageDigest;

public class QuebraSenhaMD5 {

	public boolean Cracking(String combinacao, String hash) throws Exception {

		String hashMD5compare = criptografar(combinacao);
		boolean quebra = comparar(hashMD5compare, hash);
		boolean quebrado = false;
		
		if(quebra){
			System.out.println("---------------------------------------------------");
			System.out.println("\nPalavra descoberta!\n");
			System.out.println("Hash: " + hashMD5compare);
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
