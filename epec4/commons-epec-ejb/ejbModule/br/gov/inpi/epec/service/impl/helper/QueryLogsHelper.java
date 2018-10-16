package br.gov.inpi.epec.service.impl.helper;

public class QueryLogsHelper {
	public static String logFamilia(Long idEntidade) {
		StringBuffer sqlQuery1 = new StringBuffer();
		sqlQuery1.append("SELECT ");
		sqlQuery1.append("    COUNT(*) ");
		sqlQuery1.append("FROM ");
		sqlQuery1.append("    TBLOGFAMILIA FA, ");
		sqlQuery1.append("    TBCADUSUARIO US, ");
		sqlQuery1.append("    TBCADENTIDADE EN ");
		sqlQuery1.append("WHERE ");
		sqlQuery1.append("    EN.ID_ENTIDADE_EC = US.ID_ENTIDADE_EC ");
		sqlQuery1.append("AND US.STR_USUARIO = FA.STR_USUARIO ");
		sqlQuery1.append("AND EN.ID_ENTIDADE_EC = " + idEntidade);
		return sqlQuery1.toString();
	}

	public static String logPatentes(Long idEntidade) {
		StringBuffer sqlQuery2 = new StringBuffer();
		sqlQuery2.append("SELECT ");
		sqlQuery2.append("    COUNT(*) ");
		sqlQuery2.append("FROM ");
		sqlQuery2.append("    TBLOGPATENTE PA, ");
		sqlQuery2.append("    TBCADUSUARIO US, ");
		sqlQuery2.append("    TBCADENTIDADE EN ");
		sqlQuery2.append("WHERE ");
		sqlQuery2.append("    EN.ID_ENTIDADE_EC = US.ID_ENTIDADE_EC ");
		sqlQuery2.append("AND US.STR_USUARIO = PA.STR_USUARIO ");
		sqlQuery2.append("AND EN.ID_ENTIDADE_EC =" + idEntidade);
		return sqlQuery2.toString();
	}

	public static String logRelatorio(Long idEntidade) {
		StringBuffer sqlQuery3 = new StringBuffer();
		sqlQuery3.append("SELECT ");
		sqlQuery3.append("    COUNT(*) ");
		sqlQuery3.append("FROM ");
		sqlQuery3.append("    TBLOGRELATORIO RE, ");
		sqlQuery3.append("    TBCADUSUARIO US, ");
		sqlQuery3.append("    TBCADENTIDADE EN ");
		sqlQuery3.append("WHERE ");
		sqlQuery3.append("    EN.ID_ENTIDADE_EC = US.ID_ENTIDADE_EC ");
		sqlQuery3.append("AND US.STR_USUARIO = RE.STR_USUARIO ");
		sqlQuery3.append("AND EN.ID_ENTIDADE_EC = " + idEntidade);
		return sqlQuery3.toString();
	}
}