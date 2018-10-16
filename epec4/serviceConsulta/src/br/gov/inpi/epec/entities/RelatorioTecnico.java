package br.gov.inpi.epec.entities;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.text.BadLocationException;

public class RelatorioTecnico extends Patente {

	private String idStatusParecer;

	private String numeroRelatorioTecnico;

	private String publico;

	private String resumoRelatorioTecnico;

	private String conclusao;

	private List<RelatorioPatente> anteoridadesPatentarias;

	private List<RelatorioPatente> anteoridadesNaoPatentarias;

	private List<CategoriaRelatorio> categoriasRelatorio;

	private List<Familia> familias;

	private String autor;

	private String enderecoImagem;

	private String dataAtual;

	/**
	 * @return the anteoridadesNaoPatentarias
	 */
	public List<RelatorioPatente> getAnteoridadesNaoPatentarias() {
		if (this.anteoridadesNaoPatentarias == null) {
			return Collections.emptyList();
		} else {
			return anteoridadesNaoPatentarias;
		}
	}

	/**
	 * @param anteoridadesNaoPatentarias
	 *            the anteoridadesNaoPatentarias to set
	 */
	public void setAnteoridadesNaoPatentarias(List<RelatorioPatente> anteoridadesNaoPatentarias) {
		this.anteoridadesNaoPatentarias = anteoridadesNaoPatentarias;
	}

	/**
	 * @return the idStatusParecer
	 */
	public String getIdStatusParecer() {
		return idStatusParecer;
	}

	/**
	 * @param idStatusParecer
	 *            the idStatusParecer to set
	 */
	public void setIdStatusParecer(String idStatusParecer) {
		this.idStatusParecer = idStatusParecer;
	}

	/**
	 * @return the numeroRelatorioTecnico
	 */
	public String getNumeroRelatorioTecnico() {
		return numeroRelatorioTecnico;
	}

	/**
	 * @param numeroRelatorioTecnico
	 *            the numeroRelatorioTecnico to set
	 */
	public void setNumeroRelatorioTecnico(String numeroRelatorioTecnico) {
		this.numeroRelatorioTecnico = numeroRelatorioTecnico;
	}

	/**
	 * @return the publico
	 */
	public String getPublico() {
		return publico;
	}

	/**
	 * @param publico
	 *            the publico to set
	 */
	public void setPublico(String publico) {
		this.publico = publico;
	}

	/**
	 * @return the resumoRelatorioTecnico
	 * @throws BadLocationException
	 * @throws IOException
	 */
	public String getResumoRelatorioTecnico() {
		return resumoRelatorioTecnico;
	}

	/**
	 * @param resumoRelatorioTecnico
	 *            the resumoRelatorioTecnico to set
	 */
	public void setResumoRelatorioTecnico(String resumoRelatorioTecnico) {
		this.resumoRelatorioTecnico = resumoRelatorioTecnico;
	}

	/**
	 * @return the conclusao
	 */
	public String getConclusao() {
		return conclusao;
	}

	/**
	 * @param conclusao
	 *            the conclusao to set
	 */
	public void setConclusao(String conclusao) {
		this.conclusao = conclusao;
	}

	/**
	 * @return the anteoridadesPatentarias
	 */
	public List<RelatorioPatente> getAnteoridadesPatentarias() {
		if (this.anteoridadesPatentarias == null) {
			return Collections.emptyList();
		} else {
			return anteoridadesPatentarias;
		}
	}

	/**
	 * @param anteoridadesPatentarias
	 *            the anteoridadesPatentarias to set
	 */
	public void setAnteoridadesPatentarias(List<RelatorioPatente> anteoridadesPatentarias) {
		this.anteoridadesPatentarias = anteoridadesPatentarias;
	}

	/**
	 * @return the categoriasRelatorio
	 */
	public List<CategoriaRelatorio> getCategoriasRelatorio() {
		if (this.categoriasRelatorio == null) {
			return Collections.emptyList();
		} else {
			return categoriasRelatorio;
		}
	}

	/**
	 * @param categoriasRelatorio
	 *            the categoriasRelatorio to set
	 */
	public void setCategoriasRelatorio(List<CategoriaRelatorio> categoriasRelatorio) {
		this.categoriasRelatorio = categoriasRelatorio;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RelatorioTecnico [idStatusParecer=" + idStatusParecer + ", numeroRelatorioTecnico=" + numeroRelatorioTecnico + ", publico=" + publico + ", resumoRelatorioTecnico="
				+ resumoRelatorioTecnico + ", conclusao=" + conclusao + ", anteoridadesPatentarias=" + anteoridadesPatentarias + ", anteoridadesNaoPatentarias=" + anteoridadesNaoPatentarias
				+ ", categoriasRelatorio=" + categoriasRelatorio + "]";
	}

	public List<Familia> getFamilias() {
		if (this.familias == null) {
			return Collections.emptyList();
		} else {
			return familias;
		}
	}

	public void setFamilias(List<Familia> familias) {
		this.familias = familias;
	}

	public String getNomeClafissificacoes() {
		String classificacoes = "";
		if (getClassificacoes() != null) {
			for (ClassificacaoInternacional classificacao : getClassificacoes()) {
				classificacoes += classificacao.getClassificacaoInternacional() + ", ";

			}
		}
		return classificacoes;
	}

	public String getNumerosPrioridades() {
		String prioridades = "";
		if (getPrioridades() != null) {
			for (Prioridade prioridade : getPrioridades()) {
				prioridades += prioridade.getNumero() + ", ";

			}
		}
		return prioridades;
	}

	public String getNomesDepositantes() {
		String depositantes = "";
		if (getDepositantes() != null) {
			for (Pessoa depositante : getDepositantes()) {
				depositantes += depositante.getNome() + ", ";

			}
		}
		return depositantes;
	}

	public String getNomesInventores() {
		String inventores = "";
		if (getInventores() != null) {
			for (Pessoa inventor : getInventores()) {
				inventores += inventor.getNome() + ", ";

			}
		}
		return inventores;
	}

	public String getNomeProcurador() {
		if (getProcurador() != null) {
			return getProcurador().getNome();
		} else {
			return "";
		}
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEnderecoImagem() {
		return enderecoImagem;
	}

	public void setEnderecoImagem(String enderecoImagem) {
		this.enderecoImagem = enderecoImagem;
	}

	public List<RelatorioPatente> getAnterioridades() {

		List<RelatorioPatente> anterioridades = new ArrayList<RelatorioPatente>();
		if (anteoridadesPatentarias != null) {
			for (RelatorioPatente patentaria : anteoridadesPatentarias) {
				patentaria.setTipoAnterioridade("Patentária");
				anterioridades.add(patentaria);
			}
		}

		if (anteoridadesNaoPatentarias != null) {

			for (RelatorioPatente naoPatentaria : anteoridadesNaoPatentarias) {
				naoPatentaria.setTipoAnterioridade("Não Patentária");
				anterioridades.add(naoPatentaria);
			}
		}
		return anterioridades;

	}

	public String getDataAtual() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date date = new Date();
		return dateFormat.format(date);
	}

}