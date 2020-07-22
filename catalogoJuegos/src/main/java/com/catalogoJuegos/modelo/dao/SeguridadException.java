package com.catalogoJuegos.modelo.dao;

public class SeguridadException extends Exception{

	/**
	 * Clase que se lanza en caso de que se intente saltar la seguridad al actualizar sin permisos necesarios
	 */
	private static final long serialVersionUID = 1L;
	public static final String MENSAJE="No tiene permisos para efectuar esta operacion";
	public SeguridadException() {
		super(MENSAJE);

	}

	

}
