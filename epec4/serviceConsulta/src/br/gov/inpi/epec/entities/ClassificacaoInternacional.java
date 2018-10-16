package br.gov.inpi.epec.entities;

public class ClassificacaoInternacional {
	
	
	private Long ordem;
	
	
	private String classificacaoInternacional;

	/**
	 * @return the classificacaoInternacional
	 */
	public String getClassificacaoInternacional() {
		return classificacaoInternacional;
	}

	/**
	 * @param classificacaoInternacional the classificacaoInternacional to set
	 */
	public void setClassificacaoInternacional(String classificacaoInternacional) {
		this.classificacaoInternacional = classificacaoInternacional;
	}

	public Long getOrdem() {
		return ordem;
	}

	public void setOrdem(Long ordem) {
		this.ordem = ordem;
	}

	@Override
	public String toString() {
		return "ClassificacaoInternacional [ordem=" + ordem
				+ ", classificacaoInternacional=" + classificacaoInternacional
				+ "]";
	}

	
	
	

}
