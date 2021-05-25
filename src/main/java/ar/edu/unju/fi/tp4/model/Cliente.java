package ar.edu.unju.fi.tp4.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Entity
@Table (name="CLIENTES")
@Component
public class Cliente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native", strategy="native")
	@Column
	private Integer idCliente;
	@Column
	private int nroDoc;
	@Column
	private String tipoDoc;
	@Column
	private int codigoAreaTel;
	@Column
	private int numTel;
	@Column
	private String email;
	@Column
	private String nombreApellido;
	@Column
	private String password;
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaNacimiento;
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaUltimaCompra;
	@Column
	private boolean activo;


	//Calendar
	//private Date fechaUltimCompra = new Date();
		

	public Cliente() {
		this.activo = true;
	}

	public Cliente(String tipoDoc, String nombreApellido, String email, String password, int nroDoc,
			int codigoAreaTel, int numTel, LocalDate fechaNacimiento, LocalDate fechaUltimaCompra) {
		this.tipoDoc = tipoDoc;
		this.nombreApellido = nombreApellido;
		this.email = email;
		this.password = password;
		this.nroDoc = nroDoc;
		this.codigoAreaTel = codigoAreaTel;
		this.numTel = numTel;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaUltimaCompra = fechaUltimaCompra;
	}


	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public String getNombreApellido() {
		return nombreApellido;
	}

	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getNroDoc() {
		return nroDoc;
	}

	public void setNroDoc(int nroDoc) {
		this.nroDoc = nroDoc;
	}

	public int getEdad() {
		LocalDate fechaActual = LocalDate.now();
		Period periodo = Period.between(fechaNacimiento, fechaActual);
		return periodo.getYears();
	}

	public int getCodigoAreaTel() {
		return codigoAreaTel;
	}

	public void setCodigoAreaTel(int codigoAreaTel) {
		this.codigoAreaTel = codigoAreaTel;
	}

	public int getNumTel() {
		return numTel;
	}

	public void setNumTel(int numTel) {
		this.numTel = numTel;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public LocalDate getFechaUltimaCompra() {
		return fechaUltimaCompra;
	}

	public void setFechaUltimaCompra(LocalDate fechaUltimaCompra) {
		this.fechaUltimaCompra = fechaUltimaCompra;
	}

	public String getDatosAdicionales() {
		LocalDate fechaN = this.fechaNacimiento;
		LocalDate fechaU = this.fechaUltimaCompra;
		LocalDate fechaActual = LocalDate.now();

		String datos = "THUC: ";

		Period periodo = Period.between(fechaU, fechaActual);
		datos += (periodo.getYears() + "-" + periodo.getMonths() + "-" + periodo.getDays());

		datos += "\nTHSC: ";

		LocalDate cumple;
		int dia = fechaN.getDayOfMonth() - 1, mes = fechaN.getMonthValue(), an = fechaN.getYear() + this.getEdad() + 1;
		if (dia == 0) {
			mes -= 1;
			dia = fechaN.lengthOfMonth() - 1;
		}
		cumple = LocalDate.of(an, mes, dia);

		if (fechaActual.getMonthValue() >= cumple.getMonthValue()) {
			periodo = Period.between(fechaActual, cumple);
		} else {
			periodo = Period.between(cumple, fechaActual);
		}

		Calendar hoy = Calendar.getInstance();
		int horas = hoy.get(Calendar.HOUR_OF_DAY);
		int minutos = hoy.get(Calendar.MINUTE);
		int segundos = hoy.get(Calendar.SECOND);

		horas = 24 - horas - 1;
		minutos = 60 - minutos - 1;
		segundos = 60 - segundos;

		datos += (periodo.getYears() + "-" + periodo.getMonths() + "-" + periodo.getDays() + " " + horas + ":" + minutos
				+ ":" + segundos);

		return datos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigoAreaTel;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fechaNacimiento == null) ? 0 : fechaNacimiento.hashCode());
		result = prime * result + ((fechaUltimaCompra == null) ? 0 : fechaUltimaCompra.hashCode());
		result = prime * result + ((nombreApellido == null) ? 0 : nombreApellido.hashCode());
		result = prime * result + nroDoc;
		result = prime * result + numTel;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((tipoDoc == null) ? 0 : tipoDoc.hashCode());
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
		Cliente other = (Cliente) obj;
		if (codigoAreaTel != other.codigoAreaTel)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fechaNacimiento == null) {
			if (other.fechaNacimiento != null)
				return false;
		} else if (!fechaNacimiento.equals(other.fechaNacimiento))
			return false;
		if (fechaUltimaCompra == null) {
			if (other.fechaUltimaCompra != null)
				return false;
		} else if (!fechaUltimaCompra.equals(other.fechaUltimaCompra))
			return false;
		if (nombreApellido == null) {
			if (other.nombreApellido != null)
				return false;
		} else if (!nombreApellido.equals(other.nombreApellido))
			return false;
		if (nroDoc != other.nroDoc)
			return false;
		if (numTel != other.numTel)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (tipoDoc == null) {
			if (other.tipoDoc != null)
				return false;
		} else if (!tipoDoc.equals(other.tipoDoc))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [tipoDoc=" + tipoDoc + ", nombreApellido=" + nombreApellido + ", email=" + email
				+ ", password=" + password + ", nroDoc=" + nroDoc + ", codigoAreaTel="
				+ codigoAreaTel + ", numTel=" + numTel + ", fechaNacimiento=" + fechaNacimiento
				+ ", fechaUltimaCompra=" + fechaUltimaCompra + "]";
	}

}