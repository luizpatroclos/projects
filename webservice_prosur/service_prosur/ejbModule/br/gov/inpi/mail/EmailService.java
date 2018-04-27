package br.gov.inpi.mail;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.security.PermitAll;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;

import org.jboss.logging.Logger;

@Local(IEmailService.class)
@Singleton
@Startup
public class EmailService implements IEmailService {
	public static final Logger log = Logger.getLogger(EmailService.class.getName());
	public static final String EMAIL_SESSION_JNDI_PATH = "java:/inpiMail";

	String bundleQualifierName = "resource.prosur";
	ResourceBundle resourceBundle = ResourceBundle.getBundle(bundleQualifierName, new Locale("pt", "BR"));

	URL templateCopia = getClass().getResource("/resource/template_relatorio.html");

	final String emailGrupoPROSUR = resourceBundle.getString("email_grupo_prosur");

	@PostConstruct
	public void start() throws Exception {
	}

	@PreDestroy
	public void destroy() {
	}

	// *****************[interface methods]***************//
	@Override
	@PermitAll()
	public boolean sendMail(HashMap<String, String> parametros) {
		try {

			TemplateHtmlBuilder template = new TemplateHtmlBuilder(templateCopia);
			template.addAll(parametros);

			MimeMessage message = new MimeMessage(getEmailSession());
			InternetAddress[] to = new InternetAddress[] { new InternetAddress(emailGrupoPROSUR) };
			message.setRecipients(Message.RecipientType.TO, to);
			message.setSubject("Relatório de Carga de Processos PROSUR - (" + new SimpleDateFormat("dd/MM/yyyy").format(new Date()) + ")");
			message.setSentDate(new java.util.Date());
			message.setText(template.build(), "utf-8", "html");

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