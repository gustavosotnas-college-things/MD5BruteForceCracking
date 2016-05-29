package sequencial;

public class MD5 {

	public static void main(String[] args) throws Exception {

		System.out.println("Algoritmo de Quebra de Hash MD5 (Sequencial)\n\nExecutando cálculos...");

		final String md5Hashes[] = {
			"17a0a00212dde12b063af7dc22fdf02b",
			"75abfe3020804dd73a2a6040da9df96c",
			"c77aeec24015ad7e6e0b1db9d9deed68"
		};

		long tempo1 = calculaQuebraMD5(md5Hashes[0]);
		long tempo2 = calculaQuebraMD5(md5Hashes[1]);
		long tempo3 = calculaQuebraMD5(md5Hashes[2]);

		System.out.println("---------------------------------------------------");
		System.out.println("RESULTADOS\n");

		System.out.println("Tempo de execução do algoritmos de quebra de hash MD5:\n\n"
						+ "'" + md5Hashes[0] + "': " + tempo1 + " s");
		System.out.println("'" + md5Hashes[1] + "': " + tempo2 + " s");
		System.out.println("'" + md5Hashes[2] + "': " + tempo3 + " s");

	}
	
	/**
	 * Executa o algoritmo de quebra de hash MD5 com um contador de tempo de execução.
	 * 
	 * @param md5Hash A hash para passar por decifragem.
	 * @return O tempo de execução da função de cálculo de números primos.
	 */
	private static long calculaQuebraMD5(String md5Hash) throws Exception {

		QuebraSenha objDecifrador = new QuebraSenha();
		//---------------------------------------------------------------------
		long T0 = System.currentTimeMillis(); // Tempo inicial (To)
		
		objDecifrador.iniciaQuebra(md5Hash);
		
		long T = System.currentTimeMillis(); // Tempo final (T)
		//---------------------------------------------------------------------
		
		long tempoDeExecucao = (T - T0) / 1000; // (em segundos)

		return tempoDeExecucao;
	}
}
