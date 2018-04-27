package br.gov.inpi.services.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import br.gov.inpi.intercarga.beans.AgendaCarga;
import br.gov.inpi.intercarga.beans.HistoricoCarga;
import br.gov.inpi.intercarga.beans.TbAcessoCarga;
import br.gov.inpi.intercarga.beans.TbAgendaCarga;
import br.gov.inpi.intercarga.beans.TbCargaProcesso;
import br.gov.inpi.intercarga.beans.TbCargaRetroativa;
import br.gov.inpi.intercarga.beans.TbCargaSemanal;
import br.gov.inpi.intercarga.beans.TbHistoricoCarga;
import br.gov.inpi.intercarga.beans.TbHistoricoCargaProcesso;
import br.gov.inpi.mail.EstatisticaCargaAutomatica;
import br.gov.inpi.mail.EstatisticaCargaManual;
import br.gov.inpi.services.EntityInterfaceIntercarga;

@Stateless(name = "EntityIntercargaFacade")
public class EntityFacadeIntercarga implements EntityInterfaceIntercarga {

	@PersistenceContext(unitName = "prosur_intercarga")
	protected EntityManager entityManager;

	private static final Logger LOGGER = Logger.getLogger(EntityFacadeIntercarga.class);
	
	public static final String RETROATIVA = "RETROATIVA";

	public static final String SEMANAL = "SEMANAL";
	
	public static final String AUTOMATICO = "AUTOMATICO";

	public static final String MANUAL = "MANUAL";
	
	public static final String PROCESSO = "PROCESSO";
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void save(Object object) {

		if (object != null) {
			this.entityManager.persist(object);
			this.entityManager.flush();
		}
	}	

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deleteUsuarioProsur(TbAcessoCarga acessoCarga) {
		
		entityManager.remove(entityManager.getReference(TbAcessoCarga.class, acessoCarga.getIdLogin()));
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Object merge(Object object) {
		return this.entityManager.merge(object);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@SuppressWarnings("unchecked")
	public List<TbHistoricoCargaProcesso> listarHistoricoProcessoCarga(TbHistoricoCarga historicoCarga){
		
		List<TbHistoricoCargaProcesso> listaHistoricoProcessoCarga = new ArrayList<>();
		
		try {
			
			Query query = this.entityManager.createNamedQuery("TbHistoricoCargaProcesso.findByIdHistoricoCarga");
			query.setParameter("idHistoricoCarga", historicoCarga);
			
			listaHistoricoProcessoCarga = query.getResultList();
			
			return listaHistoricoProcessoCarga;
		} catch (NoResultException nre) {

			LOGGER.error("Nenhuma carga agendada foi encontrada");
		}
		return listaHistoricoProcessoCarga;
	}
	
	public TbHistoricoCarga getHistoricoCarga(int idHistoricoCarga){
		
		TbHistoricoCarga historicoCarga = new TbHistoricoCarga();
		
		try {
			
			Query query = this.entityManager.createNamedQuery("TbHistoricoCarga.findByIdHistoricoCarga");
			query.setParameter("idHistoricoCarga", idHistoricoCarga);
			
			historicoCarga = (TbHistoricoCarga) query.getSingleResult();
		} catch (NoResultException nre) {

			LOGGER.error("Nenhuma historico foi encontrado");
		}
		return historicoCarga;		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@SuppressWarnings("unchecked")
	public List<AgendaCarga> listaAgendaCarga() {

		AgendaCarga agendaCarga;				
		TbCargaSemanal tbCargaSemanal;
		TbCargaRetroativa tbCargaRetroativa;
		TbCargaProcesso tbCargaProcesso;
		TbAgendaCarga tbAgendaCarga = new TbAgendaCarga();
		
		List<AgendaCarga> listaAgendaCarga = new ArrayList<AgendaCarga>();
		List<TbAgendaCarga> listaAgenda = new ArrayList<TbAgendaCarga>();
		
		try {
			
			Query query = this.entityManager.createNamedQuery("TbAgendaCarga.findAll");
			query.setParameter("automatico", "AUTOMATICO");
			query.setParameter("manual", "MANUAL");
			query.setParameter("s", "S");
			query.setParameter("n", "N");
			
			listaAgenda = query.getResultList();
			
			for (int i = 0; i < listaAgenda.size(); i++) {
				
				agendaCarga = new AgendaCarga();
				tbCargaSemanal = new TbCargaSemanal();
				tbCargaRetroativa = new TbCargaRetroativa();
				tbCargaProcesso = new TbCargaProcesso();
				tbAgendaCarga = listaAgenda.get(i);
				String qtdProcessos = null;
				
				if (tbAgendaCarga.getTbCargaSemanalList().size() > 0) {
					
					tbCargaSemanal = tbAgendaCarga.getTbCargaSemanalList().get(0); 
					tbCargaRetroativa = null;
				} else if (tbAgendaCarga.getTbCargaRetroativaList().size() > 0) {
					
					tbCargaRetroativa = tbAgendaCarga.getTbCargaRetroativaList().get(0);
					tbCargaSemanal = null;
				} else if (tbAgendaCarga.getTbCargaProcessoList().size() > 0) {
					
					tbCargaProcesso = tbAgendaCarga.getTbCargaProcessoList().get(0);
					qtdProcessos = Integer.toString(tbAgendaCarga.getTbCargaProcessoList().size());
					tbCargaSemanal = null;
					tbCargaRetroativa = null;
				}
				
				agendaCarga.setTipoCarga(tbAgendaCarga.getTpCarga());
				agendaCarga.setTipoAgendamento(tbAgendaCarga.getTpAgenda());
				agendaCarga.setDataHoraAgenda(tbAgendaCarga.getDhAgenda());
				agendaCarga.setStatusAgenda(tbAgendaCarga.getStAgenda());
				agendaCarga.setIdAgendaCarga(tbAgendaCarga.getIdAgenda());
				
				if (tbCargaSemanal != null) {
					
					agendaCarga.setNumRpi(tbCargaSemanal.getNoRpi());
					agendaCarga.setIdTipoCarga(tbCargaSemanal.getIdCargaSemanal());
					agendaCarga.setTipoBase(tbAgendaCarga.getTpBase());
				} else if (tbCargaRetroativa != null) {
					
					agendaCarga.setQtdMarcas(tbCargaRetroativa.getQtMarcas());
					agendaCarga.setQtdPatente(tbCargaRetroativa.getQtPatentes());
					agendaCarga.setQtdDesenho(tbCargaRetroativa.getQtDi());
					agendaCarga.setIdTipoCarga(tbCargaRetroativa.getIdCargaRetroativa());
				} else {
					
					agendaCarga.setQtdProcesso(qtdProcessos);
					agendaCarga.setTipoBase(tbAgendaCarga.getTpBase());
					
				}
				
				listaAgendaCarga.add(agendaCarga);
			}
			
		} catch (NoResultException nre) {

			LOGGER.error("Nenhuma carga agendada foi encontrada");
		}
		return listaAgendaCarga;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@SuppressWarnings("unchecked")
	public List<AgendaCarga> listarAgendamentosAtivos(){
		
		AgendaCarga agendaCarga;
		TbCargaSemanal tbCargaSemanal;
		TbCargaRetroativa tbCargaRetroativa;
		TbAgendaCarga tbAgendaCarga = new TbAgendaCarga();
		
		List<AgendaCarga> listaAgendaCarga = new ArrayList<AgendaCarga>();
		List<TbAgendaCarga> listaAgenda = new ArrayList<TbAgendaCarga>();
		
		try {
			
			Query query = this.entityManager.createNamedQuery("TbAgendaCarga.findAtivos");
			query.setParameter("stAgenda", "S");
			
			listaAgenda = query.getResultList();
			
			for (int i = 0; i < listaAgenda.size(); i++) {
				
				agendaCarga = new AgendaCarga();
				tbCargaSemanal = new TbCargaSemanal();
				tbCargaRetroativa = new TbCargaRetroativa();
				
				tbAgendaCarga = listaAgenda.get(i);
		
				if (tbAgendaCarga.getTbCargaSemanalList().size() > 0) {
					
					tbCargaSemanal = tbAgendaCarga.getTbCargaSemanalList().get(0); 
					tbCargaRetroativa = null;
				} else if (tbAgendaCarga.getTbCargaRetroativaList().size() > 0) {
					
					tbCargaRetroativa = tbAgendaCarga.getTbCargaRetroativaList().get(0);
					tbCargaSemanal = null;
				}
				
				agendaCarga.setIdAgendaCarga(tbAgendaCarga.getIdAgenda());
				agendaCarga.setTipoCarga(tbAgendaCarga.getTpCarga());
				agendaCarga.setTipoAgendamento(tbAgendaCarga.getTpAgenda());
				agendaCarga.setDataHoraAgenda(tbAgendaCarga.getDhAgenda());
				agendaCarga.setStatusAgenda(tbAgendaCarga.getStAgenda());
				agendaCarga.setTipoBase(tbAgendaCarga.getTpBase());
				agendaCarga.setNumRpi(tbCargaSemanal != null ? tbCargaSemanal.getNoRpi() : null);
				agendaCarga.setQtdMarcas(tbCargaRetroativa != null ? tbCargaRetroativa.getQtMarcas() : null);
				agendaCarga.setQtdDesenho(tbCargaRetroativa != null ? tbCargaRetroativa.getQtDi() : null);
				agendaCarga.setQtdPatente(tbCargaRetroativa != null ? tbCargaRetroativa.getQtPatentes() : null);
				
				
				listaAgendaCarga.add(agendaCarga);
			}
		} catch (NoResultException nre) {

			LOGGER.error("Nenhuma carga agendada foi encontrada");
		}
		
		return listaAgendaCarga;
	}
	
	/**
	 * retorna o proximo agendamento para execucao
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public AgendaCarga retornarProximoAgendamento(){
		
		AgendaCarga proximoAgendamento = new AgendaCarga();
		List<AgendaCarga> listaAgendaCarga = new ArrayList<>();
		
		try {
			
			listaAgendaCarga = this.listarAgendamentosAtivos();
			
			if (listaAgendaCarga != null) {
				
				Date dataAtual = new Date();
				
				for (AgendaCarga agendaCarga : listaAgendaCarga) {
					
					if (agendaCarga.getTipoAgendamento().equals(AUTOMATICO)) {
						
						if (agendaCarga.getTipoCarga().equals(SEMANAL)) {
							
							Date dataAgenda = agendaCarga.getDataHoraAgenda();

							if (this.verificaDataAgendamento(dataAtual, dataAgenda, agendaCarga)) {								
								
								proximoAgendamento = agendaCarga;
							}
						} else if (agendaCarga.getTipoCarga().equals(RETROATIVA)) {
							
							Date dataAgenda = agendaCarga.getDataHoraAgenda();
							
							if (this.verificaDataAgendamento(dataAtual, dataAgenda, agendaCarga)) {

								proximoAgendamento = agendaCarga;
							}
						}
						
					} else if (agendaCarga.getTipoAgendamento().equals(MANUAL)) {
						
						if (agendaCarga.getTipoCarga().equals(SEMANAL)) {
							
							Date dataAgenda = agendaCarga.getDataHoraAgenda();

							if (this.verificaDataAgendamento(dataAtual, dataAgenda, agendaCarga)) {
								
								proximoAgendamento = agendaCarga;
							}
						} else if (agendaCarga.getTipoCarga().equals(RETROATIVA)) {
							
							Date dataAgenda = agendaCarga.getDataHoraAgenda();
							
							if (this.verificaDataAgendamento(dataAtual, dataAgenda, agendaCarga)) {

								proximoAgendamento = agendaCarga;
							}
						} else if (agendaCarga.getTipoCarga().equals(PROCESSO)) {
							
							Date dataAgenda = agendaCarga.getDataHoraAgenda();
							
							if (this.verificaDataAgendamento(dataAtual, dataAgenda, agendaCarga)) {

								proximoAgendamento = agendaCarga;
							}
						}
					}
				}
				listaAgendaCarga = null;
			}
		} catch (Exception e) {

			LOGGER.error("Erro: " + e.getMessage());
		}
		
		return proximoAgendamento;
	}
	
	/**
	 * Responsavel por retornar o agendamento que sera executado 
	 */
	public AgendaCarga retornarAgendamentoExecucao(int idAgenda){
		
		TbAgendaCarga tbAgendaCarga = new TbAgendaCarga();
		AgendaCarga agendaCarga = new AgendaCarga();
		
		try {
			
			Query query = this.entityManager.createNamedQuery("TbAgendaCarga.findByIdAgendaCarga");
			query.setParameter("idAgenda", idAgenda);
			
			tbAgendaCarga = (TbAgendaCarga)query.getSingleResult();
			
			if (tbAgendaCarga != null) {
			
				agendaCarga.setIdAgendaCarga(tbAgendaCarga.getIdAgenda());
				agendaCarga.setTipoCarga(tbAgendaCarga.getTpCarga());
				agendaCarga.setTipoBase(tbAgendaCarga.getTpBase());
				agendaCarga.setTipoAgendamento(tbAgendaCarga.getTpAgenda());
				agendaCarga.setDataHoraAgenda(tbAgendaCarga.getDhAgenda());
				agendaCarga.setStatusAgenda(tbAgendaCarga.getStAgenda());
				
				if (tbAgendaCarga.getTbCargaSemanalList().size() > 0) {
					
					agendaCarga.setNumRpi(tbAgendaCarga.getTbCargaSemanalList().get(0).getNoRpi());
				} else if (tbAgendaCarga.getTbCargaRetroativaList().size() > 0) {
					
					agendaCarga.setQtdMarcas(tbAgendaCarga.getTbCargaRetroativaList().get(0).getQtMarcas());
					agendaCarga.setQtdDesenho(tbAgendaCarga.getTbCargaRetroativaList().get(0).getQtDi());
					agendaCarga.setQtdPatente(tbAgendaCarga.getTbCargaRetroativaList().get(0).getQtPatentes());
					
				} else if (tbAgendaCarga.getTbCargaProcessoList().size() > 0) {
					
					agendaCarga.setTipoBase(tbAgendaCarga.getTpBase());
					agendaCarga.setQtdProcesso(String.valueOf(tbAgendaCarga.getTbCargaProcessoList().size()));
				}
			}
		} catch (NoResultException nre) {

			LOGGER.error("Nenhuma carga encontrada");
		}
		
		return agendaCarga;
	}
	
	public boolean verificaDataAgendamento(Date dataAtual, Date dataAgenda, AgendaCarga agendaCarga){
		
		SimpleDateFormat dateFormat = null;
		
		if (agendaCarga.getTipoAgendamento().equals(MANUAL)) {
		
			dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		} else if (agendaCarga.getTipoAgendamento().equals(AUTOMATICO)) {
			
			dateFormat = new SimpleDateFormat("EEEEEE HH:mm");
		}
		
		String strDataAtual = dateFormat.format(dataAtual);
		String strDataAgenda = dateFormat.format(dataAgenda);
		
		return strDataAtual.equals(strDataAgenda);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@SuppressWarnings("unchecked")
	public List<HistoricoCarga> listaHistoricoCarga(){
		
		HistoricoCarga historicoCarga = new HistoricoCarga();
		
		List<HistoricoCarga> listaHistoricoCarga = new ArrayList<HistoricoCarga>();
		
		List<Object[]> listaObjetos = new ArrayList<>();
		
		try {
			
			StringBuilder strBuilder = new StringBuilder();
			
			strBuilder.append("SELECT h.id_historico_carga, h.no_rpi, h.dh_processo, h.dh_inicio_carga, h.dh_fim_carga, h.qt_marcas_proc, ");
			strBuilder.append("h.qt_di_proc, h.qt_patentes_proc, h.qt_marcas_ok, h.qt_marcas_erro, h.qt_di_ok, ");
			strBuilder.append("h.qt_di_erro, h.qt_di_sigilo_proc, h.qt_patente_ok, h.qt_patente_erro, h.qt_patentes_sigilo_proc, ");
			strBuilder.append("a.tp_agenda, a.tp_carga ");
		    strBuilder.append("FROM tb_historico_carga h, tb_agenda_carga a ");
		    strBuilder.append("WHERE a.id_agenda = h.id_agenda ");
		    
			Query query = this.entityManager.createNativeQuery(strBuilder.toString());
			
			@SuppressWarnings("rawtypes")
			List listaRetorno = query.getResultList();
			
			if (listaRetorno != null && !listaRetorno.isEmpty()) {
					
				listaObjetos.addAll(listaRetorno);
				
					for (int i = 0; i < listaObjetos.size(); i++) {
						
						Object[] objeto = listaObjetos.get(i);
						
						historicoCarga = new HistoricoCarga();
						
						historicoCarga.setIdHistoricoCarga((Integer)objeto[0]);
						historicoCarga.setNoRpi((String)objeto[1]);
						historicoCarga.setDhProcesso((Date)objeto[2]);
						historicoCarga.setDhInicioCarga((Date)objeto[3]);
						historicoCarga.setDhFimCarga((Date)objeto[4]);
						historicoCarga.setQtMarcasProc((String)objeto[5]);
						historicoCarga.setQtDiProc((String)objeto[6]);
						historicoCarga.setQtPatentesProc((String)objeto[7]);
						historicoCarga.setQtMarcasOk((String)objeto[8]);
						historicoCarga.setQtMarcasErro((String)objeto[9]);
						historicoCarga.setQtDiOk((String)objeto[10]);
						historicoCarga.setQtDiErro((String)objeto[11]);
						historicoCarga.setQtDiSigiloProc((String)objeto[12]);
						historicoCarga.setQtPatenteOk((String)objeto[13]);
						historicoCarga.setQtPatenteErro((String)objeto[14]);
						historicoCarga.setQtPatentesSigiloProc((String)objeto[15]);
						historicoCarga.setTpAgenda((String)objeto[16]);
						historicoCarga.setTpCarga((String)objeto[17]);
						
						listaHistoricoCarga.add(historicoCarga);
					}
			}
		} catch (NoResultException nre) {
			
			LOGGER.error("Nenhum historico de carga foi encontrado");
		} catch (Exception e) {

			LOGGER.error("Erro: " + e.getMessage());
		}
		
		return listaHistoricoCarga;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@SuppressWarnings("unchecked")
	public List<TbAcessoCarga> listaUsuarioProsur(){
		
		List<TbAcessoCarga> listaAcessoCarga = new ArrayList<TbAcessoCarga>();
		
		try {
			
			listaAcessoCarga = this.entityManager.createNamedQuery("TbAcessoCarga.findAll").getResultList();
		} catch (NoResultException nre) {
			
			LOGGER.error("Nenhum usu�rio foi encontrado");
		}
		
		return listaAcessoCarga;
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public TbAcessoCarga buscaUsuarioProsur(String nmLogin){

		TbAcessoCarga beanAcessoCarga = new TbAcessoCarga();

		try {

			HashMap<String, String> mapParameters = new HashMap<String, String>();

			mapParameters.put("nmLogin", nmLogin);

			beanAcessoCarga = (TbAcessoCarga) findOneResult("TbAcessoCarga.findByNmLogin", mapParameters);

		} catch (NoResultException nre) {

			LOGGER.error("Nenhum usu�rio foi encontrado");
		}

		return beanAcessoCarga;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object findOneResult(String namedQuery, HashMap mapParameters) {

		Object result = null;

		try {
			Query query = entityManager.createNamedQuery(namedQuery);

			if (!mapParameters.isEmpty()) {
				Set<String> chaves = mapParameters.keySet();
				for (String chave : chaves) {
					if (chave != null)
						query.setParameter(chave, mapParameters.get(chave));
				}
			}

			result = (Object) query.getSingleResult();

		} catch (NoResultException e) {

		}
		return result;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deleteAgendaCarga(TbAgendaCarga agendaCarga) {	
		
		this.entityManager.remove(entityManager.getReference(TbAgendaCarga.class, agendaCarga.getIdAgenda()));
	}
		
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void update(Object object) {

		merge(object);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<String> retornarQtdCargaMarcasRetro(){
		
		String qtdCargaMarcasExecutado = "";
		String qtdCargaMarcasExecutar = "";
		
		List<String> qtdCargaMarcasRetro = new ArrayList<>();
		
		try {
			
			Query query1 = this.entityManager.createNamedQuery("TbCargaRetroativaStatus.findQtVolExecutadoMrc");
			qtdCargaMarcasExecutado = query1.setMaxResults(1).getSingleResult().toString();
			
			Query query2 = this.entityManager.createNamedQuery("TbCargaRetroativaStatus.findQtVolExecutarMrc");
			qtdCargaMarcasExecutar = query2.setMaxResults(1).getSingleResult().toString();
			
			if (qtdCargaMarcasExecutado != null && qtdCargaMarcasExecutar != null) {
				
				qtdCargaMarcasRetro.add(qtdCargaMarcasExecutado);
				qtdCargaMarcasRetro.add(qtdCargaMarcasExecutar);
			}
		} catch (NoResultException nre) {
			
			LOGGER.error("Nenhuma quantidade encontrada para marcas retroativo");
		} catch (Exception e) {

			LOGGER.error("*** ERRO: " + e.getMessage());
		}
		
		return qtdCargaMarcasRetro;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<String> retornarQtdCargaDiRetro(){
		
		String qtdCargaDiExecutado = "";
		String qtdCargaDiExecutar = "";
		
		List<String> qtdCargaDiRetro = new ArrayList<>();
		
		try {
			
			Query query1 = this.entityManager.createNamedQuery("TbCargaRetroativaStatus.findQtVolExecutadoDi");
			qtdCargaDiExecutado = query1.setMaxResults(1).getSingleResult().toString();
			
			Query query2 = this.entityManager.createNamedQuery("TbCargaRetroativaStatus.findQtVolExecutarDi");
			qtdCargaDiExecutar = query2.setMaxResults(1).getSingleResult().toString();
			
			if (qtdCargaDiExecutado != null && qtdCargaDiExecutar != null) {
				
				qtdCargaDiRetro.add(qtdCargaDiExecutado);
				qtdCargaDiRetro.add(qtdCargaDiExecutar);
			}
		} catch (NoResultException nre) {
			
			LOGGER.error("Nenhuma quantidade encontrada para DI retroativo");
		} catch (Exception e) {

			LOGGER.error("*** ERRO: " + e.getMessage());
		}
		
		return qtdCargaDiRetro;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<String> retornarQtdCargaPatenteRetro(){
		
		String qtdCargaPatenteExecutado = "";
		String qtdCargaPatenteExecutar = "";
		
		List<String> qtdCargaPatenteRetro = new ArrayList<>();
		
		try {
			
			Query query1 = this.entityManager.createNamedQuery("TbCargaRetroativaStatus.findQtVolExecutadoPatente");
			qtdCargaPatenteExecutado = query1.setMaxResults(1).getSingleResult().toString();
			
			Query query2 = this.entityManager.createNamedQuery("TbCargaRetroativaStatus.findQtVolExecutarPatente");
			qtdCargaPatenteExecutar = query2.setMaxResults(1).getSingleResult().toString();
			
			if (qtdCargaPatenteExecutado != null && qtdCargaPatenteExecutar != null) {
				
				qtdCargaPatenteRetro.add(qtdCargaPatenteExecutado);
				qtdCargaPatenteRetro.add(qtdCargaPatenteExecutar);
			}
		} catch (NoResultException nre) {
			
			LOGGER.error("Nenhuma quantidade encontrada para Patente retroativo");
		} catch (Exception e) {

			LOGGER.error("*** ERRO: " + e.getMessage());
		}
		
		return qtdCargaPatenteRetro;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void salvarHistoricoCargaAutomatica(AgendaCarga agendaCarga, List<TbHistoricoCargaProcesso> histProcessos){
		
		TbAgendaCarga tbAgendaCarga;
		TbHistoricoCarga historicoCarga;
		
		try {
			
			if (EstatisticaCargaAutomatica.getCargasFinalizadas()) {
				
				LOGGER.info("************* Entrou no historico *************");
				
				HashMap<String, Integer> mapParameters = new HashMap<String, Integer>();
				tbAgendaCarga = new TbAgendaCarga();
				historicoCarga = new TbHistoricoCarga();
				
				mapParameters.put("idAgenda", agendaCarga.getIdAgendaCarga());
				tbAgendaCarga = (TbAgendaCarga)findOneResult("TbAgendaCarga.findByIdAgendaCarga", mapParameters);
				
				//Data execucao e termino
				historicoCarga.setDhInicioCarga(EstatisticaCargaAutomatica.getDataParametro("inicio_carga"));
				historicoCarga.setDhFimCarga(EstatisticaCargaAutomatica.getDataParametro("termino_carga"));
				historicoCarga.setDhInicioMarcaProc(EstatisticaCargaAutomatica.getDataParametro("inicio_processo_marcas"));
				historicoCarga.setDhFimMarcaProc(EstatisticaCargaAutomatica.getDataParametro("termino_processo_marcas"));
				historicoCarga.setDhInicioDiProc(EstatisticaCargaAutomatica.getDataParametro("inicio_processo_desenho"));
				historicoCarga.setDhFimDiProc(EstatisticaCargaAutomatica.getDataParametro("termino_processo_desenho"));
				historicoCarga.setDhInicioPatenteProc(EstatisticaCargaAutomatica.getDataParametro("inicio_processo_patente"));
				historicoCarga.setDhFimPatenteProc(EstatisticaCargaAutomatica.getDataParametro("termino_processo_patente"));
				historicoCarga.setDhProcesso(EstatisticaCargaAutomatica.getDataParametro("inicio_carga"));

				//Qtd erros, processados e rpi
				historicoCarga.setQtMarcasOk(EstatisticaCargaAutomatica.getQuantidadeParametro("quantidade_marcas_sucesso"));
				historicoCarga.setQtMarcasErro(EstatisticaCargaAutomatica.getQuantidadeParametro("quantidade_marcas_erros"));
				historicoCarga.setQtMarcasProc(EstatisticaCargaAutomatica.getQuantidadeParametro("quantidade_processados_marcas"));
				historicoCarga.setQtDiOk(EstatisticaCargaAutomatica.getQuantidadeParametro("quantidade_desenho_sucesso"));
				historicoCarga.setQtDiErro(EstatisticaCargaAutomatica.getQuantidadeParametro("quantidade_desenho_erros"));
				historicoCarga.setQtDiProc(EstatisticaCargaAutomatica.getQuantidadeParametro("quantidade_processados_desenho"));
				historicoCarga.setQtDiSigiloProc(EstatisticaCargaAutomatica.getQuantidadeParametro("quantidade_desenho_sigilo"));
				historicoCarga.setQtPatenteOk(EstatisticaCargaAutomatica.getQuantidadeParametro("quantidade_patente_sucesso"));
				historicoCarga.setQtPatenteErro(EstatisticaCargaAutomatica.getQuantidadeParametro("quantidade_patente_erros"));
				historicoCarga.setQtPatentesProc(EstatisticaCargaAutomatica.getQuantidadeParametro("quantidade_processados_patente"));
				historicoCarga.setQtPatentesSigiloProc(EstatisticaCargaAutomatica.getQuantidadeParametro("quantidade_patente_sigilo"));
				historicoCarga.setNoRpi(EstatisticaCargaAutomatica.getRpi("numero_rpi") == null ? null : 
										EstatisticaCargaAutomatica.getRpi("numero_rpi"));
				
				historicoCarga.setIdAgenda(tbAgendaCarga);				

					List<TbHistoricoCargaProcesso> histNew = new ArrayList<TbHistoricoCargaProcesso>();
					
					for(TbHistoricoCargaProcesso proc : histProcessos){
						
						proc.setIdHistoricoCarga(historicoCarga);
						
						histNew.add(proc);
					}
					historicoCarga.setTbHistoricoCargaProcessoList(histNew);
				
				
				save(historicoCarga);

				EstatisticaCargaAutomatica.recarregarValores();
			}
		} catch (Exception e) {

			LOGGER.error("*** ERRO ao salvar historico: " + e.getMessage());
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void salvarHistoricoCargaManual(AgendaCarga agendaCarga, List<TbHistoricoCargaProcesso> histProcessos){
		
		TbAgendaCarga tbAgendaCarga;
		TbHistoricoCarga historicoCarga;
		
		try {
			
			if (EstatisticaCargaManual.getCargasFinalizadas()) {
				
				LOGGER.info("************* Entrou no historico *************");
				
				HashMap<String, Integer> mapParameters = new HashMap<String, Integer>();
				
				tbAgendaCarga = new TbAgendaCarga();
				historicoCarga = new TbHistoricoCarga();
				
				mapParameters.put("idAgenda", agendaCarga.getIdAgendaCarga());
				tbAgendaCarga = (TbAgendaCarga)findOneResult("TbAgendaCarga.findByIdAgendaCarga", mapParameters);
				
				//Data execucao e termino
				historicoCarga.setDhInicioCarga(EstatisticaCargaManual.getDataParametro("inicio_carga"));
				historicoCarga.setDhFimCarga(EstatisticaCargaManual.getDataParametro("termino_carga"));
				historicoCarga.setDhInicioMarcaProc
					(EstatisticaCargaManual.getDataParametro("inicio_processo_marcas") == null ? null : EstatisticaCargaManual.getDataParametro("inicio_processo_marcas"));
				historicoCarga.setDhFimMarcaProc
					(EstatisticaCargaManual.getDataParametro("termino_processo_marcas") == null ? null : EstatisticaCargaManual.getDataParametro("termino_processo_marcas"));
				historicoCarga.setDhInicioDiProc
					(EstatisticaCargaManual.getDataParametro("inicio_processo_desenho") == null ? null : EstatisticaCargaManual.getDataParametro("inicio_processo_desenho"));
				historicoCarga.setDhFimDiProc
					(EstatisticaCargaManual.getDataParametro("termino_processo_desenho") == null ? null : EstatisticaCargaManual.getDataParametro("termino_processo_desenho"));
				historicoCarga.setDhInicioPatenteProc
					(EstatisticaCargaManual.getDataParametro("inicio_processo_patente") == null ? null : EstatisticaCargaManual.getDataParametro("inicio_processo_patente"));
				historicoCarga.setDhFimPatenteProc
					(EstatisticaCargaManual.getDataParametro("termino_processo_patente") == null ? null : EstatisticaCargaManual.getDataParametro("termino_processo_patente"));
				historicoCarga.setDhProcesso(EstatisticaCargaManual.getDataParametro("inicio_carga"));

				//Qtd erros, processados e rpi
				historicoCarga.setQtMarcasOk
					(EstatisticaCargaManual.getQuantidadeParametro("quantidade_marcas_sucesso") == "" ? null : EstatisticaCargaManual.getQuantidadeParametro("quantidade_marcas_sucesso"));
				historicoCarga.setQtMarcasErro
					(EstatisticaCargaManual.getQuantidadeParametro("quantidade_marcas_erros") == "" ? null : EstatisticaCargaManual.getQuantidadeParametro("quantidade_marcas_erros"));
				historicoCarga.setQtMarcasProc
					(EstatisticaCargaManual.getQuantidadeParametro("quantidade_processados_marcas") == "" ? null : EstatisticaCargaManual.getQuantidadeParametro("quantidade_processados_marcas"));
				historicoCarga.setQtDiOk
				 	(EstatisticaCargaManual.getQuantidadeParametro("quantidade_desenho_sucesso") == "" ? null : EstatisticaCargaManual.getQuantidadeParametro("quantidade_desenho_sucesso"));
				historicoCarga.setQtDiErro
					(EstatisticaCargaManual.getQuantidadeParametro("quantidade_desenho_erros") == "" ? null : EstatisticaCargaManual.getQuantidadeParametro("quantidade_desenho_erros"));
				historicoCarga.setQtDiProc
					(EstatisticaCargaManual.getQuantidadeParametro("quantidade_processados_desenho") == "" ? null : EstatisticaCargaManual.getQuantidadeParametro("quantidade_processados_desenho"));
				historicoCarga.setQtDiSigiloProc
					(EstatisticaCargaManual.getQuantidadeParametro("quantidade_desenho_sigilo") == "" ? null : EstatisticaCargaManual.getQuantidadeParametro("quantidade_desenho_sigilo"));
				historicoCarga.setQtPatenteOk
					(EstatisticaCargaManual.getQuantidadeParametro("quantidade_patente_sucesso") == "" ? null : EstatisticaCargaManual.getQuantidadeParametro("quantidade_patente_sucesso"));
				historicoCarga.setQtPatenteErro
					(EstatisticaCargaManual.getQuantidadeParametro("quantidade_patente_erros") == "" ? null : EstatisticaCargaManual.getQuantidadeParametro("quantidade_patente_erros"));
				historicoCarga.setQtPatentesProc
					(EstatisticaCargaManual.getQuantidadeParametro("quantidade_processados_patente") == "" ? null : EstatisticaCargaManual.getQuantidadeParametro("quantidade_processados_patente"));
				historicoCarga.setQtPatentesSigiloProc
					(EstatisticaCargaManual.getQuantidadeParametro("quantidade_patente_sigilo") == "" ? null : EstatisticaCargaManual.getQuantidadeParametro("quantidade_patente_sigilo"));
				historicoCarga.setNoRpi
					(EstatisticaCargaManual.getRpi("numero_rpi") == null ? null : EstatisticaCargaManual.getRpi("numero_rpi"));
				
				historicoCarga.setIdAgenda(tbAgendaCarga);
					
					List<TbHistoricoCargaProcesso> histNew = new ArrayList<TbHistoricoCargaProcesso>();
					
					for(TbHistoricoCargaProcesso proc : histProcessos){
						
						proc.setIdHistoricoCarga(historicoCarga);
						
						histNew.add(proc);
					}
					historicoCarga.setTbHistoricoCargaProcessoList(histNew);
				
				save(historicoCarga);

				EstatisticaCargaManual.recarregarValores();
			}
		} catch (Exception e) {

			LOGGER.error("*** ERRO ao salvar historico: " + e.getMessage());
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void atualizarAgendamentoManual(AgendaCarga agendaCarga){
		
		HashMap<String, Integer> mapParameters = new HashMap<String, Integer>();
		TbAgendaCarga tbAgendaCarga;
		
		try {
		
			tbAgendaCarga = new TbAgendaCarga();
			
			if (EstatisticaCargaManual.getCargasFinalizadas()) {
			
				mapParameters.put("idAgenda", agendaCarga.getIdAgendaCarga());
				tbAgendaCarga = (TbAgendaCarga)findOneResult("TbAgendaCarga.findByIdAgendaCarga", mapParameters);
				
				tbAgendaCarga.setStAgenda("P");
				update(tbAgendaCarga); 
				
				LOGGER.info("************* Finalizou o agendamento manual *************");
			}
		} catch (Exception e) {

			LOGGER.error("*** ERRO ao atualizar agendamento: " + e.getMessage());
		}	
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void atualizarAgendamentoAutomatico(AgendaCarga agendaCarga){
		
		HashMap<String, Integer> mapParameters = new HashMap<String, Integer>();
		TbAgendaCarga tbAgendaCarga;
		
		try {
		
			tbAgendaCarga = new TbAgendaCarga();
			
			if (EstatisticaCargaAutomatica.getCargasFinalizadas()) {
			
				mapParameters.put("idAgenda", agendaCarga.getIdAgendaCarga());
				tbAgendaCarga = (TbAgendaCarga)findOneResult("TbAgendaCarga.findByIdAgendaCarga", mapParameters);
				
				tbAgendaCarga.setStAgenda("P");
				update(tbAgendaCarga); 
				
				LOGGER.info("************* Finalizou o agendamento automatico *************");
			}
		} catch (Exception e) {

			LOGGER.error("*** ERRO ao atualizar agendamento: " + e.getMessage());
		}	
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public String recuperaProcessos(String id_Agenda) {
		   
		   TbAgendaCarga agenda = null;
		   List<TbCargaProcesso> pedidos = new ArrayList<TbCargaProcesso>();
		   List<String> list = new ArrayList<String>();
		   String finalQuery = null;
		   Integer idAgenda = Integer.parseInt(id_Agenda);
		   
		   try{
			  
	          Query query = this.entityManager.createNamedQuery("TbAgendaCarga.findByIdAgendaCarga"); 
			  query.setParameter("idAgenda",idAgenda);
			  agenda = (TbAgendaCarga)query.getSingleResult();
					
			   
			   pedidos = agenda.getTbCargaProcessoList();
	
			   for(TbCargaProcesso novo : pedidos){
				   
				   String valorTratado = "'"+novo.getNumProcesso().trim()+"'";
					
					list.add(valorTratado);
				   
			   }
			   
			   String fimvalor = list.toString();
				
			   finalQuery = fimvalor.replace("[", "(").replace("]", ")"); 
			  
		   }catch(NoResultException nre){
			   LOGGER.info("Nenhum pedido encontrado.");
		   }
		   
		   return finalQuery;
	
	}
	
	public TbAgendaCarga retornaAgenda(String id_agenda) {

		TbAgendaCarga agenda = null;
		 Integer idAgenda = Integer.parseInt(id_agenda);

		try {

			Query query = this.entityManager.createNamedQuery("TbAgendaCarga.findByIdAgendaCarga");
			query.setParameter("idAgenda", idAgenda);
			agenda = (TbAgendaCarga) query.getSingleResult();

		} catch (NoResultException nre) {

			LOGGER.info("Nenhum pedido encontrado.");
		}

		return agenda;

	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void deleteProcessoTodos(Integer id) {
		String query = ("DELETE FROM tb_carga_processo WHERE id_agenda =" + id);
		Query q = entityManager.createNativeQuery(query, TbCargaProcesso.class);
		q.executeUpdate();
	}
	
	/*@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deleteProcessoTodos(TbAgendaCarga agenda) {
		
		entityManager.remove(entityManager.getReference(TbCargaProcesso.class, agenda.getIdAgenda()));
	}*/

	
}
