package Chapter5;

public abstract class Animals {

	private int age;
	private String name;

	public Animals(int age, String name) {
		super();    
		this.age = age;    
		this.name = name;
	}
	
	public Animals(int age) {
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
