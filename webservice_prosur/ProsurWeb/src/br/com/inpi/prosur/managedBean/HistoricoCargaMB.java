package br.com.inpi.prosur.managedBean;

import static br.com.inpi.prosur.validacoes.ProsurUtil.convertDateToString;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;

import br.gov.inpi.intercarga.beans.HistoricoCarga;
import br.gov.inpi.intercarga.beans.TbHistoricoCarga;
import br.gov.inpi.intercarga.beans.TbHistoricoCargaProcesso;
import br.gov.inpi.services.EntityInterfaceIntercarga;

@ManagedBean(name = "historicoCargaMB")
@SessionScoped
public class HistoricoCargaMB {

	@EJB
	EntityInterfaceIntercarga service;

	private List<HistoricoCarga> listaHistoricoCarga;
	private List<HistoricoCarga> filteredListaHistoricoCarga;
	private List<TbHistoricoCargaProcesso> listaHistoricoProcessoCarga;
	
	private TbHistoricoCarga historicoCarga;
	private HistoricoCarga dtoHistoricoCarga;
	
	public HistoricoCarga getDtoHistoricoCarga() {
		return dtoHistoricoCarga;
	}
	public void setDtoHistoricoCarga(HistoricoCarga dtoHistoricoCarga) {
		this.dtoHistoricoCarga = dtoHistoricoCarga;
	}
	
	public TbHistoricoCarga getHistoricoCarga() {
		return historicoCarga;
	}
	public void setHistoricoCarga(TbHistoricoCarga historicoCarga) {
		this.historicoCarga = historicoCarga;
	}
	
	//LISTA HISTORICO CARGA
	public List<HistoricoCarga> getListaHistoricoCarga(){
			
		this.listaHistoricoCarga = service.listaHistoricoCarga();
		
		return this.listaHistoricoCarga;
	}
	public void setListaHistoricoCarga(List<HistoricoCarga> listaHistoricoCarga){
		
		this.listaHistoricoCarga = listaHistoricoCarga;
	}
	//FIM LISTA HISTORICO CARGA
	
	//FILTRO HISTORICO
	public List<HistoricoCarga> getFilteredListaHistoricoCarga() {
		return filteredListaHistoricoCarga;
	}
	public void setFilteredListaHistoricoCarga(List<HistoricoCarga> filteredListaHistoricoCarga) {
		this.filteredListaHistoricoCarga = filteredListaHistoricoCarga;
	}
	//FIM FILTRO HISTORICO
	
	//LISTA HISTORICO CARGA PROCESSO
	public List<TbHistoricoCargaProcesso> getListaHistoricoCargaProcesso() {
			
		this.listaHistoricoProcessoCarga = service.listarHistoricoProcessoCarga(retornarHistoricoCarga());
		
		return this.listaHistoricoProcessoCarga;
	}
	public void setListaHistoricoCargaProcesso(List<TbHistoricoCargaProcesso> listaHitoricoCargaProcesso) {
		this.listaHistoricoProcessoCarga = listaHitoricoCargaProcesso;
	}
	
	private TbHistoricoCarga retornarHistoricoCarga(){
		
		try {
			
			if (this.dtoHistoricoCarga != null) {
				
				this.historicoCarga = service.getHistoricoCarga(dtoHistoricoCarga.getIdHistoricoCarga());
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return this.historicoCarga;
	}
	//FIM LISTA HISTORICO CARGA PROCESSO
	
	@SuppressWarnings("deprecation")
	public void postProcessXLS(Object document) {
		
		HSSFWorkbook wb = (HSSFWorkbook) document;
		HSSFSheet sheet = wb.getSheetAt(0);
		
		sheet.shiftRows(0, sheet.getLastRowNum(), 4); //numero de linhas ate chegar a iteracao com os processos
		
		//Data execucao
		HSSFCell cell = sheet.createRow(0).createCell(0);
		String dataExecucao = convertDateToString(this.dtoHistoricoCarga.getDhInicioCarga());
		cell.setCellValue("Data da Execução: " + dataExecucao);
		
		//Tipo agendamento
		HSSFCell cell2 = sheet.createRow(1).createCell(0);
		String tipoAgendamento = this.dtoHistoricoCarga.getTpAgenda();
		cell2.setCellValue("Tipo Agendamento: " + tipoAgendamento);

		//Tipo carga
		HSSFCell cell3 = sheet.createRow(2).createCell(0);
		String tipoCarga = this.dtoHistoricoCarga.getTpCarga();
		cell3.setCellValue("Tipo Carga: " + tipoCarga);
		
		//metodo responsavel pelo merge entre as celulas
		//CellRangeAddress(Primeira Linha, Ultima Linha, Primeira Coluna, Ultima Coluna)
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 2));
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 2));
	}
}
