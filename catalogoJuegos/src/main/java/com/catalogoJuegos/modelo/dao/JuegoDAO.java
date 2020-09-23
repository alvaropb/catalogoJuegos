package com.catalogoJuegos.modelo.dao;

import java.util.ArrayList;

import com.catalogoJuegos.modelo.ICrudable;
import com.catalogoJuegos.modelo.pojo.Juego;
import com.catalogoJuegos.modelo.pojo.Resumen;
import com.catalogoJuegos.modelo.pojo.Usuario;

public interface JuegoDAO extends ICrudable<Juego> {
	/**
	 * Metodo que retorna los ultimos juegos
	 * @param num
	 * @return ArrayList<Juego> con los ultimos juegos
	 * @throws Exception
	 */
	public ArrayList<Juego> getAll(int num) throws Exception;
	
	/**
	 * Metodo que retorna todos los juegos
	 * @param todos booleano para indicar que son todos los juegos
	 * @return ArrayList<Juego> con todos los juegos
	 * @throws Exception
	 */
	public ArrayList<Juego> getAll(boolean todos ) throws Exception;
	
	/**
	 * Metodo que retorna un listado de juegos de una categoria
	 * @param id
	 * @param limite
	 * @return ArrayList<Juego>
	 * @throws Exception
	 */
	public ArrayList<Juego> getByCategoria(int id, int limite) throws Exception;
	
	/**
	 * Metodo que retorna un listado de juegos de un usuario
	 * @param id el id del usuario para buscar sus juegos
	 * @param isValidado buscara juegos validados o no
	 * @return ArrayList<Juego>
	 * @throws Exception
	 */
	public ArrayList<Juego> getAllByUser(int id,boolean isValidado)throws Exception;
	
	/**
	 * Metodo que retorna un resumen con los juegos totales, validados y pdtes de validar
	 * @param id
	 * @return Resumen
	 * @throws Exception
	 */
	public Resumen getResumenById(int id)throws Exception;
	/**
	 * Metodo que elimina un registro siempre y cuando se tengan permisos
	 * @param idJuego
	 * @param idUsuario
	 * @return
	 * @throws SeguridadException
	 */
	public Juego delete(int idJuego, int idUsuario)throws Exception;
	
	/**
	 * Metodo que retorna un juego por su id_usuario
	 * @param idProducto
	 * @param idUsuario
	 * @return Juego
	 * @throws SeguridadException
	 * @throws Exception 
	 */
	public Juego getById(int idJuego, int idUsuario) throws SeguridadException, Exception;
	/**
	 * Metodo que valida un listado de juegos
	 * @param juegosAValidar listado de ids a validar
	 * @throws Exception
	 */
	public void validate(ArrayList<Integer> juegosAValidar)throws Exception;
	
	/**
	 * 
	 * @param Juego t juego a actualizar
	 * @param Usuario u usuario conectado
	 * @return El juego actualizado
	 * @throws Exception
	 * @throws SeguridadException
	 */
	public Juego update(Juego t, Usuario u) throws Exception,SeguridadException;
}
