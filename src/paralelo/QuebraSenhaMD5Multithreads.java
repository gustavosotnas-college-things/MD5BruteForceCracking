package paralelo;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class QuebraSenhaMD5Multithreads {

	public boolean crackingThreads(String combinacao, String hash) throws NoSuchAlgorithmException {

		String senhaMD5 = criptografar(combinacao);
		boolean quebra = comparar(senhaMD5, hash);
		boolean quebrado = false;

		if (quebra) {
			System.out.println("\n\nHash MD5 quebrada !!\n");
			System.out.println("Hash: " + senhaMD5);
			System.out.println("Senha: " + combinacao);
			quebrado = true;
			MD5Paralelo.setTempoFinalThread(System.currentTimeMillis(), MD5Paralelo.getCounterExecucoes());
			MD5Paralelo.setCounterExecucoes(MD5Paralelo.getCounterExecucoes() + 1); // counterExecucoes++
		}

		return quebrado;
	}

	private String criptografar(String senha) throws NoSuchAlgorithmException {

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
