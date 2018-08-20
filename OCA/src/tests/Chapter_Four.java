package tests;

import java.util.ArrayList;

public class Chapter_Four {
	
	
	public Chapter_Four(String value1){}
	public Chapter_Four(String value2, String value3){System.out.println("value of constructor One"+ value2);}
	public Chapter_Four(int value4){}
	
	
	
	
	
	
	private static final ArrayList<String> values = new ArrayList<>();
	
	//public static final int NUMBER_OPERATIONS = 45;
	public static int NUMBER_OPERATIONS = 45;
	
	
	
	public static void main(String[] args){
		
		String[] valores = Operator.valores;
		
		//public static final int NUMBER_OPERATIONS = 45;
		
		//call one
		Chapter_Four ahead = new Chapter_Four(NUMBER_OPERATIONS);
		
		ahead.value(10);
		ahead.value(50);
		
		
		

		
		for (String real : valores) {
			
			values.add(real);
			
		}
		
		NUMBER_OPERATIONS = 50;
		
		System.out.println("valor do static - "+ values.get(3));
		
		
		
	
		}
	
	
	public void  value( int value){
		
		
		System.out.println("Valor real 1="+ value);
		
	}
	
	public void value(Integer value) {
		
		System.out.println("Valor real 2="+ value);

	}
		
		
	}

class Operator{
	
	static String[] valores = {"Dolar","Euro","Real","Peso","Libra"};
	
	private static final int NUM_SECONDS_PER_HOUR;
	static {
	int numSecondsPerMinute = 60;
	int numMinutesPerHour = 60;
	NUM_SECONDS_PER_HOUR = numSecondsPerMinute * numMinutesPerHour;
	}
	
	
	
}

