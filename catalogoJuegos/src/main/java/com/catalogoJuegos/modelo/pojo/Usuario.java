package com.catalogoJuegos.modelo.pojo;
/**
 * Clase que relaciona la tabla usuario con el objeto java usuario
 * @author javaee
 *
 */
public class Usuario {

	private int id;
	private String nombre;
	private String pass;
	private String imagen;
	private Rol rol;
	

	public Usuario(String nombre) {
		this();
		this.nombre = nombre;
	}

	public Usuario() {
		super();
		this.id = 0;
		this.nombre = "";
		this.pass = "";
		this.imagen = "";
		this.rol=new Rol();
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

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", pass=" + pass + ", imagen=" + imagen + ", rol=" + rol
				+ "]";
	}

	

}
