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
		// C�digo aqui
	}

	public void frear() {
		// C�digo aqui
	}

	public void ligarArCondicionado() {
		// C�digo aqui
	}
	
	public void buzinar() {
		
		super.buzinar();
		
		System.out.println("BOOOOOOOOO");
	}
}
