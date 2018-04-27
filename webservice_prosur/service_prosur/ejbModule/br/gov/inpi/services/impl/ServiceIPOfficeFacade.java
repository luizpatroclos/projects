/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.inpi.services.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import br.gov.inpi.marcas.beans.MrcFiguraProcess;
import br.gov.inpi.marcas.beans.MrcProcesso;
import br.gov.inpi.services.EntityInterfaceMarcas;
import br.gov.inpi.services.ProcessoMarcasAutomaticoInterface;
import br.gov.inpi.services.ServiceIPOfficeInterface;


/**
 *
 * @author luizAlbuquerque
 */
@Stateless
@Local(ServiceIPOfficeInterface.class)
public class ServiceIPOfficeFacade implements ServiceIPOfficeInterface {
	
	@EJB
	private ProcessoMarcasAutomaticoInterface persistMarcas;
	
    public ServiceIPOfficeFacade() {
    	
    }

	@Override
	public List<MrcProcesso> findRange(int[] range) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<MrcFiguraProcess> findProcess(String val01, String val02, String val03) {
		
        StringBuilder valor = new StringBuilder();
		
		valor.append(" from MrcFiguraProcess m where m.cdProcess = 5335 ");
		
		return null;
	}

	@Override
	public byte[] searchFile(String val01, String val02, String val03) {
		
		byte[] encodedBytes = null;
        
        MrcFiguraProcess mrcFigura = persistMarcas.pesquisarFiguraPorNumeroProcesso(val01);

		if (mrcFigura != null) {
			
			encodedBytes = mrcFigura.getImProcessFigura();
		}

		return encodedBytes;
	}    
}
