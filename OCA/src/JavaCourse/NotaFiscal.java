package JavaCourse;

import java.util.Calendar;
import java.util.Date;

public class NotaFiscal {

	private Integer codigo;
	private Date dataEmissao;
	private Double valor;

	// Construtor parametrizado
	public NotaFiscal(Integer codigo, Date dataEmissao, Double valor) {
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
		if ((obj instanceof NotaFiscal) && ((NotaFiscal) obj).getCodigo().equals(this.getCodigo())) {
			return true;
		} else {
			return false;
		}
	}

	// retorna o codigo da nota fiscal
	public Integer getCodigo() {
		return codigo;
	}
	
	// retorna o codigo da nota fiscal
		public Date getDate() {
			return dataEmissao;
		}
		
		

	public static void main(String[] args) {
		// Criacao da nota fiscal 1
		NotaFiscal nf1 = new NotaFiscal(1, Calendar.getInstance().getTime(), 123456.0);
		// Criacao da nota fiscal 2
		NotaFiscal nf2 = new NotaFiscal(1, Calendar.getInstance().getTime(), 555566.0);
		// Criacao da nota fiscal 3
		NotaFiscal nf3 = new NotaFiscal(1, Calendar.getInstance().getTime(), 788955.0);
		System.out.println("nf1 igual a nf2: " + nf1.equals(nf2));// resulta false
		System.out.println("nf1 igual a nf3: " + nf1.equals(nf3));// resulta verdadeiro
	}
}
