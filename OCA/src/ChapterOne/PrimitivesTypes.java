package ChapterOne;

public class PrimitivesTypes {
	
	static int    a = 20;
	static float  b = 17;
	static double c = 11;
	static double d = 10.3d;
	static float  e = 20.2f;
	static short  f = 10;
	static short  g = 20;
	static char   h = 30;
	static byte   n = 10;
	static char   p = 10;
	//1. If two values have different data types, Java will automatically promote one of the values to the larger of the two data types. 
	//2. If one of the values is integral and the other is floating-point, Java will automatically promote the integral value to the floating-point value’s data type.
	//3. Smaller data types, namely byte, short, and char, are first promoted to int any time they’re used with a Java binary arithmetic operator, 
	   //even if neither of the operands is int. 
	//4. After all promotion has occurred and the operands have the same data type, the resulting value 
	   //will have the same data type as its promoted operands. 
	
	public static void main( String args[]){
		
	float l = a * b;	
	double j = a * b / d;	
	float m = (float) (b * c);
	
	double   w = n + c;
		
		
		
		
	}

}
