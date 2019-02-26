package Abstract;



//import Abstract.Animal;

public class test {
	
	
	
	public static void main (String args[]) {
		
		
		Bola bola = new Bola();
		
		Ave ave = new Ave();
		
		
		limpar(bola);
		
		limpar(ave);
				
		
	}
	
	
	
	public static void limpar(Object obj) {
		
		// Código para limpar uma bola ou um animal...
		if (obj instanceof Bola) {
			
			((Bola) obj).emitirSom();
			
		} else if (obj instanceof Ave) {
			
			///((Animal) obj).emitirSom();
		}
	}
	
	

}
