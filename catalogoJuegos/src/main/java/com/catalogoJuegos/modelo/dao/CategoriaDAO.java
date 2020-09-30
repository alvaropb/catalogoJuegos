package com.catalogoJuegos.modelo.dao;

import java.util.ArrayList;

import com.catalogoJuegos.modelo.ICrudable;
import com.catalogoJuegos.modelo.pojo.Categoria;

/**
 * Interfaz para agregar metodos especificos para las Categorias
 * @author javaee
 *
 */
public interface CategoriaDAO extends ICrudable<Categoria> {

	/**
	 * Metodo que devuelve un listado de juegos agrupados por categoria
	 * @return ArrayList<Categoria>
	 * @throws Exception
	 */
	public ArrayList<Categoria> getAllWithProducts()throws Exception ;
}
