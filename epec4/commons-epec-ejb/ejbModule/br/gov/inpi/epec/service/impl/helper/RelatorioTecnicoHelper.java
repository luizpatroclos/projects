package br.gov.inpi.epec.service.impl.helper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.gov.inpi.epec.beans.Tbantnaopatentaria;
import br.gov.inpi.epec.beans.Tbantnaopatentariacatrelec;
import br.gov.inpi.epec.beans.Tbantnaopatentariarelec;
import br.gov.inpi.epec.beans.Tbantpatentaria;
import br.gov.inpi.epec.beans.Tbantpatentariacatrelec;
import br.gov.inpi.epec.beans.Tbantpatentariarelec;
import br.gov.inpi.epec.beans.Tbcaraccatrelatorioec;
import br.gov.inpi.epec.beans.Tbcatrelatorioec;
import br.gov.inpi.epec.beans.Tbpatenteec;
import br.gov.inpi.epec.beans.Tbrelatorioec;
import br.gov.inpi.epec.entities.Caracteristica;
import br.gov.inpi.epec.entities.CategoriaRelatorio;
import br.gov.inpi.epec.entities.Patente;
import br.gov.inpi.epec.entities.RelatorioPatente;
import br.gov.inpi.epec.entities.RelatorioTecnico;

public class RelatorioTecnicoHelper {

	public RelatorioTecnico obterRelatorioTecnico(Tbpatenteec tbPatenteec, Tbrelatorioec tbRelatorioec) {

		RelatorioTecnico relatorio = new RelatorioTecnico();
		relatorio.setNumeroPedidoEpoDoc(tbPatenteec.getStrPedNumEpodoc());
		relatorio.setDataPublicacao(tbPatenteec.getDtPublicacao());

		String id = Long.toString(tbRelatorioec.getIdStatus().getIdStatus());
		relatorio.setIdStatusParecer(id);
		relatorio.setNumeroRelatorioTecnico(tbRelatorioec.getStrRelatorio());

		String publico = "0";
		boolean isPublico = tbRelatorioec.getBPublico();
		if (isPublico) {
			publico = "1";
		}
		relatorio.setPublico(publico);

		String familiaId = Long.toString(tbRelatorioec.getIdFamiliaEc().getIdFamiliaEc());
		relatorio.setFamiliaId(familiaId);

		String resumo = tbRelatorioec.getTxResumo();
		if (resumo != null && !"".equals(resumo.trim())) {
			relatorio.setResumoRelatorioTecnico(resumo.trim());
		}

		String conclusao = tbRelatorioec.getTxConclusao();
		if (conclusao != null && !"".equals(conclusao.trim())) {
			relatorio.setConclusao(conclusao.trim());
		}

		List<RelatorioPatente> anterioridades = this.retornarAnterioridades(tbRelatorioec.getTbantpatentariarelecList());
		if (anterioridades != null && !anterioridades.isEmpty()) {
			relatorio.setAnteoridadesPatentarias(anterioridades);
		}

		List<RelatorioPatente> anterioridadesNaoPatentaria = this.retornarAnterioridadesNaoPatentetarias(tbRelatorioec.getTbantnaopatentariarelecList());
		if (anterioridadesNaoPatentaria != null && !anterioridadesNaoPatentaria.isEmpty()) {
			relatorio.setAnteoridadesNaoPatentarias(anterioridadesNaoPatentaria);
		}

		return relatorio;

	}

	public RelatorioTecnico obterRelatorioTecnicoCompleto(Patente patente, Tbrelatorioec tbRelatorioec) {
		RelatorioTecnico relatorio = new RelatorioTecnico();
		relatorio.setIdDocumentoPatente(patente.getIdDocumentoPatente());
		relatorio.setTipoDocumento(patente.getTipoDocumento());
		relatorio.setNumero(patente.getNumero());
		relatorio.setDataDeposito(patente.getDataDeposito());
		relatorio.setDataPublicacao(patente.getDataPublicacao());
		relatorio.setNumeroPedidoOriginal(patente.getNumeroPedidoOriginal());
		relatorio.setPaisPublicacao(patente.getPaisPublicacao());
		relatorio.setNumeroPublicacao(patente.getNumeroPublicacao());
		relatorio.setKindCodePublicacao(patente.getKindCodePublicacao());
		relatorio.setPais(patente.getPais());
		relatorio.setNumeroAplicacao(patente.getNumeroAplicacao());
		relatorio.setKindCode(patente.getKindCode());
		relatorio.setNumeroPedidoEpoDoc(patente.getNumeroPedidoEpoDoc());
		relatorio.setTitulo(patente.getTitulo());
		relatorio.setRevindicacao(patente.getRevindicacao());
		relatorio.setResumo(patente.getResumo());
		relatorio.setProcurador(patente.getProcurador());
		relatorio.setInventores(patente.getInventores());
		relatorio.setDepositantes(patente.getDepositantes());
		relatorio.setClassificacoes(patente.getClassificacoes());
		relatorio.setPrioridades(patente.getPrioridades());

		// ----------------------------------------------------------------
		String nome = tbRelatorioec.getIdUsuario().getTxNome();
		if (nome != null && !"".equals(nome.trim())) {
			relatorio.setAutor(nome.trim());
		}

		String id = Long.toString(tbRelatorioec.getIdStatus().getIdStatus());
		relatorio.setIdStatusParecer(id);
		relatorio.setNumeroRelatorioTecnico(tbRelatorioec.getStrRelatorio());

		String publico = "0";
		boolean isPublico = tbRelatorioec.getBPublico();
		if (isPublico) {
			publico = "1";
		}
		relatorio.setPublico(publico);

		String familiaId = Long.toString(tbRelatorioec.getIdFamiliaEc().getIdFamiliaEc());
		relatorio.setFamiliaId(familiaId);

		String resumo = tbRelatorioec.getTxResumo();
		if (resumo != null && !"".equals(resumo.trim())) {
			// String strippedText=
			// resumo.replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", " ");
			relatorio.setResumoRelatorioTecnico(resumo.trim());
		}
		
		String conclusao = tbRelatorioec.getTxConclusao();
		if (conclusao != null && !"".equals(conclusao.trim())) {
			relatorio.setConclusao(conclusao.trim());
		}

		List<RelatorioPatente> anterioridades = this.retornarAnterioridades(tbRelatorioec.getTbantpatentariarelecList());
		if (anterioridades != null && !anterioridades.isEmpty()) {
			relatorio.setAnteoridadesPatentarias(anterioridades);
		}

		List<RelatorioPatente> anterioridadesNaoPatentaria = this.retornarAnterioridadesNaoPatentetarias(tbRelatorioec.getTbantnaopatentariarelecList());
		if (anterioridadesNaoPatentaria != null && !anterioridadesNaoPatentaria.isEmpty()) {
			relatorio.setAnteoridadesNaoPatentarias(anterioridadesNaoPatentaria);
		}

		return relatorio;

	}

	private List<RelatorioPatente> retornarAnterioridades(List<Tbantpatentariarelec> tbantpatentariarelecList) {

		List<RelatorioPatente> anterioridades = new ArrayList<RelatorioPatente>();

		if (tbantpatentariarelecList != null && !tbantpatentariarelecList.isEmpty()) {

			Iterator<Tbantpatentariarelec> it = tbantpatentariarelecList.iterator();
			while (it.hasNext()) {

				Tbantpatentariarelec tbantPatentariarelec = it.next();

				RelatorioPatente relatorioPatente = new RelatorioPatente();
				relatorioPatente.setNamePrior(tbantPatentariarelec.getStrAntpatentaria());
				relatorioPatente.setDocumentCategory(tbantPatentariarelec.getIdCadtipoanterioridade().getStrOmpi());
				relatorioPatente.setRelevance(tbantPatentariarelec.getIdCadtipoanterioridade().getStrPortugues());
				relatorioPatente.setRelations(tbantPatentariarelec.getTxRelacao());
				relatorioPatente.setRelationsClaims(tbantPatentariarelec.getTxReivindicacao());

				anterioridades.add(relatorioPatente);

			}

		}

		return anterioridades;

	}

	private List<RelatorioPatente> retornarAnterioridadesNaoPatentetarias(List<Tbantnaopatentariarelec> tbantnaopatentariarelecList) {

		List<RelatorioPatente> anterioridades = new ArrayList<RelatorioPatente>();

		if (tbantnaopatentariarelecList != null && !tbantnaopatentariarelecList.isEmpty()) {

			Iterator<Tbantnaopatentariarelec> it = tbantnaopatentariarelecList.iterator();
			while (it.hasNext()) {

				Tbantnaopatentariarelec tbAntNaoPatentariarelec = it.next();

				RelatorioPatente relatorioPatente = new RelatorioPatente();
				relatorioPatente.setNamePrior(tbAntNaoPatentariarelec.getTxTitulo());
				relatorioPatente.setDocumentCategory(tbAntNaoPatentariarelec.getIdCadtipoanterioridade().getStrOmpi());
				relatorioPatente.setRelevance(tbAntNaoPatentariarelec.getIdCadtipoanterioridade().getStrPortugues());
				relatorioPatente.setRelations(tbAntNaoPatentariarelec.getTxRelacao());
				relatorioPatente.setRelationsClaims(tbAntNaoPatentariarelec.getTxReivindicacao());

				anterioridades.add(relatorioPatente);

			}

		}

		return anterioridades;

	}

	/*
	 * public List<CategoriaRelatorio> obterDadosCategoriasDoRelatorio(
	 * List<Tbcatrelatorioec> tbcatrelatorioecList){
	 * 
	 * List<CategoriaRelatorio> categoriasDoRelatorio = new
	 * ArrayList<CategoriaRelatorio>(); if(tbcatrelatorioecList != null &&
	 * !tbcatrelatorioecList.isEmpty()){
	 * 
	 * 
	 * Iterator<Tbcatrelatorioec> it = tbcatrelatorioecList.iterator(); while
	 * (it.hasNext()){
	 * 
	 * Tbcatrelatorioec tbCatrelatorioec = it.next();
	 * 
	 * CategoriaRelatorio categoria = new CategoriaRelatorio();
	 * 
	 * long idFamilia =
	 * tbCatrelatorioec.getIdRelatorioEc().getIdFamiliaEc().getIdFamiliaEc();
	 * if(idFamilia != 0.0){ categoria.setIdFamilia(Long.toString(idFamilia)); }
	 * 
	 * categoria.setNome(tbCatrelatorioec.getIdCategoria().getStrPortugues());
	 * 
	 * long idRelatorio =
	 * tbCatrelatorioec.getIdRelatorioEc().getIdRelatorioEc(); if(idRelatorio !=
	 * 0.0){ categoria.setNumeroRelatorio(Long.toString(idRelatorio)); }
	 * 
	 * categoria.setResumo(tbCatrelatorioec.getTxResumo());
	 * 
	 * System.out.println(" Categoria " + categoria.toString());
	 * 
	 * 
	 * List<RelatorioPatente> anterioridadesPatentariasPorCategoria =
	 * this.retornarAnterioridadesPatentariasPorCategoria
	 * (tbCatrelatorioec.getTbantpatentariacatrelecList());
	 * categoria.setAnterioridadesPatentariasPorCategoria
	 * (anterioridadesPatentariasPorCategoria);
	 * 
	 * List<RelatorioPatente> anterioridadesNaoPatentariasPorCategoria =
	 * this.pesquisarAnterioridadesNaoPatentariasPorCategoria
	 * (tbCatrelatorioec.getTbantnaopatentariacatrelecList());
	 * categoria.setAnterioridadesNaoPatentariasPorCategoria
	 * (anterioridadesNaoPatentariasPorCategoria);
	 * 
	 * List<Caracteristica> caracteristicas =
	 * this.obterCaracteristicasRelatorio(
	 * tbCatrelatorioec.getTbcaraccatrelatorioecList());
	 * categoria.setCaracteristica(caracteristicas);
	 * 
	 * 
	 * }
	 * 
	 * }
	 * 
	 * return categoriasDoRelatorio;
	 * 
	 * }
	 * 
	 * 
	 * 
	 * private List<RelatorioPatente>
	 * retornarAnterioridadesPatentariasPorCategoria
	 * (List<Tbantpatentariacatrelec> tbantpatentariacatrelecList){
	 * 
	 * List<RelatorioPatente> anterioridades = new
	 * ArrayList<RelatorioPatente>(); while(anterioridades != null &&
	 * !anterioridades.isEmpty()){
	 * 
	 * Iterator<Tbantpatentariacatrelec> it =
	 * tbantpatentariacatrelecList.iterator(); while(it.hasNext()){
	 * 
	 * Tbantpatentariacatrelec tbantpatentariacatrelec = it.next();
	 * 
	 * RelatorioPatente relatorioPatente = new RelatorioPatente();
	 * relatorioPatente
	 * .setNamePrior(tbantpatentariacatrelec.getIdCatrelatorioec(
	 * ).getIdRelatorioEc().getStrRelatorio());
	 * relatorioPatente.setDocumentCategory
	 * (tbantpatentariacatrelec.getIdCadtipoanterioridade().getStrOmpi());
	 * relatorioPatente
	 * .setRelevance(tbantpatentariacatrelec.getIdCadtipoanterioridade
	 * ().getStrPortugues());
	 * relatorioPatente.setRelations(tbantpatentariacatrelec.getTxRelacao());
	 * relatorioPatente
	 * .setRelationsClaims(tbantpatentariacatrelec.getTxReivindicacao());
	 * 
	 * }
	 * 
	 * } return anterioridades;
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * private List<RelatorioPatente>
	 * pesquisarAnterioridadesNaoPatentariasPorCategoria
	 * (List<Tbantnaopatentariacatrelec> tbantnaopatentariacatrelecList){
	 * 
	 * List<RelatorioPatente> anterioridadesNao = new
	 * ArrayList<RelatorioPatente>(); while(tbantnaopatentariacatrelecList !=
	 * null && !tbantnaopatentariacatrelecList.isEmpty()){
	 * 
	 * 
	 * Iterator<Tbantnaopatentariacatrelec> it =
	 * tbantnaopatentariacatrelecList.iterator(); while(it.hasNext()){
	 * 
	 * Tbantnaopatentariacatrelec tbAntnaopatentariacatrelec = it.next();
	 * 
	 * RelatorioPatente relatorioPatente = new RelatorioPatente();
	 * relatorioPatente
	 * .setNamePrior(tbAntnaopatentariacatrelec.getIdCatrelatorioec
	 * ().getIdRelatorioEc().getStrRelatorio());
	 * relatorioPatente.setDocumentCategory
	 * (tbAntnaopatentariacatrelec.getIdCadtipoanterioridade().getStrOmpi());
	 * relatorioPatente
	 * .setRelevance(tbAntnaopatentariacatrelec.getIdCadtipoanterioridade
	 * ().getStrPortugues());
	 * relatorioPatente.setRelations(tbAntnaopatentariacatrelec.getTxRelacao());
	 * relatorioPatente
	 * .setRelationsClaims(tbAntnaopatentariacatrelec.getTxReivindicacao());
	 * 
	 * }
	 * 
	 * 
	 * } return anterioridadesNao;
	 * 
	 * }
	 */

	/*
	 * private List<Caracteristica>
	 * obterCaracteristicasRelatorio(List<Tbcaraccatrelatorioec>
	 * tbcaraccatrelatorioecList){
	 * 
	 * List<Caracteristica> caracteristicas = new ArrayList<Caracteristica>();
	 * if(tbcaraccatrelatorioecList != null &&
	 * !tbcaraccatrelatorioecList.isEmpty()){
	 * 
	 * Iterator<Tbcaraccatrelatorioec> it =
	 * tbcaraccatrelatorioecList.iterator(); while(it.hasNext()){
	 * 
	 * Tbcaraccatrelatorioec tbCaraccatrelatorioec = it.next();
	 * 
	 * Caracteristica caracteristica = new Caracteristica(); long idFamilia =
	 * tbCaraccatrelatorioec
	 * .getIdCatrelatorioec().getIdRelatorioEc().getIdFamiliaEc
	 * ().getIdFamiliaEc();
	 * caracteristica.setIdFamilia(Long.toString(idFamilia));
	 * 
	 * caracteristica.setIdRelatorioEc(tbCaraccatrelatorioec.getIdCatrelatorioec(
	 * ).getIdRelatorioEc().getStrRelatorio());
	 * 
	 * long idCategoria =
	 * tbCaraccatrelatorioec.getIdCatrelatorioec().getIdCategoria
	 * ().getIdCategoria();
	 * caracteristica.setIdCategoria(Long.toString(idCategoria));
	 * 
	 * long idCaracateristica = tbCaraccatrelatorioec.getIdCaraccatrelatorio();
	 * caracteristica.setIdCaracteristica(Long.toString(idCaracateristica));
	 * 
	 * 
	 * //caracteristica.setIndice( res.getString("I_INDICE"));
	 * caracteristica.setTextoCaracteristica
	 * (tbCaraccatrelatorioec.getTxCaracteristica());
	 * 
	 * long idRelatorio =
	 * tbCaraccatrelatorioec.getIdCatrelatorioec().getIdRelatorioEc
	 * ().getIdRelatorioEc();
	 * caracteristica.setIdRelatorioEc(Long.toString(idRelatorio));
	 * 
	 * List<RelatorioPatente> anterioridadesPatentariasPorCaracteristica =
	 * this.obterAnterioridadesPorCaracteristica
	 * (tbCaraccatrelatorioec.getTbantpatentariaList());
	 * caracteristica.setAnterioridadesPatentariasPorCaracteristica
	 * (anterioridadesPatentariasPorCaracteristica);
	 * 
	 * List<RelatorioPatente> tbAntnaopatentarias =
	 * this.obterAnterioridadesNaoPatentariasPorCaracteristica
	 * (tbCaraccatrelatorioec.getTbantnaopatentariaList());
	 * caracteristica.setAnterioridadesNaoPatentariasPorCaracteristica
	 * (tbAntnaopatentarias);
	 * 
	 * caracteristicas.add(caracteristica);
	 * 
	 * }
	 * 
	 * } return caracteristicas;
	 * 
	 * }
	 */

	public RelatorioTecnico obterRelatorioTecnicoCompleto(Tbpatenteec tbPatenteec, Tbrelatorioec tbRelatorioec) {

		RelatorioTecnico relatorio = new RelatorioTecnico();
		relatorio.setNumeroPedidoEpoDoc(tbPatenteec.getStrPedNumEpodoc());
		relatorio.setDataPublicacao(tbPatenteec.getDtPublicacao());

		String id = Long.toString(tbRelatorioec.getIdStatus().getIdStatus());
		relatorio.setIdStatusParecer(id);
		relatorio.setNumeroRelatorioTecnico(tbRelatorioec.getStrRelatorio());

		String publico = "0";
		boolean isPublico = tbRelatorioec.getBPublico();
		if (isPublico) {
			publico = "1";
		}
		relatorio.setPublico(publico);

		String familiaId = Long.toString(tbRelatorioec.getIdFamiliaEc().getIdFamiliaEc());
		relatorio.setFamiliaId(familiaId);

		String resumo = tbRelatorioec.getTxResumo();
		if (resumo != null && !"".equals(resumo.trim())) {
			relatorio.setResumo(resumo.trim());
		}

		String conclusao = tbRelatorioec.getTxConclusao();
		if (conclusao != null && !"".equals(conclusao.trim())) {
			relatorio.setConclusao(conclusao.trim());
		}

		List<RelatorioPatente> anterioridades = this.retornarAnterioridades(tbRelatorioec.getTbantpatentariarelecList());
		if (anterioridades != null && !anterioridades.isEmpty()) {
			relatorio.setAnteoridadesPatentarias(anterioridades);
		}

		List<RelatorioPatente> anterioridadesNaoPatentaria = this.retornarAnterioridadesNaoPatentetarias(tbRelatorioec.getTbantnaopatentariarelecList());
		if (anterioridadesNaoPatentaria != null && !anterioridadesNaoPatentaria.isEmpty()) {
			relatorio.setAnteoridadesNaoPatentarias(anterioridadesNaoPatentaria);
		}

		return relatorio;

	}

}
