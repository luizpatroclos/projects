package br.gov.inpi.epec.writer.helper;

import java.util.ArrayList;
import java.util.List;

import br.gov.inpi.epec.xml.parties.Parties;
import br.gov.inpi.epec.xml.parties.applicants.Applicant;
import br.gov.inpi.epec.xml.parties.applicants.ApplicantList;
import br.gov.inpi.epec.xml.parties.inventors.Inventor;
import br.gov.inpi.epec.xml.parties.inventors.InventorList;
import br.gov.inpi.epec.entities.Patente;
import br.gov.inpi.epec.entities.Pessoa;


public class PartiesHelper {
	
		
	private static final PartiesHelper instance;
	
	
	static{
		instance = new PartiesHelper();
	}
	
	public static PartiesHelper getInstance() {
		return instance;
	}


	public Parties retornarInteressados(Patente patente){
		
		Parties parties = new Parties();
		
		List<Pessoa> depositantes = patente.getDepositantes();
		List<Pessoa> inventores = patente.getInventores();
		
		if(depositantes != null && !depositantes.isEmpty()){
			ApplicantList applicants = this.retornarDepositantes(depositantes);
			parties.setApplicantList(applicants);	
		}
		
		if(inventores != null && !inventores.isEmpty()){
			InventorList inventors = this.retornarInventores(inventores);
			parties.setInventorList(inventors);	
		}
	
		return parties;
	}
		
	
	private ApplicantList retornarDepositantes(List<Pessoa> depositantes) {
		
		ApplicantList applicantsRoot = new ApplicantList();
		
		List<Applicant> applicants = new ArrayList<Applicant>();
		      
       	int ordem = 1;
       	for(int i = 0; i < depositantes.size(); i++){
      		Pessoa pessoa = depositantes.get(i);
       		Applicant applicant = this.retornarApplicant(pessoa, ordem);
       		applicants.add(applicant);
       		ordem++;
       	}
     	
       	applicantsRoot.setApplicants(applicants);
	    return applicantsRoot;
		
	}
		
     private Applicant retornarApplicant(Pessoa pessoa, int sequence){
		
		Applicant applicant = new Applicant();
		String nome = pessoa.getNome();
		
		if(nome!= null && !"".equals(nome.trim())){
			applicant.setName(nome.trim());	
		}
		
	   	applicant.setSequence(Integer.toString(sequence));
       	return applicant;
     }
		
	

	private InventorList retornarInventores(List<Pessoa> inventores){
		InventorList inventorsRoot = new InventorList();
		
		List<Inventor> inventors = new ArrayList<Inventor>();
		int ordem = 1;
		
		for(int i = 0; i < inventores.size(); i++){
		     Pessoa pessoa = inventores.get(i);
		     Inventor inventor = this.retornarInventor(pessoa,ordem);
		     inventors.add(inventor);
		     ordem++;
		}
		
		inventorsRoot.setInventor(inventors);
		return inventorsRoot;
	}
	
	
	
	private Inventor retornarInventor(Pessoa pessoa, int sequence){
		
		Inventor inventor = new Inventor();
		String nome = pessoa.getNome();
		if(nome != null && !"".equals(nome.trim())){
			inventor.setName(nome.trim());
			inventor.setSequence(Integer.toString(sequence));
		}
		
		return inventor;
	}

}
