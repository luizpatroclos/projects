package br.gov.inpi.epec.service.impl;

import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

import br.gov.inpi.epec.facade.CertificadoServiceFacade;

@Stateless(name = "CertificadoServiceImpl")
public class CertificadoServiceImpl implements CertificadoServiceFacade {

	@Override
	public String recuperarPropriedadesCertificado(HttpServletRequest req, String propriedade) throws Exception {
		String cipherSuite = (String) req.getAttribute("javax.servlet.request.cipher_suite");

		String property = "";

		try {
			if (cipherSuite != null) {
				X509Certificate certChain[] = (X509Certificate[]) req.getAttribute("javax.servlet.request.X509Certificate");
				if (certChain != null) {

					X509Certificate certificado = certChain[0];

					String[] props = certificado.getSubjectDN().getName().split(",");

					Map<String, String> propriedadesSeparadas = separarChaveDoValor(props);

					if (!propriedadesSeparadas.containsKey(propriedade)) {
						throw new IllegalArgumentException("Não foi localizada nenhum propridade com o chave [" + propriedade + "] no certificado");
					}

					property = propriedadesSeparadas.get(propriedade);
				}

			}
		} catch (Exception e) {
			throw e;
		}
		
		return property;
	}

	private Map<String, String> separarChaveDoValor(String[] props) {

		Map<String, String> propriedadesSeparadas = new HashMap<String, String>();

		for (String propriedade : props) {
			String[] chaveValor = propriedade.trim().split("=");
			propriedadesSeparadas.put(chaveValor[0], chaveValor[1]);
		}

		return propriedadesSeparadas;

	}

}
