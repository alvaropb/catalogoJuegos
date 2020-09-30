package com.catalogoJuegos.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.catalogoJuegos.modelo.impl.CategoriaDAOImpl;
import com.catalogoJuegos.modelo.impl.JuegoDAOImpl;
import com.catalogoJuegos.modelo.pojo.Categoria;
import com.catalogoJuegos.modelo.pojo.Juego;
import com.catalogoJuegos.utilidades.Alerta;
import com.catalogoJuegos.utilidades.Constantes;

/**
 * Servlet implementation class CatalogoInicialController
 */
@WebServlet(description = "catalogo inicial", urlPatterns = { "/inicio" })
public class CatalogoInicialController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final JuegoDAOImpl juegoDAO=JuegoDAOImpl.getInstance();
	private static final CategoriaDAOImpl categoriaDAO=CategoriaDAOImpl.getInstance();
	private final static Logger LOG = Logger.getLogger(CatalogoInicialController.class);

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
/**
 * Método que carga en funcion de la id categoria: 
 * Si no viene informada la id-&gt carga los ultimos 10 juegos de cualquier categoria
 * Si id es 0, Listado con todos los juegos
 * Si id es -1 &gt todos los juegos agrupados por categoria.
 * Si id viene informada y es &gt 0 -&gt Ultimos 10 juegos de la categoria informada
 * @param request
 * @param response
 * @throws ServletException
 * @throws IOException
 */
	protected void doProccess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		ArrayList<Juego> juegos = new ArrayList<Juego>();
		ArrayList<Categoria>categorias=new ArrayList<Categoria>();
		Categoria c=new Categoria();

		Alerta alerta=new Alerta();
		
		// recoger paramentros
		String idCategoria=request.getParameter(Constantes.ID_CATEGORIA);
		String categoriaNombre=request.getParameter(Constantes.CATEGORIA_NOMBRE);
				
		//redireccion
		
		RequestDispatcher dispatcher = null;
		dispatcher= request.getRequestDispatcher(Constantes.LISTADO_JUEGOS_INICIAL_JSP);
		try {
			
			if (idCategoria==null || idCategoria.isEmpty()) {// si no viene informada la id, se muestran los ultimos 10 juegos
				juegos = juegoDAO.getAll(Constantes.DIEZ);
				c.setJuegos(juegos);
				c.setNombre("");
				
				categorias.add(c);
				
				
			}else if("0".equals(idCategoria)) {// listado de juegos
				juegos = juegoDAO.getAll();
				dispatcher= request.getRequestDispatcher(Constantes.LISTADO_JUEGOS_JSP);
				
			}else if("-1".equals(idCategoria)) {// todos los juegos por categoria
				categorias=categoriaDAO.getAllWithProducts();
			}
			else {
				int id=Integer.parseInt(idCategoria);
				juegos=juegoDAO.getByCategoria(id,Constantes.DIEZ);
				c.setId(id);
				c.setNombre(categoriaNombre);
				c.setJuegos(juegos);
				categorias.add(c);
				
			}

		} catch (Exception e) {
			alerta.setMensaje(Constantes.ERROR_INESPERADO);
			alerta.setTipo(Constantes.DANGER);
			request.setAttribute(Constantes.ALERTA, alerta);
			LOG.error(e);
		} finally {
			
			request.setAttribute(Constantes.CATEGORIA_NOMBRE, (categoriaNombre==null)?"Todas las categorias":"<b>"+juegos.size()+"</b> juego"+((juegos.size()==1)?"":"s")+" de la categoria <b>"+ categoriaNombre+"</b>" );
			request.setAttribute(Constantes.JUEGOS, juegos);// para el listado de juegos
			request.setAttribute(Constantes.JUEGOS_POR_CATEGORIA, categorias);// para el listado de cards			
			dispatcher.forward(request, response);
			
		}
	}

}
