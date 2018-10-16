package br.gov.inpi.epec.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CategoriaRelatorio {

	private String idFamilia;

	private String nome;

	private String resumo;

	private String numeroRelatorio;

	private List<RelatorioPatente> anterioridadesPatentariasPorCategoria;

	private List<RelatorioPatente> anterioridadesNaoPatentariasPorCategoria;

	private List<Caracteristica> caracteristica;

	/**
	 * @return the idFamilia
	 */
	public String getIdFamilia() {
		return idFamilia;
	}

	/**
	 * @param idFamilia
	 *            the idFamilia to set
	 */
	public void setIdFamilia(String idFamilia) {
		this.idFamilia = idFamilia;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the resumo
	 */
	public String getResumo() {
		return resumo;
	}

	/**
	 * @param resumo
	 *            the resumo to set
	 */
	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	/**
	 * @return the numeroRelatorio
	 */
	public String getNumeroRelatorio() {
		return numeroRelatorio;
	}

	/**
	 * @param numeroRelatorio
	 *            the numeroRelatorio to set
	 */
	public void setNumeroRelatorio(String numeroRelatorio) {
		this.numeroRelatorio = numeroRelatorio;
	}

	/**
	 * @return the anterioridadesPatentariasPorCategoria
	 */
	public List<RelatorioPatente> getAnterioridadesPatentariasPorCategoria() {
		if (this.anterioridadesPatentariasPorCategoria == null) {
			return Collections.emptyList();
		} else {
			return anterioridadesPatentariasPorCategoria;
		}
	}

	/**
	 * @param anterioridadesPatentariasPorCategoria
	 *            the anterioridadesPatentariasPorCategoria to set
	 */
	public void setAnterioridadesPatentariasPorCategoria(List<RelatorioPatente> anterioridadesPatentariasPorCategoria) {
		this.anterioridadesPatentariasPorCategoria = anterioridadesPatentariasPorCategoria;
	}

	/**
	 * @return the anterioridadesNaoPatentariasPorCategoria
	 */
	public List<RelatorioPatente> getAnterioridadesNaoPatentariasPorCategoria() {
		if (this.anterioridadesNaoPatentariasPorCategoria == null) {
			return Collections.emptyList();
		} else {
			return anterioridadesNaoPatentariasPorCategoria;
		}
	}

	/**
	 * @param anterioridadesNaoPatentariasPorCategoria
	 *            the anterioridadesNaoPatentariasPorCategoria to set
	 */
	public void setAnterioridadesNaoPatentariasPorCategoria(List<RelatorioPatente> anterioridadesNaoPatentariasPorCategoria) {
		this.anterioridadesNaoPatentariasPorCategoria = anterioridadesNaoPatentariasPorCategoria;
	}

	public List<Caracteristica> getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(List<Caracteristica> caracteristica) {
		this.caracteristica = caracteristica;
	}

	@Override
	public String toString() {
		return "CategoriaRelatorio [idFamilia=" + idFamilia + ", nome=" + nome + ", resumo=" + resumo + ", numeroRelatorio=" + numeroRelatorio + ", anterioridadesPatentariasPorCategoria="
				+ anterioridadesPatentariasPorCategoria + ", anterioridadesNaoPatentariasPorCategoria=" + anterioridadesNaoPatentariasPorCategoria + ", caracteristica=" + caracteristica + "]";
	}

	public List<RelatorioPatente> getAnterioridades() {

		List<RelatorioPatente> anterioridades = new ArrayList<RelatorioPatente>();
		if (anterioridadesPatentariasPorCategoria != null) {
			for (RelatorioPatente patentaria : anterioridadesPatentariasPorCategoria) {
				patentaria.setTipoAnterioridade("Patentária");
				anterioridades.add(patentaria);
			}
		}
		if (anterioridadesNaoPatentariasPorCategoria != null) {
			for (RelatorioPatente naoPatentaria : anterioridadesNaoPatentariasPorCategoria) {
				naoPatentaria.setTipoAnterioridade("Não Patentária");
				anterioridades.add(naoPatentaria);
			}
		}
		return anterioridades;

	}

}
