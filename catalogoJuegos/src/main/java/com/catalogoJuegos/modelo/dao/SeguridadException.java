package com.catalogoJuegos.modelo.dao;

public class SeguridadException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String MENSAJE="No tiene permisos para efectuar esta operacion";
	public SeguridadException() {
		super(MENSAJE);

	}

	

}
