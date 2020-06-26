package com.catalogoJuegos.modelo.dao;

import java.util.ArrayList;

import com.catalogoJuegos.modelo.ICrudable;
import com.catalogoJuegos.modelo.pojo.Juego;

public interface JuegoDAO extends ICrudable<Juego> {
	/**
	 * Metodo que retorna los ultimos juegos
	 * @param num
	 * @return ArrayList<Juego> con los ultimos juegos
	 * @throws Exception
	 */
	public ArrayList<Juego> getAll(int num) throws Exception;
	
	/**
	 * Metodo que retorna un listado de juegos de una categoria
	 * @param id
	 * @param limite
	 * @return ArrayList<Juego>
	 * @throws Exception
	 */
	public ArrayList<Juego> getByCategoria(int id, int limite) throws Exception;
}
