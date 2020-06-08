package catalogoJuegos.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import catalogoJuegos.modelo.Juego;

import catalogoJuegos.modelo.JuegoDAOImpl;

/**
 * Servlet implementation class CatalogoInicialController
 */
@WebServlet(description = "catalogo inicial", urlPatterns = { "/inicio" })
public class CatalogoInicialController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		ArrayList<Juego>juegos=new ArrayList();
		JuegoDAOImpl dao=JuegoDAOImpl.getInstance();
		try {
			juegos=dao.getAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			request.setAttribute("juegos", juegos);
			request.getRequestDispatcher("listado-juegos.jsp").forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
