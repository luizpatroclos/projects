package tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Chapter_two {
	
	
	public static void main(String[] args){
		
		
		
		List<Integer> numbers = new ArrayList<>();
		numbers.add(1);
		numbers.add(2);
		
		//numbers.remove(1);
		
		numbers.remove(new Integer(1));
		System.out.println(numbers);
		
		
		
		
		int bad1 = Integer.parseInt("1");
		
		
		Integer bad2 = Integer.parseInt("1");
		
		int bad3 = Integer.valueOf(9);
		
		System.out.println("valor da Int é -"+bad1);
		
		
		/*int y = 1;
		int z = 1;
		final int x = y<10 ? y++ : z++;
		System.out.println(y+","+z);*/
		
		byte a = 40, b = 50;
		byte sum = (byte) ((byte) a + b);
		System.out.println(sum);
		
		
		int j = 1;
		int k = 1;
		final int h = j<=10 ? j++ : ++k;
		System.out.println(j+","+k);
		
		
		/*int j = 1;
		int k = 1;
		
		j++;
		++k;
		
		System.out.println(j+","+k);*/
		
		SimpleLoop loo = new SimpleLoop();
		
		complexLoop loo2 = new complexLoop();
		
		//loo2.mainLoop();
		
		//loo.mainLoop();
		
		//loo.otherLoop();
		
		//loo2.max();
		
		loo2.lambda();
		
	
		}
		
		
	}


	
class SimpleLoop {

	public void mainLoop() {

		int[][] values = { { 1, 2, 3, 4 }, { 9, 7, 5, 2 }, { 6, 8, 0, 7 } };

		for (int[] newValue : values) {

			for (int i = 0; i < newValue.length; i++){
				
				System.out.println("value number - " + newValue[i]);
		
			if(newValue[i] == 0){
				
				System.out.println("Valor a valor -"+ newValue[i - 1]);
			

		}

	}

}
}
	
	public void otherLoop(){
		
		
		int x = 0;
		while(x++ < 10) {}
		String message = x > 10 ? "Greater than" : "false";
		
		int num = 5;
		
		int TT = (num > 4 ? num < 4 ? 10 : 8 : 7);
		
		System.out.println(message+","+x);
		 }
		
		
	}


class complexLoop {
	
	public void mainLoop() {

	int[][] list = { { 1, 13, 5 }, { 1, 2, 5 }, { 2, 7, 2 } };
	
	int searchValue = 2;
	int positionX = -1;
	int positionY = -1;
	
	PARENT_LOOP:for(int i = 0;i<list.length;i++){
		
		for (int j = 0; j < list[i].length; j++) {
			if (list[i][j] == searchValue) {
				positionX = i;
				positionY = j;
				break PARENT_LOOP;
			}
		}
	}if(positionX==-1||positionY==-1){
		
		System.out.println("Value " + searchValue + " not found");
	}else
	{
		System.out.println("Value " + searchValue + " found at: " + "(" + positionX + "," + positionY + ")");
	}

}
	
	public void max(){
		
		boolean x = true, z = true;
		int y = 20;
		x = (y != 10) ^ (z= false);
		
		System.out.println(x+", "+y+", "+z);
		
		
		
	}
	
	public void lambda(){
		
		System.out.println("Imprime todos os elementos pares da lista!");
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
		list.forEach(n -> {
		       if (n % 2 == 0) {
		             System.out.println(n);
		       }                   
		});
		
	}
}