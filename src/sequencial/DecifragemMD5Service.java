package sequencial;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DecifragemMD5Service {

	/**
	 * Define se a palavra de entrada é a mesma palavra que gerou a hash MD5 em questão.
	 * Se correspondem, significa que a hash MD5 foi "quebrada".
	 * @param possivelPalavra Uma palavra que pode ser ou não a palavra original da hash MD5. 
	 * @param hash A hash MD5 para comparação.
	 * @return true, se a hash da possível palavra corresponde à hash de entrada, 
	 *         false, caso contrário.
	 * @throws NoSuchAlgorithmException
	 */
	public static boolean quebraHashMD5(String possivelPalavra, String hash) throws NoSuchAlgorithmException {

		String hashMD5compare = geraNovaHashMD5(possivelPalavra);
		boolean quebra = comparaHashes(hash, hashMD5compare);
		boolean quebrado = false;
		
		if(quebra) {
			quebrado = true;
		}
		
		return quebrado;
	}

	/**
	 * Gera uma hash MD5 de uma string qualquer.
	 * Função baseada na solução dada pelo professor Msc. Elias Ferreira.
	 * @param palavra A palavra desejada para se obter a hash.
	 * @return A hash MD5 correspondente à palavra de entrada.
	 * @throws NoSuchAlgorithmException
	 */
	private static String geraNovaHashMD5(String palavra) throws NoSuchAlgorithmException {

		String hashMD5 = "";
		MessageDigest m = MessageDigest.getInstance("MD5");
		BigInteger hash = new BigInteger(1, m.digest(palavra.getBytes()));
		hashMD5 = hash.toString(16);
		return hashMD5;
	}

	/**
	 * Compara se duas hashes MD5 são iguais.
	 * @param originalMD5Hash A hash base para a comparação. 
	 * @param hashTocompare A hash alvo da comparação.
	 * @return true, se são iguais, false, caso contrário.
	 */
	private static boolean comparaHashes(String originalMD5Hash, String hashTocompare) {

		if (hashTocompare.equals(originalMD5Hash)) {
			return true;
		} else {
			return false;
		}
	}
}
