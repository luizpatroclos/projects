package tests;

import java.util.List;

import ChapterOne.Vehicle;

public class LogicalOperators {
	
	
	public static void main(String[] args){
		
		
		int x = 6; 
		
		boolean y = (x >= 6) || (++x <= 7);
		
		System.out.println(x); 
		
		Vehicle car = new Vehicle();
		
		
		List<Vehicle> myList = car.getAllVehicle();
		
		String m = "value";
		String n = null;
		
		
		if((m != null) && (myList.get(0).getYear() < 5)) {  // Do something }
			
			System.out.println("this compile for the first statement");
			
		}
	
		
		if(n != null & myList.get(0).getYear() < 5) { // Throws an exception if x is null  // Do something }
			
			System.out.println("this compile for the second statement");
		}
		
	}
}
