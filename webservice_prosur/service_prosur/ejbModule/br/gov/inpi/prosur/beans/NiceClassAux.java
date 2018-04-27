package br.gov.inpi.prosur.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;



@Entity
public class NiceClassAux {
	
	
	@Column(name="cd_process")
	public String cd_process;
	
	
	@Column(name="no_process")
	public String no_process;
	
	@Id
	@Column(name="cd_classe")
	public String cd_classe;

	public String getCd_process() {
		return cd_process;
	}

	public void setCd_process(String cd_process) {
		this.cd_process = cd_process;
	}

	public String getNo_process() {
		return no_process;
	}

	public void setNo_process(String no_process) {
		this.no_process = no_process;
	}

	public String getCd_classe() {
		return cd_classe;
	}

	public void setCd_classe(String cd_classe) {
		this.cd_classe = cd_classe;
	}
    
	
	
	

}
