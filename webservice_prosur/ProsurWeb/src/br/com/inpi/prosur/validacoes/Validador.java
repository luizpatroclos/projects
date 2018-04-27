package br.com.inpi.prosur.validacoes;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe de validação sintática de dados.
 */
public final class Validador {

    private Validador() {
        //construtor privado para que a classe nÃ£o seja instanciada
    }

    /**
     * Testa se uma String � nula ou está vazia.
     *
     * @param campo String a ser testada.
     * @return true se estiver vazio.
     */
    public static boolean vazio(final String campo) {

        return campo == null || campo.length() == 0;
    }

    /**
     * Testa se uma String contém um número inteiro.
     *
     * @param campo String a ser testada.
     * @return true se o conteúdo da String for um número inteiro.
     */
    public static boolean inteiro(final String campo) {

        return campo.matches("[0-9]*");
    }

    /**
     * Testa se uma String contém um número (inteiro ou real).
     *
     * @param campo String a ser testada.
     * @return true se o conteúdo da String for um número.
     */
    public static boolean numero(final String campo) {

        return (Validador.inteiro(campo) || Validador.real(campo));
    }

    /**
     * Testa se uma String contém um número hexadecimal.
     *
     * @param campo String a ser testada.
     * @return true se o conteúdo da String for um número.
     */
    public static boolean hexa(final String campo) {

        return !Validador.vazio(campo) && campo.matches("[0-9a-fA-F]*");
    }

    /**
     * Testa se a String tem pelo menos a quandidade de bytes informada.
     *
     * @param campo String a ser testada.
     * @param qtde Quantidade de bytes que se espera encontrar no campo.
     * @return true se a String tiver pelo menos a quantidade de bytes
     * informada.
     */
    public static boolean minChars(final String campo, final int qtde) {

        return !Validador.vazio(campo) && campo.length() >= qtde;
    }

    /**
     * Testa se a String não excede a quandidade de bytes informada.
     *
     * @param campo String a ser testada.
     * @param qtde Quantidade de bytes limite que o campo pode ter.
     * @return true se a String tiver menos bytes que a quantidade informada.
     */
    public static boolean maxChars(final String campo, final int qtde) {

        return !Validador.vazio(campo) && campo.length() <= qtde;
    }

    /**
     * Testa se uma String contém um número real.
     *
     * @param campo String a ser testada.
     * @return true se o conteúdo da String for um número real.
     */
    public static boolean real(final String campo) {

        return !Validador.vazio(campo) && campo.matches("[0-9]*\\.[0-9]*");
    }

    /**
     * Testa se uma String contém somente letras.
     *
     * @param campo String a ser testada.
     * @return true se o conteúdo da String for composta somente por letras.
     */
    public static boolean alfabetico(final String campo) {

        return !Validador.vazio(campo) && campo.matches("[a-zA-Z\\s]*");
    }

    /**
     * Testa se uma String contém um email válido.
     *
     * @param campo String a ser testada.
     * @return true se o conteúdo da String contiver um email válido.
     */
    public static boolean email(final String campo) {

        String regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=" + 
			"?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+" + 
			"[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
        return !Validador.vazio(campo) && campo.matches(regexp);
    }

    /**
     * Verifica se o campo possui um formato de data válida. E necessario passar
     * a mascara a ser validado.
     *
     * @param campo String a ser validado.
     * @param mascara Formato a ser verificado.
     * @return True ou False.
     */
    public static boolean data(final String campo, final String mascara) {

        boolean retorno = false;
        if (!Validador.vazio(campo) && !Validador.vazio(mascara)) {

            try {

                SimpleDateFormat sdf = new SimpleDateFormat(mascara);
                sdf.setLenient(false);
                Date data = sdf.parse(campo);
                if (data != null) {

                    retorno = true;
                }
            } catch (Exception e) {
                // Possível ParseException
            }
        }
        return retorno;
    }

    /**
     *
     * @param string
     * @param limite
     * @return
     */
    public static String modulo11(String string, int limite) {

        int[] campo = new int[string.length()];
        int[] resultado = new int[string.length()];
        int[] multiplicador = new int[string.length()];
        int total = 0;

        int fator = 2;
        for (int i = multiplicador.length - 1; i >= 0; i--) {

            if (limite != 0 && fator > limite) {

                fator = 2;
            }
            multiplicador[i] = fator++;
        }
        for (int i = 0; i < string.length(); i++) {

            campo[i] = Integer.parseInt(string.substring(i, i + 1));
        }
        for (int i = 0; i < resultado.length; i++) {

            resultado[i] = campo[i] * multiplicador[i];
        }
        for (int i = 0; i < resultado.length; i++) {

            total += resultado[i];
        }
        int DV = 11 - (total % 11);
        return String.valueOf((DV == 10 | DV == 11) ? 0 : DV);
    }

    /**
     * Realiza a validacao do CNPJ e indica-o se e valido.
     *
     * @param cnpj
     * @return True se o CNPJ e valido.
     */
    public static boolean cnpj(String cnpj) {

        boolean retorno = false;
        if (!Validador.vazio(cnpj) || !Validador.maxChars(cnpj, 14)) {

            String nCnpj = preencherDireita(cnpj, '0', 14);
            String dv1 = modulo11(nCnpj.substring(0, 12), 9);
            String dv2 = modulo11(nCnpj.substring(0, 13), 9);
            retorno = nCnpj.substring(12).equals(dv1 + dv2);
        }
        return retorno;
    }

    private static int calcularDigito(String string, int[] peso) {

        int soma = 0;
        if (!Validador.vazio(string) && peso != null) {

            for (int indice = string.length() - 1, digito; indice >= 0; indice--) {

                digito = Integer.parseInt(string.substring(indice, indice + 1));
                soma += digito * peso[peso.length - string.length() + indice];
            }
            soma = 11 - soma % 11;
        }
        return soma > 9 ? 0 : soma;
    }

    /**
     * Realiza a validacao do CPF e indica-o se e valido.
     *
     * @param cpf
     * @return True se o CPF e valido.
     */
    public static boolean cpf(String cpf) {

        boolean retorno = false;
        if (!Validador.vazio(cpf)) {
            
            int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
            if (Validador.vazio(cpf) || !Validador.maxChars(cpf, 11)) {

                return false;
            }
            String nCpf = preencherDireita(cpf, '0', 11);
            Integer digito1 = calcularDigito(nCpf.substring(0, 9), pesoCPF);
            Integer digito2 = calcularDigito(nCpf.substring(0, 9) + digito1, pesoCPF);
            retorno = nCpf.equals(nCpf.substring(0, 9) + digito1.toString() + digito2.toString());
        }
        return retorno;
    }

    /**
     *
     * @param string
     * @param preenche
     * @param tamanho
     * @return
     */
    public static String preencherDireita(String string, char preenche, int tamanho) {

        char[] array = new char[tamanho];
        int len = tamanho - string.length();
        for (int i = 0; i < len; i++) {
            array[i] = preenche;
        }
        string.getChars(0, string.length(), array, tamanho - string.length());
        return String.valueOf(array);
    }
    
    public static boolean validaHora(String hora){
    	
    	Pattern pattern;
    	Matcher matcher;
    	String regexFormatoHora = "([01]?[0-9]|2[0-4]):[0-5][0-9]";
    	
    	pattern = Pattern.compile(regexFormatoHora);    	
    	matcher = pattern.matcher(hora);
    	
    	return matcher.matches();
    	
    } 
    
    
    /**
     * Calcula a diferenca de horas entre duas datas
     * @param dataAgendada
     * @param dataNovoAgendamento
     * @return
     * @throws ParseException 
     */
    public static int calculaDiferencaDataEmHoras(String horaAgendada, String horaNovoAgendamento) throws ParseException {    	
    		
    	SimpleDateFormat dateFormate = new SimpleDateFormat("HH:mm");
		
		Date dataAgendada = dateFormate.parse(horaAgendada);
		Date dataNovoAgendamento = dateFormate.parse(horaNovoAgendamento); //quando os min da segunda data � maior que 2 ele subtrai 1 
    	
		long diferenca = dataAgendada.getTime() - dataNovoAgendamento.getTime(); //retorna a diferenca em milisegundos
    	int horas = (int) ((diferenca / (1000*60*60)) % 24); //converte para horas
		
    	Calendar cal = Calendar.getInstance();
		cal.setTime(dataAgendada);
		int minDataAgendada = cal.get(Calendar.MINUTE);		
		
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(dataNovoAgendamento);
		int minDataNovoAgendamento = cal2.get(Calendar.MINUTE);
		
		Calendar cal3 = Calendar.getInstance();
		cal3.setTime(dataAgendada);
		int horaDataAgendada = cal3.get(Calendar.HOUR_OF_DAY);		
		
		Calendar cal4 = Calendar.getInstance();
		cal4.setTime(dataNovoAgendamento);
		int horaDataNovoAgendamento = cal4.get(Calendar.HOUR_OF_DAY);
		
		if (horaDataAgendada < horaDataNovoAgendamento) {
			
			horas =+ horas * -1;
		}
		
		if ((horaDataAgendada > horaDataNovoAgendamento && minDataNovoAgendamento > minDataAgendada ) || 
			(horaDataNovoAgendamento > horaDataAgendada && minDataAgendada > minDataNovoAgendamento)) {
			
			horas++;
		}
    	
		return horas;
    }
    
    public static String converteNumeroDecimal(String numero){
    	
    	if (numero(numero)) {
		
    		String pattern = "###,###.###";
        	DecimalFormat decimalFormat = new DecimalFormat(pattern);

        	int intNumero = Integer.parseInt(numero);
        	
        	String format = decimalFormat.format(intNumero);
        	
        	return format;
		} else {
			
			return numero;
		}
    }
}