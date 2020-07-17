package com.catalogoJuegos.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.catalogoJuegos.utilidades.Constantes;

/**
 * Servlet implementation class ProductosFrontofficeController
 */
@WebServlet("/views/frontoffice/productos")
public class JuegosFrontofficeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recoger parametros
		String validados=request.getParameter(Constantes.VALIDAR);
		
		// buscar productos validados o sin validar
		if (validados==null) {	
			//productos sin validar
			request.setAttribute(Constantes.JUEGOS, "Juegos sin validar");
		}else {
			//productos validados
			request.setAttribute(Constantes.JUEGOS, "Juegos validados");
			
		}
		//redirigir a productos.jsp
		request.getRequestDispatcher(Constantes.JUEGOS_JSP).forward(request, response);

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
