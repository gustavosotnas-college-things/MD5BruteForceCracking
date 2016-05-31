package paralelo;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

public class DecifragemMD5Controller extends Thread {

	// Variáveis de classe
	private String hashMD5Compare = "";
	private String palavraDescobertaMD5 = "";
	private int inicio;
	private int fim;
	public static volatile boolean descobriu = false; // variável compartilhada

	// Getters e Setters
	
	synchronized String getHashMD5Compare() {
		return this.hashMD5Compare;
	}
	synchronized void setHashMD5Compare(String newHashMD5Compare) {
		this.hashMD5Compare = newHashMD5Compare;
	}
	public String getPalavraDescobertaMD5() {
		return this.palavraDescobertaMD5;
	}
	public void setPalavraDescobertaMD5(String newPalavraDescobertaMD5) {
		this.palavraDescobertaMD5 = newPalavraDescobertaMD5;
	}

	// Construtor
	public DecifragemMD5Controller(int charInicio, int charFim, String hash) {
		// qual caractere entre os caracteres alfanuméricos 
		// que será iniciada/terminada a verificação? inicio e fim
		this.inicio = charInicio;
		this.fim = charFim;
		this.hashMD5Compare = hash;
	}

	/**
	 * Função que inicia o processo de "quebra" da hash MD5 que corresponde 
	 * a alguma palavra de 5 dígitos composta por caracteres alfanuméricos.
	 */
	public void run() {

		try {
			if (descobrePalavraCorrespondente(this.hashMD5Compare, this.inicio, this.fim)) {

				System.out.println("---------------------------------------------------");
				System.out.println("\nPalavra descoberta!\n");
				System.out.println("Hash: " + getHashMD5Compare());
				System.out.println("Senha: " + getPalavraDescobertaMD5());

				// Seta tempo final de execução da thread
				MD5Paralelo.setTempoFinalThread(System.currentTimeMillis(), MD5Paralelo.getCounterExecucoes());
				MD5Paralelo.setCounterExecucoes(MD5Paralelo.getCounterExecucoes() + 1); // counterExecucoes++
			}
		} catch (NoSuchAlgorithmException e) {e.printStackTrace();}

	}

	/**
	 * Método que faz "força-bruta" para descobrir qual palavra tem tal hash de
	 * entrada. Ele faz isso permutando entre todos as palavras alfanuméricos 
	 * (minúsculos) de 5 dígitos.
	 * @param hash A hash a qual deseja descobrir a palavra que a originou.
	 * @param inicio Um número de 0 a 36 que marca qual char começa a fazer combinações.
	 * @param fim Um número de 0 a 36 que marca qual char terminam as combinações.
	 * @return true, se o algoritmo descobriu qual a palavra correspondente à hash,
	 *         false, caso contrário.
	 * @throws NoSuchAlgorithmException Quando a manipulação de MD5 dá problema.
	 */
	private boolean descobrePalavraCorrespondente(String hash, int inicio, int fim)
			throws NoSuchAlgorithmException {
		
		String[] caracteresAlfaNum = {"0", "1", "2", "3", "4", "5", "6", "7",
				"8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", 
				"k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
				"w", "x", "y", "z"};
		boolean euDescobri = false;

		DecifragemMD5Service quebra = new DecifragemMD5Service();

		for (int a = inicio; a <= fim; a++) {
			for (String b : caracteresAlfaNum) {
				for (String c : caracteresAlfaNum) {
					for (String d : caracteresAlfaNum) {
						for (String e : caracteresAlfaNum) {

							String combinacao = caracteresAlfaNum[a] + b + c + d + e;
							euDescobri = DecifragemMD5Service.quebraHashMD5(combinacao, hash);
							descobriu = euDescobri;

							if (descobriu) {
								if (euDescobri) {
									setPalavraDescobertaMD5(combinacao);
									return descobriu;
								}
								else // outra thread descobriu a palavra
								{
									Thread.currentThread().interrupt(); // mata thread
								}
								break; //fecha este for "e"
							}
						}
						if (descobriu) {
							break; //fecha este for "d"
						}
					}
					if (descobriu) {
						break; //fecha este for "c"
					}
				}
				if (descobriu) {
					break; //fecha este for "b"
				}
			}
			if (descobriu) {
				break; //fecha este for "a"
			}
		}
		return descobriu; // caso o for seja percorrido até o final; false
	}

}
