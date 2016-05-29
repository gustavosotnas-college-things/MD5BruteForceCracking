package sequencial;

import java.util.ArrayList;

public class QuebraSenha {

	public void iniciaQuebra(String hash) throws Exception {

		ArrayList<String> completo = new ArrayList<>();

		for (int i = 0; i < 10; i++) {

			completo.add(Integer.toString(i));
		}

		for (char x = 'a'; x <= 'z'; x++) {

			completo.add(String.valueOf(x));
		}

		permutacao(completo, hash);

	}

	private void permutacao(ArrayList<String> completo, String hash) throws Exception {

		QuebraSenhaMD5 quebra = new QuebraSenhaMD5();
		boolean quebrado = false;
		
		for (String a : completo) {
			for (String b : completo) {
				for (String c : completo) {
					for (String d : completo) {
						for (String e : completo) {

							String combinacao = a + b + c + d + e;
						    quebrado = quebra.Cracking(combinacao, hash);
							
							if(quebrado){
								return;
							}
						}
					}
				}
			}
		}

	}
}
