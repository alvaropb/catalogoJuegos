package catalogoJuegos.modelo.dao;

import java.util.ArrayList;

import catalogoJuegos.modelo.ICrudable;
import catalogoJuegos.modelo.pojo.Categoria;

public interface CategoriaDAO extends ICrudable<Categoria> {

	/**
	 * Metodo que devuelve un listado de juegos agrupados por categoria
	 * @return ArrayList<Categoria>
	 * @throws Exception
	 */
	public ArrayList<Categoria> getAllWithProducts()throws Exception ;
}
