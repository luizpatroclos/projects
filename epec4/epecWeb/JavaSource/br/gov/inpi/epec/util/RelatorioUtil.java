package br.gov.inpi.epec.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.io.FileUtils;

public class RelatorioUtil {

	public void gerarRelatorioPdf(String nomeRelatorioJasper, String nomeRelatorioSaida, JRDataSource dataSource) {

		FacesContext context = FacesContext.getCurrentInstance();

		try {

			String caminhoArquivoJasper = context.getExternalContext().getRealPath("resources/relatorios/" + nomeRelatorioJasper + ".jasper");

			FileInputStream fis = new FileInputStream(caminhoArquivoJasper);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);

			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(bufferedInputStream);

			@SuppressWarnings({ "rawtypes", "unchecked" })
			JasperPrint impressoraJasper = JasperFillManager.fillReport(jasperReport, new HashMap(), dataSource);

			byte[] b = JasperExportManager.exportReportToPdf(impressoraJasper);
			context.getExternalContext().getSessionMap().put("reportBytes", b);
		} catch (Exception e) {

			System.out.println("Erro: " + e);
		}
	}

	//
	// private File arquivoParaDownload(final Documento documento) {
	// try {
	//
	// File file = new File(configuracoesProperties.getString("armazenamentoGeradoc") +
	// documento.getArquivoFinalizado());
	//
	// if (!file.exists()) {
	// LOGGER.error("Arquivo não Encontrado: " + file.getAbsolutePath());
	// validator.add(new I18nMessage("error", "nao.encontrou.arquivo"));
	// return null;
	// }
	//
	// return file;
	// } catch (ErroSistema e) {
	// retornaMensagemDeErroEmJSON(e);
	// }
	// return null;
	// }
	
//	private void salvar(OutputStream outputStream, Documento documento) throws IOException {
//
//		ByteArrayOutputStream baos = (ByteArrayOutputStream) outputStream;
//
//		String caminhoArquivo = documentoService.recuperarCaminhoParaSalvarArquivo(documento, LocalArmazenamento.originais());
//		this.salvarArquivoEmDisco(baos.toByteArray(), caminhoArquivo);
//		this.apagarArquivoDoDisco(documento.getNomeArquivoTemporario());
//
//	}
	public static void salvarArquivoEmDisco(InputStream arquivo, String path) {
		try {
			FileOutputStream out = new FileOutputStream(path);
			int b;

			while ((b = arquivo.read()) > -1) {
				out.write(b);
			}
			arquivo.close();
			out.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	public static void apagarArquivoDoDisco(String caminho) throws FileNotFoundException {

		delete(caminho);
	}

	public static boolean delete(String localArquivo) {
		try {
			File file = new File(localArquivo);
			file.delete();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public static void salvarArquivoEmDisco(byte[] arquivo, String caminhoArquivo) throws IOException {
		FileUtils.writeByteArrayToFile(new File(caminhoArquivo), arquivo);
	}

	public void startDownload(String caminho) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();

		File file = new File(caminho);

		externalContext.setResponseHeader("Content-Disposition", "attachment;filename=\"" + file.getAbsolutePath() + "\"");
		facesContext.responseComplete();
	}
}
