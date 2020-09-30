package com.catalogoJuegos.modelo.pojo;

/**
 * Clase que relaciona la vista resumen con un objeto resumen java
 * @author javaee
 *
 */
public class Resumen {

	private int idUsuario;
	private int juegosTotales;
	private int juegosValidados;
	private int juegosPendientes;
	
	public Resumen() {
		super();
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public int getJuegosTotales() {
		return juegosTotales;
	}
	public void setJuegosTotales(int juegosTotales) {
		this.juegosTotales = juegosTotales;
	}
	public int getJuegosValidados() {
		return juegosValidados;
	}
	public void setJuegosValidados(int juegosValidados) {
		this.juegosValidados = juegosValidados;
	}
	public int getJuegosPendientes() {
		return juegosPendientes;
	}
	public void setJuegosPendientes(int juegosPendientes) {
		this.juegosPendientes = juegosPendientes;
	}
	@Override
	public String toString() {
		return "Resumen [idUsuario=" + idUsuario + ", juegosTotales=" + juegosTotales + ", juegosValidados="
				+ juegosValidados + ", juegosPendientes=" + juegosPendientes + "]";
	}
	
	
}
