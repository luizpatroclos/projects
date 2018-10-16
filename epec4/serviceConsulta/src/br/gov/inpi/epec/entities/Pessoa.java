package br.gov.inpi.epec.entities;

public class Pessoa {
	
	private String dataFormat;
	
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataFormat() {
		return dataFormat;
	}

	public void setDataFormat(String dataFormat) {
		this.dataFormat = dataFormat;
	}

	@Override
	public String toString() {
		return "Pessoa [dataFormat=" + dataFormat + ", nome=" + nome + "]";
	}
	
	
	
	

}
