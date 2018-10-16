package br.gov.inpi.epec.mb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import br.gov.inpi.epec.beans.Tbclausulatipo;
import br.gov.inpi.epec.beans.Tbnos;
import br.gov.inpi.epec.facade.EpecServiceFacade;
import br.gov.inpi.epec.util.Document;

@ManagedBean(name = "documentService")
@ApplicationScoped
public class DocumentService extends AbstractMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	EpecServiceFacade service;

	/**
	 * Classe Respons�vel por tratar os Ditet�rios by Luiz Albuquerque
	 *
	 */

	public TreeNode createDocuments(List<Tbclausulatipo> clausulas) {

	

		TreeNode root1 = new DefaultTreeNode(new Document("Files", "-", "Folder", null), null);
		getHashParametro().put("idNo", 1L);

		Tbnos newNo = (Tbnos) service.findOneResult(FIND_TBNOS_BY_ID, hashParametro);
		for (int i = 0; i < newNo.getTbnosList().size(); i++) {
			ValidarArvore(newNo.getTbnosList().get(i), root1, null ,clausulas);
		}

		return root1;
	}


	@SuppressWarnings("unused")
	private void ValidarArvore(Tbnos no, TreeNode pai, TreeNode filho, List<Tbclausulatipo> clausulas ) {

		
		filho = new DefaultTreeNode(new Document(no.getTxTituloPortugues(), "-", "Folder", null), pai);
		
		if (no.getIdClausulaTipo() != null ) {
			if(clausulaValida(no.getIdClausulaTipo(),clausulas)){
				TreeNode clausula = new DefaultTreeNode(new Document(no.getIdClausulaTipo().getTxPortugues(), "-", "Pastas", null), filho);
			}

		}
		for (int i = 0; i < no.getTbnosList().size(); i++) {		
			
			ValidarArvore(no.getTbnosList().get(i), filho, null, clausulas);
		}


	}
	
	private boolean clausulaValida(Tbclausulatipo tbclausulatipo, List<Tbclausulatipo> clausulas){
		for(int c = 0; c< clausulas.size(); c++){
			if(clausulas.get(c).equals(tbclausulatipo)){
				return true;
			}
		}	
		
		return false;
		
	}
}
