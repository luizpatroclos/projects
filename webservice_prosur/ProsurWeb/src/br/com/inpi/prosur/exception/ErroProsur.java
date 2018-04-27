package br.com.inpi.prosur.exception;


public class ErroProsur extends Exception {

	private static final long serialVersionUID = 8663116718230290782L;

	private String mensagemUsuario;

	private String mensagemErro;

	public ErroProsur(String mensagemErro){
		
		super(mensagemErro);
		this.mensagemErro = mensagemErro;
	}
	
	public ErroProsur(String mensagemUsuario, String mensagemErro, Exception e) {
		super(mensagemErro, e);
		this.mensagemUsuario = mensagemUsuario;
		this.mensagemErro = mensagemErro;
	}
	
	public ErroProsur(String mensagemUsuario, String mensagemErro) {
		super(mensagemErro);
		this.mensagemUsuario = mensagemUsuario;
		this.mensagemErro = mensagemErro;
	}

	@Override
	public String getMessage() {
		return mensagemErro;
	}

	public String getMensagemUsuario() {
		return mensagemUsuario;
	}

}
