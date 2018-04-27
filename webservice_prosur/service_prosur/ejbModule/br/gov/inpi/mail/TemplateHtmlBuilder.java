package br.gov.inpi.mail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class TemplateHtmlBuilder {

	private String conteudo;

	public TemplateHtmlBuilder(InputStream arquivo) throws IOException {

		BufferedReader input = new BufferedReader(new InputStreamReader(arquivo));

		StringBuffer texto = new StringBuffer();

		String line = null;

		while ((line = input.readLine()) != null) {
			if (!line.isEmpty()) {
				texto.append(line);
			}
		}

		input.close();

		this.conteudo = texto.toString();

	}

	public TemplateHtmlBuilder(URL arquivo) throws IOException {

		BufferedReader input = new BufferedReader(new InputStreamReader(arquivo.openStream()));

		StringBuffer texto = new StringBuffer();

		String line = null;

		while ((line = input.readLine()) != null) {
			if (!line.isEmpty()) {
				texto.append(line);
			}
		}

		input.close();

		this.conteudo = texto.toString();

	}

	public TemplateHtmlBuilder replace(String chave, String valor) {
		conteudo = conteudo.replaceAll(Pattern.quote("{{" + chave + "}}"), valor);
		return this;
	}

	public TemplateHtmlBuilder addAll(HashMap<String, String> parametros) {

		for (Map.Entry<String, String> entry : parametros.entrySet()) {
			replace(entry.getKey(), entry.getValue());
		}

		return this;
	}

	public String build() {
		return conteudo;
	}

}
