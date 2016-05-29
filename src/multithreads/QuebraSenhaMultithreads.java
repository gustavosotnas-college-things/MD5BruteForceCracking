package multithreads;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

public class QuebraSenhaMultithreads implements Runnable {

	private int inicio;
	private int fim;
	private String hash;
	private static boolean quebrado = false;

	synchronized static boolean getQuebrado() {
		return quebrado;
	}

	synchronized static void setQuebrado(boolean newquebrado) {
		quebrado = newquebrado;
	}

	public QuebraSenhaMultithreads(int inicio, int fim, String hash) {
		this.inicio = inicio; // inicio da posição do elemento no arraylist
		this.fim = fim; // fim da posição do elemento no arraylist
		this.hash = hash;
	}

	public void run() {

		Collections.synchronizedCollection(new ArrayList<>());
		ArrayList<String> completo = new ArrayList<>();

		for (int i = 0; i < 10; i++) {

			completo.add(Integer.toString(i));
		}

		for (char x = 'a'; x <= 'z'; x++) {

			completo.add(String.valueOf(x));
		}

		try {
			permutacao(completo, this.hash, this.inicio, this.fim);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (ExecutionException a) {
			// System.out.println("Thread parou");
		}

	}

	private void permutacao(ArrayList<String> completo, String hash, int inicio, int fim)
			throws NoSuchAlgorithmException, ExecutionException {

		QuebraSenhaMD5Multithreads quebra = new QuebraSenhaMD5Multithreads();

		for (int a = inicio; a <= fim; a++) {
			for (String b : completo) {
				for (String c : completo) {
					for (String d : completo) {
						for (String e : completo) {

							String combinacao = completo.get(a) + b + c + d + e;
							setQuebrado(quebra.crackingThreads(combinacao, hash));

							if (getQuebrado()) {
								return;
							}
						}
					}
				}
			}
		}

	}

}
