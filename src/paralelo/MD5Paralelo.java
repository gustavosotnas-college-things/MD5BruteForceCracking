package paralelo;

import java.security.NoSuchAlgorithmException;

public class MD5Paralelo {

	public final static int NUMERO_THREADS = 4;
	public final static int CARACTERES = 36; // Alfanuméricos minúsculos

	private static long[] tempoInicialThreads = new long[NUMERO_THREADS];
	private static long[] tempoFinalThreads = new long[NUMERO_THREADS];

	synchronized static long[] getTempoThreads() {
		return MD5Paralelo.tempoFinalThreads;
	}

	synchronized static void setTempoThread(long tempoThread, int index) {
		MD5Paralelo.tempoFinalThreads[index] = tempoThread;
	}

	public static void main(String[] args) {

		System.out.println("Algoritmo de Quebra de Hash MD5 (Sequencial)\n\nExecutando cálculos...");

		final String md5Hashes[] = {
			"17a0a00212dde12b063af7dc22fdf02b",
			"75abfe3020804dd73a2a6040da9df96c",
			"c77aeec24015ad7e6e0b1db9d9deed68"
		};

		QuebraSenhaMultithreads quebra1 = new QuebraSenhaMultithreads(0, 8, md5Hashes[2]);
		QuebraSenhaMultithreads quebra2 = new QuebraSenhaMultithreads(9, 17, md5Hashes[2]);
		QuebraSenhaMultithreads quebra3 = new QuebraSenhaMultithreads(18, 26, md5Hashes[2]);
		QuebraSenhaMultithreads quebra4 = new QuebraSenhaMultithreads(27, 35, md5Hashes[2]);

		//inicio = System.currentTimeMillis();

		quebra1.start();
		quebra2.start();
		quebra3.start();
		quebra4.start();

		try {

			quebra1.join();
			quebra2.join();
			quebra3.join();
			quebra4.join();

		} catch (Exception e) {

			System.err.println("Erro ao executar Threads");
		}
	}

	public static void calculaTempo() {
		//System.out.println("Tempo gasto: " + (fim - inicio) / 1000 + " seg");
		System.exit(0);
	}

	/**
	 * Executa o algoritmo de quebra de hash MD5 com um contador de tempo de execução.
	 * 
	 * @param md5Hash A hash para passar por decifragem.
	 * @return O tempo de execução da função de cálculo de números primos.
	 * @throws NoSuchAlgorithmException 
	 */
	private static long calculaQuebraMD5(String md5Hash) throws NoSuchAlgorithmException {
		
		QuebraSenhaMultithreads[] pseudopoolThreads = new QuebraSenhaMultithreads[NUMERO_THREADS];
		
		for (int i = 0; i<NUMERO_THREADS; i++)
		{
			pseudopoolThreads[i] = new QuebraSenhaMultithreads
					((CARACTERES/(NUMERO_THREADS/i)), (CARACTERES/(NUMERO_THREADS/i+1))-1, md5Hash);
			tempoInicialThreads[i] = System.currentTimeMillis();
			pseudopoolThreads[i].start();
			try {
				pseudopoolThreads[i].join();
			} catch (InterruptedException e) {
				System.err.println("Erro ao executar uma thread");
			}
		}
	}
}
