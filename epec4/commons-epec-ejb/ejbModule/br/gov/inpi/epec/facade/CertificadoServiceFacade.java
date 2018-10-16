package br.gov.inpi.epec.facade;

import javax.servlet.http.HttpServletRequest;

public interface CertificadoServiceFacade {

	public abstract String recuperarPropriedadesCertificado(HttpServletRequest req, String propriedade) throws Exception;

}