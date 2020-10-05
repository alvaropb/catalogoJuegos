package com.catalogoJuegos.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.catalogoJuegos.modelo.impl.JuegoDAOImpl;
import com.catalogoJuegos.modelo.pojo.Juego;
import com.catalogoJuegos.modelo.pojo.Usuario;
import com.catalogoJuegos.utilidades.Constantes;

/**
 * Servlet que muestra los juegos de un usuario conectado que esten pendientes de validar o no.
 * Servlet implementation class ProductosFrontofficeController
 */
@WebServlet("/views/frontoffice/juegos")
public class JuegosFrontofficeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(JuegosFrontofficeController.class);
	private static final JuegoDAOImpl juegoDAO = JuegoDAOImpl.getInstance();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Juego> juegos = new ArrayList<Juego>();

		// recoger parametros
		String validados = request.getParameter(Constantes.VALIDAR);


		LOG.trace("validados: "+validados);
		Usuario usu = (Usuario) request.getSession().getAttribute(Constantes.USUARIO);
		LOG.trace("usuario: "+usu);

		try {
			// buscar productos validados o sin validar
			if (validados == null) {
				// productos sin validar
				juegos = juegoDAO.getAllByUser(usu.getId(), false);

			} else if("1".equals(validados)) {
				// productos validados
				juegos = juegoDAO.getAllByUser(usu.getId(), true);
			}else {
				juegos = juegoDAO.getAllByUser(usu.getId(), false);
				juegos.addAll(juegoDAO.getAllByUser(usu.getId(), true));
			}

		} catch (Exception e) {
			LOG.error(e);
		} finally {
			request.setAttribute(Constantes.JUEGOS, juegos);
		}
		// redirigir a juegos.jsp
		request.getRequestDispatcher(Constantes.JUEGOS_JSP).forward(request, response);
	

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}

}
