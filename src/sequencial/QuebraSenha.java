package sequencial;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class QuebraSenha {

	public void iniciaQuebra(String hash) throws NoSuchAlgorithmException {

		String[] caracteresAlfaNum = {"0", "1", "2", "3", "4", "5", "6", "7",
				"8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", 
				"k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
				"w", "x", "y", "z"};

		if (permutacao(caracteresAlfaNum, hash)) {
			System.out.println("---------------------------------------------------");
			System.out.println("\nPalavra descoberta!\n");
			System.out.println("Hash: " + hashMD5compare);
			System.out.println("Senha: " + combinacao);
		}

	}

	private void permutacao(String[] caracteresAlfaNum, String hash) throws NoSuchAlgorithmException {

		QuebraSenhaMD5 quebra = new QuebraSenhaMD5();
		boolean descobriu = false;

		for (String a : caracteresAlfaNum) {
			for (String b : caracteresAlfaNum) {
				for (String c : caracteresAlfaNum) {
					for (String d : caracteresAlfaNum) {
						for (String e : caracteresAlfaNum) {

							String combinacao = a + b + c + d + e;
						    descobriu = quebra.Cracking(combinacao, hash);
							
							if(descobriu){
								return descobriu;
							}
						}
					}
				}
			}
		}

	}
}
