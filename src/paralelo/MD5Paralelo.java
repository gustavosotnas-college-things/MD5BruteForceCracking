package paralelo;

import java.security.NoSuchAlgorithmException;

public class MD5Paralelo {

	public final static int NUMERO_THREADS = 4;
	public final static int CARACTERES = 36; // Alfanuméricos minúsculos

	// "Entrada" do usuário
	private final static String md5Hashes[] = {
		"17a0a00212dde12b063af7dc22fdf02b",
		"75abfe3020804dd73a2a6040da9df96c",
		"c77aeec24015ad7e6e0b1db9d9deed68"
	};

	// Tempo inicial e final de execução de cada hash MD5, independentemente das threads
	private static long[] tempoInicialThreads = new long[md5Hashes.length];
	private static long[] tempoFinalThreads = new long[md5Hashes.length];
	private static int counterExecucoes = 0; // Incrementará até md5Hashes.length 

	synchronized static long[] getTempoFinalThreads() {
		return MD5Paralelo.tempoFinalThreads;
	}

	synchronized static void setTempoFinalThread(long tempoThread, int index) {
		MD5Paralelo.tempoFinalThreads[index] = tempoThread;
	}
	
	synchronized static int getCounterExecucoes() {
		return MD5Paralelo.counterExecucoes;
	}
	
	synchronized static void setCounterExecucoes(int newCounterExecucoes) {
		MD5Paralelo.counterExecucoes = newCounterExecucoes;
	}

	// MAIN
	public static void main(String[] args) throws NoSuchAlgorithmException {

		System.out.println("Algoritmo de Quebra de Hash MD5 (Paralelo, "
					+ NUMERO_THREADS + " threads)\n\nExecutando cálculos...");

		// Chamadas do algoritmo para cada hash MD5 sequencialmente
		for (String md5Hash : md5Hashes) {
			calculaQuebraMD5(md5Hash);
		}

		long[] temposDeExecucao = calculaTemposDeExecucao();

		System.out.println("---------------------------------------------------");
		System.out.println("RESULTADOS\n");
		System.out.println("Tempo de execução do algoritmo de quebra de hash MD5:\n\n");
		
		for (int i=0; i<md5Hashes.length; i++) {
			System.out.println("'" + md5Hashes[i] + "': " + temposDeExecucao[i] + " s");
		}

		System.exit(0); // Sai do programa fechando todas as threads
	}

	/**
	 * Executa o algoritmo de quebra de hash MD5 com um contador de tempo de execução.
	 * 
	 * @param md5Hash A hash para passar por decifragem.
	 * @throws NoSuchAlgorithmException 
	 */
	private static void calculaQuebraMD5(String md5Hash) throws NoSuchAlgorithmException {

		QuebraSenhaMultithreads[] pseudoPoolThreads = new QuebraSenhaMultithreads[NUMERO_THREADS];

		for (int i = 0; i<NUMERO_THREADS; i++)
		{
			if (i != 0) {
				// Cria as threads, dividindo os CARACTERES igualmente entre elas
				pseudoPoolThreads[i] = new QuebraSenhaMultithreads
						((CARACTERES/(NUMERO_THREADS/i)), (CARACTERES/(NUMERO_THREADS/(i+1)))-1, md5Hash);
			}
			else { // i == 0
				pseudoPoolThreads[i] = new QuebraSenhaMultithreads
						(0, (CARACTERES/(NUMERO_THREADS/(i+1)))-1, md5Hash);
			} // Para evitar exception de divisão por Zero
			
			 // Tempo inicial (To)
			tempoInicialThreads[i] = System.currentTimeMillis();

			// Executa as threads
			pseudoPoolThreads[i].start();

			// Main espera as threads terminarem
			try {
				pseudoPoolThreads[i].join();
			} catch (InterruptedException e) {
				System.err.println("Erro ao executar uma thread");
			}
		}
	}
	
	/**
	 * Calcula os tempos de execução do algoritmo de quebra de MD5 em segundos.
	 * @return Uma "lista" com os tempos de execução para cada hash processada.
	 */
	private static long[] calculaTemposDeExecucao() {

		long[] temposDeExecucao = new long[md5Hashes.length];

		for (int i=0; i<NUMERO_THREADS; i++) {
			temposDeExecucao[i] = (tempoFinalThreads[i] - tempoInicialThreads[i]) / 1000; // (em segundos)
		}
		return temposDeExecucao;		
	}
}
