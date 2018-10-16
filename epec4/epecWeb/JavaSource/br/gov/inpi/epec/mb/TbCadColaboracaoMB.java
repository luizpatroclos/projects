package br.gov.inpi.epec.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.gov.inpi.epec.beans.Status;
import br.gov.inpi.epec.beans.Tbcadcolaboracao;
import br.gov.inpi.epec.beans.Tbcadentidade;
import br.gov.inpi.epec.beans.Tbcadusuario;
import br.gov.inpi.epec.beans.Tbcolaboracaoentidade;
import br.gov.inpi.epec.beans.Tbrelatoriocolaboracao;
import br.gov.inpi.epec.facade.EpecServiceFacade;

@SessionScoped
@ManagedBean
public class TbCadColaboracaoMB extends AbstractMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	EpecServiceFacade service;

	private List<Tbcadcolaboracao> colaboracoes;
	private List<Tbcadentidade> entidades;
	private List<Tbcadentidade> entidadesTeste;
	private Tbcadcolaboracao colaboracao = new Tbcadcolaboracao();
	private Tbcadcolaboracao colaboracaoSelected;

	private HttpSession session;
	public Tbcadusuario usuarioLogado;
	

	Locale localeES = new Locale("es", "");
	Locale localeEN = new Locale("en", "");

	public Tbcadusuario getUsuarioLogado() {
		obterUsuarioSessao();
		return usuarioLogado;
	}

	private void obterUsuarioSessao() {
		FacesContext fc = FacesContext.getCurrentInstance();

		session = (HttpSession) fc.getExternalContext().getSession(true);

		usuarioLogado = (Tbcadusuario) session.getAttribute("usuarioAutenticado");

	}

	public void setUsuarioLogado(Tbcadusuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	private Status status = new Status();

	public Tbcadcolaboracao getColaboracao() {
		return colaboracao;
	}

	public List<Tbcadcolaboracao> getColaboracaoListar() {

		loadColaboracao();

		List<Tbcadcolaboracao> teste = new ArrayList<>();
		for (int i = 0; i < colaboracoes.size(); i++) {
			
			if(colaboracoes.get(i).getEntidades() != null){
				for (int e = 0; e < colaboracoes.get(i).getEntidades().size(); e++) {
					if (colaboracoes.get(i).getEntidades().get(e).getIdEntidadeEc().equals(usuarioLogado.getIdEntidadeEc().getIdEntidadeEc())) {
						if (colaboracoes.get(i).getEntidades().get(e).getIdAtivo() == 1 && colaboracoes.get(i).getIdAtivo() == 1)
							teste.add(colaboracoes.get(i));
					}
				}
			}
			

		}

		return teste;

	}

	public Tbcadcolaboracao getColaboracaoSelected() {
		return colaboracaoSelected;
	}

	public void setColaboracaoSelected(Tbcadcolaboracao colaboracaoSelected) {
		this.colaboracaoSelected = colaboracaoSelected;
	}

	public void setColaboracao(Tbcadcolaboracao colaboracao) {
		this.colaboracao = colaboracao;
	}

	private List<Tbcadcolaboracao> colaboracaoTeste;

	@SuppressWarnings("unchecked")
	private void loadColaboracao() {

		usuarioLogado = getUsuarioLogado();
		colaboracoes = (List<Tbcadcolaboracao>) (Object) service.listAll(FIND_ALL_COLABORACAO);

		for (int i = 0; i < colaboracoes.size(); i++) {
			if (colaboracoes.get(i).getIdAtivo() == 1) {
				colaboracoes.get(i).setTextoAtivo("Ativo");
			} else {
				colaboracoes.get(i).setTextoAtivo("Inativo");
			}

			try {

				getHashParametro().put("idColaboracao", colaboracoes.get(i).getIdColaboracao());

				List<Tbcolaboracaoentidade> tbcolaboEntidades = (List<Tbcolaboracaoentidade>) (Object) service.list(FIND_ENTIDADE_COLABORACAO, hashParametro);

				if (!tbcolaboEntidades.isEmpty()) {

					List<Tbcadentidade> tbcadentidades = new ArrayList<Tbcadentidade>();

					List<String> nomesEntidade = new ArrayList<>();

					for (int e = 0; e < tbcolaboEntidades.size(); e++) {

						Tbcadentidade tbcadentidade = new Tbcadentidade();

						getHashParametro().put("idEntidadeEc", tbcolaboEntidades.get(e).getIdEntidadeEc());

						tbcadentidade = (Tbcadentidade) service.findOneResult(FIND_BY_ID_ENTIDADE, hashParametro);
						nomesEntidade.add(tbcadentidade.getTxOrganizacao());
						tbcadentidades.add(tbcadentidade);

					}
					colaboracoes.get(i).setEntidades(tbcadentidades);
					colaboracoes.get(i).setEntidadesNome(nomesEntidade);
				}

			} catch (Exception ignore) {
			}

		}

	}

	@SuppressWarnings("unchecked")
	public List<Tbcadcolaboracao> loadColaboracao2() {

		obterUsuarioSessao();

		colaboracoes = (List<Tbcadcolaboracao>) (Object) service.listAll(FIND_ALL_COLABORACAO);

		List<Tbcadcolaboracao> tbcadcolaboracaos = new ArrayList<>();

		for (int i = 0; i < colaboracoes.size(); i++) {
			if (colaboracoes.get(i).getIdAtivo() == 1) {
				colaboracoes.get(i).setTextoAtivo("Ativo");
			} else {
				colaboracoes.get(i).setTextoAtivo("Inativo");
			}

			try {

				getHashParametro().put("idColaboracao", colaboracoes.get(i).getIdColaboracao());

				List<Tbcolaboracaoentidade> tbcolaboEntidades = (List<Tbcolaboracaoentidade>) (Object) service.list(FIND_ENTIDADE_COLABORACAO, hashParametro);

				if (!tbcolaboEntidades.isEmpty()) {

					List<Tbcadentidade> tbcadentidades = new ArrayList<Tbcadentidade>();

					List<String> nomesEntidade = new ArrayList<>();

					for (int e = 0; e < tbcolaboEntidades.size(); e++) {

						Tbcadentidade tbcadentidade = new Tbcadentidade();

						getHashParametro().put("idEntidadeEc", tbcolaboEntidades.get(e).getIdEntidadeEc());

						tbcadentidade = (Tbcadentidade) service.findOneResult(FIND_BY_ID_ENTIDADE, hashParametro);

						nomesEntidade.add(tbcadentidade.getTxOrganizacao());
						tbcadentidades.add(tbcadentidade);
					}
					colaboracoes.get(i).setEntidades(tbcadentidades);
					colaboracoes.get(i).setEntidadesNome(nomesEntidade);

					if (usuarioLogado.getIdPerfilusuario().getIdPerfilusuario() == 2L) {
						
						if(usuarioLogado.getIdEntidadeEc().getOficina()){
							for (int o = 0; o < tbcolaboEntidades.size(); o++) {
								Tbcadentidade tbcadentidade = new Tbcadentidade();

								getHashParametro().put("idEntidadeEc", tbcolaboEntidades.get(o).getIdEntidadeEc());

								tbcadentidade = (Tbcadentidade) service.findOneResult(FIND_BY_ID_ENTIDADE, hashParametro);

								if (usuarioLogado.getIdEntidadeEc().getIdPais().equals(tbcadentidade.getIdPais())) {
									tbcadcolaboracaos.add(colaboracoes.get(i));
									o = tbcolaboEntidades.size() + 1;
								}
							}
							
						}else{
							for (int o = 0; o < tbcolaboEntidades.size(); o++) {
								Tbcadentidade tbcadentidade = new Tbcadentidade();

								getHashParametro().put("idEntidadeEc", tbcolaboEntidades.get(o).getIdEntidadeEc());

								tbcadentidade = (Tbcadentidade) service.findOneResult(FIND_BY_ID_ENTIDADE, hashParametro);

								if (usuarioLogado.getIdEntidadeEc().equals(tbcadentidade)) {
									tbcadcolaboracaos.add(colaboracoes.get(i));
									o = tbcolaboEntidades.size() + 1;
								}
							}
						}
						
					} else {
						tbcadcolaboracaos.add(colaboracoes.get(i));
					}

				}

			} catch (Exception ignore) {
			}

		}

		return tbcadcolaboracaos;
	}

	public void createColaboracao() {
		colaboracao.setTxDescricao(colaboracao.getStrColaboracao());

		if (colaboracao.getStrColaboracao().trim().isEmpty()) {
			dadosIncorretosVerifique();
		} else if (colaboracao.getEntidades().size() == 0) {
			dadosIncorretosVerifique();
		} else {
			if (!verificarNomeDuplicado()) {
				colaboracaoJaExiste(colaboracao.getStrColaboracao());					

			} else {

				colaboracao.setIdAtivo((short) 1);

				Object[] arrayEntidades = colaboracao.getEntidades().toArray();

				if (arrayEntidades != null) {

					List<Tbcadentidade> tbcadentidades = new ArrayList<Tbcadentidade>();

					for (int i = 0; i < arrayEntidades.length; i++) {

						Long tbcadentidades1 = Long.parseLong(arrayEntidades[i].toString());

						Tbcadentidade tbcadentidade = new Tbcadentidade();

						getHashParametro().put("idEntidadeEc", tbcadentidades1);

						tbcadentidade = (Tbcadentidade) service.findOneResult(FIND_BY_ID_ENTIDADE, hashParametro);

						tbcadentidades.add(tbcadentidade);
					}

					colaboracao.setTbcadentidadeList(tbcadentidades);

					service.save(colaboracao);
				}

			incluidoComSucesso();

			}
			flush();
		}
	}

	public void updateColaboracao() {

		Tbcadcolaboracao auxColaboracao = null;

		try {
			
			auxColaboracao = colaboracaoSelected;
			if ("Inativo".equals(colaboracaoSelected.getTextoAtivo())) {

				auxColaboracao.setIdAtivo((short) 0);

			} else {
				auxColaboracao.setIdAtivo((short) 1);
			}

		} catch (Exception e) {
		}

		service.update(auxColaboracao);

		
		alteradoComSucesso();
		
		flush();
	}

	@SuppressWarnings("unchecked")
	private boolean verificarNomeDuplicado() {
		boolean verificarNomeDuplicado = true;
		colaboracoes = (List<Tbcadcolaboracao>) (Object) service.listAll(FIND_ALL_COLABORACAO);
		for (int i = 0; i < colaboracoes.size(); i++) {
			if (colaboracoes.get(i).getStrColaboracao().toUpperCase().equals(colaboracao.getStrColaboracao().toUpperCase())) {
				verificarNomeDuplicado = false;
			}
		}
		return verificarNomeDuplicado;
	}

	public List<Tbcadentidade> getEntidades() {
		return entidades;
	}

	public void flush() {
		colaboracao = new Tbcadcolaboracao();
		loadColaboracao2();
	}

	public void setEntidades(List<Tbcadentidade> entidades) {
		this.entidades = entidades;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Tbcadcolaboracao> getColaboracaoTeste() {
		colaboracaoTeste = loadColaboracao2();

		return colaboracaoTeste;
	}

	public void setColaboracaoTeste(List<Tbcadcolaboracao> colaboracaoTeste) {
		this.colaboracaoTeste = colaboracaoTeste;
	}

	@SuppressWarnings("unchecked")
	private List<Tbcadentidade> loadEntidade() {
		obterUsuarioSessao();
		entidadesTeste = (List<Tbcadentidade>) (Object) service.listAll(FIND_ALL_ENTIDADE);

		for (int i = 0; i < entidadesTeste.size(); i++) {

			if (entidadesTeste.get(i).getIdAtivo() == 1) {
				entidadesTeste.get(i).setTextoAtivo("Ativo");
			} else {
				entidadesTeste.get(i).setTextoAtivo("Inativo");
			}
		}

		if (usuarioLogado.getIdPerfilusuario().getIdPerfilusuario() == 2L) {

			if (usuarioLogado.getIdEntidadeEc().getOficina()) {
				entidadesTeste = new ArrayList<>();
				getHashParametro().put("idPais", usuarioLogado.getIdEntidadeEc().getIdPais().getIdPais());
				entidadesTeste = (List<Tbcadentidade>) (Object) service.list(FIND_ALL_ENTIDADE_PAIS, hashParametro);
			} else {
				entidadesTeste = new ArrayList<>();
				entidadesTeste.add(usuarioLogado.getIdEntidadeEc());
			}

		}

		return entidadesTeste;
	}

	public List<Tbcadentidade> getEntidadesTeste() {
		loadEntidade();
		return entidadesTeste;
	}

	public void setEntidadesTeste(List<Tbcadentidade> entidadesTeste) {
		this.entidadesTeste = entidadesTeste;
	}

	@SuppressWarnings("unchecked")
	public void excluirColaboracao() {

		getHashParametro().put("idColaboracao", colaboracaoSelected.getIdColaboracao());

		List<Tbrelatoriocolaboracao> tbrelatoriocolaboracaos = (List<Tbrelatoriocolaboracao>) (Object) service.list(FIND_RELATORIO_COLABORACAO_BY_COLABORACAO, hashParametro);

		if (tbrelatoriocolaboracaos.size() == 0) {
			
				
			service.deleteColaboracao(colaboracaoSelected);
			
			excluidoComSucesso();

		} else {

			operacaoNaoPodeSerRealizada();
			
		}

	}

}
