package oca.inheritance.two;

public interface I {
	int x = 10;
	
	public default int getValue() {
		return 5;
	}
	
	//Such limitation is that we cannot override methods of Objects class.
	//public default String toString() {
		
	//	return "I";
		
//	}

}
