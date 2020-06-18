package catalogoJuegos.modelo;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Juego {
	
	@NotNull(message = "Nombre no puede ser null")
	@Size(min = 3, max = 100, message 
    = "Debe tener entre 3 y 100 caracteres")
	private String nombre;
	
	private int id;
	
	@DecimalMin(value = "0.1",message ="precio debe ser mayor que 0" )
	private BigDecimal precio;
	
	
	private Categoria categoria;
	
	public Juego() {
		super();
		this.nombre = "";
		this.id = 0;
		this.precio=BigDecimal.ZERO;
		this.categoria=new Categoria();
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



	@Override
	public String toString() {
		return "Juego [nombre=" + nombre + ", id=" + id + ", precio=" + precio + ", categoria=" + categoria + "]";
	}




}
