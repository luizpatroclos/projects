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
		
		
		
		int[] jogoSena = null; //Declara��o
		
		// jogoSena = new int[6]; //Cria��o
		
		//jogoSena[] = 1;
		
		 jogoSena[0] = 23;
			
		
		Integer valor = 1;
		
		boolean valor2 = valor.intValue() != 1 ? true : false;
		
		int j = 25;
		
		boolean valor3 = (j % 2 == 0);
		
		
		
		for ( int x = 0,  y = 25; x < 10 && (y % 2 == 1); x++, y = y - 1 ) {
			
			
			System.out.println (x + y);
			
			
			}
		
		
		
	
		
		
		
		/*Declaramos 8 vari�veis. Exatamente o mesmo n�mero
		dos tipos primitivos do Java. Cada vari�vel � de
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
		Atribu�mos o valor 65 � vari�vel c, que � do tipo
		char.
		OBS Importante: O tipo char tamb�m � um inteiro!
		No entanto � um inteiro que faz refer�ncia a tabela
		Unicode, que cont�m 65535 simbolos.
		*/
		c = 65;
		System.out.println("=============================");
		System.out.println("char");
		/*
		www.t2ti.com
		4
		Curso Java Starter
		Note a diferen�a entre a impress�o das duas linhas
		abaixo:
		*/
		System.out.println("=============================");
		System.out.println("valor de c como char = " + c);
		System.out.printf("valor de c como numero = %d \n",(int)c);
		System.out.println("-----------------------------");
		/*
		Sa�da da impress�o:
		=============================
		char
		=============================
		valor de c como char = A
		valor de c como numero = 65
		-----------------------------
		Interessante notar que al�m do println voc� tamb�m
		pode usar o printf que aprendemos l� no C. Vale
		aqui tudo que aprendemos no M�dulo 1 em rela��o
		� fun��o printf.
		*/
		/*
		Agora vamos trabalhar com os outros tipos inteiros:
		byte, short e int.
		Veja que estamos trabalhando com o valor 10 e que
		atribu�mos esse valor � vari�vel b, que � do tipo
		byte. Como esse valor "cabe" em um short e em um int
		n�o h� problema nenhum quando atribu�mos o valor de b
		� vari�vel s (short) e o valor de s � vari�vel i (int)
		*/
		b = 10;
		s = b;
		i = s;
		System.out.println("=============================");
		System.out.println("inteiros");
		System.out.println("=============================");
		System.out.println("i = s = b = "+i);
		/*
		Sa�da da impress�o:
		=============================
		inteiros
		=============================
		i = s = b = 10
		*/
		/*
		Agora multiplicamos i por 100 e atribuimos o total
		dessa multiplica��o ao pr�prio i.
		*/
		
		i *= 100;
		System.out.println("novo valor de i = "+i);
	/*
		Sa�da da impress�o:
		novo valor de i = 1000
		*/
		/*
		Olhe para a linha de c�digo abaixo com muita
		aten��o. Eu tento colocar o valor de i em b, mas o
		Java n�o deixa. Preciso ent�o fazer um casting
		(convers�o) expl�cito. Antes da vari�vel i eu insiro
		entre par�nteses o tipo de dado para o qual eu
		quero convert�-la. Lembre-se que acima eu converti
		de byte para short e de short para int e isso n�o
		foi necess�rio. No entanto, essa convers�o acima
		tamb�m foi um casting, s� que foi um casting impl�cito.
		Quando eu realizo a convers�o abaixo algo vai dar errado.
		Imagine que voc� pegasse 10 copos de suco de laranja
		e jogasse o cote�do dos 10 copos em uma jarra. Tranquilo,
		sem problemas! Agora imagine voc� pegando o conte�do da
		jarra e jogando em apenas um dos copos. Haver� uma
		perda consider�vel de suco!
		Depois da convers�o de int para byte abaixo, onde atualmente
		o valor de i � 1000, o valor de b ser� -24! Veja mais abaixo
		porque isso acontece.
		*/
		b = (byte)i;
		System.out.println("novo valor de b = "+b);
		/*
		Sa�da da impress�o:
		novo valor de b = -24
		*/
		/*
		O que ocorre � que no lugar de o Java lan�ar uma exce��o
		ou converter para zero ele retira a parte bin�ria do int
		e deixa apenas a do byte e apresenta o que restou.
		Observe o esquema abaixo:
		i = 1000 em bin�rio --> 00000000 00000000 00000011 11101000
		b = 1000 em bin�rio --> -------- -------- -------- 11101000
		Veja que boa parte do n�mero � perdido. Se voc� pegar o que
		sobrou no b e fizer uma convers�o de bin�rio para decimal
		usando complemento de dois vai chegar ao n�mero -24.
		Portanto, saiba que o Java vai deixar voc� fazer esse tipo
		de convers�o porque ele acha que voc�, programador, sabe
		que poder� perder bits nessa convers�o!
		*/
	
		/*
		Abaixo simplesmente inserimos o valor do int em um long
		que � o dobro de um int. Ou seja, sem problemas!
		*/
		l = i;
		System.out.println("valor de l = "+l);
		System.out.println("-----------------------------");
		/*
		Sa�da da impress�o:
		valor de l = 1000
		-----------------------------
		*/
		/*
		Chegamos aos n�meros com ponto flutuante (casas decimais).
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
		no primeiro caso atribuimos 125.32 � variavel d.
		sem problemas. funciona legal, como podemos ver
		na Sa�da da impress�o.
		Logo depois atribuimos 125.32d. Mas pra que serve
		esse "d" depois do valor? Indica que estou afirmando
		para o Java que a constante (125.32) � um double.
		Os dois casos acima s�o id�nticos. Como assim? Para
		o Java, o tipo padr�o de um literal com ponto
		flutuante � double. Portanto, n�o precisa colocar o
		"d" depois do literal se quiser que ela seja double.
		literal --> 125.32
		Mas o terceiro caso � interessante. Atribuimos �
		vari�vel d o seguinte valor: 125.32f. Dessa vez estamos
		inserindo um float num double. problema nenhum, pois
		o float � 32 bit e o double � 64 bit. Mas, observe a Sa�da
		da impress�o. Por que o n�mero depois da casa decimal
		ficou t�o quebrado? Investigue e comente na lista.
		Sa�da da impress�o:
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
		Logo no primeiro caso j� � necess�rio fazer um casting.
		Lembre-se: o tipo padr�o de um n�mero literal em
		ponto flutuante � double.
		No segundo caso nada � preciso.
		No terceiro caso novamente a presen�a no casting
		j� que estamos informando explicitamente que o literal
		� do tipo double.
		� bom lembrar que ao converter de double para float
		pode haver perda de bits.
		Pergunta: por que todos os valores sa�ram t�o quebrados,
		em contraste com o double? Investigue e comente na lista.
		Sa�da da impress�o:
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
		Se atribuirmos uma opera��o � vari�vel. Essa opera��o
		ser� avaliada e seu valor ser� armazenado na vari�vel.
		No caso de (1>2) sabemos que o resultado ser� false.
		Depois testamos se f � igual a d. Observe que o operador
		de teste de igualdade � o == (igual duas vezes), semelhante
		� linguagem C. Falaremos sobre operadores mais a frente.
		Sa�da da impress�o:
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
		resultado na sa�da de impress�o.
		Sa�da de impress�o:
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
		f ficou igual ao valor de i. Por isso atribu�mos o
		valor de d novamente � vari�vel f.
		Agora o que queremos saber � se � poss�vel atribuir
		o valor de um float a um inteiro. O contr�rio foi
		poss�vel sem casting. Mas dessa vez o casting ser�
		necess�rio. Mas, al�m desse detalhe, outra coisa
		vai ocorrer. Pode ser desej�vel ou n�o. Comente na
		lista o que ocorreu nessa convers�o de float para int
		e em que situa��o isso seria desejavel.
		Sa�da de impress�o:
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
				System.out.println("Valor da posi��o [" + i + "," + j + "]: " + matriz[i][j]);
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
