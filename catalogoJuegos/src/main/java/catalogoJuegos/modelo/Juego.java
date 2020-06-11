package catalogoJuegos.modelo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Juego {
	
	@NotNull(message = "Nombre no puede ser null")
	@Size(min = 3, max = 100, message 
    = "Debe tener entre 3 y 100 caracteres")
	private String nombre;
	
	private int id;
	
	
	public Juego() {
		super();
		this.nombre = "";
		this.id = 0;
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



	@Override
	public String toString() {
		return "Juego [nombre=" + nombre + ", id=" + id + "]";
	}
	
	
	
	

}
