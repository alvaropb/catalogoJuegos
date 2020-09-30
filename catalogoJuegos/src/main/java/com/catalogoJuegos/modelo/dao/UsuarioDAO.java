package com.catalogoJuegos.modelo.dao;

import com.catalogoJuegos.modelo.ICrudable;
import com.catalogoJuegos.modelo.pojo.Usuario;

/**
 * Interfaz con metodos para usuario que no estan contemplados en la Interfaz ICrudable
 * @author javaee
 *
 */
public interface UsuarioDAO extends ICrudable<Usuario>{

	/**
	 * Metodo que comprueba que un usuario existe y su contraseña es correcta
	 * @param usuario
	 * @return el usuario buscado
	 * @throws Exception
	 */
	public Usuario usuarioExiste(Usuario usuario)throws Exception;
}
