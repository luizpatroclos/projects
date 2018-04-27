package br.com.inpi.prosur.util;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class ConnectServiceLDAP {	

	public ConnectServiceLDAP() {
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static DirContext connect(String login, String senha) {
		DirContext context = null;

		String ldapCF = "com.sun.jndi.ldap.LdapCtxFactory";
		String ldapURL = "ldap://172.16.85.54:389 ";
		String IdapBaseDN = "dc=inpi,dc=gov,dc=br";
		String ldapUserID = login + "@inpi.gov.br";

		Hashtable authEnv = new Hashtable(7);

		authEnv.put(Context.INITIAL_CONTEXT_FACTORY, ldapCF);
		authEnv.put(Context.PROVIDER_URL, ldapURL + IdapBaseDN);
		authEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
		authEnv.put(Context.SECURITY_PRINCIPAL, ldapUserID);
		authEnv.put(Context.SECURITY_CREDENTIALS, senha);
		authEnv.put(Context.REFERRAL, "follow");

		try {
			context = new InitialDirContext(authEnv);

		} catch (Exception e) {
			System.out.println("Erro ao Autenticar " + login + " no LDAP");
		}

		return context;
	}
}