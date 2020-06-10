package catalogoJuegos.modelo;

import java.util.ArrayList;

public interface ICrudable<T> {
	
	/**
	 * Metodo que devuelve una coleccion de <T>
	 * @return ArrayList<T> 
	 * @throws Exception
	 */
	public ArrayList<T> getAll() throws Exception;
	
	/**
	 * Método que inserta un objeto <T>
	 * @param juego
	 * @return Juego
	 * @throws Exception
	 */
	public T insert(T t) throws Exception;
	 
	/**
	  * Método que busca y retorna el <T> buscado  
	  * @param t
	  * @return 
	  * @throws Exception
	  */
	public T getByName(T t)throws Exception;
	
	
	/**
	 * Método que busca y retorna un <T> 
	 * @param t
	 * @return <T>
	 * @throws Exception
	 */
	public T getById(T t) throws Exception;
	
	/**
	 * Método que actualiza un <T>
	 * @param <T>
	 * @return <T>
	 * @throws Exception
	 */
	public T update(T t)throws Exception;
	
	/**
	 * Método que borra un <T>
	 * @param <T>
	 * @return <T>
	 * @throws Exception
	 */
	public T delete(T t)throws Exception;

}
