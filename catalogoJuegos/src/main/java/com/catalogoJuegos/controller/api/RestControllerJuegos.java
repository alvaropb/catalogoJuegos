package com.catalogoJuegos.controller.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.catalogoJuegos.modelo.dao.JuegoDAO;
import com.catalogoJuegos.modelo.impl.JuegoDAOImpl;
import com.catalogoJuegos.modelo.pojo.Juego;
import com.google.gson.Gson;

/**
 * Servlet implementation class RestController
 */
@WebServlet("/api/juego/*")
public class RestControllerJuegos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(RestControllerJuegos.class);
	private static final JuegoDAO dao = JuegoDAOImpl.getInstance();
	private static PrintWriter out = null;
	private int id = 0;
	private int status;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		LOG.debug("Se inicializa el Servlet");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		LOG.debug("Se para el Servlet");
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		out = response.getWriter();

		try {

			String[] uri = request.getPathInfo().split("/");
			
			if (uri.length > 0) {
				id = Integer.parseInt(uri[1]);
			}

			super.service(request, response);
			response.setStatus(status);
		} catch (Exception e) {
			LOG.error(e);
			status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			response.setStatus(status);
		} finally {

			out.flush();
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cadena = "";
		Gson gson = new Gson();

		try {

			if (id == 0) {
				cadena = gson.toJson(dao.getAll());
				out.print(cadena);

				if ("".equals(cadena)) {
					status = HttpServletResponse.SC_NO_CONTENT;
				} else {
					status = HttpServletResponse.SC_OK;
				}
			} else {
				Juego j = new Juego();
				j.setId(id);
				cadena = gson.toJson(dao.getById(j));

				if ("".equals(cadena)) {
					status = HttpServletResponse.SC_NO_CONTENT;
				} else {
					status = HttpServletResponse.SC_OK;
					out.print(cadena);
				}

			}

		} catch (Exception e) {
			LOG.error(e);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//recuperar el juego a insertar de la request
		BufferedReader br= request.getReader();
		Juego juego=new Gson().fromJson(br, Juego.class);//TODO si fechaValidacion!=null, falla al parsear el objeto
		
		try {
			juego=dao.insert(juego);
			status=HttpServletResponse.SC_CREATED;
			out.print(new Gson().toJson(juego));
			
		} catch (Exception e) {
			LOG.error(e);
			status=HttpServletResponse.SC_CONFLICT;
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cadena = "";

		// recoger el objeto json del body
		BufferedReader br = request.getReader();
		Juego juego = new Gson().fromJson(br, Juego.class);

		// TODO validar

		try {
			juego = dao.update(juego,juego.getUsuario());
			out.print(new Gson().toJson(juego));
			status = HttpServletResponse.SC_OK;
		} catch (Exception e) {
			LOG.error(e);
			status = HttpServletResponse.SC_CONFLICT;
		}

	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
