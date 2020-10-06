package com.catalogoJuegos.controller.backoffice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.catalogoJuegos.modelo.impl.CategoriaDAOImpl;
import com.catalogoJuegos.modelo.pojo.Categoria;
import com.catalogoJuegos.utilidades.Alerta;
import com.catalogoJuegos.utilidades.Constantes;


/**
 * Servlet para crear, editar o eliminar una categoria
 * Servlet implementation class CategoriaController
 */
@WebServlet("/views/backoffice/categorias")
public class CategoriaBackofficeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(CategoriaBackofficeController.class);
	private static final CategoriaDAOImpl categoriaDao = CategoriaDAOImpl.getInstance();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Categoria> categorias = new ArrayList<Categoria>();
		Alerta alerta = new Alerta();
		Categoria categoria = new Categoria();
		String msj = "";
		// recoger parametros
		String id = request.getParameter(Constantes.ID_CATEGORIA);
		String operacion=request.getParameter(Constantes.OPERACION);
		String view = Constantes.LISTADO_CATEGORIAS_JSP;

	
		try {
			if ( id != null ) {// crear, editar o eliminar
				int idCat = Integer.parseInt(id);
				view = Constantes.CREAR_CATEGORIA;
				categoria.setId(idCat);
				

				
				if (idCat == 0 && operacion==null) {// creamos, ya que id==0
					msj = Constantes.MSJ_CREAR_CATEGORIA;


				} else if(idCat != 0 && operacion==null) {//Editamos, ya que id!=0 y operacion no viene informada
					msj = Constantes.MSJ_EDITAR_CATEGORIA + ": <b>" + "</b>";
					categoria=categoriaDao.getById(categoria);
					
					
				}else {//eliminar
					view=Constantes.LISTADO_CATEGORIAS_JSP;
					categoriaDao.delete(categoria);
					categorias = categoriaDao.getAll();
					alerta.setMensaje(Constantes.DELETE_CORRECTO);
					alerta.setTipo(Constantes.SUCCESS);
					request.setAttribute(Constantes.CATEGORIAS, categorias);
					request.setAttribute(Constantes.ALERTA, alerta);
				}
				
				
				

			}else {// listado de categorias

				categorias = categoriaDao.getAll();
				request.setAttribute(Constantes.CATEGORIAS, categorias);
			}

		}		
		catch (Exception e) {
			LOG.error(e);
			alerta.setMensaje(Constantes.ERROR_INESPERADO);
			alerta.setTipo(Constantes.DANGER);
			if (e.getMessage().contains(Constantes.INTEGRITY_ERROR)) {
				alerta.setMensaje(Constantes.ERROR_INTEGRIDAD);
				alerta.setTipo(Constantes.DANGER);
			}
			request.setAttribute(Constantes.ALERTA, alerta);
		}
		 finally {
			request.setAttribute(Constantes.CATEGORIA, categoria);
			request.setAttribute(Constantes.MSJ, msj);
			
			request.getRequestDispatcher(view).forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Categoria categoria = new Categoria();
		ArrayList<Categoria> categorias = new ArrayList<Categoria>();
		Alerta alerta = new Alerta();
		String view = "";
		// recoger el nombre desde la vista

		String nombre = request.getParameter(Constantes.CATEGORIA_NOMBRE);
		String idCategoria = request.getParameter(Constantes.ID_CATEGORIA);

		try {
			categoria.setNombre(nombre);

			if (idCategoria != null) {
				categoria.setId(Integer.parseInt(idCategoria));
			}
			if (categoria.getId() == 0) {// insertamos
				alerta.setMensaje(Constantes.INSERT_ERRONEO);
				categoria = categoriaDao.insert(categoria);
				
				alerta.setMensaje(Constantes.INSERT_CORRECTO);

			} else {// editamos
				alerta.setMensaje(Constantes.UPDATE_ERRONEO);
				categoria = categoriaDao.update(categoria);
				alerta.setMensaje(Constantes.UPDATE_CORRECTO);
			}

			alerta.setTipo(Constantes.SUCCESS);

			view = Constantes.LISTADO_CATEGORIAS_JSP;
			// recuperamos las categorias para enviarlas al listado
			categorias = categoriaDao.getAll();
		} catch (Exception e) {
			LOG.error(e);
			// le mandamos de vuelta a crear/editar
			if (e.getMessage().contains(Constantes.DUPLICATE_ENTRY)) {
				alerta.setMensaje(Constantes.NOMBRE_YA_EXISTE);
			}
			alerta.setTipo(Constantes.DANGER);
			request.setAttribute(Constantes.CATEGORIA, categoria);
			view = Constantes.CREAR_CATEGORIA;

		} finally {
			request.getServletContext().setAttribute(Constantes.CATEGORIAS,categorias );
			request.setAttribute(Constantes.ALERTA, alerta);
			request.setAttribute(Constantes.CATEGORIAS, categorias);
			request.getRequestDispatcher(view).forward(request, response);

		}

	}

}
