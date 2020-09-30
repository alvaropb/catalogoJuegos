package com.catalogoJuegos.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.catalogoJuegos.modelo.impl.JuegoDAOImpl;
import com.catalogoJuegos.utilidades.Constantes;

/**
 * Servlet que carga estadisticas sobre juegos 
 */
@WebServlet("/views/backoffice/inicio")
public class BackofficeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG=Logger.getLogger(BackofficeController.class);
	private static final JuegoDAOImpl juegoDAO=JuegoDAOImpl.getInstance();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.trace("Entra en BackofficeController doGet() request:"+request);
		// cargar en atributos los datos que recogemos del DAO
		
		try {
			request.setAttribute("juegosTotales", juegoDAO.getNumTotales());
			request.setAttribute("juegosTotalesPendientes", juegoDAO.getNumTotalesPendientes());
			request.setAttribute("usuariosTotales", juegoDAO.getNumUsuarios());
		} catch (Exception e) {
			
			LOG.error(e);
		}
		request.setAttribute("datos2", "listado de datos 2");
		
		// redireccion 
		
		request.getRequestDispatcher(Constantes.INDEX).forward(request, response);
		LOG.trace("Entra en BackofficeController doGet() response:"+response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
