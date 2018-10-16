package tests;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import ChapterOne.Vehicle;

public class Developer {
	
	Date valor = new Date();
	

	public static void main(String[] args) {
		
		//int y = 5;
		
		//int value = (y > 5) ? 21 : "Zebra");
		//System.out.println((y > 5) ? 21 : "Zebra"); 
		
		// final float valor = 102.0f;
		
		/*int y = 1; 
		int z = 1; 
		final int x = y<10 ? y++ : z++; 
		System.out.println(y+","+z); // Outputs 2,1 
*/		
		
		/*int y = 1; int z = 1; 
		final int x = y>=10 ? y++ : z++; 
		System.out.println(y+","+z); // Outputs 1,2 
*/	
		
		
		/*int dayOfWeek = 5;
		switch (dayOfWeek) {
		default:
			System.out.println("Weekday");
			break;
		case 0:
			System.out.println("Sunday");
			break;
		case 6:
			System.out.println("Saturday");
			break;
		} */
		
		
		//With a value of dayOfWeek of 5, this code will output: Weekday  
		
		
		
		/*int dayOfWeek = 6;
		switch (dayOfWeek) {
		case 0:
			System.out.println("Sunday");
		default:
			System.out.println("Weekday");
		case 6:
			System.out.println("Saturday");
			break;
		case 7:
			System.out.println("Monday");
		case 8:
			System.out.println("Thursday");
		case 9:
			System.out.println("Friday");
			break;
		}

		*/
		
		
		/*int dayOfWeek = 0;
		int dayOfWeekend = 6;
		
		if (dayOfWeek >= 0) {

			do {
				dayOfWeek++;
				System.out.println("this is a day of Week:" + dayOfWeek);

			} while (dayOfWeek <= dayOfWeekend);
			System.out.println("This is a day of Weekend :" + dayOfWeekend);

		} else {

			System.out.println("this is a day of Week:" + dayOfWeek);

		}*/
		
		
		
		List<String> myCarList = Arrays.asList("Renegade","Compass","Camaro","F150","HRV","Outback","Accord","Civic");
		
		Vehicle myTestAux = new Vehicle();
		
		List<Vehicle> myObjectAux = myTestAux.getAllVehicle();
		
		Collections.sort(myCarList);
		
		
		
		for(int i = 0; i < 10; i++){
			
			Collections.rotate(myCarList, i);
		
		
		//Return year of existence models
		int x = 0;
		for (int y = 0, z = 0; x < myCarList.size() && y < myObjectAux.size(); x++, y++) {
			
			String car = myCarList.get(x);
			
			Vehicle veh = myObjectAux.get(y);
			
			if (car.equals(veh.getModel())) {
				
				System.out.println("Vehicle : " +car+ " -- " +"Year : "+veh.getYear());
				
			}
		
		}
		 
	}	
		
		
		
		
		List<String> myList = Arrays.asList("claudio","roberto","fabricio","ronaldo","paulo");
		
		Lambda dev = new Lambda();
		
		String valorEx = "paulo";
		
		dev.listagemLambdaString(myList, (n) -> n == valorEx);
		
		Vehicle myTest = new Vehicle();
		Vehicle myBean = new Vehicle();
		
		myBean.setModel("Civic");
		
		
		
		
		List<Vehicle> myObject = myTest.getAllVehicle();
		
		
		Vehicle returnVehicle = dev.listagemLambdaObject(myObject, myBean);
		
		
		
		
		
		
		/*BigDecimal valor1 = new BigDecimal("0.00");
		
		
		System.out.println("valor BigDecimal "+valor1);
		
		 
		 System.out.println(args[0]);
		 System.out.println(args[1]);
		
		LocalDate date = LocalDate.of(2020, Month.JANUARY, 20);
		//date.plusDays(10);
		LocalDate date2 = date.plusDays(10);
		System.out.println(date2);
		
		
		//LocalDate date = LocalDate.now();
		
		int dayOfMonth = date.getDayOfMonth();
		
		DayOfWeek dayOfWeek = date.getDayOfWeek();
		
		int year = date.getYear();
		
		System.out.println("Parans of LocalDate = "+year+" - "+dayOfMonth+" - "+dayOfWeek+" - "+date+"");
		
		Lambda lamb = new Lambda();
		
		
		lamb.listagemLambda();
		
		
		String string = "  % presente";
		
		String  valor7 = "78.036.374/0001-54";
		
		//Chaining
		String correct = string.replaceAll("%", "").trim().toLowerCase().replace('p', 'P');
		
		//Plano parcelamento = 2018200002
		
		String cnpjTratado = valor7.replaceAll("\\.", "").replaceAll("\\-", "").replaceAll("/", "");
		
		long valor5;
		
		
		valor5 = (Long.valueOf(cnpjTratado));
		
		
		System.out.println("valor convertido - "+ valor5);
		
		 
		// boolean valor1 = false;
		 int valor2 = 0;
		 double valor3 = 1_223.02;
		 
		 int valor4 = 12_23;
		 OtherClass cla = new OtherClass();*/
		 
		// cla.eatMore(valor1, valor2);
		 
		 
		 
		  
		 
		/* int x = 0;
		 while(++x < 5) {
			 x+=1; 
			 }
		 String message = x > 5 ? "Greater than" : "Less Than";
		 System.out.println(message+","+x);*/
		 
		/* @SuppressWarnings("unused")
		boolean keepGoing = true;
			 int count = 0;
			 int x = 3;
			 while(count++ < 3) {
			 int y = (1 + 2 * count) % 3;
			 switch(y) {
			 default:
			 case 0: x -= 1; break;
			 case 1: x += 5;
			 }
			 }
			 System.out.println(x);*/
		 
		 
		
		 

	}
	
	
	 
	 
	 
	
}

class OtherClass{
	
	
	 public String eatMore(boolean hungry, int amountOfFood) {
		  int roomInBelly = 5;
		  if (hungry) {
		  boolean timeToEat = true;
		  while (amountOfFood > 0) {
		  int amountEaten = 2;
		 
		 
		  roomInBelly = roomInBelly - amountEaten;
		  amountOfFood = amountOfFood - amountEaten;
		  }
		  }
		  System.out.println(amountOfFood);
		  
		  return null;
		  }
	 
	
	
	}

class Lambda {
	
	
	
	public void listagemLambda(){
		
		System.out.println("Cria a lista com os elementos que serão realizadas operações");
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
  
        System.out.println("Imprime todos os números:");
        avaliaExpressao(list, (n)->true);
  
        System.out.println("Não imprime nenhum número:");
        avaliaExpressao(list, (n)->false);
  
        System.out.println("Imprime apenas número pares:");
        avaliaExpressao(list, (n)-> n%2 == 0 );
  
        System.out.println("Imprime apenas números impares:");
        avaliaExpressao(list, (n)-> n%2 == 1 );
  
        System.out.println("Imprime apenas números maiores que 5:");
        avaliaExpressao(list, (n)-> n > 5 );
  
        System.out.println("Imprime apenas números maiores que 5 e menores que 10:");
        avaliaExpressao(list, (n)-> n > 5 && n < 10);
  
    }
	
	
	public void listagemLambdaString(List<String> mainList, Predicate<String> cursor){
		
	    mainList.forEach(n -> {
         if(cursor.test(n)) {
               System.out.println(n + " ");
           }
       });
  
    }


	public Vehicle listagemLambdaObject(List<Vehicle> list, Vehicle cursor) {

		Vehicle objReturn = new Vehicle();

		list.forEach(n -> {
			if (n.getModel().equals(cursor.getModel())) {
				System.out.println(n.getBrand() + "--" + n.getColor() + "--" + n.getFuel() + "--" + n.getModel() + "--"
						+ n.getYear());

			}
		});

		/*
		 * list.stream().filter(n -> { if(n.equals(cursor)) {
		 * System.out.println(n + " "); }}).collect(Collectors.toList());
		 */

		return objReturn;

		/*
		 * this.listaEmpregadoDebitoRescisorio =
		 * listaEmpregado.stream().filter(emp -> emp.getDtAfastamento() != null)
		 * .collect(Collectors.toList());
		 */

		/*
		 * view.getListaConfissao().forEach(c -> { if (c.getTipoOperacao() !=
		 * null) { listaAgrupada.addAll(c.getListaDebito()); } });
		 * 
		 * 
		 * list.stream().filter(n -> predicate).collect(Collectors.toList());
		 * 
		 * 
		 */

	}
	
	 public void avaliaExpressao(List<Integer> list, Predicate<Integer> predicate) {
	
	        list.forEach(n -> {
	          if(predicate.test(n)) {
	                System.out.println(n + " ");
	            }
	        });
	        
	    }
		
	}
	
	
	
	