package com.catalogoJuegos.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.catalogoJuegos.utilidades.Alerta;
import com.catalogoJuegos.utilidades.Constantes;

/**
 * Servlet implementation class LogoutController
 */
@WebServlet(description = "Controller para logout", urlPatterns = { "/logout" })
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG=Logger.getLogger(LogoutController.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Alerta alerta=new Alerta();
		try {
			
			request.getSession().invalidate();
			
			// al salir de la session , guardar un entero con el num de usuarios y 
			//mostrarlo para que todos los usuarios conectados puedan ver el num total de usuarios
			
			int numUsus=(int) (request.getServletContext().getAttribute("numUsuLog"));
			request.getServletContext().setAttribute("numUsuLog",numUsus-1);
			
			
			alerta.setMensaje(Constantes.LOGOUT_CORRECTO);
			alerta.setTipo(Constantes.SUCCESS);
			
		} catch (Exception e) {
			LOG.error(e);
		}finally {
			request.getSession().setAttribute("alerta", alerta);
			request.getRequestDispatcher(Constantes.INICIO).forward(request, response);
			
		}
		
		

	}

}
