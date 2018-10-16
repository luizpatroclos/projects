package br.gov.inpi.epec.mb;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.gov.inpi.epec.util.JSFMessageUtil;

public class AbstractMB {

	Locale localeES = new Locale("es", "");
	Locale localeEN = new Locale("en", "");

	private static final String KEEP_DIALOG_OPENED = "KEEP_DIALOG_OPENED";

	public static final String FIND_BY_ID_COLABORACAO = "Tbcadcolaboracao.findByIdColaboracao";

	public static final String FIND_ALL_COLABORACAO = "Tbcadcolaboracao.findAll";

	public static final String FIND_ENTIDADE_COLABORACAO = "Tbcolaboracaoentidade.findByIdColaboracaoEntidade_1";

	public static final String FIND_COLABORACAO_ENTIDADE_02 = "Tbcolaboracaoentidade.findByIdColaboracaoEntidade_2";

	public static final String FIND_RELATORIO_COLABORACAO_BY_COLABORACAO = "Tbrelatoriocolaboracao.findByIdRelatorioColaboracao_2";

	public static final String FIND_BY_ID_ENTIDADE = "Tbcadentidade.findByIdEntidadeEc";

	public static final String FIND_ALL_ENTIDADE = "Tbcadentidade.findAll";

	public static final String FIND_ALL_ENTIDADE_PAIS = "Tbcadentidade.findBypais";

	public static final String FIND_BY_ID_PAIS = "Tbcadpais.findByIdPais";

	public static final String FIND_ALL_PAIS = "Tbcadpais.findAll";

	public static final String FIND_PATENTE_POR_PAIS = "Tbpatenteec.findEntidadeEC";

	public static final String FIND_ALL_FAMILIA = "Tbfamiliaec.findAll";

	public static final String FIND_ALL_PATENTE = "Tbpatenteec.findAll";

	public static final String FIND_ENTIDADE_BY_ID_PAIS = "Tbcadentidade.findByIdPais";

	public static final String FIND_BY_ID_PEDIDO = "Tbpatenteec.findByIdPatenteEc";

	public static final String FIND_RELATORIO_FAMILIA = "Tbrelatorioec.findByIdFamiliaEc";

	public static final String FIND_PRIORIDADE = "Tbprioridadeec.findByIdPrioridadeEc";

	public static final String FIND_PRIORIDADE_PEDIDO = "Tbprioridadeec.findByIdPatenteEc";

	public static final String FIND_FAMILIA = "Tbfamiliaec.findByIdFamiliaEc";

	public static final String FIND_RELATORIOPATENTE_RELATORIO = "Tbrelatoriopatente.findByIdRelatorioPatente_1";

	public static final String FIND_RELATORIOPATENTE_PATENTE = "Tbrelatoriopatente.findByIdRelatorioPatente_2";

	public static final String FIND_BY_ID_RELATORIO = "Tbrelatorioec.findByIdRelatorioEc";

	public static final String FIND_PATENTE_BY_NUMPEDIDO = "Tbpatenteec.findByStrPedNumDocdb";

	public static final String EPODOC = "epodoc";

	public static final String PRIORITY = "priority";

	public static final String BIBLIO = "biblio";

	public static final String FIND_LOGRELATORIO_BY_ID_RELATORIO = "Tblogrelatorio.findByIdLogRelatorio";

	public static final String FIND_LOGFAMILIA_BY_FAMILIAID = "Tblogfamilia.findByIdFamiliaEc";

	public static final String FIND_LOGPEDIDO_BY_PEDIDOID = "Tblogpatente.findByIdPatenteEc";

	public static final String FIND_COMENTARIOFAMILIA_BY_FAMILIAID = "Tbcomentariosfamiliaec.findByIdComentariosfamiliaEc";

	public static final String FIND_USUARIO = "Tbcadusuario.findByIdUsuario";

	public static final String FIND_RELATORIO_BY_PEDIDO = "Tbrelatorioec.findByStrRelatorio";

	public static final String FIND_USUARIO_NOME = "Tbcadusuario.findByStrUsuario";

	public static final String PERFIL_VISITANTE = "Visitante";

	public static final String PERFIL_EXAMINADOR = "Examinador";

	public static final String PERFIL_ADM_ENTIDADES = "Administrador de Entidade";

	public static final String PERFIL_ADM_SISTEMAS = "Administrador de Sistema";

	public static final String MSG_INFO = " Atenção !";

	public static final String MSG_ERRO = " Erro !";

	public static final String MSG_SUCESSO = " Sucesso !";

	public Map<String, Object> parameters;

	public HashMap<String, Long> hashParametro;

	public static final String FIND_ALL_INVENTOR = "Tbinventor.findAll";

	public static final String FIND_ID_INVENTOR = "Tbinventor.findByIdInventor";

	public static final String FIND_ALL_DEPOSITANTE = "Tbdepositante.findAll";

	public static final String FIND_ID_DEPOSITANTE = "Tbdepositante.findByIdDepositante";

	public static final String FIND_TEXT_DEPOSITANTE = "Tbdepositante.findByTxDepositante";

	public static final String FIND_ALL_CLASSIFICACAO = "Tbclassificacao.findAll";

	public static final String FIND_ID_CLASSIFICACAO = "Tbclassificacao.findByIdClassificacao";

	public static final String FIND_ID_RELATORIO = "Tbrelatorioec.findByIdRelatorioEc";

	public static final String FIND_ALL_CATEGORIA = "Tbcadcategoria.findAll";

	public static final String FIND_ALL_USERS = "Tbcadusuario.findAll";

	public static final String FIND_ALL_CLAUSULA_TIPO = "Tbclausulatipo.findAll";

	public static final String FIND_ALL_NOS = "Tbnos.findAll";

	public static final String FIND_TBNO_BY_ENTIDADE = "Tbnos.findByEntidade";

	public static final String FIND_ALL_CAD_ATIVO = "Tbcadativo.findAll";

	public static final String FIND_RELATORIO_COLABORACAO = "Tbrelatoriocolaboracao.findByIdRelatorioColaboracao_1";

	public static final String FIND_INVENTORPATENTE_PELA_PATENTE = "Tbinventorpatente.findByIdInventorPatente_2";

	public static final String FIND_DEPOSITANTEPATENTE_PELA_PATENTE = "Tbdepositantepatente.findByIdDepositantePatente_2";

	public static final String FIND_CLASSIFICACAOPATENTE_PELA_PATENTE = "Tbclassificacaopatente.findByIdPatenteEc";

	public static final String FIND_ANTPATENTARIARELATORIO_PELO_RELATORIO = "Tbantpatentariarelec.findByIdRelatorio";

	public static final String FIND_ANTNAOPATENTARIARELATORIO_PELO_RELATORIO = "Tbantnaopatentariarelec.findByIdRelatorio";

	public static final String FIND_ALL_CADTIPOANTERIORIDADE = "Tbcadtipoanterioridade.findAll";

	public static final String FIND_ALL_CADTIPOANTERIORIDADE_NAOPATENTARIA = "Tbcadtipoanterioridade.findByIdAnterioridadenaopatentaria";

	public static final String FIND_CATEGORIARELATORIO_BY_RELATORIO = "Tbcatrelatorioec.findByRelatorioEc";

	public static final String FIND_CADCATEGORIA_BY_ID = "Tbcadcategoria.findByIdCategoria";

	public static final String FIND_TIPOANTERIORIDADE_BY_ID = "Tbcadtipoanterioridade.findByIdCadtipoanterioridade";

	public static final String FIND_CATRELATORIO_BY_RELATORIO = "Tbcatrelatorioec.findByRelatorioEc";

	public static final String FIND_ANTPATENTARIACATRELEC_BY_ID = "Tbantpatentariarelec.findByIdAntpatentariarelEc";

	public static final String FIND_ANTPATENTARIACATRELEC_BY_CATRELATORIO = "Tbantpatentariacatrelec.findByIdCatrelatorioEca";

	public static final String FIND_ANTNAOPATENTARIACATRELEC_BY_CATRELATORIO = "Tbantnaopatentariacatrelec.findByidCatrelatorioec";

	public static final String FIND_CATRELATORIO_BY_ID = "Tbcatrelatorioec.findByIdCatrelatorioec";

	public static final String FIND_CARACCATRELATORIO_BY_CATRELATORIO = "Tbcaraccatrelatorioec.findByIdCatrelatorioec";

	public static final String FIND_PATENTARIA_BY_CARAC = "Tbantpatentaria.findByIdCaraccatrelatorio";

	public static final String FIND_NAO_PATENTARIA_BY_CARC = "Tbantnaopatentaria.findByIdCaraccatrelatorio";

	public static final String FIND_CADSTATUSRELATORIOBYID = "Tbcadstatusrelatorio.findByIdStatus";

	public static final String FIND_TBNOS_BY_CLASULA = "Tbnos.findByClasulaTipo";

	public static final String FIND_TBNOS_BY_ID = "Tbnos.findByIdNo";

	public static final String FIND_USUARIO_BY_ENTIDADE = "Tbcadusuario.findByIdEntidade";

	public static final String FIND_PATENTE_BY_ENTIDADE = "Tbpatenteec.findByEntidadeEC";

	public static final String FIND_PATENTE_BY_NUMEROPUBLICACAO = "Tbpatenteec.findByStrPubNumDocdb";

	public static final String FIND_PATENTE_BY_NUMEROPEDIDO = "Tbpatenteec.findByStrPedNumDocdb";

	public static final String FIND_PATENTE_BY_NUMEROPEDIDO_EPODOC = "Tbpatenteec.findByStrPedNumEpodoc";

	public static final String FIND_PATENTE_BY_NUMEROORIGINAL = "Tbpatenteec.findByStrPedNumOriginal";

	public String numerofamilia;

	public String inserirCategoria;
	
	public String inserirCaracteristica;

	public String isAdminstrador = "0";

	public String idioma = "0";

	// public String isRecuperaSenha;

	public String getIdioma() {
		return idioma;
	}

	public void setInserirCaracteristica(String inserirCaracteristica) {
		this.inserirCaracteristica = inserirCaracteristica;
	}
	
	public String getInserirCaracteristica() {
		return inserirCaracteristica;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	
	

	public AbstractMB() {

		super();
	}

	protected void displayErrorMessageToUser(String message) {
		JSFMessageUtil messageUtil = new JSFMessageUtil();
		messageUtil.sendErrorMessageToUser(message, MSG_ERRO);
	}

	protected void displayErrorFatalMessageToUser(String message) {
		JSFMessageUtil messageUtil = new JSFMessageUtil();
		messageUtil.sendErrorMessageToUser(message, MSG_ERRO);
	}

	protected void displayInfoMessageToUser(String message) {
		JSFMessageUtil messageUtil = new JSFMessageUtil();
		messageUtil.sendInfoMessageToUser(message, MSG_INFO);
	}

	protected void displaySucessoMessageToUser(String message) {
		JSFMessageUtil messageUtil = new JSFMessageUtil();
		messageUtil.sendInfoMessageToUser(message, MSG_SUCESSO);
	}

	protected void closeDialog() {
		getRequestContext().addCallbackParam(KEEP_DIALOG_OPENED, false);
	}

	protected void keepDialogOpen() {
		getRequestContext().addCallbackParam(KEEP_DIALOG_OPENED, true);
	}

	protected RequestContext getRequestContext() {
		return RequestContext.getCurrentInstance();
	}

	public HashMap<String, Long> getHashParametro() {
		return hashParametro = new HashMap<String, Long>();
	}

	public void setHashParametro(HashMap<String, Long> hashParametro) {
		this.hashParametro = hashParametro;
	}

	public void setNumerofamilia(String numerofamilia) {
		this.numerofamilia = numerofamilia;
	}

	public String getIsAdminstrador() {
		return isAdminstrador;
	}

	public void setIsAdminstrador(String isAdminstrador) {
		this.isAdminstrador = isAdminstrador;
	}

	/*
	 * public String getIsRecuperaSenha() { return isRecuperaSenha; }
	 * 
	 * public void setIsRecuperaSenha(String isRecuperaSenha) {
	 * 
	 * if (isRecuperaSenha != null) {
	 * 
	 * this.isRecuperaSenha = isRecuperaSenha; }
	 * 
	 * }
	 */

	// mensagem Locais do sistema evitar codigo repitido

	public void mensagemOcampoAnterioridadeNaoPodeFicarEmBranco() {
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "El campo Antecedentes no puede estar en blanco"));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "The prior field can not be blank"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", "O campo anterioridade não pode ficar em branco"));
		}

	}

	public void mensagemOcampoCaracteristicaNaoPodeFicarEmBranco() {
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "El campo de función no puede estar vacío"));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "The feature field can not be empty"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", "O campo característica não pode ficar em branco"));
		}

	}

	public void oUsuarioLogadoNaoTemAcessoAoRelatorioDesejado() {

		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "El usuario conectado no tiene acceso al informe requerido"));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "The logged in user does not have access to the required report"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", "O usuário logado não tem acesso ao relatório desejado"));
		}

	}

	public void oUsuarioLogadoNaoPodeRealizarTalOperacao() {

		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "El usuario conectado no puede realizar esta operación"));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "The logged in user can not perform this operation"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", "O usuário logado não pode realizar tal operação"));
		}

	}

	public void statusAlteradoComSucesso() {

		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sucesso", "La situación ha sido cambiada con éxito"));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sucesso", "The status has been changed successfully"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sucesso", "O  Status foi alterado com sucesso"));
		}

	}

	public void dadosIncorretosVerifique() {
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "Datos incorrectos, por favor verifique los datos introducidos y vuelva a intentarlo."));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "Incorrect data, please check the entered data and try again."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", "Dados incorretos, por favor verifique os dados digitados e tente novamente."));
		}
	}

	public void relatorioJaVinculado() {
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "La operación no se puede realizar porque el informe ya está consolidado"));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "Operation can not be performed because the report is already bound"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", "Operação não pode ser realizada pois o relatório já está vinculado"));
		}

	}

	public void incluidoComSucesso() {
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("éxito", "Incluido con éxito"));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("success", "Included with success"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sucesso", "Incluído com sucesso"));
		}

	}

	public void relatorioCopiadoComSucesso() {
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("éxito", "Informe copiado con éxito"));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("success", "Report successfully copied"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sucesso", "Relatório copiado com sucesso"));
		}

	}

	public void alteradoComSucesso() {
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("éxito", "Cambiado Correctamente"));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("success", "Successfully Changed"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sucesso", "Alterado com Sucesso"));
		}

	}

	public void salvoComSucesso() {
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("éxito", "Guardado Correctamente"));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("success", "Successfully saved"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sucesso", "Salvo com Sucesso"));
		}

	}

	public void excluidoComSucesso() {
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("éxito", "Eliminado correctamente"));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("success", "Included with success"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sucesso", "Excluído com sucesso"));
		}

	}

	public void operacaoNaoPodeSerRealizada() {
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "Operación no Realiza"));

		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "operation not performed"));

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", "Operação não realizada"));
		}

	}

	public void colaboracaoJaExiste(String colaboracao) {
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error ", " Colaboración " + colaboracao + " ya existe "));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error ", " Collaboration " + colaboracao + " already exists "));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", "A colaboração " + colaboracao + " já existe"));
		}
	}

	public void soPodeCriarEntidadeParaoPais(String pais) {
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "Sólo se pueden crear entidades para los padres" + pais));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "You can only create entities for parents" + pais));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", "O usuário só pode criar entidades para o pais " + pais));
		}
	}

	public void soUmaEntidadeMesmoPaisPodeSerEscritorio() {
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "Sólo una entidad del mismo país puede ser oficina de patentes"));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "Only an entity of the same country can be patent office"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", "Só uma entidade de um mesmo país pode ser escritório de patente"));
		}

	}

	public void entidadeJaExiste(String entidade) {
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "Entidad " + entidade + " ya existe."));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "Entity " + entidade + " already exists."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", "A Entidade " + entidade + " já existe."));

		}
	}

	public void soPodeAlterarParaoPais(String pais) {
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "Sólo se pueden cambio entidades para los padres" + pais));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "You can only change entities for parents" + pais));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", "O usuário só pode alterar entidades para o pais " + pais));
		}
	}

	public void nenhumItemSelecionado() {
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Advertencia: ", " Ningún elemento seleccionado!"));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Warning: ", " No item selected!"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atenção: ", "Nenhum item selecionado!"));
		}
	}

	public void nomeDoPaisJaExiste(String pais) {
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "El nombre" + pais + " ya existe"));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "The name" + pais + " already exists"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "O nome " + pais + " já existe"));
		}
	}

	public void CodigoJaExiste(String pais) {
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "El código " + pais + " ya existe"));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "The code " + pais + " already exists"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "O código " + pais + " já existe"));
		}

	}

	public void ComentarioEnviadoComSucesso() {
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("éxito", "Comentario enviado correctamente"));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("success", "Comment sent successfully"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sucesso", "Comentário enviado com sucesso"));
		}
	}

	public void mensagemEnviadaComSucesso() {
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("éxito", "Mensaje enviado correctamente"));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("success", "Message sent successfully"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sucesso", "Mensagem enviado com sucesso"));
		}
	}

	public void nomeDoinventorEmBranco() {
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "El nombre del inventor está en blanco"));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "The inventor's name is blank"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", "O nome do Inventor está em branco."));
		}
	}

	public void nomeDepositanteEmBranco() {
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "El nombre del solicitante está en blanco"));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "The applicant's name is blank"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", "O nome do Depositante está em branco."));
		}
	}
	
	
	public void nomeClassificaoEmBranco() {
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "El campo Clasificación está en blanco."));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "The Rating field is blank."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", "O campo Classificação está em branco."));
		}
	}

	public void relatorioVinculadoComSucesso() {
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("éxito", "Informe correctamente vinculado"));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("success", "Successfully linked report"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sucesso", "Relatório vinculado com sucesso"));
		}
	}

	public void relatorioSelecinadonaoFinalizado() {
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "El informe de selección está finalizado"));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "The selecting report is finalized"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", "O relatório selecionando está finalizado"));
		}
	}

	public void relatorioFinalNaoEstaFinalizado() {
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "El presente informe no se ha terminado."));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "The current report is not finalized."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", "O relatório atual não está finalizado."));
		}

	}

	public void soEhPossivelPublicarRelatorioFinalizado() {
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "Sólo puede publicar informes finalizados"));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "You can only publish finalized reports"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", "Só é possível publicar relatórios finalizados"));
		}
	}

	public void relatorioPublicado() {
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sucesso", "El informe fue publicado"));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sucesso", "The report was published"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sucesso", "O relatório foi publicado"));
		}
	}

	public void categoriaJaexiste() {

		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "La categoría ya existe en el informe"));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "The category already exists in the report"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", "A categoria já existe no relatorio"));
		}
	}

	public void relatorioSalvoComSucesso() {
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sucesso", "El informe se ha guardado correctamente."));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sucesso", "The report was saved successfully."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sucesso", "O relatório foi salvo com sucesso."));
		}
	}

	public void relatorioSemColaboracao() {

		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "Debe elegir al menos una colaboración"));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "You must choose at least one collaboration"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", "É necessário escolher pelo menos uma colaboração"));
		}
	}

	public void noComClausulaTipo() {

		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "Supresión no permitido, el nodo seleccionado tiene tipo una o más cláusulas."));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "Deletion not allowed, the selected node has one or more clauses type."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", "Exclusão não permitida, o nó selecionado possui uma ou mais cláusulas tipo."));
		}
	}

	public void relatorioNaoPodeVincularOuCopiar() {

		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "Operación no permitida. El informe no es parte de la colaboración"));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "Operation not permitted. The report is not part of collaboration"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", "Operação não permitida. O relatório não faz parte da colaboração"));
		}
	}

	public void relatorioNaoPodealterarColaboracao() {

		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "Los cambios no permitidos, este informe esta vinculado con al menos una petición de otra entidad"));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "Changes not permitted, this report this linked to at least one request of another entity"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", "Alteração não permitida, este relatório esta vinculado a pelo menos um pedido de outra entidade"));
		}
	}

	public void campoObrigatorio(String nome) {
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "El campo " + nome + " es obligatorio"));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "The field " + nome + " is mandatory"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", "O campo " + nome + " é de preenchimento obrigatório"));
		}
	}

	public void campoInvalido(String nome) {
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "El campo " + nome + " es no válido"));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "The field " + nome + " is invalid"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", "O campo " + nome + " é inválido"));
		}
	}

	public void preencherTodosOsCampos() {
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "Debe llenar todos los campos"));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "You must fill out all fields"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", "É necessário preencher todos os campos."));
		}
	}

	public void preencherTodosOsCamposDaClausula() {
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "Debe llenar todos los campos tipo cláusula"));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "You must fill out all fields clause type"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", "É necessário preencher todos os campos da Cláusula Tipo."));
		}
	}

	public void mensagemDeErroClausulaTipo() {
		String texto = "";
		String categoria = "";

		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			categoria = "Error";
			texto = "Proceso Error Cláusula!";
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			categoria = "Error";
			texto = "Error Process Clause!";
		} else {
			categoria = "Erro";
			texto = "Erro ao Processar Cláusula !";
		}

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(categoria, texto));

	}

	public void mensagemDeSucessoClausulaTipo() {

		String texto = "";
		String categoria = "";
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			categoria = "éxito";
			texto = "Proceso Incluye éxito!";
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			categoria = "Success";
			texto = "Process successfully Included!";
		} else {
			categoria = "Sucesso";
			texto = "Processo Incluído com sucesso!";
		}

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(categoria, texto));

	}
	
	
	public void OhValoraExiste(String pais) {
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "El valor" + pais + " ya existe"));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "The value" + pais + " already exists"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "O valor " + pais + " já existe"));
		}
	}
	
	
	
	public void pedidoRecuperado(){
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sucesso", "Con éxito recuperado petición!"));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sucesso", "Successfully recovered request!"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sucesso", "Pedido recuperado com sucesso!"));
		}
	}
	
	public void erroPedidoManual(){
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "Error en la solicitud, petición introducir manualmente!"));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "Error in the request, request entered manually!"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", "Erro na solicitação, pedido inserido manualmente!"));
		}
	}
	
	public void erroSolicitacao(){
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "Problemas con la Solicitud de Servicio"));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "Error in the Request Service"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", "Erro na Solicitação do Serviço"));
		}
	}
	
	
	public void senhaIncorreta(){
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "La contraseña actual es incorrecto."));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "The current password is incorrect."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", "A senha atual está incorreta."));
		}
	}
	
	public void confirmarSenhaDiferente(){
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "La contraseña de confirmación no es correcta."));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "The confirmation password is incorrect."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", "A confirmação de senha está incorreta."));
		}
	}
	
	public void minimoSeisCaracteres(){
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "La contraseña debe contener como mínimo 6 caracteres"));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "The password must contain at least 6 characters"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", "A senha deve conter no mínimo 6 caracteres"));
		}
	}
	
	
	
	public void selecionarResponsavel(){
		if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeES)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "Debe seleccionar el examinador responsable del informe"));
		} else if (FacesContext.getCurrentInstance().getViewRoot().getLocale().equals(localeEN)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "You must select the examiner responsible for the report"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro", "É necessário selecionar o examinador responsável pelo relatório"));
		}
	}
	
	
	
	
	

}