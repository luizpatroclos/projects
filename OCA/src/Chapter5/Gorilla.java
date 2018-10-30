package Chapter5;

public class Gorilla extends Animal {

	
	public Gorilla(int age) {
		super(age, "Gorilla");
	}

	public Gorilla() {
		super(5);
	}
	

	
	public static void main( String args[] ){
		
		int valor = 24;
		
		Gorilla gorilla = new Gorilla(valor);
		
		System.out.println("Valor do Animal - " + gorilla.getAge() +" - "+ gorilla.getName());
		
		
		
	}
	
	

}
