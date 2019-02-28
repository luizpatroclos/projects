package JavaCourse;

public  class Veiculo {

	private String marca;
	private Double capacidadeTanqueCombustivel;

	public Double getTanqueCombustivel() {
		
		return capacidadeTanqueCombustivel;
	}

	private void setTanqueCombustivel(Double capacidadeTanqueCombustivel) {
		this.capacidadeTanqueCombustivel = capacidadeTanqueCombustivel;
	}

	private String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void acelerar() {
		// Código aqui
	}

	public void frear() {
		// Código aqui
	}
	
	public void buzinar() {
		
		System.out.println("Bééééémmmm");
	}
	
public static Veiculo criarVeiculo(String tipo) {
		
		
		if (tipo == null) {
			
			return new CarroPasseio();
			
		} else if (tipo.equals("utilitario")) {
			
			return new Utilitario();
			
		} else if (tipo.equals("tanque")) {
			
			return new TanqueGuerra();
			
		} else if (tipo.equals("passeio")) {
			
			return new CarroPasseio();

		}
		return new Utilitario();
	}


	public static void main(String[] args) {

		Veiculo veiculo = criarVeiculo("passeio");

		veiculo.buzinar();
		
		
		/*Veiculo veiculoComDesc = new Veiculo("fiat", 3.0);
		
		Veiculo veiculo3Zerado= new Veiculo();*/
		

	}

	public Veiculo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Veiculo(String marca, Double capacidadeTanqueCombustivel) {
		
		Double valor1 =+ 2.0; 
		
		this.marca = marca;
		this.capacidadeTanqueCombustivel = capacidadeTanqueCombustivel + valor1;
	}
	
	

}
