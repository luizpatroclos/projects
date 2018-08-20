package ChapterOne;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {
	
	private String brand;
	
	private String model;
	
	private int year;
	
	private String color;
	
	private String fuel;
	
	private boolean automatic;
	
	List<Vehicle> auxList;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	public boolean isAutomatic() {
		return automatic;
	}

	public void setAutomatic(boolean automatic) {
		this.automatic = automatic;
	}
	

	public List<Vehicle> getAllVehicle() {
	
		auxList = new ArrayList<>();
		Vehicle bean;
		
		for(int i = 0; i < 10; i++){
			
			bean = new Vehicle();
			
			init(i);
			
			bean.setAutomatic(automatic);
			bean.setBrand(brand);
			bean.setColor(color);
			bean.setFuel(fuel);
			bean.setModel(model);
			bean.setYear(year);
			
			auxList.add(bean);
			
		}
			
		return auxList;
	}
	

	public void init (int i){
		
		switch (i){
		
		case 1:
			brand = "Honda";
			model = "Civic";
			year = 2015;			
			color = "Black";
			fuel = "Diesel";
			automatic = false;
			break;
		case 2:
			brand = "Honda";
			model = "Accord";
			year = 2018;			
			color = "Red";
			fuel = "Gasoline";
			automatic = true;
			break;
		case 3:
			brand = "Subaru";
			model = "Outback";
			year = 2019;			
			color = "Gray";
			fuel = "Diesel";
			automatic = true;
			break;
		case 4:
			brand = "Honda";
			model = "HRV";
			year = 2015;			
			color = "yellow";
			fuel = "Diesel";
			automatic = false;
			break;
		case 5:
			brand = "GM";
			model = "F150";
			year = 2018;			
			color = "Gold";
			fuel = "Diesel_Turbo";
			automatic = true;
			break;
		case 6:
			brand = "GM";
			model = "Camaro";
			year = 2012;			
			color = "Black";
			fuel = "Gasoline";
			automatic = true;
			break;
		case 7:
			brand = "Jeep";
			model = "Compass";
			year = 2019;			
			color = "Blue";
			fuel = "Gasoline";
			automatic = true;
			break;
		case 8:
			brand = "Jeep";
			model = "Renegade";
			year = 2017;			
			color = "Black";
			fuel = "Gasoline";
			automatic = true;
			break;
		case 9:
			brand = "Mazda";
			model = "CX-9";
			year = 2018;			
			color = "Red";
			fuel = "Bio-Diesel";
			automatic = true;
			break;
		case 10:
			brand = "Mazda";
			model = "CX-5";
			year = 2019;			
			color = "Yellow";
			fuel = "Diesel";
			automatic = true;
			break;
			
			default:
				brand = "Volkswagen";
				model = "Fusca";
				year = 1978;			
				color = "White";
				fuel = "Gasoline";
				automatic = false;
				break;
		
		}
		
	}

}
