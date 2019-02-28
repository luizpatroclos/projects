package JavaCourse;

import java.util.Arrays;

public class TiposPrimitivos {
	
	public static void main(String[] args) {
		
			
		System.out.println("oi");
		
		String[] valorx;
		
		String[][] valorz = new String[3][];
		
		String[][] valorw = new String[3][3];
		
		System.out.println("oi");
		
		int[] outroJogoSena = {23, 12, 55, 02, 07, 19};
		
		int[] outroJogoSena1 = new int[]{23, 12, 55, 02, 07, 19};
		
		
		
		int[] jogoSena = null; //Declaração
		
		// jogoSena = new int[6]; //Criação
		
		//jogoSena[] = 1;
		
		 jogoSena[0] = 23;
			
		
		Integer valor = 1;
		
		boolean valor2 = valor.intValue() != 1 ? true : false;
		
		int j = 25;
		
		boolean valor3 = (j % 2 == 0);
		
		
		
		for ( int x = 0,  y = 25; x < 10 && (y % 2 == 1); x++, y = y - 1 ) {
			
			
			System.out.println (x + y);
			
			
			}
		
		
		
	
		
		
		
		/*Declaramos 8 variáveis. Exatamente o mesmo número
		dos tipos primitivos do Java. Cada variável é de
		um dos tipos.
		*/
		boolean bo;
		char c;
		byte b;
		short s;
		int i;
		long l;
		float f;
		double d;
		/*
		Atribuímos o valor 65 à variável c, que é do tipo
		char.
		OBS Importante: O tipo char também é um inteiro!
		No entanto é um inteiro que faz referência a tabela
		Unicode, que contém 65535 simbolos.
		*/
		c = 65;
		System.out.println("=============================");
		System.out.println("char");
		/*
		www.t2ti.com
		4
		Curso Java Starter
		Note a diferença entre a impressão das duas linhas
		abaixo:
		*/
		System.out.println("=============================");
		System.out.println("valor de c como char = " + c);
		System.out.printf("valor de c como numero = %d \n",(int)c);
		System.out.println("-----------------------------");
		/*
		Saída da impressão:
		=============================
		char
		=============================
		valor de c como char = A
		valor de c como numero = 65
		-----------------------------
		Interessante notar que além do println você também
		pode usar o printf que aprendemos lá no C. Vale
		aqui tudo que aprendemos no Módulo 1 em relação
		à função printf.
		*/
		/*
		Agora vamos trabalhar com os outros tipos inteiros:
		byte, short e int.
		Veja que estamos trabalhando com o valor 10 e que
		atribuímos esse valor à variável b, que é do tipo
		byte. Como esse valor "cabe" em um short e em um int
		não há problema nenhum quando atribuímos o valor de b
		à variável s (short) e o valor de s à variável i (int)
		*/
		b = 10;
		s = b;
		i = s;
		System.out.println("=============================");
		System.out.println("inteiros");
		System.out.println("=============================");
		System.out.println("i = s = b = "+i);
		/*
		Saída da impressão:
		=============================
		inteiros
		=============================
		i = s = b = 10
		*/
		/*
		Agora multiplicamos i por 100 e atribuimos o total
		dessa multiplicação ao próprio i.
		*/
		
		i *= 100;
		System.out.println("novo valor de i = "+i);
	/*
		Saída da impressão:
		novo valor de i = 1000
		*/
		/*
		Olhe para a linha de código abaixo com muita
		atenção. Eu tento colocar o valor de i em b, mas o
		Java não deixa. Preciso então fazer um casting
		(conversão) explícito. Antes da variável i eu insiro
		entre parênteses o tipo de dado para o qual eu
		quero convertê-la. Lembre-se que acima eu converti
		de byte para short e de short para int e isso não
		foi necessário. No entanto, essa conversão acima
		também foi um casting, só que foi um casting implícito.
		Quando eu realizo a conversão abaixo algo vai dar errado.
		Imagine que você pegasse 10 copos de suco de laranja
		e jogasse o coteúdo dos 10 copos em uma jarra. Tranquilo,
		sem problemas! Agora imagine você pegando o conteúdo da
		jarra e jogando em apenas um dos copos. Haverá uma
		perda considerável de suco!
		Depois da conversão de int para byte abaixo, onde atualmente
		o valor de i é 1000, o valor de b será -24! Veja mais abaixo
		porque isso acontece.
		*/
		b = (byte)i;
		System.out.println("novo valor de b = "+b);
		/*
		Saída da impressão:
		novo valor de b = -24
		*/
		/*
		O que ocorre é que no lugar de o Java lançar uma exceção
		ou converter para zero ele retira a parte binária do int
		e deixa apenas a do byte e apresenta o que restou.
		Observe o esquema abaixo:
		i = 1000 em binário --> 00000000 00000000 00000011 11101000
		b = 1000 em binário --> -------- -------- -------- 11101000
		Veja que boa parte do número é perdido. Se você pegar o que
		sobrou no b e fizer uma conversão de binário para decimal
		usando complemento de dois vai chegar ao número -24.
		Portanto, saiba que o Java vai deixar você fazer esse tipo
		de conversão porque ele acha que você, programador, sabe
		que poderá perder bits nessa conversão!
		*/
	
		/*
		Abaixo simplesmente inserimos o valor do int em um long
		que é o dobro de um int. Ou seja, sem problemas!
		*/
		l = i;
		System.out.println("valor de l = "+l);
		System.out.println("-----------------------------");
		/*
		Saída da impressão:
		valor de l = 1000
		-----------------------------
		*/
		/*
		Chegamos aos números com ponto flutuante (casas decimais).
		*/
		System.out.println("=============================");
		System.out.println("ponto flutuante");
		System.out.println("=============================");
		d = 125.32;
		System.out.println("valor de d = " + d);
		d = 125.32d;
		System.out.println("valor de d = " + d);
		d = 125.32f;
		System.out.println("valor de d = " + d);
		/*
		no primeiro caso atribuimos 125.32 à variavel d.
		sem problemas. funciona legal, como podemos ver
		na Saída da impressão.
		Logo depois atribuimos 125.32d. Mas pra que serve
		esse "d" depois do valor? Indica que estou afirmando
		para o Java que a constante (125.32) é um double.
		Os dois casos acima são idênticos. Como assim? Para
		o Java, o tipo padrão de um literal com ponto
		flutuante é double. Portanto, não precisa colocar o
		"d" depois do literal se quiser que ela seja double.
		literal --> 125.32
		Mas o terceiro caso é interessante. Atribuimos à
		variável d o seguinte valor: 125.32f. Dessa vez estamos
		inserindo um float num double. problema nenhum, pois
		o float é 32 bit e o double é 64 bit. Mas, observe a Saída
		da impressão. Por que o número depois da casa decimal
		ficou tão quebrado? Investigue e comente na lista.
		Saída da impressão:
		=============================
		ponto flutuante
		www.t2ti.com
		7
		Curso Java Starter
		=============================
		valor de d = 125.32
		valor de d = 125.32
		valor de d = 125.31999969482422
		*/
		f = (float)125.32;
		System.out.println("valor de f = " + d);
		f = 125.32f;
		System.out.println("valor de f = " + d);
		f = (float)125.32d;
		System.out.println("valor de f = " + d);
		System.out.println("-----------------------------");
		/*
		Mesma coisa que foi feita com o Double, mas dessa
		vez com o float.
		Logo no primeiro caso já é necessário fazer um casting.
		Lembre-se: o tipo padrão de um número literal em
		ponto flutuante é double.
		No segundo caso nada é preciso.
		No terceiro caso novamente a presença no casting
		já que estamos informando explicitamente que o literal
		é do tipo double.
		É bom lembrar que ao converter de double para float
		pode haver perda de bits.
		Pergunta: por que todos os valores saíram tão quebrados,
		em contraste com o double? Investigue e comente na lista.
		Saída da impressão:
		valor de f = 125.31999969482422
		valor de f = 125.31999969482422
		valor de f = 125.31999969482422
		-----------------------------
		*/
		/*
		Abordaremos agora o tipo booleano.
		*/
		System.out.println("=============================");
		System.out.println("booleano");
		System.out.println("=============================");
		bo = true;
		System.out.println("valor de bo = " + bo);
		bo = (1 > 2);
		System.out.println("valor de bo = " + bo);
		bo = (f == d);
		System.out.println("valor de bo = " + bo);
		System.out.println("-----------------------------");
		/*
		www.t2ti.com
		8
		Curso Java Starter
		O valor true ou false pode ser atribuido sem problemas.
		Se atribuirmos uma operação à variável. Essa operação
		será avaliada e seu valor será armazenado na variável.
		No caso de (1>2) sabemos que o resultado será false.
		Depois testamos se f é igual a d. Observe que o operador
		de teste de igualdade é o == (igual duas vezes), semelhante
		à linguagem C. Falaremos sobre operadores mais a frente.
		Saída da impressão:
		=============================
		booleano
		=============================
		valor de bo = true
		valor de bo = false
		valor de bo = true
		-----------------------------
		*/
		System.out.println("=============================");
		System.out.println("brincando com os tipos");
		System.out.println("=============================");
		System.out.println("posso converter int para float?");
		System.out.println("i antes da conversao = " + i);
		System.out.println("f antes da conversao = " + f);
		f = i;
		System.out.println("i depois da conversao = " + i);
		System.out.println("f depois da conversao = " + f);
		System.out.println("--");
		/*
		Em nossa primeira "brincadeira" com os tipos tentamos
		converter de int para float. Observe atentamente o
		resultado na saída de impressão.
		Saída de impressão:
		=============================
		brincando com os tipos
		=============================
		posso converter int para float?
		i antes da conversao = 1000
		f antes da conversao = 125.32
		i depois da conversao = 1000
		f depois da conversao = 1000.0
		--
		*/
		f = (float)d;
		System.out.println("posso converter float para int?");
		System.out.println("i antes da conversao = " + i);
		System.out.println("f antes da conversao = " + f);
		i = (int)f;
		System.out.println("i depois da conversao = " + i);
		System.out.println("f depois da conversao = " + f);
		System.out.println("-----------------------------");
		
		/*
		Depois de nossa primeira "brincadeira" o valor de
		f ficou igual ao valor de i. Por isso atribuímos o
		valor de d novamente à variável f.
		Agora o que queremos saber é se é possível atribuir
		o valor de um float a um inteiro. O contrário foi
		possível sem casting. Mas dessa vez o casting será
		necessário. Mas, além desse detalhe, outra coisa
		vai ocorrer. Pode ser desejável ou não. Comente na
		lista o que ocorreu nessa conversão de float para int
		e em que situação isso seria desejavel.
		Saída de impressão:
		posso converter float para int?
		i antes da conversao = 1000
		f antes da conversao = 125.32
		i depois da conversao = 125
		f depois da conversao = 125.32
		-----------------------------
		*/
		}
	
	
/*	public static void main(String[] args) {
		int[] arrayInteiros = { 55, 27, 33, 45, 92, 100, 3, 8, 11, 70 };
		boolean estaOrdenado = false;
		while (!estaOrdenado) {
			estaOrdenado = true;
			for (int i = 1; i < arrayInteiros.length; i++) {
				if (arrayInteiros[i - 1] > arrayInteiros[i]) {
					estaOrdenado = false;
					int aux = arrayInteiros[i];
					arrayInteiros[i] = arrayInteiros[i - 1];
					arrayInteiros[i - 1] = aux;
					System.out.println("Ordem atual: " + arrayInteiros[0] + " " + arrayInteiros[1] + " "
							+ arrayInteiros[2] + " " + arrayInteiros[3] + " " + arrayInteiros[4] + " "
							+ arrayInteiros[5] + " " + arrayInteiros[6] + " " + arrayInteiros[7] + " "
							+ arrayInteiros[8] + " " + arrayInteiros[9]);
				}
			}
		}
	}*/

}


class Matriz {
	
	public static void main(String[] args) {
		
		double matriz[][] = new double[4][4];// declaracao e construcao da matriz
		for (int i = 0; i < matriz.length; i++)// percorre a matriz no eixo i
		{
			for (int j = 0; j < matriz[i].length; j++)// percorre a matriz no eixo j
			{
				matriz[i][j] = i * j;// atribui o valor a celula
			}
		}
		for (int i = 0; i < matriz.length; i++) // percorre a matriz no eixo i
		{
			for (int j = 0; j < matriz[i].length; j++) // percorre a matriz no eixo j
			{
//imprime o resultado
				System.out.println("Valor da posição [" + i + "," + j + "]: " + matriz[i][j]);
			}
		}
	}
}


class OrdenacaoArray {
	public static void main(String[] args) {
//Criacao do array de 10.000 posicoes
		int[] arrayInteiros = new int[10000];
//Atribuindo os valores a cada posicao
		for (int i = 10000; i > 0; i--) {
			arrayInteiros[arrayInteiros.length - i] = i;

		}
//inicio do algoritmo de ordenacao - implementado
		boolean estaOrdenado = false;
//armazena o tempo de inicio da ordenacao
		long inicio = System.currentTimeMillis();
		while (!estaOrdenado) {
			estaOrdenado = true;
			for (int i = 1; i < arrayInteiros.length; i++) {
				if (arrayInteiros[i - 1] > arrayInteiros[i]) {
					estaOrdenado = false;
					int aux = arrayInteiros[i];
					arrayInteiros[i] = arrayInteiros[i - 1];
					arrayInteiros[i - 1] = aux;
				}
			}
		}
//armazena o tempo fim da ordenacao
		long fim = System.currentTimeMillis();
//Imprime o tempo total de ordenacao
		System.out.println("Tempo ordenar 1: " + (fim - inicio) + " ms");
//Atribuindo os valores a cada posicao
		for (int i = 10000; i > 0; i--) {
			arrayInteiros[arrayInteiros.length - i] = i;
		}
//armazena o tempo de inicio da ordenacao
		inicio = System.currentTimeMillis();
//Ordena utilizando a classe Arrays
		Arrays.sort(arrayInteiros);
//armazena o tempo fim da ordenacao
		fim = System.currentTimeMillis();
//Imprime o tempo total de ordenacao
		System.out.println("Tempo ordenar 2: " + (fim - inicio) + " ms");
	}
}
