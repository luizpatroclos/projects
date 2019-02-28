package JavaCourse;

public class CarroPasseio extends Veiculo {
	
	
	private String marca;
	private Double capacidadeTanqueCombustivel;

	public Double getTanqueCombustivel() {
		
		return capacidadeTanqueCombustivel;
	}

	public void setTanqueCombustivel(Double capacidadeTanqueCombustivel) {
		this.capacidadeTanqueCombustivel = capacidadeTanqueCombustivel;
	}

	public String getMarca() {
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

	public void ligarArCondicionado() {
		// Código aqui
	}
	
	public void buzinar() {
		
		super.buzinar();
		
		System.out.println("BOOOOOOOOO");
	}
}
