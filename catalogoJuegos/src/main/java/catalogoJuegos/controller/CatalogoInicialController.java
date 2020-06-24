package catalogoJuegos.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import catalogoJuegos.modelo.impl.CategoriaDAOImpl;
import catalogoJuegos.modelo.impl.JuegoDAOImpl;
import catalogoJuegos.modelo.pojo.Categoria;
import catalogoJuegos.modelo.pojo.Juego;
import catalogoJuegos.utilidades.Alerta;
import catalogoJuegos.utilidades.Constantes;

/**
 * Servlet implementation class CatalogoInicialController
 */
@WebServlet(description = "catalogo inicial", urlPatterns = { "/inicio" })
public class CatalogoInicialController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final JuegoDAOImpl juegoDAO=JuegoDAOImpl.getInstance();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProccess(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doProccess(request, response);
	}

	protected void doProccess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Juego> juegos = new ArrayList<Juego>();

		Alerta alerta=new Alerta();
		
		// recoger paramentros
		String idCategoria=request.getParameter(Constantes.ID_CATEGORIA);
		String categoriaNombre=request.getParameter(Constantes.CATEGORIA_NOMBRE);
				
		//redireccion
		
		RequestDispatcher dispatcher = null;
		
		try {
			if (idCategoria==null || idCategoria.isEmpty()) {// si no viene informada la id, se muestran los ultimos 10 juegos
				juegos = juegoDAO.getAll(Constantes.DIEZ);
				dispatcher= request.getRequestDispatcher(Constantes.LISTADO_JUEGOS_INICIAL_JSP);
			}else if("0".equals(idCategoria)) {
				juegos = juegoDAO.getAll();
				dispatcher= request.getRequestDispatcher(Constantes.LISTADO_JUEGOS_JSP);
			}
			
			else {
				int id=Integer.parseInt(idCategoria);
				juegos=juegoDAO.getByCategoria(id,Constantes.DIEZ);
				dispatcher= request.getRequestDispatcher(Constantes.LISTADO_JUEGOS_INICIAL_JSP);
			}

		} catch (Exception e) {
			alerta.setMensaje(Constantes.ERROR_INESPERADO);
			alerta.setTipo(Constantes.DANGER);
			request.setAttribute(Constantes.ALERTA, alerta);
			e.printStackTrace();
		} finally {
			
			request.setAttribute(Constantes.CATEGORIA_NOMBRE, (categoriaNombre==null)?"Todas las categorias":"<b>"+juegos.size()+"</b> juego"+((juegos.size()==1)?"":"s")+" de la categoria <b>"+ categoriaNombre+"</b>" );
			request.setAttribute(Constantes.JUEGOS, juegos);
			dispatcher.forward(request, response);
			
		}
	}

}
