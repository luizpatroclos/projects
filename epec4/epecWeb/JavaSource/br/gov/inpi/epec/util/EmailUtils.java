package br.gov.inpi.epec.util;

import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.security.PermitAll;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;

import br.gov.inpi.epec.beans.mensagemEmail;

public class EmailUtils {
	
	public static final Logger log = Logger.getLogger(EmailUtils.class.getName());
	public static final String EMAIL_SESSION_JNDI_PATH = "java:/inpiMail";

	//String bundleQualifierName = "resource.prosur";
	//ResourceBundle resourceBundle = ResourceBundle.getBundle(bundleQualifierName, new Locale("pt", "BR"));

	//URL templateCopia = getClass().getResource("/resource/template_relatorio.html");


	@PostConstruct
	public void start() throws Exception {
	}

	@PreDestroy
	public void destroy() {
	}

	// *****************[interface methods]***************//
	@PermitAll()
	public boolean sendMail(mensagemEmail mensagemFamilia) {
		try {

			/*TemplateHtmlBuilder template = new TemplateHtmlBuilder(templateCopia);
			template.addAll(parametros);*/

			MimeMessage message = new MimeMessage(getEmailSession());
			InternetAddress to = new InternetAddress(mensagemFamilia.getDestino());
			message.setRecipient(Message.RecipientType.TO, to);
			message.setSubject(mensagemFamilia.getTitulo());
			message.setSentDate(new java.util.Date());
			message.setText(mensagemFamilia.getMensagem());

			//
			Transport.send(message);
			//
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private Session getEmailSession() throws Exception {
		InitialContext context = new InitialContext();
		return (Session) context.lookup(EMAIL_SESSION_JNDI_PATH);
	}
	
}