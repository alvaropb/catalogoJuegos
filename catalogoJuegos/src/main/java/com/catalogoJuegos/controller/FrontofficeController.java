package com.catalogoJuegos.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.catalogoJuegos.utilidades.Constantes;

/**
 * Servlet implementation class FrontofficeController
 */
@WebServlet("/views/frontoffice/inicio")
public class FrontofficeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG=Logger.getLogger(FrontofficeController.class);
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.trace("Entra en FrontofficeController doGet() request:"+request);
		//TODO cargar en vaiables los datos que queramos del dao
		
		request.setAttribute("juegos_alta", "listado de juegos de alta");
		request.setAttribute("juegos_validar", "listado de juegos a validar");
		
		//redireccion
		request.getRequestDispatcher(Constantes.INDEX).forward(request, response);
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		LOG.trace("sale de FrontofficeController doGet() response:"+response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
