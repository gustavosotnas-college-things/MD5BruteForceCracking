package paralelo;

public class MD5Multithreads {

	private static long inicio;
	private static long fim;

	public static void setFim(long tempofim) {
		fim = tempofim;
	}

	public static void main(String[] args) {

		// String hash1 = "17a0a00212dde12b063af7dc22fdf02b";
		// String hash2 = "75abfe3020804dd73a2a6040da9df96c";
		String hash3 = "c77aeec24015ad7e6e0b1db9d9deed68";

		QuebraSenhaMultithreads quebra1 = new QuebraSenhaMultithreads(0, 8, hash3);
		QuebraSenhaMultithreads quebra2 = new QuebraSenhaMultithreads(9, 17, hash3);
		QuebraSenhaMultithreads quebra3 = new QuebraSenhaMultithreads(18, 26, hash3);
		QuebraSenhaMultithreads quebra4 = new QuebraSenhaMultithreads(27, 35, hash3);

		inicio = System.currentTimeMillis();
		Thread t1 = new Thread(quebra1);
		Thread t2 = new Thread(quebra2);
		Thread t3 = new Thread(quebra3);
		Thread t4 = new Thread(quebra4);

		t1.start();
		t2.start();
		t3.start();
		t4.start();

		try {

			t1.join();
			t2.join();
			t3.join();
			t4.join();

		} catch (Exception e) {

			System.err.println("Erro ao executar Threads");
		}
	}

	public static void calculaTempo() {
		System.out.println("Tempo gasto: " + (fim - inicio) / 1000 + " seg");
		System.exit(0);
	}

}
