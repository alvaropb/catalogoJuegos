package com.catalogoJuegos.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.catalogoJuegos.modelo.dao.JuegoDAO;
import com.catalogoJuegos.modelo.impl.JuegoDAOImpl;
import com.catalogoJuegos.modelo.pojo.Juego;
import com.google.gson.Gson;

/**
 * Servlet implementation class RestGSON
 */
@WebServlet("/views/ejemplos/rest-2")
public class RestGSON extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id=request.getParameter("id");
		
		PrintWriter out= response.getWriter();
		
		try {
			JuegoDAO dao=JuegoDAOImpl.getInstance();
			Juego j=new Juego();
			j.setId(Integer.parseInt(id));
			j=dao.getById(j);
			

			Gson gson = new Gson();
			
			String json = gson.toJson(j);
			out.print(json);
			if ("".equals(j.getNombre())) {
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			}else {
				response.setStatus(HttpServletResponse.SC_OK);
			}
			
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}finally {
			out.flush();
		}
		
		

		

		
		
		
		
	}

}
