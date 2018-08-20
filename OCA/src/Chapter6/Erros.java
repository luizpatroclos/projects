package Chapter6;

public class Erros {

	public String exceptions() {
		String result = "";
		String v = null;
		try {
			try {
				result += "before";
				//v.length();
				result += "after";
			} catch (NullPointerException e) {
				result += "catch";
				throw new RuntimeException();
			} finally {
				result += "finally";
				throw new Exception();
			}
		} catch (Exception e) {
			result += "done";
		}
		System.out.println("Valor da REQ "+ result);
		return result;
	}
	
	public static void  main(String args[]){
		
		Erros erro = new Erros();
		
		erro.exceptions();
		
		System.out.println("Foi");
		
		
	}

}
