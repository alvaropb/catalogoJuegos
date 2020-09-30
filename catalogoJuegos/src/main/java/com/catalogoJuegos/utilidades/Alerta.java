package com.catalogoJuegos.utilidades;

/**
 * Clase para mostrar alertas al usuario
 * @see https://getbootstrap.com/docs/4.0/components/alerts/
 * @author javaee
 *
 */
public class Alerta {

	private String mensaje;
	private String tipo;

	public Alerta(String mensaje, String tipo) {
		this();
		this.mensaje = mensaje;
		this.tipo = tipo;
	}

	public Alerta() {
		super();
		this.mensaje = "";
		this.tipo = "";
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
