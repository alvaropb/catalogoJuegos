package catalogoJuegos.modelo;

public class Juego {
	
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
