package br.gov.inpi.epec.beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "TBLOGLOGIN")
@XmlRootElement
public class Tbloglogin implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LOGUSUARIO")
    private Long idLogUsuario;

    @Column(name = "STR_USUARIO")
    private String strUsuario;
    
	@Column(name = "DT_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
	private Date dtRegistro;

    @Column(name = "STR_IP")
    private String strIp;

	public Long getIdLogUsuario() {
		return idLogUsuario;
	}

	public void setIdLogUsuario(Long idLogUsuario) {
		this.idLogUsuario = idLogUsuario;
	}

	public String getStrUsuario() {
		return strUsuario;
	}

	public void setStrUsuario(String strUsuario) {
		this.strUsuario = strUsuario;
	}

	public Date getDtRegistro() {
		return dtRegistro;
	}

	public void setDtRegistro(Date dtRegistro) {
		this.dtRegistro = dtRegistro;
	}

	public String getStrIp() {
		return strIp;
	}

	public void setStrIp(String strIp) {
		this.strIp = strIp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dtRegistro == null) ? 0 : dtRegistro.hashCode());
		result = prime * result + ((idLogUsuario == null) ? 0 : idLogUsuario.hashCode());
		result = prime * result + ((strIp == null) ? 0 : strIp.hashCode());
		result = prime * result + ((strUsuario == null) ? 0 : strUsuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tbloglogin other = (Tbloglogin) obj;
		if (dtRegistro == null) {
			if (other.dtRegistro != null)
				return false;
		} else if (!dtRegistro.equals(other.dtRegistro))
			return false;
		if (idLogUsuario == null) {
			if (other.idLogUsuario != null)
				return false;
		} else if (!idLogUsuario.equals(other.idLogUsuario))
			return false;
		if (strIp == null) {
			if (other.strIp != null)
				return false;
		} else if (!strIp.equals(other.strIp))
			return false;
		if (strUsuario == null) {
			if (other.strUsuario != null)
				return false;
		} else if (!strUsuario.equals(other.strUsuario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tbloglogin [idLogUsuario=" + idLogUsuario + ", strUsuario=" + strUsuario + ", dtRegistro=" + dtRegistro + ", strIp=" + strIp + "]";
	}

	public Tbloglogin(Long idLogUsuario, String strUsuario, Date dtRegistro, String strIp) {
		super();
		this.idLogUsuario = idLogUsuario;
		this.strUsuario = strUsuario;
		this.dtRegistro = dtRegistro;
		this.strIp = strIp;
	}

	public Tbloglogin() {
	}


    
}
