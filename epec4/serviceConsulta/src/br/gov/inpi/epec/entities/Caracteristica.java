package br.gov.inpi.epec.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Caracteristica {

	private String idFamilia;

	private String numeroRelatorioEc;

	private String idCategoria;

	private String idCaracteristica;

	private String indice;

	private String textoCaracteristica;

	private String idRelatorioEc;

	private List<RelatorioPatente> anterioridadesPatentariasPorCaracteristica;

	private List<RelatorioPatente> anterioridadesNaoPatentariasPorCaracteristica;

	private Boolean temAnterioridades = false;

	public String getIdFamilia() {
		return idFamilia;
	}

	public void setIdFamilia(String idFamilia) {
		this.idFamilia = idFamilia;
	}

	public String getNumeroRelatorioEc() {
		return numeroRelatorioEc;
	}

	public void setNumeroRelatorioEc(String numeroRelatorioEc) {
		this.numeroRelatorioEc = numeroRelatorioEc;
	}

	public String getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getIdCaracteristica() {
		return idCaracteristica;
	}

	public void setIdCaracteristica(String idCaracteristica) {
		this.idCaracteristica = idCaracteristica;
	}

	public String getIndice() {
		return indice;
	}

	public void setIndice(String indice) {
		this.indice = indice;
	}

	public String getTextoCaracteristica() {
		return textoCaracteristica;
	}

	public void setTextoCaracteristica(String textoCaracteristica) {
		this.textoCaracteristica = textoCaracteristica;
	}

	public String getIdRelatorioEc() {
		return idRelatorioEc;
	}

	public void setIdRelatorioEc(String idRelatorioEc) {
		this.idRelatorioEc = idRelatorioEc;
	}

	public List<RelatorioPatente> getAnterioridadesPatentariasPorCaracteristica() {
		if (this.anterioridadesPatentariasPorCaracteristica == null) {
			return Collections.emptyList();
		} else {
			return anterioridadesPatentariasPorCaracteristica;
		}
	}

	public void setAnterioridadesPatentariasPorCaracteristica(List<RelatorioPatente> anterioridadesPatentariasPorCaracteristica) {
		this.anterioridadesPatentariasPorCaracteristica = anterioridadesPatentariasPorCaracteristica;
	}

	public List<RelatorioPatente> getAnterioridadesNaoPatentariasPorCaracteristica() {
		if (this.anterioridadesNaoPatentariasPorCaracteristica == null) {
			return Collections.emptyList();
		} else {
			return anterioridadesNaoPatentariasPorCaracteristica;
		}
	}

	public void setAnterioridadesNaoPatentariasPorCaracteristica(List<RelatorioPatente> anterioridadesNaoPatentariasPorCaracteristica) {
		this.anterioridadesNaoPatentariasPorCaracteristica = anterioridadesNaoPatentariasPorCaracteristica;
	}

	@Override
	public String toString() {
		return "Caracteristica [idFamilia=" + idFamilia + ", numeroRelatorioEc=" + numeroRelatorioEc + ", idCategoria=" + idCategoria + ", idCaracteristica=" + idCaracteristica + ", indice=" + indice
				+ ", textoCaracteristica=" + textoCaracteristica + ", idRelatorioEc=" + idRelatorioEc + ", anterioridadesPatentariasPorCaracteristica=" + anterioridadesPatentariasPorCaracteristica
				+ ", anterioridadesNaoPatentariasPorCaracteristica=" + anterioridadesNaoPatentariasPorCaracteristica + "]";
	}

	public Boolean getTemAnterioridades() {
		return temAnterioridades;
	}

	public void setTemAnterioridades(Boolean temAnterioridades) {
		this.temAnterioridades = temAnterioridades;
	}

	public List<RelatorioPatente> getAnterioridades() {

		List<RelatorioPatente> anterioridades = new ArrayList<RelatorioPatente>();
		if (anterioridadesPatentariasPorCaracteristica != null) {
			for (RelatorioPatente patentaria : anterioridadesPatentariasPorCaracteristica) {
				patentaria.setTipoAnterioridade("Patentária");
				anterioridades.add(patentaria);
			}
		}
		if (anterioridadesNaoPatentariasPorCaracteristica != null) {
			for (RelatorioPatente naoPatentaria : anterioridadesNaoPatentariasPorCaracteristica) {
				naoPatentaria.setTipoAnterioridade("Não Patentária");
				anterioridades.add(naoPatentaria);
			}
		}
		return anterioridades;
	}

}
