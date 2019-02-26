package Excecao;

public class RecebeArray {
	
	public RecebeArray(String[] array) {
		
		imprimirPosicao0(array);
	}

	public void imprimirPosicao0(String[] array) {
		
		
		try {
			System.out.println(array[0]);
			
		} catch (ArrayIndexOutOfBoundsException e) {
			
			System.out.println("Erro: Array vazio, execute o programa novamente" + " passando ao menos um parâmetro.");
		}
		
		
		
		
		
		System.out.println(array[0]);
	}

}
