package br.gov.inpi.mail;

import java.util.HashMap;

public interface IEmailService {

	boolean sendMail(HashMap<String, String> parametros);

}
