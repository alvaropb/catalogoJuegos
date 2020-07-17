package com.catalogoJuegos.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.catalogoJuegos.utilidades.Constantes;

/**
 * Servlet implementation class CrearProductosFrontofficeController
 */
@WebServlet("/views/frontoffice/crear-juegos")
public class CrearJuegosFrontofficeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.getRequestDispatcher(Constantes.FORMULARIO).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//recoger parametros de formulario.jsp
		String nombre=request.getParameter(Constantes.NOMBRE);
		
		//validar parametros
		
		
		
		//redirigir
		request.setAttribute(Constantes.NOMBRE, nombre);
		request.getRequestDispatcher(Constantes.JUEGOS_JSP).forward(request, response);
	}

}
