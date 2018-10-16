package br.gov.inpi.epec.writer;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import br.gov.inpi.epec.entities.Caracteristica;
import br.gov.inpi.epec.entities.CategoriaRelatorio;
import br.gov.inpi.epec.entities.RelatorioPatente;
import br.gov.inpi.epec.entities.RelatorioTecnico;
import br.gov.inpi.epec.utils.Utils;
import br.gov.inpi.epec.xml.document.DocumentId;
import br.gov.inpi.epec.xml.report.ReportApplicationReference;
import br.gov.inpi.epec.xml.report.category.Category;
import br.gov.inpi.epec.xml.report.category.CategoryList;
import br.gov.inpi.epec.xml.report.category.NonPatentPriorCategory;
import br.gov.inpi.epec.xml.report.category.NonPatentPriorCategoryList;
import br.gov.inpi.epec.xml.report.category.PatentPriorCategory;
import br.gov.inpi.epec.xml.report.category.PatentPriorCategoryList;
import br.gov.inpi.epec.xml.report.characteristic.CharNonPatentList;
import br.gov.inpi.epec.xml.report.characteristic.CharPatentPriorList;
import br.gov.inpi.epec.xml.report.characteristic.CharacteristicList;
import br.gov.inpi.epec.xml.report.characteristic.CharacteristicsRelatedPrior;
import br.gov.inpi.epec.xml.report.characteristic.NonPatentPriorCharacteristics;
import br.gov.inpi.epec.xml.report.characteristic.PatentPriorCharacteristics;
import br.gov.inpi.epec.xml.report.reportdata.NonPatentPrior;
import br.gov.inpi.epec.xml.report.reportdata.NonPatentPriorList;
import br.gov.inpi.epec.xml.report.reportdata.PatentPrior;
import br.gov.inpi.epec.xml.report.reportdata.PatentPriorList;
import br.gov.inpi.epec.xml.report.reportdata.ReportData;

public class ReportDataXmlWriter {
	
	private Utils utils = new Utils();
		
	private RelatorioTecnico relatorioTecnico;
	
	
 	private int totalRelatorios;
	
	
	public RelatorioTecnico getRelatorioTecnico() {
		return relatorioTecnico;
	}

	public void setRelatorioTecnico(RelatorioTecnico relatorioTecnico) {
		this.relatorioTecnico = relatorioTecnico;
	}

	public int getTotalRelatorios() {
		return totalRelatorios;
	}

	public void setTotalRelatorios(int totalRelatorios) {
		this.totalRelatorios = totalRelatorios;
	}

	public ReportApplicationReference converterRelatorioTecnico(RelatorioTecnico relatorioTecnico){
		
		ReportApplicationReference xml = new ReportApplicationReference();
				
		this.setRelatorioTecnico(relatorioTecnico);
		this.setTotalRelatorios(1);
		
		DocumentId documentId = this.retornarDocumentId();
		xml.setDocumentId(documentId);
		
		ReportData reportData = this.converterReportData();
			
		PatentPriorList patentPriorList = this.obterPatentPrior(relatorioTecnico.getAnteoridadesPatentarias());
	    reportData.setListaAnteoridades(patentPriorList);
		
		NonPatentPriorList nonPatentPriorList = this.obterNonPatentPriorList(relatorioTecnico.getAnteoridadesNaoPatentarias());
		reportData.setAntioridadesNaoPatentarias(nonPatentPriorList);	
		
		CategoryList categoryList = this.obterCategoryList(relatorioTecnico.getCategoriasRelatorio());
		reportData.setCategoriasDoRelatorio(categoryList);
		
		xml.setReportarData(reportData);
	    return xml;
		
	}
	
	
	
	
	
	private DocumentId retornarDocumentId(){
	    DocumentId documentId = new DocumentId();
	    documentId.setType("epodoc");
		documentId.setNumber(this.getRelatorioTecnico().getNumeroPedidoEpoDoc());
		Date dataPublicacao = this.getRelatorioTecnico().getDataPublicacao();
		String data = utils.converterData(dataPublicacao);
		documentId.setDate(data);
		return documentId;
	}
	
	
	
	
	
	private ReportData converterReportData(){
	    
		ReportData reportData = new ReportData();
		reportData.setSequence(Integer.toString(totalRelatorios));
		reportData.setReportNumber(this.getRelatorioTecnico().getNumeroRelatorioTecnico());
		reportData.setSituation(this.getRelatorioTecnico().getPublico());
		
		String resumo = this.getRelatorioTecnico().getResumoRelatorioTecnico();
		if(resumo != null && !"".equals(resumo.trim())){
            reportData.setSumary(resumo);
		}else{
			reportData.setSumary("");
		}
		
        String conclusion = this.getRelatorioTecnico().getConclusao();
        if(conclusion != null && !"".equals(conclusion.trim())){
        	reportData.setConclusion(conclusion.trim());
        }else{
        	reportData.setConclusion("");
        }
	
        String status = this.getRelatorioTecnico().getIdStatusParecer();
        if(status != null && !"".equals(status.trim())){
		    reportData.setReportStatus(status);
        }else{
        	reportData.setReportStatus("");
        }
        
		this.totalRelatorios++;
		return reportData;
	
	}
	
	
	private PatentPriorList obterPatentPrior(List<RelatorioPatente> anterioridades){
		
		PatentPriorList patentPriorList = null;
		
		if(anterioridades != null && !anterioridades.isEmpty()){
			
			List<PatentPrior> patentsPrior = this.retornarAnterioridadesPatentarias(relatorioTecnico.getAnteoridadesPatentarias());
			if(patentsPrior != null && !patentsPrior.isEmpty()){
				patentPriorList = new PatentPriorList();
				patentPriorList.setAnterioridades(patentsPrior);
			}
			return patentPriorList;	
		}
		return patentPriorList;
		
	}
	
		
	
	private List<PatentPrior> retornarAnterioridadesPatentarias(List<RelatorioPatente> anterioridades){
		
		
		List<PatentPrior> patentsPrior = new ArrayList<PatentPrior>();
		if(anterioridades != null && !anterioridades.isEmpty()){
		    
			int i = 1;
			Iterator<RelatorioPatente> it = anterioridades.iterator();
			while(it.hasNext()){
				
				RelatorioPatente rel = it.next();
				
				PatentPrior patentPrior = new PatentPrior();
				patentPrior.setDocumentCategory(rel.getDocumentCategory());
				patentPrior.setNamePrior(rel.getNamePrior());
				patentPrior.setRelations(rel.getRelations());
				patentPrior.setRelationsClaims(rel.getRelationsClaims());
				patentPrior.setRelevance(rel.getRelevance());
				patentPrior.setSequence(i);
				patentsPrior.add(patentPrior);
				i++;
				
			}
			
		}
		
		return patentsPrior;
				
	}
	
	
	
	private NonPatentPriorList obterNonPatentPriorList(List<RelatorioPatente> anterioridadesNaoPatentarias){
		
	   NonPatentPriorList nonPatentPriorList = null;
	   
	   if(anterioridadesNaoPatentarias != null && !anterioridadesNaoPatentarias.isEmpty()){
		  
		   List<NonPatentPrior> nonPatentsPrior = this.retornarAnterioridadesNaoPatentarias(relatorioTecnico.getAnteoridadesNaoPatentarias());
		   if(nonPatentsPrior != null && !nonPatentsPrior.isEmpty()){
			   nonPatentPriorList = new NonPatentPriorList();	
			   nonPatentPriorList.setListaAnterioridadesNaoPatentarias(nonPatentsPrior);
		   }
		   
		   return nonPatentPriorList;
	   }
	   
	   return nonPatentPriorList;
	   
	}
	
		
    private List<NonPatentPrior> retornarAnterioridadesNaoPatentarias(List<RelatorioPatente> anterioridadesNaoPatentarias){
		
    	List<NonPatentPrior> patentsPrior = new ArrayList<NonPatentPrior>();
		if(anterioridadesNaoPatentarias != null && !anterioridadesNaoPatentarias.isEmpty()){
			
			int i = 1;
			Iterator<RelatorioPatente> it = anterioridadesNaoPatentarias.iterator();
		    while(it.hasNext()){
							
				RelatorioPatente rel = it.next();
			    
				NonPatentPrior noPatentPrior = new NonPatentPrior();
				noPatentPrior.setDocumentCategory(rel.getDocumentCategory());
				noPatentPrior.setNamePrior(rel.getNamePrior());
				noPatentPrior.setRelations(rel.getRelations());
				noPatentPrior.setRelationsClaims(rel.getRelationsClaims());
				noPatentPrior.setRelevance(rel.getRelevance());
				noPatentPrior.setSequence(i);
				patentsPrior.add(noPatentPrior);
	            i++; 	    
		    }
		    
		}
		return patentsPrior;
		
		
	}
    
    
    
    private CategoryList obterCategoryList(List<CategoriaRelatorio> categoriasDoRelatorio){
    	
    	CategoryList categoryList = null;
    	List<Category> categories = this.obterCategoriasDoRelatorio(categoriasDoRelatorio);
    	if(categories != null && !categories.isEmpty()){
    		categoryList = new CategoryList();
    		categoryList.setCategorias(categories);
    		return categoryList;	
    	}
    	return categoryList;
    	
    }
        
    
    private List<Category> obterCategoriasDoRelatorio(List<CategoriaRelatorio> categoriasDoRelatorio) {
       	
    	List<Category> categories = null;
       	if(categoriasDoRelatorio != null && !categoriasDoRelatorio.isEmpty()){
           
       		categories = new ArrayList<Category>();
       		int i =1;
       		Iterator<CategoriaRelatorio> it = categoriasDoRelatorio.iterator();
    	    while(it.hasNext()){
    	    	
    	    	CategoriaRelatorio categoriaRelatorio = it.next();
    	    	Category category = this.obterCategory(categoriaRelatorio);
   	    	    if(category != null){
    	    		category.setSequence(i);
    	    		categories.add(category);	
    	    		i++;
    	    	}
    	    	
    	    }
    	    if(categories != null && !categories.isEmpty()){
    	    	  return categories;  	
    	    }else{
    	    	return null;
    	    }
    	      	    
    	}

       	return categories;
    }
    
    
    
    
    
    
    private Category obterCategory(CategoriaRelatorio categoriaRelatorio){
    	
    	Category category = new Category();
      	if(categoriaRelatorio != null){
    	
	       	category.setCategoryName(categoriaRelatorio.getNome());
	    	category.setCategorySumary(categoriaRelatorio.getResumo());
	    	    	
	    	PatentPriorCategoryList patentPriorCategoryList = this.obterPatentPriorCategoryList(categoriaRelatorio.getAnterioridadesPatentariasPorCategoria());
	    	if(patentPriorCategoryList != null){
	    	  category.setPatentPriorCategoryList(patentPriorCategoryList);
	    	}
	    	
	    	NonPatentPriorCategoryList nonPatentPriorCategoryList = this.obterNonPatentPriorCategoryList(categoriaRelatorio.getAnterioridadesNaoPatentariasPorCategoria());
	    	if(nonPatentPriorCategoryList != null){
	    	  category.setNonPatentPriorCategoryList(nonPatentPriorCategoryList);
	    	}
	    	
	    	CharacteristicList characteristicList = this.obterCaracteristicasDoRelatorio(categoriaRelatorio.getCaracteristica());
	       	if(characteristicList !=null){
	       	   category.setCharacteristics(characteristicList);
	       	}
             
	       	return category;
	       	
    	}else{
    		return null;
    	}
    	
    	
    }
	
    
    
    private PatentPriorCategoryList obterPatentPriorCategoryList(List<RelatorioPatente> patentes){
    	
    	PatentPriorCategoryList categoryList = new PatentPriorCategoryList();
    	if(patentes != null && !patentes.isEmpty()){
	    	List<PatentPriorCategory> patentsPriorCategory = this.obterPatentPriorCategory(patentes);
	    	if(patentsPriorCategory != null && !patentsPriorCategory.isEmpty()){
	    		categoryList.setPatentPriorCategories(patentsPriorCategory);
	    	}
	    	return categoryList;
    	}else{
    		return null;
    	}
    	
    	
    	
    	
    }
    
    
    
    
    private List<PatentPriorCategory> obterPatentPriorCategory(List<RelatorioPatente> patentes){
    	
    	List<PatentPriorCategory> patentsCategories = new ArrayList<PatentPriorCategory>();
      	if(patentes != null && !patentes.isEmpty()){
    		
    		int i = 1;
	    	Iterator<RelatorioPatente> it = patentes.iterator();
	    	while(it.hasNext()){
	    		
	    		RelatorioPatente relatorio = it.next();
	    		
	    		PatentPriorCategory pat = new PatentPriorCategory();
	    		pat.setDocumentCategory(relatorio.getDocumentCategory());
	    		pat.setNamePrior(relatorio.getNamePrior());
	    		pat.setRelations(relatorio.getRelations());
	    		pat.setRelationsClaims(relatorio.getRelationsClaims());
	    		pat.setRelevance(relatorio.getRelevance());
	    		pat.setSequence(i);
	    		patentsCategories.add(pat);
	    		i++;
	    		
	    	}
	    	
	    	return patentsCategories;  	
	    	
    	}else{
    		return null;
    	}
    	
    }
    
    
    private NonPatentPriorCategoryList obterNonPatentPriorCategoryList(List<RelatorioPatente> patentes){
    	
    	NonPatentPriorCategoryList nonPatentPriorCategoryList = new NonPatentPriorCategoryList();
    	if(patentes != null && !patentes.isEmpty()){
    		
	    	List<NonPatentPriorCategory> nonPatentsPriorCategory = this.obterNonPatentPriorCategory(patentes);
	    	if(nonPatentsPriorCategory != null && !nonPatentsPriorCategory.isEmpty()){
	    		nonPatentPriorCategoryList.setNonPatentPriorCategories(nonPatentsPriorCategory);
	    	}
	    	
	    	return nonPatentPriorCategoryList;
	    	
    	}else{
    		return null;
    	}
    	
    	
    }
    
    
    
    private List<NonPatentPriorCategory> obterNonPatentPriorCategory(List<RelatorioPatente> patentes){
    	
    	List<NonPatentPriorCategory> nonPatentsCategories = new ArrayList<NonPatentPriorCategory>();
    	if(patentes != null && !patentes.isEmpty()){
    		
    		int i = 1;
	    	Iterator<RelatorioPatente> it = patentes.iterator();
	    	while(it.hasNext()){
	    		
	    		RelatorioPatente relatorio = it.next();
	    		
	    		NonPatentPriorCategory nonPat = new NonPatentPriorCategory();
	    		nonPat.setDocumentCategory(relatorio.getDocumentCategory());
	    		nonPat.setNamePrior(relatorio.getNamePrior());
	    		nonPat.setRelations(relatorio.getRelations());
	    		nonPat.setRelationsClaims(relatorio.getRelationsClaims());
	    		nonPat.setRelevance(relatorio.getRelevance());
	    		nonPat.setSequence(i);
	    		nonPatentsCategories.add(nonPat);
	    		i++;
	   
	    	}
	    	
    	}
      	return   nonPatentsCategories;  	    	
    }
    
    
    
    
    
    
    private CharacteristicList obterCaracteristicasDoRelatorio(List<Caracteristica> caracteristicas){
    	
    	CharacteristicList characteristicList = new CharacteristicList();
      	if(caracteristicas != null && !caracteristicas.isEmpty()){
    	   
    		List<CharacteristicsRelatedPrior> characters = new ArrayList<CharacteristicsRelatedPrior>();
    		
    		int y = 1;
    		CharacteristicsRelatedPrior characteristicRelatedPrior = null;
    		for(int i = 0; i < caracteristicas.size(); i++){
    			    			
    			Caracteristica caracteristica = caracteristicas.get(i);
    			characteristicRelatedPrior = this.obterCharacteristicas(caracteristica,y);
    			if(characteristicRelatedPrior != null){
    				characteristicRelatedPrior.setSequence(y);	
    				characters.add(characteristicRelatedPrior);
    				y++;
    			}
    			
    		}
    		
    		if(characters.size() >=1){
    		  characteristicList.setCharacteristicsRelatedPrior(characters);
    		  return characteristicList;
    		}else{
    			return null;
    		}
    		
    	}else{
    		return null;
    	}
    	  	
    }
        
    
    
    private CharacteristicsRelatedPrior obterCharacteristicas(Caracteristica caracteristica, int sequence){
    	
    
    	CharacteristicsRelatedPrior character = new CharacteristicsRelatedPrior();
       	if(caracteristica != null){
    		
	    	String textoCaracteristica = caracteristica.getTextoCaracteristica();
	    	if(textoCaracteristica != null && !"".equals(textoCaracteristica.trim())){
	    	   character.setCharacteristicsName(textoCaracteristica.trim());
	    	}
	    	
	    	//if(sequence == 1){
	    		
		    	CharPatentPriorList characterPatentPriorList = this.obterPatentPriorList(caracteristica.getAnterioridadesPatentariasPorCaracteristica());
		      	if(characterPatentPriorList != null){
		    	  character.setPatentPriorList(characterPatentPriorList);
		    	}
		    	
		      	CharNonPatentList list = this.obterNonPatentPriorListChar(caracteristica.getAnterioridadesNaoPatentariasPorCaracteristica());
		    	if(list != null){
		    	  character.setCharNonPatentList(list);
		    	}
		    	
	    	//}
	    	
	    	return character;
	    	
    	}else{
    		return null;
    	}
    	
    	
    	    	
    }
    
    
    
    private CharPatentPriorList obterPatentPriorList(List<RelatorioPatente> anterioridadesPatentariasPorCaracteristica){
    	
    	CharPatentPriorList charPatentPrioList = new CharPatentPriorList();
     	if(anterioridadesPatentariasPorCaracteristica != null && !anterioridadesPatentariasPorCaracteristica.isEmpty()){
    	   List<PatentPriorCharacteristics> patents = this.retornarPatentPriorChar(anterioridadesPatentariasPorCaracteristica);
    	   charPatentPrioList.setPatentPriorCharacteristics(patents);
    	}
    	
    	return charPatentPrioList;
    	
    }
    
    
    
    @SuppressWarnings("unused")
	private CharNonPatentList obterNonPatentPriorListChar(List<RelatorioPatente> caracteristicaAntNonPat){
    	
    	CharNonPatentList list = new CharNonPatentList();
      	if(caracteristicaAntNonPat != null && !caracteristicaAntNonPat.isEmpty()){
    	    List<NonPatentPriorCharacteristics> patents = this.retornarNonPatentPriorChar(caracteristicaAntNonPat);
        	list.setNonPatentPriorCharacteristics(patents);
    	}
    	return list;
    	
    	
    }
    
    
    private List<PatentPriorCharacteristics> retornarPatentPriorChar(List<RelatorioPatente> anterioridadesPatentariasPorCaracteristica){
    	
    	List<PatentPriorCharacteristics> patents = new ArrayList<PatentPriorCharacteristics>();
    	if(anterioridadesPatentariasPorCaracteristica != null && !anterioridadesPatentariasPorCaracteristica.isEmpty()){
	    	
    		int i = 1;
    		Iterator<RelatorioPatente> it = anterioridadesPatentariasPorCaracteristica.iterator();
	    	while(it.hasNext()){
	    		
	    		RelatorioPatente relatorio = it.next();
	    		PatentPriorCharacteristics pat = new PatentPriorCharacteristics();
	    		pat.setDocumentCategory(relatorio.getDocumentCategory());
	    		pat.setNamePrior(relatorio.getNamePrior());
	    		pat.setRelations(relatorio.getRelations());
	    		pat.setRelationsClaims(relatorio.getRelationsClaims());
	    		pat.setRelevance(relatorio.getRelevance());
	    		pat.setSequence(i);
	    		patents.add(pat);
	    		i++;
	    		
	    	}
    	}
    	return patents;
    	
    	
    }
    

    
    private List<NonPatentPriorCharacteristics> retornarNonPatentPriorChar(List<RelatorioPatente> caracteristicaAntNonPat){
    	
    	List<NonPatentPriorCharacteristics> patentsNon = new ArrayList<NonPatentPriorCharacteristics>();
       	if(caracteristicaAntNonPat != null && !caracteristicaAntNonPat.isEmpty()){
	    	
       		int i = 1;
    		Iterator<RelatorioPatente> it = caracteristicaAntNonPat.iterator();
	    	while(it.hasNext()){
	    		
	    		RelatorioPatente relatorio = it.next();
	    		NonPatentPriorCharacteristics pat = new NonPatentPriorCharacteristics();
	    		pat.setDocumentCategory(relatorio.getDocumentCategory());
	    		pat.setNamePrior(relatorio.getNamePrior());
	    		pat.setRelations(relatorio.getRelations());
	    		pat.setRelationsClaims(relatorio.getRelationsClaims());
	    		pat.setRelevance(relatorio.getRelevance());
	    		pat.setSequence(i);
    		    patentsNon.add(pat);
	    		i++;
	    		
	    	}
    	}
    	
    	return patentsNon;
    	    	
    }

	
	

}
