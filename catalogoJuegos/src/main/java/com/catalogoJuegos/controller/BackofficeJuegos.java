package com.catalogoJuegos.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.catalogoJuegos.modelo.impl.JuegoDAOImpl;
import com.catalogoJuegos.modelo.pojo.Juego;
import com.catalogoJuegos.utilidades.Constantes;

/**
 * Servlet implementation class BackofficeJuegos
 */
@WebServlet("/views/backoffice/juegos")
public class BackofficeJuegos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(BackofficeJuegos.class);
	private final static JuegoDAOImpl dao = JuegoDAOImpl.getInstance();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Juego> juegos = null;
		// cargar todos los juegos estren validados o no.
		try {

			juegos = new ArrayList<Juego>();
			juegos = dao.getAll(true);

		} catch (Exception e) {
			LOG.error(e);
		} finally {
			request.setAttribute(Constantes.JUEGOS, juegos);
			request.getRequestDispatcher(Constantes.JUEGOS_JSP).forward(request, response);
		}

	}

	/**
	 * 
	 * Metodo que recoge todos los atributos de la request y filtra los que se 
	 * corresponden a los checkbox marcados de juegos que se quieren validar
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Integer> juegosAValidar = new ArrayList<Integer>();
		try {
			Enumeration<String> nombresAtributos = request.getParameterNames();
			// iterar sobre coleccion de nombres para guardar los id de los juegos en un
			// array
			while (nombresAtributos.hasMoreElements()) {
				String nombre = (String) nombresAtributos.nextElement();
				if (nombre.contains("validarJuego")) {//El formato del nombre del parametro que buscamos es validarJuego# 

					juegosAValidar.add(Integer.parseInt(nombre.split("validarJuego")[1]));// sacamos el id del nombre

				}
			}
			// llamar al dao
			dao.validate(juegosAValidar);

		} catch (Exception e) {
			LOG.error(e);
		} finally {
			request.getRequestDispatcher(Constantes.INICIO).forward(request, response);//redirigimos a inicio del backoffice
		}
	}

}
