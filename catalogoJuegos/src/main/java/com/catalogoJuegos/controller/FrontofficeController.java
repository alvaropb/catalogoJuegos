package com.catalogoJuegos.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.catalogoJuegos.modelo.impl.JuegoDAOImpl;
import com.catalogoJuegos.modelo.pojo.Resumen;
import com.catalogoJuegos.modelo.pojo.Usuario;
import com.catalogoJuegos.utilidades.Constantes;

/**Servlet que muestra un resumen para el usuario conectado de juegos totales, ptes de validar y validados.
 * Servlet implementation class FrontofficeController
 */
@WebServlet("/views/frontoffice/inicio")
public class FrontofficeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(FrontofficeController.class);
	private static final JuegoDAOImpl juegoDAO = JuegoDAOImpl.getInstance();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.trace("Entra en FrontofficeController doGet() request:" + request);
		// TODO cargar en vaiables los datos que queramos del dao
		Resumen resumen =null;
		Usuario usu =null;
		try {
			// sacar id_usuario de la sesion
			usu = (Usuario) request.getSession().getAttribute(Constantes.USUARIO);
			resumen = juegoDAO.getResumenById(usu.getId());
		} catch (Exception e) {
			LOG.error(e);
		}finally {
			request.setAttribute(Constantes.RESUMEN, resumen);
			
			// redireccion
			request.getRequestDispatcher(Constantes.INDEX).forward(request, response);
			
		}

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
