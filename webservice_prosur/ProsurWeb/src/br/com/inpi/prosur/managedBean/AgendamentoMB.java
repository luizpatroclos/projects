package br.com.inpi.prosur.managedBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.SelectEvent;

import br.com.inpi.prosur.exception.ErroProsur;
import br.com.inpi.prosur.util.AgendamentoUtil;
import br.com.inpi.prosur.util.FacesMessageUtil;
import br.com.inpi.prosur.validacoes.ProsurUtil;
import br.com.inpi.prosur.validacoes.ValidacaoAtualizarAgendamento;
import br.com.inpi.prosur.validacoes.ValidacaoNovoAgendamento;
import br.com.inpi.usuarioprosur.bean.UsuarioProsur;
import br.gov.inpi.intercarga.beans.AgendaCarga;
import br.gov.inpi.intercarga.beans.TbAcessoCarga;
import br.gov.inpi.intercarga.beans.TbAgendaCarga;
import br.gov.inpi.intercarga.beans.TbCargaProcesso;
import br.gov.inpi.intercarga.beans.TbCargaRetroativa;
import br.gov.inpi.intercarga.beans.TbCargaSemanal;
import br.gov.inpi.services.EntityInterfaceIntercarga;
import br.gov.inpi.services.ProcessoMarcasManualInterface;
import br.gov.inpi.services.ProcessoPatenteManualInterface;

/**
 * @author ahlucena
 *
 */
@ManagedBean(name = "agendamentoMB")
@ViewScoped
public class AgendamentoMB {

	public static final String AUTOMATICO = "AUTOMATICO";

	public static final String MANUAL = "MANUAL";
	
	public static final String SEMANAL = "SEMANAL";

	public static final String RETROATIVA = "RETROATIVA";
	
	public static final String PROCESSO = "PROCESSO";
	
	private String tipoCarga;
	private String tipoProcesso;
	private String numProcesso;
	private String tipoAgendamento;
	private String[] checkTipoBase;
	private String dataNovoAgendamento;
	private String hora;
	private String statusAgenda;
	private Date calendarDataNovoAgendamento;
	private HashMap<String, Integer> mapParameters = new HashMap<String, Integer>();
	private TbAgendaCarga agendaCarga = new TbAgendaCarga();
	private TbCargaSemanal cargaSemanal = new TbCargaSemanal();
	private TbCargaRetroativa cargaRetroativa = new TbCargaRetroativa();
	private TbCargaProcesso cargaProcesso = null;
	private AgendaCarga dtoAgendaCarga = new AgendaCarga();
	private boolean isReset;
	public String onSelected;
	
	private HttpSession session;
	
	private List<TbCargaSemanal> listaCargaSemanal;
	private List<TbCargaRetroativa> listaCargaRetroativa;
	
	private List<TbCargaProcesso> listaCargaProcesso;
	
	private ValidacaoNovoAgendamento validacaoNovoAgendamento;
	
	private List<AgendaCarga> listaAgendaCarga;
	private List<AgendaCarga> filteredListaAgendaCarga;
	List<String> valor = new ArrayList<String>();
	
	public TbAgendaCarga getAgendaCarga() {
		return agendaCarga;
	}
	
	public AgendaCarga getDtoAgendaCarga() {
		return dtoAgendaCarga;
	}
	
	public void setDtoAgendaCarga(AgendaCarga dtoAgendaCarga) {
		this.dtoAgendaCarga = dtoAgendaCarga;
	}
	
	public TbCargaSemanal getCargaSemanal() {
		return cargaSemanal;
	}

	public TbCargaRetroativa getCargaRetroativa() {
		return cargaRetroativa;
	}	

	public String getHora() {
		return hora;
	}	

	public void setHora(String hora) {
		this.hora = hora;
	}
	
	public String getTipoAgendamento() {
		return tipoAgendamento;
	}

	public void setTipoAgendamento(String tipoAgendamento) {
		this.tipoAgendamento = tipoAgendamento;
	}

	public String getTipoCarga() {
		return tipoCarga;
	}

	public void setTipoCarga(String tipoCarga) {
		this.tipoCarga = tipoCarga;
	}
	
	public String getDataNovoAgendamento() {
		return dataNovoAgendamento;
	}

	public void setDataNovoAgendamento(String dataNovoAgendamento) {
		
		this.dataNovoAgendamento = dataNovoAgendamento;
	}
	
	public String[] getCheckTipoBase(){
		
		return checkTipoBase;
	}
	
	public void setCheckTipoBase(String[] checkTipoBase){
		
		this.checkTipoBase = checkTipoBase;
	}
	
	public String getStatusAgenda() {
		return statusAgenda;
	}

	public void setStatusAgenda(String statusAgenda) {
		this.statusAgenda = statusAgenda;
	}
	
	public String[] getDiasSemana(){
		
		return new String[]{"SELECIONE..", "Segunda-feira", "Terça-feira", "Quarta-feira", 
						    "Quinta-feira", "Sexta-feira", "Sábado", "Domingo"};
	}
	
	public Date getCalendarDataNovoAgendamento() {
		return calendarDataNovoAgendamento;
	}

	public void setCalendarDataNovoAgendamento(Date calendarDataNovoAgendamento) {
		this.calendarDataNovoAgendamento = calendarDataNovoAgendamento;
	}

	public boolean getIsReset(){
		
		return this.isReset;
	}
	
	public void setIsReset(boolean isReset){
		
		this.isReset = isReset;
	}
	
	public List<AgendaCarga> getListaAgendaCarga() {
		
		FacesContext facesCtx = FacesContext.getCurrentInstance();
			
			this.listaAgendaCarga = service.listaAgendaCarga();
			facesCtx.getExternalContext().getSessionMap().put("listaAgendamento", this.listaAgendaCarga);
		
		return listaAgendaCarga;
	}
	
	public void setListaAgendaCarga(List<AgendaCarga> listaAgendaCarga) {
		this.listaAgendaCarga = listaAgendaCarga;
	}	
	
	public List<AgendaCarga> getFilteredListaAgendaCarga() {
		return filteredListaAgendaCarga;
	}

	public void setFilteredListaAgendaCarga(List<AgendaCarga> filteredListaAgendaCarga) {
		this.filteredListaAgendaCarga = filteredListaAgendaCarga;
	}
	
	@EJB
	EntityInterfaceIntercarga service;
	@EJB
	ProcessoPatenteManualInterface persistPatentes;
	@EJB
	ProcessoMarcasManualInterface persistMarcas;
	
	public void salvarNovoAgendamento() {
		
		this.listaCargaSemanal = new ArrayList<TbCargaSemanal>();
		this.listaCargaRetroativa = new ArrayList<TbCargaRetroativa>();
		this.listaCargaProcesso = new ArrayList<TbCargaProcesso>();
		
		ValidacaoNovoAgendamento validacaoNovoAgendamento = new ValidacaoNovoAgendamento(persistPatentes, persistMarcas);
		
		try {
			
			if (this.tipoAgendamento.equals(MANUAL)) {
				
				if (validacaoNovoAgendamento.agendamentoManual(this.hora, this.tipoCarga, this.tipoAgendamento,
														  	   this.cargaRetroativa, this.cargaSemanal, this.dataNovoAgendamento, 
														  	   this.checkTipoBase)) {
		
					Date dataAgendamentoConvertida = AgendamentoUtil.converteDataHora(this.dataNovoAgendamento, this.hora, this.tipoAgendamento);
					String tipoBase = AgendamentoUtil.preparaTipoBase(this.checkTipoBase);
					
					AgendamentoUtil.validaDataAgendamento(this.tipoAgendamento, dataAgendamentoConvertida, this.dataNovoAgendamento);
					
					if (this.tipoCarga.equals(SEMANAL)) {
						
		 				this.agendaCarga.setTpBase(tipoBase);
		 				this.agendaCarga.setDhAgenda(dataAgendamentoConvertida);
						this.agendaCarga.setTpCarga(this.tipoCarga);
						this.agendaCarga.setTpAgenda(this.tipoAgendamento);
						this.agendaCarga.setStAgenda("S");
						this.agendaCarga.setIdLogin(getUserSession());
						
						this.cargaSemanal.setIdAgenda(this.agendaCarga);
						
						this.listaCargaSemanal.add(this.cargaSemanal);
						this.agendaCarga.setTbCargaSemanalList(this.listaCargaSemanal);
						
						service.save(this.agendaCarga);
						
						new FacesMessageUtil(true).mensagemSucesso("agendamento_sucesso");
						
						ProsurUtil.redirecionar("agendamento.jsf");
					} else if (this.tipoCarga.equals(RETROATIVA)) {
						
						this.agendaCarga.setDhAgenda(dataAgendamentoConvertida);
						this.agendaCarga.setTpCarga(this.tipoCarga);
						this.agendaCarga.setTpAgenda(this.tipoAgendamento);
						this.agendaCarga.setStAgenda("S");
						this.agendaCarga.setIdLogin(getUserSession());
						
						this.cargaRetroativa.setIdAgenda(this.agendaCarga);
						
						this.listaCargaRetroativa.add(this.cargaRetroativa);

						this.agendaCarga.setTbCargaRetroativaList(this.listaCargaRetroativa);
						
						service.save(this.agendaCarga);
						
						new FacesMessageUtil(true).mensagemSucesso("agendamento_sucesso");
						
						ProsurUtil.redirecionar("agendamento.jsf");
					}else if (this.tipoCarga.equals(PROCESSO)) {
						
						this.agendaCarga.setDhAgenda(dataAgendamentoConvertida);
						this.agendaCarga.setTpCarga(this.tipoCarga);
						this.agendaCarga.setTpAgenda(this.tipoAgendamento);
						this.agendaCarga.setStAgenda("S");
						this.agendaCarga.setTpBase(tipoProcesso);
						this.agendaCarga.setIdLogin(getUserSession());
						
						for (String processo : listProcs) {

							String semVirgula = processo.replace(",", "");
							String semPonto = semVirgula.replace(".", "");
							String semPontoVirgula = semPonto.replace(";", "");

							if ((semPontoVirgula != null) & (semPontoVirgula.length() > 5)) {
								
								cargaProcesso = new TbCargaProcesso();
								
								cargaProcesso.setNumProcesso(semPontoVirgula);
								
								this.listaCargaProcesso.add(cargaProcesso);
								
								this.cargaProcesso.setIdAgenda(this.agendaCarga);
							}
						}

						this.agendaCarga.setTbCargaProcessoList(this.listaCargaProcesso);
						
						
						service.save(this.agendaCarga);
						
						new FacesMessageUtil(true).mensagemSucesso("agendamento_sucesso");
						
						ProsurUtil.redirecionar("agendamento.jsf");
					}
				}
			} else if (this.tipoAgendamento.equals(AUTOMATICO)) {
				
				if (validacaoNovoAgendamento.agendamentoAutomatico(this.hora, this.tipoCarga, this.tipoAgendamento, this.dataNovoAgendamento)) {
					
					Date dataAgendamentoConvertida = AgendamentoUtil.converteDataHora(this.dataNovoAgendamento, this.hora, this.tipoAgendamento);
					
					AgendamentoUtil.validaDataAgendamento(this.tipoAgendamento, dataAgendamentoConvertida, this.dataNovoAgendamento);
					
					if (this.tipoCarga.equals(SEMANAL)) {
						
						this.agendaCarga.setDhAgenda(dataAgendamentoConvertida);
						this.agendaCarga.setTpCarga(this.tipoCarga);
						this.agendaCarga.setTpAgenda(this.tipoAgendamento);
						this.agendaCarga.setStAgenda("S");
						this.agendaCarga.setIdLogin(getUserSession());
						
						this.cargaSemanal.setIdAgenda(this.agendaCarga);
						
						this.listaCargaSemanal.add(this.cargaSemanal);
						
						this.agendaCarga.setTbCargaSemanalList(this.listaCargaSemanal);
						
						service.save(this.agendaCarga);
						
						new FacesMessageUtil(true).mensagemSucesso("agendamento_sucesso");
						
						ProsurUtil.redirecionar("agendamento.jsf");
					} else if (this.tipoCarga.equals(RETROATIVA)) {
						
						this.agendaCarga.setDhAgenda(dataAgendamentoConvertida);
						this.agendaCarga.setTpCarga(this.tipoCarga);
						this.agendaCarga.setTpAgenda(this.tipoAgendamento);
						this.agendaCarga.setStAgenda("S");
						this.agendaCarga.setIdLogin(getUserSession());
						
						this.cargaRetroativa.setIdAgenda(this.agendaCarga);
						
						this.listaCargaRetroativa.add(this.cargaRetroativa);
						
						this.agendaCarga.setTbCargaRetroativaList(this.listaCargaRetroativa);
						
						service.save(this.agendaCarga);
						
						new FacesMessageUtil(true).mensagemSucesso("agendamento_sucesso");
						
						ProsurUtil.redirecionar("agendamento.jsf");
					}
				}
			}
			
		} catch(ErroProsur e){
			
			new FacesMessageUtil().mensagemErro(e.getMensagemUsuario());
			e.printStackTrace();
		} catch (Exception e) {
			
			new FacesMessageUtil().mensagemErro("erro_sistema");
			e.printStackTrace();
		}
	}

	public void atualizarAgendamento(){
		
		this.listaCargaSemanal = new ArrayList<TbCargaSemanal>();
		this.listaCargaRetroativa = new ArrayList<TbCargaRetroativa>();
		String tipoAgendamento = this.dtoAgendaCarga.getTipoAgendamento();
		
		ValidacaoAtualizarAgendamento validacaoAtualizarAgendamento = new ValidacaoAtualizarAgendamento(persistPatentes, persistMarcas);
		
		try {
		
			if (tipoAgendamento.equals(MANUAL)) {
				
				this.dataNovoAgendamento = ProsurUtil.convertDateToString(this.calendarDataNovoAgendamento);
				
				verificarExistenciaRpiETipoBase();
				
				if (validacaoAtualizarAgendamento.agendamentoManual(this.statusAgenda, this.dataNovoAgendamento, this.hora, this.dtoAgendaCarga)) {
					
					Date dataAgendamentoConvertida = AgendamentoUtil.converteDataHora(this.dataNovoAgendamento, this.hora, this.dtoAgendaCarga);										
					String tipoBase = AgendamentoUtil.preparaTipoBase(this.checkTipoBase);
					
					AgendamentoUtil.validaDataAgendamento(tipoAgendamento, dataAgendamentoConvertida, this.dataNovoAgendamento);
					
					if (this.dtoAgendaCarga.getTipoCarga().equals(SEMANAL)) {
						
						String rpi = this.cargaSemanal.getNoRpi();
						
						mapParameters.put("idAgenda", this.dtoAgendaCarga.getIdAgendaCarga());
						this.agendaCarga = (TbAgendaCarga)service.findOneResult("TbAgendaCarga.findByIdAgendaCarga", mapParameters);
						this.agendaCarga.setTpBase(tipoBase);
						this.cargaSemanal = this.agendaCarga.getTbCargaSemanalList().get(0);
						this.cargaSemanal.setIdAgenda(this.agendaCarga);
						this.cargaSemanal.setNoRpi(rpi);
						
		 				this.listaCargaSemanal.add(this.cargaSemanal);
		 				
		 				if (this.statusAgenda != null) {
		 					
							this.agendaCarga.setStAgenda(this.statusAgenda.toUpperCase());
						}
		 				
						this.agendaCarga.setDhAgenda(dataAgendamentoConvertida);
										
						this.agendaCarga.setTbCargaSemanalList(this.listaCargaSemanal);
						
						service.update(this.agendaCarga);
						
						new FacesMessageUtil(true).mensagemSucesso("agendamento_alterado_sucesso");						
						ProsurUtil.redirecionar("agendamento.jsf");
					} else if (this.dtoAgendaCarga.getTipoCarga().equals(RETROATIVA)) {
						
						String qtMarcas = this.cargaRetroativa.getQtMarcas();
						String qtDesenho = this.cargaRetroativa.getQtDi();
						String qtPatente = this.cargaRetroativa.getQtPatentes();
						
						mapParameters.put("idAgenda", this.dtoAgendaCarga.getIdAgendaCarga());
						this.agendaCarga = (TbAgendaCarga)service.findOneResult("TbAgendaCarga.findByIdAgendaCarga", mapParameters);					
						this.cargaRetroativa = this.agendaCarga.getTbCargaRetroativaList().get(0);
						
						Date dataAgendamento = AgendamentoUtil.converteDataHora(this.dataNovoAgendamento, this.hora, this.dtoAgendaCarga);
						this.cargaRetroativa.setQtMarcas(qtMarcas);
						this.cargaRetroativa.setQtDi(qtDesenho);
						this.cargaRetroativa.setQtPatentes(qtPatente);
						
						this.listaCargaRetroativa.add(this.cargaRetroativa);
						
						this.agendaCarga.setDhAgenda(dataAgendamento);
						
						if (this.statusAgenda != null) {
		 					
							this.agendaCarga.setStAgenda(this.statusAgenda);
						}
						
						this.agendaCarga.setTbCargaRetroativaList(this.listaCargaRetroativa);
						
						service.update(this.agendaCarga);
						
						new FacesMessageUtil(true).mensagemSucesso("agendamento_alterado_sucesso");
						ProsurUtil.redirecionar("agendamento.jsf");
						
					} else if (this.dtoAgendaCarga.getTipoCarga().equals(PROCESSO)) {
						
						mapParameters.put("idAgenda", this.dtoAgendaCarga.getIdAgendaCarga());
						this.agendaCarga = (TbAgendaCarga)service.findOneResult("TbAgendaCarga.findByIdAgendaCarga", mapParameters);					
						List<TbCargaProcesso> listProcessos = this.agendaCarga.getTbCargaProcessoList();
						
						Date dataAgendamento = AgendamentoUtil.converteDataHora(this.dataNovoAgendamento, this.hora, this.dtoAgendaCarga);
						this.agendaCarga.setIdLogin(getUserSession());
						this.agendaCarga.setDhAgenda(dataAgendamento);
						
						if (this.statusAgenda != null) {
		 					
							this.agendaCarga.setStAgenda(this.statusAgenda);
						}
						

						if (listProcessos.size() > listProcsDto.size()) {
							
							this.agendaCarga.setTbCargaProcessoList(null);
							service.deleteProcessoTodos(this.agendaCarga.getIdAgenda()); 
							
							List<TbCargaProcesso> listProcessosAux = new ArrayList<TbCargaProcesso>();
							
							for (String processo : listProcsDto) {

								String semVirgula = processo.replace(",", "");
								String semPonto = semVirgula.replace(".", "");
								String semPontoVirgula = semPonto.replace(";", "");

								if ((semPontoVirgula != null) & (semPontoVirgula.length() > 5)) {
										
										cargaProcesso = new TbCargaProcesso();
										
										cargaProcesso.setNumProcesso(semPontoVirgula);
										
										listProcessosAux.add(cargaProcesso);
										
										cargaProcesso.setIdAgenda(this.agendaCarga);
								
								}
							}
							
							this.agendaCarga.setTbCargaProcessoList(listProcessosAux);
					
						}else{
						
						for (String processo : listProcsDto) {

							String semVirgula = processo.replace(",", "");
							String semPonto = semVirgula.replace(".", "");
							String semPontoVirgula = semPonto.replace(";", "");

							if ((semPontoVirgula != null) & (semPontoVirgula.length() > 5)) {
								
								if (verificaProc(listProcessos, semPontoVirgula)) {
									
									cargaProcesso = new TbCargaProcesso();
									
									cargaProcesso.setNumProcesso(semPontoVirgula);
									
									listProcessos.add(cargaProcesso);
									
									cargaProcesso.setIdAgenda(this.agendaCarga);
									
								}
								
							}
						}

						this.agendaCarga.setTbCargaProcessoList(listProcessos);
						}
						
						service.update(this.agendaCarga);
						
						new FacesMessageUtil(true).mensagemSucesso("agendamento_alterado_sucesso");
						ProsurUtil.redirecionar("agendamento.jsf");
					}
				}
			}
			else {
				
				if (validacaoAtualizarAgendamento.agendamentoAutomatico(this.statusAgenda, this.dataNovoAgendamento, this.hora)) {
					
					Date dataAgendamentoConvertida = AgendamentoUtil.converteDataHora(this.dataNovoAgendamento, this.hora, this.dtoAgendaCarga);
					
					AgendamentoUtil.validaDataAgendamento(tipoAgendamento, dataAgendamentoConvertida, this.dataNovoAgendamento);
					
					mapParameters.put("idAgenda", this.dtoAgendaCarga.getIdAgendaCarga());
					this.agendaCarga = (TbAgendaCarga)service.findOneResult("TbAgendaCarga.findByIdAgendaCarga", mapParameters);
					
					if (this.statusAgenda != null) {
					
						this.agendaCarga.setStAgenda(this.statusAgenda);
					}
					this.agendaCarga.setDhAgenda(dataAgendamentoConvertida);
					
					service.update(this.agendaCarga);
					
					new FacesMessageUtil(true).mensagemSucesso("agendamento_alterado_sucesso");
					ProsurUtil.redirecionar("agendamento.jsf");
				}
			}
		} catch(ErroProsur e){
			
			new FacesMessageUtil().mensagemErro(e.getMensagemUsuario());
			e.printStackTrace();
		} catch (Exception e) {
			
			new FacesMessageUtil().mensagemErro("erro_sistema");
			e.printStackTrace();
		}
	}

	private void verificarExistenciaRpiETipoBase() {
		
		if (this.cargaSemanal.getIdAgenda() != null) {
		
			String numeroRpi = this.cargaSemanal.getNoRpi();
			String tipoBase = this.checkTipoBase.length > 0 ? AgendamentoUtil.preparaTipoBase(this.checkTipoBase) : "";
			
			if (numeroRpi != null) {
				
				this.dtoAgendaCarga.setNumRpi(numeroRpi);
			}
			
			if (tipoBase != null) {
				
				this.dtoAgendaCarga.setTipoBase(tipoBase);
			}
		}
	}
	
	public void deleteAgendaCarga(){
		
		try {
			
			mapParameters.put("idAgenda", this.dtoAgendaCarga.getIdAgendaCarga());
			this.agendaCarga = (TbAgendaCarga)service.findOneResult("TbAgendaCarga.findByIdAgendaCarga", mapParameters);
			
			service.deleteAgendaCarga(this.agendaCarga);
			
			new FacesMessageUtil(true).mensagemSucesso("agendamento_excluido_sucesso");
			ProsurUtil.redirecionar("agendamento.jsf");
		} catch (Exception e) {
			
			new FacesMessageUtil().mensagemErro("erro_sistema");
		}
	}

	public void preparaAtualizacao(){
		
		String tipoAgendamento;
		String tipoCarga;
		
		try {
			
			if (!this.isReset) {
				
				if (this.dtoAgendaCarga.getIdAgendaCarga() != null) {
					
					tipoAgendamento = this.dtoAgendaCarga.getTipoAgendamento();
					tipoCarga = this.dtoAgendaCarga.getTipoCarga();
					
					if (AUTOMATICO.equals(tipoAgendamento)) {
						
						this.setValoresViewEManagedBean(this.dtoAgendaCarga.getDataHoraAgenda(), this.dtoAgendaCarga.getStatusAgenda(), tipoAgendamento);
					} else if (MANUAL.equals(tipoAgendamento)) {
						
						if (SEMANAL.equals(tipoCarga)) {
							
							this.setValoresViewEManagedBean(this.dtoAgendaCarga.getDataHoraAgenda(), this.dtoAgendaCarga.getStatusAgenda(), tipoAgendamento);
							this.cargaSemanal.setNoRpi(this.dtoAgendaCarga.getNumRpi());
							this.setCheckTipoBase(ProsurUtil.separaTipoBase(this.dtoAgendaCarga.getTipoBase()));
						} else if (RETROATIVA.equals(tipoCarga)) {

							this.setValoresViewEManagedBean(this.dtoAgendaCarga.getDataHoraAgenda(), this.dtoAgendaCarga.getStatusAgenda(), tipoAgendamento);
							
							this.cargaRetroativa.setQtMarcas(this.dtoAgendaCarga.getQtdMarcas());			
							this.cargaRetroativa.setQtPatentes(this.dtoAgendaCarga.getQtdPatente());			
							this.cargaRetroativa.setQtDi(this.dtoAgendaCarga.getQtdDesenho());
							
						} else if (PROCESSO.equals(tipoCarga)) {

							this.setValoresViewEManagedBean(this.dtoAgendaCarga.getDataHoraAgenda(), this.dtoAgendaCarga.getStatusAgenda(), tipoAgendamento);
							
							this.cargaRetroativa.setQtMarcas(this.dtoAgendaCarga.getQtdMarcas());			
							this.cargaRetroativa.setQtPatentes(this.dtoAgendaCarga.getQtdPatente());
							
							if (this.listProcsDto == null & onSelected == null) 
								recuperaProcessos(this.dtoAgendaCarga.getIdAgendaCarga().toString());
							
							this.setTipoProcesso(this.dtoAgendaCarga.getTipoBase());
						}
					}
				}
			}
		} catch (Exception e) {

			new FacesMessageUtil().mensagemErro("erro_sistema");			
			e.printStackTrace();
		}
	}
	
	/**
	 * Seta os valores da view e managed bean
	 * para serem atualizados
	 * @param dataAgendamento
	 * @param status
	 * @param tipoAgendamento
	 */
	public void setValoresViewEManagedBean(Date dataAgendamento, String status, String tipoAgendamento){
		
		try {
		
			if (AUTOMATICO.equals(tipoAgendamento)) {
				
				this.setDataNovoAgendamento(ProsurUtil.extrairDiaSemana(this.dtoAgendaCarga.getDataHoraAgenda()));
			} else if (MANUAL.equals(tipoAgendamento)) {
			
				String data = ProsurUtil.extrairData(dataAgendamento);
				this.setCalendarDataNovoAgendamento(ProsurUtil.convertStringToCalendar(data).getTime()); //seta para view
			}
			
			this.statusAgenda = status;
			this.setHora(ProsurUtil.extrairHora(dataAgendamento));
		} catch (ErroProsur e) {

			new FacesMessageUtil().mensagemErro(e.getMensagemUsuario());			
			e.printStackTrace();
		}
	}
	
	public void preparaNovoAgendamento() throws ErroProsur{
		
		this.tipoAgendamento = "AUTOMATICO";
		
		//reseta o valor das atualizacoes 
		//para nao refletir no novo agendamento
		this.dtoAgendaCarga = new AgendaCarga();
		this.resetValuesNovoAgendamento();
	}
	
	public void calendarNovoAgendamento(SelectEvent event){
		
		if (this.tipoAgendamento.equals(AUTOMATICO)) {
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEEE");
			this.dataNovoAgendamento = dateFormat.format(event.getObject());
		} else if (this.tipoAgendamento.equals(MANUAL)){
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			this.dataNovoAgendamento = dateFormat.format(event.getObject());
		}
	}
	
	public void calendarAtualizarAgendamento(SelectEvent event){
		
		String tipoAgendamento = this.dtoAgendaCarga.getTipoAgendamento();
		
		if (tipoAgendamento.equals(AUTOMATICO)) {
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEEE");
			this.dataNovoAgendamento = dateFormat.format(event.getObject());
		} else if (tipoAgendamento.equals(MANUAL)){
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			this.dataNovoAgendamento = dateFormat.format(event.getObject());
		}
	}
	
	public void recuperaProcessos(String idAgenda){
		
		TbAgendaCarga agenda  = service.retornaAgenda(idAgenda);
		
		List<TbCargaProcesso> processos = agenda.getTbCargaProcessoList();
		
		valor = new ArrayList<String>();
		
		for (TbCargaProcesso proc : processos) {
			
			valor.add(proc.getNumProcesso().toString().trim());
			
			this.setListProcsDto(valor);
		}
		
	}

	public void resetValues() throws ErroProsur{
		
		this.tipoCarga = "";
		this.tipoAgendamento = "";
		this.statusAgenda = "";
		this.dataNovoAgendamento = "";
		this.calendarDataNovoAgendamento = null;
		this.hora = "";
		this.cargaSemanal.setNoRpi("");
		this.cargaRetroativa.setQtMarcas("");
		this.cargaRetroativa.setQtPatentes("");
		this.cargaRetroativa.setQtDi("");
		this.checkTipoBase = null;
		this.listProcs = null;
	}
	
	public void resetValuesNovoAgendamento(){
		
		this.tipoCarga = "";
		this.dataNovoAgendamento = "";
		this.calendarDataNovoAgendamento = null;
		this.hora = "";
		this.cargaSemanal.setNoRpi("");
		this.cargaRetroativa.setQtMarcas("");
		this.cargaRetroativa.setQtPatentes("");
		this.cargaRetroativa.setQtDi("");
		this.checkTipoBase = null;
		this.listProcs = null;
	}
	
	private List<String> selectedOptions;
	
	private List<String> listProcs;
	private List<String> listProcsDto;
    
    public List<String> getSelectedOptions() {
    	
    	if (numProcesso != null) {
			
			listProcs.add(numProcesso);
		}
    	
        return selectedOptions;
    }
    
    
	public void setProcess() throws ErroProsur {

		if (numProcesso != null) {
			
			if (!validaProc(numProcesso)) {
				
				valor.add(numProcesso);

				this.setListProcs(valor);
			}
		}
		numProcesso = "";

	}
	
	public boolean validaProc(String numProc) throws ErroProsur{
		
		validacaoNovoAgendamento = new ValidacaoNovoAgendamento(persistPatentes, persistMarcas);
		
		if ("M".equals(this.tipoProcesso)) 
			 validacaoNovoAgendamento.processoMarcas(numProc);
			
		if ("P".equals(this.tipoProcesso))
			validacaoNovoAgendamento.processoPatente(numProc);
			
		if ("D".equals(this.tipoProcesso)) 
			validacaoNovoAgendamento.processoDesenho(numProc);
			
		
		return validacaoNovoAgendamento.msgErroProcesso;
	}
	
    public void setSelectedOptions(List<String> selectedOptions) {
    	
        this.selectedOptions = selectedOptions;
        
    }
    
    public void validaProc(){
    	
    }
    
   
	public TbAcessoCarga getUserSession() {

		FacesContext facesCtx = FacesContext.getCurrentInstance();

		session = (HttpSession) facesCtx.getExternalContext().getSession(true);
		
		UsuarioProsur user = (UsuarioProsur) session.getAttribute("usuarioProsur");

		return service.buscaUsuarioProsur(user.getUsuario());

	}
  
	public String getTipoProcesso() {
		return tipoProcesso;
	}

	public void setTipoProcesso(String tipoProcesso) {
		this.tipoProcesso = tipoProcesso;
	}

	public String getNumProcesso() {
		return numProcesso;
	}

	public void setNumProcesso(String numProcesso) {
		this.numProcesso = numProcesso;
	}

	public List<String> getListProcs() {
		return listProcs;
	}

	public void setListProcs(List<String> listProcs) {
		this.listProcs = listProcs;
	}

	public List<TbCargaProcesso> getListaCargaProcesso() {
		return listaCargaProcesso;
	}

	public void setListaCargaProcesso(List<TbCargaProcesso> listaCargaProcesso) {
		this.listaCargaProcesso = listaCargaProcesso;
	}
	
	

	public String getOnSelected() {
		
		return onSelected;
	}

	public void setOnSelected(String onSelected) {
		
		if (onSelected != null) {
			
			listProcsDto.remove(onSelected);
		
		}
		this.onSelected = onSelected;
	}
	
    public boolean verificaProc(List<TbCargaProcesso> procs,String proc) {  
        for(TbCargaProcesso p : procs) {  
           if (proc.equals(p.getNumProcesso().trim())) {  
              return false;  
           }  
        }  
        return true;  
     }
    
    public void verificaListaVazia(){
    	
    	if (listProcs != null) {
    	
    		listProcs = new ArrayList<String>();
		}
    	
    }

	public List<String> getListProcsDto() {
		return listProcsDto;
	}

	public void setListProcsDto(List<String> listProcsDto) {
		this.listProcsDto = listProcsDto;
	}
    
  
}
