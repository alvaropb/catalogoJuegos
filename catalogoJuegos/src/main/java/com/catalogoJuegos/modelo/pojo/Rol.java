package com.catalogoJuegos.modelo.pojo;

/**
 * Clase que relaciona el rol de la tabla roles con el objeto Rol de la clase java
 * @author javaee
 *
 */
public class Rol {
	public static final int ADMINISTRADOR=2;
	public static final int USUARIO=1;
	private int id;
	private String nombre;
	
	public Rol() {
		super();
		this.id=0;
		this.nombre="";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Rol [id=" + id + ", nombre=" + nombre + "]";
	}

	
}
