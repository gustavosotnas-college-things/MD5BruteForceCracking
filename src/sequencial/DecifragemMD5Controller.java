package sequencial;

import java.security.NoSuchAlgorithmException;

public class DecifragemMD5Controller {

	// Variáveis de classe
	private String hashMD5Compare = "";
	private String palavraDescobertaMD5 = "";
	
	// Getters e Setters
	
	public String getHashMD5Compare() {
		return this.hashMD5Compare;
	}
	public void setHashMD5Compare(String newHashMD5Compare) {
		this.hashMD5Compare = newHashMD5Compare;
	}
	public String getPalavraDescobertaMD5() {
		return this.palavraDescobertaMD5;
	}
	public void setPalavraDescobertaMD5(String newPalavraDescobertaMD5) {
		this.palavraDescobertaMD5 = newPalavraDescobertaMD5;
	}

	// Métodos

	public void iniciaQuebra(String hash) throws NoSuchAlgorithmException {
		
		setHashMD5Compare(hash);

		if (descobrePalavraCorrespondente(hash)) {
			System.out.println("---------------------------------------------------");
			System.out.println("\nPalavra descoberta!\n");
			System.out.println("Hash: " + getHashMD5Compare());
			System.out.println("Senha: " + getPalavraDescobertaMD5());
		}

	}

	/**
	 * Método que faz "força-bruta" para descobrir qual palavra tem tal hash de
	 * entrada. Ele faz isso permutando entre todos as palavras alfanuméricos 
	 * (minúsculos) de 5 dígitos.
	 * @param hash A hash a qual deseja descobrir a palavra que a originou.
	 * @return true, caso o algoritmo encontra uma palavra que origina a mesma 
	 * hash MD5 que a hash de entrada; false, caso contrário.
	 * @throws NoSuchAlgorithmException
	 */
	private boolean descobrePalavraCorrespondente(String hash) throws NoSuchAlgorithmException {

		String[] caracteresAlfaNum = {"0", "1", "2", "3", "4", "5", "6", "7",
				"8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", 
				"k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
				"w", "x", "y", "z"};
		boolean descobriu = false;

		for (String a : caracteresAlfaNum) {
			for (String b : caracteresAlfaNum) {
				for (String c : caracteresAlfaNum) {
					for (String d : caracteresAlfaNum) {
						for (String e : caracteresAlfaNum) {

							String combinacao = a + b + c + d + e;
						    descobriu = DecifragemMD5Service.quebraHashMD5(combinacao, hash);
							
							if(descobriu){
								setPalavraDescobertaMD5(combinacao);
								return descobriu;
							}
						}
					}
				}
			}
		}
		return descobriu; // caso o for seja percorrido até o final
	}
}
