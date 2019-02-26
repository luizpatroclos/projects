package JavaCourse;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class IntegerLinkedHashSet {

	public static void main(String[] args) {
		
		
		Set hashSet = new HashSet();// Criacao do HashSet
		Set linkedHashSet = new LinkedHashSet(); // Criacao do LinkedHashSet
		
		
		for (int i = 0; i < 20; i++) // For para preencher as duas colecoes
		{
			hashSet.add(i); // Adicionando elemento i ao hashSet
			linkedHashSet.add(i); // Adicionando elemento i ao linkedHashSet
		}
		Iterator itHashSet = hashSet.iterator(); // obtendo Iterator para HashSet
		// obtendo Iterator para LinkedHashSet
		Iterator itLinkedHashSet = linkedHashSet.iterator();

		// Enquanto existir proximo elemento nos Iterators
		while (itHashSet.hasNext() && itLinkedHashSet.hasNext()) {
			// Imprime (system.out.println) o proximo elemento de cada Iterator
			System.out.println("HashSet: " + itHashSet.next() + " LinkedHashSet: " + itLinkedHashSet.next());
		}
	}
}
