package com.catalogoJuegos.controller.backoffice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.catalogoJuegos.modelo.impl.UsuarioDAOImpl;
import com.catalogoJuegos.utilidades.Constantes;

/**
 * Servlet que se encarga de la gestion de usuarios Servlet implementation class
 * BackofficeUsuarios
 */
@WebServlet("/views/backoffice/usuarios")
public class BackofficeUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final UsuarioDAOImpl usuarioDAO = UsuarioDAOImpl.getInstance();
	private final static Logger LOG = Logger.getLogger(BackofficeUsuarios.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		try {
			request.setAttribute(Constantes.USUARIOS, usuarioDAO.getAll());
		} catch (Exception e) {
			LOG.error(e);
		} finally {
			request.getRequestDispatcher(Constantes.BACKOFFICE_USUARIOS_JSP).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
