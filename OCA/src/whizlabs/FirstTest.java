package whizlabs;

import java.util.Arrays;
import static java.lang.System.*;// here we have the possibility to import a static import and use it in a static method.

public class FirstTest {

	public static void main(String args[]){
		
		//basic question on the OCA from Oracle certification 
		char[] chars = {'A','B','1','2','@'};
		
		int i = chars[chars.length -1];
		
		out.print(i);
		
		out.println(" correct value of "+ chars[chars.length -1]);//4
		
		System.out.println(" correct value of "+ chars[3]);//2
		
		System.out.println(" correct value of "+ chars[5]);//ArrayIndexOutOfBound
		
		//System.out.println(" correct value of "+ chars[chars.last]);//exception / Method (last) do not exist
		
	}

}

class Program {
	
    public static void main(String [ ] args) {
    	
    int a [ ] = { 1,2,053,4};
    int b [ ][ ] = { {1,2,4} , {2,2,1},{0,43,2}};
    
    System.out.print(a[3] == b[0][2] );
    
    System.out.print(" " + (a[2] == b[2][1]));
    
    }
    
}


class Program4 {

	public static void main(String[] args) {

		// In addition to “normal” numbers, numeric literals are allowed
		// to begin with 0 (octal), 0x (hex), 0X (hex), 0b (binary), or 0B
		// (binary).
		int[] ints = new int[0b101]; // have to be converted to binary 101 = 5
										// positions

		int len = ints.length;

		for (int i : ints)
			System.out.print(i);

	}
	
}
	class Program6 {

		public static void main(String[] args) {

            String[ ][ ] strings = {{"A","Z"},{"C","D","S"},{"L"}}; //do not sorting a multidimensional Array
            
            //String[ ] stringsT = {"A","Z"};
            
            Arrays.sort(strings);
            
            for(String [ ]str : strings) {
            	
                for(String s :  str) {
                	
                    System.out.print(s);
                    
                }
            
                }
            }
			
			

		}
    

	
	class Program9 {
		
		static Integer I;
	

	public static void main(String[] args) {

		String s;

		try {

			s = I.toString();
			
		} finally {
			try {

				int i = Integer.parseInt(args[0]);

			} catch (NumberFormatException E) {

				System.out.print("NumberFormat ");
			} finally {

				System.out.print("Fin2 ");
			}

			System.out.print("Fin1 ");
		}

	}
}
	
class Program12 {

	public static void main(String[] args) {

		Object obj = new Double(3);

		Number num = (Number) obj;// Number can be converted to any types of primitives like (float, double, int, short)

		System.out.println(num);

	}
}


class Program17 {

	public static void main(String[] args) {

        int x = 2;
        
      //  for ( int x = 0; x < 3; x++ ) {// is not allow to declare a variable with the same name in the same scope
        	
        //    System.out.print(x);
       // }

	}
}


class Program18 {

	static int x = 10;

	public static void main(String[] args) {
		
		for (x = 1; x < 3; x++) {// The secret is (value of x was changed)

			System.out.print(x);
		}

		System.out.println(x);

	}
}

//question 20
// Top level class must be public
// public class Point {}



class Program26 {

	static String o = "";

	public static void main(String[] args) {
		
		
        z: for (int x = 3; x < 8; x++) {                         
		
            if (x == 4)
                continue;
            if (x == 4)
                break z;
            o += x;
		
        }
    System.out.println(o);

	}
}

class Program27 {

    static int x = 1;

	public static void main(String[] args) {
		
        int[ ] nums = {0,1,2,3,4}; 
        
        for(int x : nums) {
        	
            System.out.print(x);
            
            continue;
             
            //System.out.print(x + Program27.x);// every statement that becomes before 'continue' won't be recognized by the compiler 
        }
		
        

	}
}

class Program28 {

	public static void main(String[] args) {
	
        int x = 20;
        
        while( x > 0 ) {
        	
            do {
                x -= 2;
            } while ( x > 5 );
            
            x--;
            System.out.print(x);
            
            
            }
        }
		
}

class Program31 {

	public static void main(String[] args) {

		final int x = 0;
		final int y = 2;
		switch (x + y) {

		case x: {
			System.out.print("A");
		}
		case 1:
			System.out.print("B");
		default:
			System.out.print("default");
			break;
		case y:
			System.out.print("C");

		}

	}

}


class Program32 {

	public static void main(String[] args) {

		final int x;
		x = 0;
		final int y = 2;
		
		//compile time, the variable must be compile as a time constant 
		//ex: final int x = 0; instead of final int x; and then in the next line x = 0;
		
		switch (x) {

		//case x: {
		//	System.out.print("A");
		//}
		case 1:
			System.out.print("B");
		default:
			System.out.print("default");
			break;
		case y:
			System.out.print("C");

		}

	}

}

class Program34 {

	public static void main(String[] args) {

		String out = "0";
	
		
		int i = -1, j = -5;
		
		if (i < 5)
			if (j > 0)
				if (i > j)
					out += "1";
				else
					out += "2";
			else
				out += "3";
		else
			out += "4";
		System.out.println(out);// result is "03" because you can't forget that we're talking about the "String" type.
								// So, when we have  " += " pay close attention to the type.

	}

}


class Program38 {

	public static void main(String[] args) {

		
        System.out.print("" + 1 + 2);
        
        System.out.print("" + (1 + 2));
		
		
		
		

}
	
}		
		
        
class Program42A {

	static void method() {

		System.out.print(" A B");

	}

}

class Program42B extends Program42A {

	protected static void method(String[] args) {

		System.out.print(" C D");

	}

}	

class Program42C {

	public static void main(String[] args) {

		Program42A a = new Program42B();
		a.method();

	}

}

class Program50C {

	static {

		//x = 10;//Here at this point, primitive types will be recognized only if it were static as well.

		y = 5;
	}

	//final int x;
	final static int y;

	public static void main(String[] args) {

		try {

			Program pr = new Program();

			//int c = pr.x / y;

			//System.out.print(c);

		} catch (ArithmeticException E) {

			System.out.print("Arithmetic Exception");
		}

	}

}


class Program51C {

	public static void main(String[] args) {

		/*A ab = new B();
		
		System.out.println(ab.x);
		System.out.println(ab.j);
		
		System.out.println(B.j);
		
		System.out.println(A.j);*/
		
		//System.out.println(A.x);
		
		//System.out.println(B.x); For both example: They will not be able to get a variable that is not static.

	}

}

/*class A {

	protected int x = 10;
	static int j = 21;

}

class B extends A {

}*/
