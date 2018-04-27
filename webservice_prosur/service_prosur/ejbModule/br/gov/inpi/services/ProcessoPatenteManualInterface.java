package br.gov.inpi.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.ejb.Local;

import br.gov.inpi.patentes.beans.DesignPatentLocarno;
import br.gov.inpi.patentes.beans.DiClassificacao;
import br.gov.inpi.patentes.beans.DiPedido;
import br.gov.inpi.patentes.beans.PtnClassif;
import br.gov.inpi.patentes.beans.PtnClassifDesc;
import br.gov.inpi.patentes.beans.PtnEscritorio;
import br.gov.inpi.patentes.beans.PtnPaisProsur;
import br.gov.inpi.patentes.beans.PtnPct;
import br.gov.inpi.patentes.beans.PtnPedido;
import br.gov.inpi.patentes.beans.PtnPrioridade;
import br.gov.inpi.patentes.beans.PtnProcessoProsur;
import br.gov.inpi.patentes.beans.PtnResumoPedido;
import br.gov.inpi.patentes.beans.PtnSolicitante;
import br.gov.inpi.patentes.beans.PtnTipoDespacho;

@Local
public interface ProcessoPatenteManualInterface {

public abstract void save(Object object);
	
	
	public abstract void update(Object object);

	
	public abstract void delete(Object object);

	
	public abstract Object undo(Object object);

	
	public abstract Object undo(Class<Object> klass, Object id);
	
	
	@SuppressWarnings("rawtypes")
	public abstract List list(String namedQuery);
	
	
	@SuppressWarnings("rawtypes")
	public abstract List list(String namedQuery, HashMap mapParameters);
	
	
	public abstract Object pesquisarPorCodigoPedido(Object object, Integer codigoPedido);
		
	@SuppressWarnings("rawtypes")
	public abstract List pesquisarTodosObject(String pesquisa);
		
	
	public abstract List<String> pesquisarTodos(String pesquisa);
	
	
	public abstract List<DiPedido> pesquisarPedidosDiPorSemana(String numRpi);
	
	
	public abstract List<DiPedido> pesquisarPedidosDiRetroativa(String quantidadeDi);
	
	
	public abstract List<PtnPedido> pesquisarPedidosDePatentePorSemana(String numRpi);
	
	
	public abstract List<PtnPedido> pesquisarPedidosDePatenteRetroativa(String quantidadePatente);
		
	
	public abstract Object pesquisar(String pesquisa);
		
	
	public abstract String pesquisarPedidoRenumerado(String numeroPedido);
		
	
    public abstract List<PtnPrioridade> pesquisarPrioridades(Integer codigoPedido);
    
    
    public abstract Date pesquisarDataDePublicacaoPT(Integer codigoPedido);
    
    public abstract Date pesquisarDataDePublicacaoDI(Integer codigoPedido);
	
    
	public abstract PtnPedido pesquisarPedidoPatente(Integer codigoPedido);
		
	
	public abstract PtnPct pesquisarPctPedidoPatente(Integer codigoPedido);
		
	
	public abstract List<PtnSolicitante> pesquisarSolicitantePorCodigoPedido(Integer codigoPedido);
	
	
	public abstract PtnEscritorio pesquisarEscritorio(Integer codigoPedido);
	
	
	public abstract PtnTipoDespacho pesquisarTipoDespacho(Integer codigoPedido);
	
	
	public abstract List<PtnTipoDespacho> pesquisarTodosTipoDespachos(Integer codigoPedido);
	
	
	public abstract List<String> pesquisarInventoresPorCodigoDoPedido(Integer codigoPedido);
	
	
	/***
	 * 
	 * Pesquisa os autores de um pedido de Desenho Industrial que n�o estejam
	 * com a condi��o de anonimato. 
	 * 
	 * @param codigoPedido
	 * @return
	 */
	public abstract List<String> pesquisarAutoresPorCodigoDoPedidoDI(Integer codigoPedido);
		
	
	public abstract List<DesignPatentLocarno> pesquisarDescricaoLocarno(Integer codigoPedido);
	
	
	public abstract List<PtnClassif> pesquisarClassificacoesPedido(Integer codigoPedido);
	
	
	public abstract List<DiClassificacao> pesquisarClassificacoesPedidoDI(Integer codigoPedido);
	
	
	public abstract void salvar(Set<PtnProcessoProsur> processos);
	
	public List<PtnPedido> pesquisarPedidosDePatentePorNumeroPedido(String numeroPedido);
	
	
	public Integer verificarCodigoOrginalPedidoDivido(int codigoPedido);
	
	
	public PtnResumoPedido pesquisarResumoDoPedido(long codigoPedido);
	
	
    public DiPedido pesquisarPorNumeroDePedidoDesenhoIndustrial(String numeroPedido);
	
    
	public PtnClassifDesc retornarDescricaoDosProjetosDI(Integer codigoClassificacao);
	
	
	public List<PtnPaisProsur> retornarTodosPaisesProsur();
	
	
	public boolean isPedidoEmSigilo(String numeroPedido);
	
	
	public void salvarPedidosEnviados(List<PtnProcessoProsur> processosProsur);
	
	public String retornarVolumeTotalDiRetroativo();
	
	public String retornarVolumeTotalPatenteRetroativo();

	public boolean verificarRpiExiste(String numeroRpi);

	public List<DiPedido> pesquisarPedidosDiPorProcesso(String idAgenda);
	
	public Boolean verificaProceso(String processo);
	
	public List<PtnPedido> pesquisarProcessoPatente(String quantidadePatente);
	
	public Boolean verificaProcesoPatente(String processo);
	
	public Boolean validaProcesoPatente(String processo);
	
	public Boolean validaProcesoDesenho(String processo);
}
