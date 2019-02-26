package JavaCourse;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class NotaFiscalHashSet {

	private Integer codigo;
	private Date dataEmissao;
	private Double valor;

	// Construtor parametrizado
	public NotaFiscalHashSet(Integer codigo, Date dataEmissao, Double valor) {
		super();
		this.codigo = codigo; // atribui o valor do codigo
		this.dataEmissao = dataEmissao; // atribui a data da emissao
		this.valor = valor;// atribui o valor
	}

	// metodo equals
	public boolean equals(Object obj) {
		// Verifica se o objeto é da classe NotaFiscal e verifica se os codigos são
		// iguais
		// Se a resposta for afirmativa para os dois testes retorna verdadeiro (true)
		if ((obj instanceof NotaFiscalHashSet) && ((NotaFiscalHashSet) obj).getCodigo().equals(this.getCodigo())) {
			return true;//
		} else {
			return false;
		}
	}

	// retorna o codigo da nota fiscal
	public Integer getCodigo() {
		return codigo;
	}

	// metodo hashcode
	public int hashCode() {
		return codigo;// funcao de espalhamento retorna o valor do codigo
		// return 2;//funcao de espalhamento retorna sempre o mesmo valor
	}

	// Retorna um long que identifica o tempo atual
	public static long getTempoAtual() {
		return Calendar.getInstance().getTimeInMillis();
	}

	public static void main(String[] args) {

		Set hashset = new HashSet();// Criacao do HashSet
		Long inicio = getTempoAtual(); // Inicio da contagem do tempo
		for (int i = 0; i < 10000; i++)// Iteracao sobre os elementos
		{
			// adicao dos elementos
			hashset.add(new NotaFiscalHashSet(i, Calendar.getInstance().getTime(), 0.0));
		}
		// remocao dos elementos
		hashset.remove(new NotaFiscalHashSet(5000, Calendar.getInstance().getTime(), 0.0));
		Long fim = getTempoAtual();// fim da contagem do tempo
		System.out.println("Tempo total: " + (fim - inicio) + " ms");// impressao do tempo total

	}

}
