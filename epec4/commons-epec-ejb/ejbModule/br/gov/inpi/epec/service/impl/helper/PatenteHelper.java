package br.gov.inpi.epec.service.impl.helper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.gov.inpi.epec.beans.Tbclassificacao;
import br.gov.inpi.epec.beans.Tbclassificacaopatente;
import br.gov.inpi.epec.beans.Tbdepositante;
import br.gov.inpi.epec.beans.Tbfamiliaec;
import br.gov.inpi.epec.beans.Tbinventor;
import br.gov.inpi.epec.beans.Tbpatenteec;
import br.gov.inpi.epec.beans.Tbprioridadeec;
import br.gov.inpi.epec.entities.ClassificacaoInternacional;
import br.gov.inpi.epec.entities.Patente;
import br.gov.inpi.epec.entities.Pessoa;
import br.gov.inpi.epec.entities.Prioridade;

/***
 * 
 * Classe Helper responsável em criar um documento de Patente a partir das
 * entidades envolvidas na pesquisa.
 * 
 * 
 * @author allanlq
 *
 */
public class PatenteHelper {

	/***
	 * Informa o tipo do documento de Patente - DocDb / EpoDoc / Original.
	 */
	private String docType;

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public Patente obterDadosPatente(Tbpatenteec tbPatenteec, String docType) {

		Patente patente = new Patente();

		this.setDocType(docType);

		patente.setIdDocumentoPatente(tbPatenteec.getIdPatenteEc());

		patente.setTipoDocumento(this.getDocType());
		String paisPub = tbPatenteec.getStrPubPaisDocdb().trim();
		String numeroPedido = tbPatenteec.getStrPubNumDocdb().trim();

		patente.setNumero(paisPub.concat(numeroPedido));
		patente.setDataDeposito(tbPatenteec.getDtDeposito());
		patente.setDataPublicacao(tbPatenteec.getDtPublicacao());
		patente.setNumeroPedidoOriginal(tbPatenteec.getStrPedNumOriginal());
		patente.setPaisPublicacao(tbPatenteec.getStrPedPaisDocdb());
		patente.setNumeroPublicacao(tbPatenteec.getStrPubNumDocdb());
		patente.setKindCodePublicacao(tbPatenteec.getStrPubKindDocdb());
		patente.setPais(tbPatenteec.getStrPedPaisDocdb());
		patente.setNumeroAplicacao(tbPatenteec.getStrPedNumDocdb());
		patente.setKindCode(tbPatenteec.getStrPedKindDocdb());
		patente.setNumeroPedidoEpoDoc(tbPatenteec.getStrPedNumEpodoc());

		Tbfamiliaec tbFamiliaec = tbPatenteec.getIdFamiliaEc();
		if (tbFamiliaec != null) {
			patente.setFamiliaId(Long.toString(tbFamiliaec.getIdFamiliaEc()));
		}

		String titulo = tbPatenteec.getTxTitulo();
		if (titulo != null) {
			patente.setTitulo(titulo.trim());
		}

		String revindicacao = tbPatenteec.getStrReivindicacao();
		if (revindicacao != null) {
			patente.setRevindicacao(revindicacao.trim());
		}

		String resumo = tbPatenteec.getTxResumo();
		if (resumo != null) {
			patente.setResumo(resumo.trim());
		}

		String procurador = tbPatenteec.getTxProcurador();
		Pessoa pessoa = this.retornarPessoa(procurador);
		if (pessoa != null) {
			patente.setProcurador(pessoa);
		}

		List<Pessoa> inventores = this.retornarInventores(tbPatenteec.getTbinventorList());
		if (inventores != null && !inventores.isEmpty()) {
			patente.setInventores(inventores);
		}

		List<Pessoa> depositantes = this.retornarDepositantes(tbPatenteec.getTbdepositanteList());
		if (depositantes != null && !depositantes.isEmpty()) {
			patente.setDepositantes(depositantes);
		}

		List<ClassificacaoInternacional> classificacoes = this.retornarClassificacoes(tbPatenteec.getTbclassificacaopatenteList());
		if (classificacoes != null && !classificacoes.isEmpty()) {
			patente.setClassificacoes(classificacoes);
		}

		List<Prioridade> prioridades = this.retornarPrioridades(tbPatenteec.getTbprioridadeecList());
		if (prioridades != null && !prioridades.isEmpty()) {
			patente.setPrioridades(prioridades);
		}

		System.out.println(" >>> Patente :: " + patente.toString());
		return patente;

	}

	/***
	 * 
	 * M�todo utilizado somente para consultas que envolvam familia de
	 * documentos de patentes, pois n�o precisam todos os dados de uma patente.
	 * 
	 * @param tbPatenteec
	 * @param docType
	 * @return
	 */
	public Patente obterDadosPatenteFamilia(Tbpatenteec tbPatenteec, String docType) {

		Patente patente = new Patente();

		this.setDocType(docType);

		patente.setIdDocumentoPatente(tbPatenteec.getIdPatenteEc());
		patente.setTipoDocumento(this.getDocType());

		String paisPub = tbPatenteec.getStrPubPaisDocdb().trim();
		String numeroPedido = tbPatenteec.getStrPubNumDocdb().trim();

		patente.setNumero(paisPub.concat(numeroPedido));
		patente.setDataDeposito(tbPatenteec.getDtDeposito());
		patente.setDataPublicacao(tbPatenteec.getDtPublicacao());
		patente.setNumeroPedidoOriginal(tbPatenteec.getStrPedNumOriginal());
		patente.setPaisPublicacao(tbPatenteec.getStrPedPaisDocdb());
		patente.setNumeroPublicacao(tbPatenteec.getStrPubNumDocdb());
		patente.setKindCodePublicacao(tbPatenteec.getStrPubKindDocdb());
		patente.setPais(tbPatenteec.getStrPedPaisDocdb());
		patente.setNumeroAplicacao(tbPatenteec.getStrPedNumDocdb());
		patente.setKindCode(tbPatenteec.getStrPedKindDocdb());
		patente.setNumeroPedidoEpoDoc(tbPatenteec.getStrPedNumEpodoc());

		Tbfamiliaec tbFamiliaec = tbPatenteec.getIdFamiliaEc();
		if (tbFamiliaec != null) {
			patente.setFamiliaId(Long.toString(tbFamiliaec.getIdFamiliaEc()));
		}

		String titulo = tbPatenteec.getTxTitulo();
		if (titulo != null) {
			patente.setTitulo(titulo.trim());
		}

		System.out.println(" >>> Patente :: " + patente.toString());
		return patente;

	}

	/***
	 * 
	 * Obtem os inventores da patente.
	 * 
	 * @param tbinventorList
	 * @return
	 */
	private List<Pessoa> retornarInventores(List<Tbinventor> tbinventorList) {
		try {
			List<Pessoa> inventores = null;
			if (tbinventorList != null && !tbinventorList.isEmpty()) {

				inventores = new ArrayList<Pessoa>();
				Iterator<Tbinventor> it = tbinventorList.iterator();
				while (it.hasNext()) {

					Tbinventor tbInventor = it.next();
					Pessoa pessoa = this.retornarPessoa(tbInventor.getTxInventor());
					if (pessoa != null) {
						pessoa.setDataFormat(this.getDocType());
						inventores.add(pessoa);
					}

				}

			}
			return inventores;

		} catch (Exception e) {
			return null;
		}

	}

	/***
	 * 
	 * Obtem os depositantes da patente.
	 * 
	 * @param tbdepositanteList
	 * @return
	 */
	private List<Pessoa> retornarDepositantes(List<Tbdepositante> tbdepositanteList) {
		try {
			List<Pessoa> depositantes = null;
			if (tbdepositanteList != null && !tbdepositanteList.isEmpty()) {

				depositantes = new ArrayList<Pessoa>();
				Iterator<Tbdepositante> it = tbdepositanteList.iterator();
				while (it.hasNext()) {

					Tbdepositante tbDepositante = it.next();
					Pessoa pessoa = this.retornarPessoa(tbDepositante.getTxDepositante());
					if (pessoa != null) {
						pessoa.setDataFormat(this.getDocType());
						depositantes.add(pessoa);
					}

				}

			}
			return depositantes;
		} catch (Exception e) {
			return null;
		}
	}

	/***
	 * 
	 * Formata o nome do Inventor / Depositante / Procurador
	 * 
	 * @param nome
	 * @return
	 */
	private Pessoa retornarPessoa(String nome) {

		Pessoa pessoa = new Pessoa();
		if (nome != null && !"".equals(nome.trim())) {
			pessoa.setNome(nome.trim());
			return pessoa;
		} else {
			return null;
		}

	}

	/***
	 * 
	 * Retorna as classificações de uma patente.
	 * 
	 * @param tbclassificacoesList
	 * @return
	 */
	private List<ClassificacaoInternacional> retornarClassificacoes(List<Tbclassificacaopatente> tbclassificacaopatenteList) {
		try {
			List<ClassificacaoInternacional> classificacoes = null;
			if (tbclassificacaopatenteList != null && !tbclassificacaopatenteList.isEmpty()) {

				classificacoes = new ArrayList<ClassificacaoInternacional>();
				Iterator<Tbclassificacaopatente> it = tbclassificacaopatenteList.iterator();
				while (it.hasNext()) {

					Tbclassificacaopatente tbClassificacaoPatente = it.next();
					Tbclassificacao tbclassificacao = tbClassificacaoPatente.getTbclassificacao();

					if (tbclassificacao != null) {

						ClassificacaoInternacional classificacao = new ClassificacaoInternacional();
						classificacao.setClassificacaoInternacional(tbclassificacao.getTxClassificacao().trim());
						classificacao.setOrdem(tbClassificacaoPatente.getIdOrdem());
						classificacoes.add(classificacao);

					}

				}

			}
			return classificacoes;
		} catch (Exception e) {
			return null;
		}
	}

	/***
	 * 
	 * Formata a classificação.
	 * 
	 * @param classificacao
	 * @return
	 */
	private String obterClassificacaoInternacional(String classificacao) {
		String classficacaoFormatada = null;
		classficacaoFormatada = classificacao.substring(0, 15);
		return classficacaoFormatada;
	}

	/***
	 * 
	 * Retorna a prioridades unionistas de uma patente.
	 * 
	 * @param tbPrioridadeecs
	 * @return
	 */
	private List<Prioridade> retornarPrioridades(List<Tbprioridadeec> tbPrioridadeecs) {

		List<Prioridade> prioridades = null;
		if (tbPrioridadeecs != null && !tbPrioridadeecs.isEmpty()) {

			prioridades = new ArrayList<Prioridade>();
			Iterator<Tbprioridadeec> it = tbPrioridadeecs.iterator();
			while (it.hasNext()) {

				Tbprioridadeec tbPrioridadeec = it.next();
				Prioridade prioridade = new Prioridade();
				prioridade.setDataDeposito(tbPrioridadeec.getDtDeposito());
				prioridade.setNumero(tbPrioridadeec.getStrPrioridade().trim());
				prioridades.add(prioridade);

			}

		}

		return prioridades;

	}

}
