package paralelo;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

public class QuebraSenhaMultithreads extends Thread {

	private int inicio;
	private int fim;
	private String hash;
	private static boolean quebrado = false;
	private volatile Thread blinker = null; //Variável nula pra parar thread em stop()

	synchronized static boolean getQuebrado() {
		return quebrado;
	}

	synchronized static void setQuebrado(boolean newquebrado) {
		quebrado = newquebrado;
	}

	public QuebraSenhaMultithreads(int charInicio, int charFim, String hash) {
		this.inicio = charInicio; // inicio da posi��o do elemento no arraylist
		this.fim = charFim; // fim da posi��o do elemento no arraylist
		this.hash = hash;
	}

	public void run() {

		// pega instância da thread atual para fazer parada preemptiva da thread
		Thread thisThread = Thread.currentThread();
		Collections.synchronizedCollection(new ArrayList<>());
		ArrayList<String> completo = new ArrayList<>();

		for (int i = 0; i < 10; i++) {

			completo.add(Integer.toString(i));
		}

		for (char x = 'a'; x <= 'z'; x++) {

			completo.add(String.valueOf(x));
		}

		try {
			descobrePalavraCorrespondente(completo, this.hash, this.inicio, this.fim);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (ExecutionException a) {
			// System.out.println("Thread parou");
		}
		stopThread();
	}

	private void descobrePalavraCorrespondente(ArrayList<String> completo, String hash, int inicio, int fim)
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

	/**
	 * Interrompe a thread setando a variável flag 'blinker' como NULL
	 * e na função run o while faz a função parar.
	 */
	public void stopThread() {
        blinker = new Thread(this);
        blinker.start();
    }
}
