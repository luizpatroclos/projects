package oca.inheritance.one;

public class Test {
	
	static Animal a = new Bird();
	
	//Since the class “Bird” has implemented the interface “Fly” Bird object can be considerd as a “Fly”, 
	//simply a “Bird” class object passes the “is-a” test with “Fly”.
	public static void main (String args[]) {
		
	System.out.print(a instanceof Fly);	
		
		//yes this is true
		
	}

}
