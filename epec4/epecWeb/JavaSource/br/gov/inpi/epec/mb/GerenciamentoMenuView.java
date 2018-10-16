package br.gov.inpi.epec.mb;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.gov.inpi.epec.util.EPECUtil;

@ViewScoped
@ManagedBean
public class GerenciamentoMenuView implements Serializable {
	

	private static final long serialVersionUID = -1773948283255601577L;
			
	
	
	public String getManterPais(){
		EPECUtil.redirecionar("/epecWeb/pages/manterPais.jsf");
		
		return "";
	}
	
	public void colaboracao(){
		EPECUtil.redirecionar("/epecWeb/pages/manterColaboracao.jsf");
	}

	public void manterEntidade(){
		EPECUtil.redirecionar("/epecWeb/pages/manterEntidade.jsf");
	}

}
