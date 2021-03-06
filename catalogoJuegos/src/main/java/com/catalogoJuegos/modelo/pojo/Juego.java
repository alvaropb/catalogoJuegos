package com.catalogoJuegos.modelo.pojo;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Clase que relaciona la tabla juegos con objeto tipo juego en java
 * @author javaee
 *
 */
public class Juego {

	@NotNull(message = "Nombre no puede ser null")
	@Size(min = 3, max = 100, message = "Debe tener entre 3 y 100 caracteres")
	private String nombre;

	private int id;

	@DecimalMin(value = "0.1", message = "precio debe ser mayor que 0")
	private BigDecimal precio;

	private Categoria categoria;
	@Size(min = 0, max = 255, message = "Debe tener un maximo de 255 caracteres")
	private String imagen;
	
	private Date fechaValidacion;
	


	private Usuario usuario;


	public Juego() {
		super();
		this.nombre = "";
		this.id = 0;
		this.precio = BigDecimal.ZERO;
		this.categoria = new Categoria();
		this.imagen = "";
		this.setUsuario(new Usuario());
		this.fechaValidacion= null;
	}

	public Juego(String nombre) {
		this();
		this.nombre = nombre;

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	

	public Date getFechaValidacion() {
		return fechaValidacion;
	}

	public void setFechaValidacion(Date fechaValidacion) {
		this.fechaValidacion = fechaValidacion;
	}

	@Override
	public String toString() {
		return "Juego [nombre=" + nombre + ", id=" + id + ", precio=" + precio + ", categoria=" + categoria
				+ ", imagen=" + imagen + ", fechaValidacion=" + fechaValidacion + ", usuario=" + usuario + "]";
	}



	


}
