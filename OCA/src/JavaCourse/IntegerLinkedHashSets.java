package JavaCourse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class IntegerLinkedHashSets {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		
		
		
		
		//Criando ArrayList
		List arrayList = new ArrayList();
		//Adicionando um String
		arrayList.add(new String("Texto"));
		//Adicionando um Integer
		arrayList.add(new Integer(3));
		
		
		//Criando ArrayList de Integer
		List<Integer> arrayLists = new ArrayList<Integer>();
		//Adicionando Integer
		arrayLists.add(new Integer(3));
		//arrayLists.add(new String("Texto"));
		
		
		

		Set hashSet = new HashSet();// Criacao do HashSet

		hashSet.add(100);// Adicionando valores aleatoriamente
		hashSet.add(0);
		hashSet.add(23);
		hashSet.add(465);
		hashSet.add(45);
		hashSet.add(21);
		hashSet.add(10);
		hashSet.add(3);
		hashSet.add(12);
		hashSet.add(5);
		hashSet.add(147);
		hashSet.add(29);
		hashSet.add(7);
		hashSet.add(5);
		hashSet.add(3);
		hashSet.add(12);
		hashSet.add(54);
		
		Set linkedHashSets = new HashSet(hashSet);

		// Criacao do LinkedHashSet a partir do hashSet
		Set linkedHashSet = new LinkedHashSet(hashSet);
		Set treeSet = new TreeSet(hashSet);// Criacao do TreeSet a partir do hashSet

		Iterator itHashSet = hashSet.iterator(); // obtendo Iterator para HashSet

		// obtendo Iterator para LinkedHashSet
		Iterator itLinkedHashSet = linkedHashSet.iterator();

		Iterator itTreeSet = treeSet.iterator(); // obtendo Iterator para TreeSet

		// Enquanto existir proximo elemento nos Iterators
		while (itHashSet.hasNext() && itLinkedHashSet.hasNext() && itTreeSet.hasNext()) {
			
			// Imprime (system.out.println) o proximo elemento de cada Iterator
			System.out.println("HashSet: " + itHashSet.next() + " LinkedHashSet: " + itLinkedHashSet.next()
					+ " TreeSet: " + itTreeSet.next());
			
		}
	}

}
