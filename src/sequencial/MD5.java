package sequencial;

public class MD5 {

	public static void main(String[] args) throws Exception {

		final String md5Hashes[] = {
			"17a0a00212dde12b063af7dc22fdf02b",
			"75abfe3020804dd73a2a6040da9df96c",
			"c77aeec24015ad7e6e0b1db9d9deed68"
		};
		
		long inicio1 = System.currentTimeMillis();
		QuebraSenha hack1 = new QuebraSenha();
		hack1.iniciaQuebra(md5Hashes[0]);
		long fim1 = System.currentTimeMillis();
		
		System.out.println("Tempo gasto: " + (fim1-inicio1)/1000 + "s\n\n");
		
		long inicio2 = System.currentTimeMillis();
		QuebraSenha hack2 = new QuebraSenha();
		hack2.iniciaQuebra(md5Hashes[1]);
		long fim2 = System.currentTimeMillis();
		
		System.out.println("Tempo gasto: " + (fim2-inicio2)/1000 + "s\n\n");
		
		long inicio3 = System.currentTimeMillis();
		QuebraSenha hack3 = new QuebraSenha();
		hack3.iniciaQuebra(md5Hashes[2]);
		long fim3 = System.currentTimeMillis();
		
		System.out.println("Tempo gasto: " + (fim3-inicio3)/1000 + "s");

	}
}
