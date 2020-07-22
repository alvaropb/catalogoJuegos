package com.catalogoJuegos.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.log4j.Logger;

import com.catalogoJuegos.modelo.impl.JuegoDAOImpl;
import com.catalogoJuegos.modelo.pojo.Categoria;
import com.catalogoJuegos.modelo.pojo.Juego;
import com.catalogoJuegos.modelo.pojo.Usuario;
import com.catalogoJuegos.utilidades.Alerta;
import com.catalogoJuegos.utilidades.Constantes;

/**
 * Servlet implementation class CrearProductosFrontofficeController
 */
@WebServlet("/views/frontoffice/crear-juegos")
public class CrearJuegosFrontofficeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(CrearJuegosFrontofficeController.class);
	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static Validator validator = factory.getValidator();
	private static final JuegoDAOImpl juegoDAO=JuegoDAOImpl.getInstance();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// crear juego inicializado a 0
		Juego juego=new Juego();
		String idParam=request.getParameter(Constantes.ID);
		int id=0;
		// recoger las categorias
		ArrayList<Categoria>categorias=new ArrayList<Categoria>();
		
		try {
			
			if (idParam==null) {
				//creamos juego
			}else {
				//editamos juego
				id=Integer.parseInt(idParam);
				juego.setId(id);
				juego=juegoDAO.getById(juego);
			}

			categorias=(ArrayList<Categoria>) request.getServletContext().getAttribute(Constantes.CATEGORIAS);
		} catch (Exception e) {
			LOG.error(e);
		} finally {
			request.setAttribute(Constantes.JUEGO,juego );
			request.setAttribute(Constantes.CATEGORIAS,categorias );
			// redirigir a formulario.jsp
			request.getRequestDispatcher(Constantes.FORMULARIO).forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Juego juego=null;
		String listadoErrores="";
		Alerta alerta=new Alerta();// TODO meter alert en cabecera-frontoffice
		//recoger usuario de la sesion
		Usuario usuario=new Usuario();
		usuario=(Usuario) request.getSession().getAttribute(Constantes.USUARIO);
		
		// recoger parametros
		String nombre = request.getParameter("nombre");//
		String id = request.getParameter("id");//Creamos, asi que la id se genera
		String precio=request.getParameter("precio");//
		String imagen = request.getParameter("imagen");//
		int idCategoria=Integer.valueOf(request.getParameter("idCategoria"));//
		
		//setear juego
		juego = new Juego(nombre);
		juego.setImagen(imagen);
		juego.getCategoria().setId(idCategoria);
		
		if ( precio!=null && !precio.isEmpty() ) {
			juego.setPrecio(BigDecimal.valueOf(Double.valueOf(precio)));
		}

		juego.setUsuario(usuario);
		
		
		
		try {
			if (id!=null) {
				//parsear para setear en juego y editar
				juego.setId(Integer.parseInt(id));
			}
			
			
			// validar parametros
			Set<ConstraintViolation<Juego>> errores=validator.validate(juego);
			if (errores.size()>0) {
				for (ConstraintViolation<Juego> constraintViolation : errores) {
					listadoErrores+="<p><strong>"+constraintViolation.getPropertyPath()+"</strong>"+constraintViolation.getMessage()+"</p>";
				}
				alerta.setTipo(Constantes.DANGER);
				alerta.setMensaje(listadoErrores);
				
			}else {
				if (juego.getId()!=0) {
					juego=juegoDAO.update(juego);
					//request.setAttribute(Constantes.VALIDAR, 1);
					
					//TODO redireccion. SI juego update validado-> enviar atributo "validar" String validados = request.getParameter(Constantes.VALIDAR);
//					if (condition) {
//						
//					}
				}else {
					juego = juegoDAO.insert(juego);
					
				}
			}
			
			//insertar
		} catch (Exception e) {
			LOG.error(e);
			alerta.setTipo(Constantes.DANGER);
			if (e.getMessage().contains(Constantes.DUPLICATE_ENTRY)) {
				listadoErrores+="<p><strong>"+Constantes.DUPLICATE_ENTRY+" </strong>Nombre ya existe</p>";
			}
			if (e.getMessage().contains("No tiene permisos para efectuar esta operacion")) {
				listadoErrores+=e.getMessage();
			}
			
			alerta.setMensaje(listadoErrores);
		}finally {
			
			//setear atributos
			request.setAttribute(Constantes.NOMBRE, nombre);
			request.getSession().setAttribute(Constantes.ALERTA, alerta);
			// redirigir, Esto peta fijo
			request.getRequestDispatcher("/views/frontoffice/juegos").forward(request, response);
			
		}
		
		
		
		
		
	}

}
