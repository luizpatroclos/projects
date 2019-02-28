package JavaCourse;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import tests.Documento;


class Programa {

	public static void main(String[] args) {
		
		Calendar novo = Calendar.getInstance();

        //Declarando meu objeto documento1
		Documento  documento1;
        //Criando objeto documento1
		
		String d, b, c, e = "";
		
		String a = "";
		
		
		Date valor2 = new Date();
		
        //Atribuindo os valores para o documento1
		/*documento1.codigo = 123456;
		documento1.nome = "Alfredo";
		documento1.setFoto("Img1.png");
		documento1.dataNascimento = "20/05/1990";
		System.out.println("Código do documento: " + documento1.codigo);*/
	}
	
	
}

class JavaIniciante {
	public static void main(String args[]) {
		String s = "Java Iniciante";
		System.out.println("Do 3° caratere até o fim: " + s.substring(2));
		System.out.println("Do 1° caratere até o 6°: " + s.substring(0, 6));
		//System.out.println("Do 4° caratere até o 13°: " + s.substring(3, 15));
		
		
		String valor = "thiago; joao; luiz; erick; lion; jose; mauro";
		
		System.out.println("valor final  - "+ valor);
		
		String[] valor2 = valor.split(";");
		
		for (String nova : valor2) {
			
			System.out.println("New Name "+ nova);
			
		}
		
		for (int i = 0; i < valor2.length; i++) {
			
			
			System.out.println("valor " + valor2[i] );
			
			
		}
		
		
		
		/*String a = new String("valor");
		
		String z = new String("valor");
		
		boolean y = a == z;
		
		String b = "valor";
		
		boolean f = a.equals(b);
		
		boolean g = b.equals(a);
		
		boolean h = b == a;
		
		boolean i = a == b;
		*/
		
		
		
		
	}
}
