package JavaCourse;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;
import java.util.Vector;

public class ArrayListVector {

	public static void main(String[] args) {
		
		
		//Criacao do HashMap
		Map notasFiscaisMap = new HashMap();
		//Criacao das Notas Fiscais
		NotaFiscal nf1 = new NotaFiscal(1, Calendar.getInstance().getTime(), 123.56);
		
		NotaFiscal nf2 = new NotaFiscal(2, Calendar.getInstance().getTime(), 127.00);
		NotaFiscal nf3 = new NotaFiscal(3, Calendar.getInstance().getTime(), 100.25);
		//Armazenamento das notas fiscais no HashMap
		notasFiscaisMap.put(nf1.getCodigo(), nf1);
		notasFiscaisMap.put(nf2.getCodigo(), nf2);
		notasFiscaisMap.put(nf3.getCodigo(), nf3);
		//Obtem nf2
		NotaFiscal nf = (NotaFiscal)notasFiscaisMap.get(2);
		
		
		//Criacao do LinkedHashMap
		Map notasFiscaisMaps = new LinkedHashMap();
		//Criacao das Notas Fiscais
		NotaFiscal nf11 = new NotaFiscal(1, Calendar.getInstance().getTime(), 123.56);
		NotaFiscal nf22 = new NotaFiscal(2, Calendar.getInstance().getTime(), 127.00);
		NotaFiscal nf33 = new NotaFiscal(3, Calendar.getInstance().getTime(), 100.25);
		//Armazenamento das notas fiscais no HashMap
		notasFiscaisMaps.put(nf11.getCodigo(), nf11);
		notasFiscaisMaps.put(nf22.getCodigo(), nf22);
		notasFiscaisMaps.put(nf33.getCodigo(), nf33);
		//Obtem nf2
		NotaFiscal nf111 = (NotaFiscal)notasFiscaisMap.get(2);
		
		
		
		// Criacao do TreeMap utilizando Generics
		Map<Integer, String> treeMap = new TreeMap<Integer, String>();
		// Inserindo os valores
		treeMap.put(5, "Numero 5");
		treeMap.put(1, "Numero 1");

		treeMap.put(100, "Numero 100");
		treeMap.put(22, "Numero 22");
		
		// For-Each, percorrendo todos os elementos do Map
		for (String str : treeMap.values()) {
			// Imprimindo os valores encontrados
			System.out.println(str);
		}
		
		
		//Criando a minha Queue utilizando Generics
		Queue<Integer> filaPrioridade = new PriorityQueue<Integer>();
		//Adicionando elementos
		filaPrioridade.add(5);
		filaPrioridade.add(5);
		filaPrioridade.add(2);
		filaPrioridade.add(3);
		filaPrioridade.add(0);
		filaPrioridade.add(-2);
		//Removendo elementos e imprimindo
		System.out.println("poll: "+filaPrioridade.poll());
		System.out.println("poll: "+filaPrioridade.poll());
		System.out.println("poll: "+filaPrioridade.poll());
		System.out.println("poll: "+filaPrioridade.poll());
		System.out.println("poll: "+filaPrioridade.poll());
		System.out.println("poll: "+filaPrioridade.poll());
		System.out.println("poll: "+filaPrioridade.poll());
		
		
		
		
		
		Integer quantidadeElementos = 25000;// Quantidade total de elementos
		List<Integer> arrayList = new ArrayList<Integer>();// Criacao do ArrayList de Integer
		for (int i = 0; i < quantidadeElementos; i++)// Inserir elementos dentro do ArrayList
		{
			arrayList.add(i);
		}
		// Criacao do LinkedList de Integer, com os mesmos elementos do ArrayList
		LinkedList<Integer> linkedList = new LinkedList<Integer>(arrayList);
		// Criacao do Vector de Integer, com os mesmos elementos do ArrayList
		List<Integer> vector = new Vector<Integer>(arrayList);
		// inicio da contagem do tempo
		long inicio = System.currentTimeMillis();
		// percorrendo todos os elementos do ArrayList
		for (int i = 0; i < arrayList.size(); i++) {
			arrayList.get(i);// Obtendo elementos do ArrayList
		}
		// fim da contagem do tempo
		long fim = System.currentTimeMillis();
		System.out.println("Obter elemento. Tempo arrayList: " + (fim - inicio) + " ms");
		// inicio da contagem do tempo

		inicio = System.currentTimeMillis();
		// percorrendo todos os elementos do Vector
		for (int i = 0; i < vector.size(); i++) {
			vector.get(i);// Obtendo elementos do Vector
		}
		// fim da contagem do tempo
		fim = System.currentTimeMillis();
		System.out.println("Obter elemento. Tempo Vector: " + (fim - inicio) + " ms");
		// inicio da contagem do tempo
		inicio = System.currentTimeMillis();
		// percorrendo todos os elementos do LinkedList
		for (int i = 0; i < linkedList.size(); i++) {
			linkedList.get(i);// Obtendo elementos do LinkedList
		}
		// fim do cronomentro
		fim = System.currentTimeMillis();
		System.out.println("Obter elemento. Tempo linkedList: " + (fim - inicio) + " ms");
		// inicio da contagem do tempo
		inicio = System.currentTimeMillis();
		// percorrendo todos os elementos do ArrayList
		for (int i = 0; i < quantidadeElementos; i++) {
			arrayList.add(0, i);// Adicionando um elemento no inicio do ArrayList
		}
		// fim da contagem do tempo
		fim = System.currentTimeMillis();
		System.out.println("Inserir elemento no inicio. Tempo arrayList: " + (fim - inicio) + " ms");
		// inicio da contagem do tempo
		inicio = System.currentTimeMillis();
		// percorrendo todos os elementos do Vector
		for (int i = 0; i < quantidadeElementos; i++) {
			vector.add(0, i);// Adicionando um elemento no inicio do Vector
		}
		// fim da contagem do tempo
		fim = System.currentTimeMillis();
		System.out.println("Inserir elemento no inicio. Tempo Vector: " + (fim - inicio) + " ms");
		// inicio da contagem do tempo
		inicio = System.currentTimeMillis();
		// percorrendo todos os elementos do LinkedList
		for (int i = 0; i < quantidadeElementos; i++) {
			linkedList.addFirst(i);// Adicionando elemento no inicio do LinkedList
		}
		// fim da contagem do tempo
		fim = System.currentTimeMillis();
		System.out.println("Inserir elemento no inicio. Tempo linkedList: " + (fim - inicio) + " ms");
	}
}
