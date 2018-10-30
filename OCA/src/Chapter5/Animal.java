package Chapter5;

public class Animal {

	private int age;
	private String name;

	public Animal(int age, String name) {
		super();    
		this.age = age;    
		this.name = name;
	}
	
	public Animal(int age) {
		super();
		this.age = age;
		this.name = null;
	}
	
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
