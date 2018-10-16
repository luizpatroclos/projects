package br.gov.inpi.epec.service.impl.helper;

public class QueryAnterioridadesHelper {
	public static String query1(String tabela, Long idEntidade) {
		StringBuffer sqlQuery1 = new StringBuffer();
		sqlQuery1.append("SELECT ");
		sqlQuery1.append("    COUNT(*) ");
		sqlQuery1.append("FROM ");
		sqlQuery1.append("    " + tabela + " AN, ");
		sqlQuery1.append("    TBCARACCATRELATORIOEC CA, ");
		sqlQuery1.append("    TBCATRELATORIOEC CT, ");
		sqlQuery1.append("    TBRELATORIOEC RE, ");
		sqlQuery1.append("    TBCADUSUARIO US, ");
		sqlQuery1.append("    TBCADENTIDADE EN ");
		sqlQuery1.append("WHERE ");
		sqlQuery1.append("    EN.ID_ENTIDADE_EC = US.ID_ENTIDADE_EC ");
		sqlQuery1.append("AND US.ID_USUARIO = RE.ID_USUARIO ");
		sqlQuery1.append("AND RE.ID_RELATORIO_EC = CT.ID_RELATORIO_EC ");
		sqlQuery1.append("AND CT.ID_CATRELATORIOEC = CA.ID_CATRELATORIOEC ");
		sqlQuery1.append("AND CA.ID_CARACCATRELATORIO = AN.ID_CARACCATRELATORIO ");
		sqlQuery1.append("AND EN.ID_ENTIDADE_EC =" + idEntidade);

		return sqlQuery1.toString();
	}

	public static String query2(String tabela, Long idEntidade) {
		StringBuffer sqlQuery2 = new StringBuffer();
		sqlQuery2.append("SELECT ");
		sqlQuery2.append("    COUNT(*) ");
		sqlQuery2.append("FROM ");
		sqlQuery2.append("    " + tabela + " AN, ");
		sqlQuery2.append("    TBCATRELATORIOEC CA, ");
		sqlQuery2.append("    TBRELATORIOEC RE, ");
		sqlQuery2.append("    TBCADUSUARIO US, ");
		sqlQuery2.append("    TBCADENTIDADE EN ");
		sqlQuery2.append("WHERE ");
		sqlQuery2.append("    EN.ID_ENTIDADE_EC = US.ID_ENTIDADE_EC ");
		sqlQuery2.append("AND US.ID_USUARIO = RE.ID_USUARIO ");
		sqlQuery2.append("AND RE.ID_RELATORIO_EC = CA.ID_RELATORIO_EC ");
		sqlQuery2.append("AND CA.ID_CATRELATORIOEC = AN.ID_CATRELATORIOEC ");
		sqlQuery2.append("AND EN.ID_ENTIDADE_EC = " + idEntidade);

		return sqlQuery2.toString();
	}

	public static String query3(String tabela, Long idEntidade) {
		StringBuffer sqlQuery3 = new StringBuffer();
		sqlQuery3.append("SELECT ");
		sqlQuery3.append("    COUNT(*) ");
		sqlQuery3.append("FROM ");
		sqlQuery3.append("    " + tabela + " AN, ");
		sqlQuery3.append("    TBRELATORIOEC RE, ");
		sqlQuery3.append("    TBCADUSUARIO US, ");
		sqlQuery3.append("    TBCADENTIDADE EN ");
		sqlQuery3.append("WHERE ");
		sqlQuery3.append("    EN.ID_ENTIDADE_EC = US.ID_ENTIDADE_EC ");
		sqlQuery3.append("AND US.ID_USUARIO = RE.ID_USUARIO ");
		sqlQuery3.append("AND RE.ID_RELATORIO_EC = AN.ID_RELATORIO_EC ");
		sqlQuery3.append("AND EN.ID_ENTIDADE_EC = " + idEntidade);

		return sqlQuery3.toString();
	}
}