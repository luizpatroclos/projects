package Chapter6;

public class Lemur extends Primate implements Hastail {

	
	public boolean isTailStriped() {
		
		return false;
	}

	public int age = 10;

	public static void main(String args[]) {

		Lemur lemur = new Lemur();

		System.out.println(lemur.age);

		Hastail hasTail = lemur;

		System.out.println(hasTail.isTailStriped());

		Primate primate = lemur;

		System.out.println(primate.hasHair());
		
		Primate primate2 = new Primate();
		
		Lemur newLemur = (Lemur) primate2;
		
		//Hastail hasTail2 = lemur;
		
		//Lemur lemur2 = (Lemur) hasTail2;

	}

}
