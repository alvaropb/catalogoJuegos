package catalogoJuegos.modelo;

import java.util.ArrayList;

public interface ICrudable<T> {
	
	/**
	 * Metodo que devuelve una coleccion de <T>
	 * @return ArrayList<T> 
	 * @throws Exception
	 */
	public ArrayList<T> getAll() throws Exception;

}
