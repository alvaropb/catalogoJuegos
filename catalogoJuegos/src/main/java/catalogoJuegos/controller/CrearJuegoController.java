package catalogoJuegos.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
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

import catalogoJuegos.modelo.Categoria;
import catalogoJuegos.modelo.CategoriaDAO;
import catalogoJuegos.modelo.CategoriaDAOImpl;
import catalogoJuegos.modelo.Juego;
import catalogoJuegos.modelo.JuegoDAOImpl;
import catalogoJuegos.utilidades.Alerta;
import catalogoJuegos.utilidades.Constantes;

/**
 * Servlet implementation class CrearJuegoController
 */
@WebServlet(description = "Controller para crear un registro de un nuevo juego", urlPatterns = { "/crear-juego" })
public class CrearJuegoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static Validator validator = factory.getValidator();
	private static JuegoDAOImpl dao = JuegoDAOImpl.getInstance();
	private static CategoriaDAO categoriaDao=CategoriaDAOImpl.getInstance();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String vista = "";
		Juego juego = new Juego();
		ArrayList<Categoria>categorias=new ArrayList<Categoria>();
		

		// recoger id de la url
		try {
			categorias=categoriaDao.getAll();
			String id = request.getParameter("id");
			if (id != null && !id.isEmpty()) {
				int idDao = Integer.parseInt(id);
				juego.setId(idDao);
				juego = dao.getById(juego);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			vista = Constantes.CREAR_JUEGO_JSP;
			// TODO cargar en un combo los valores de la tabla categorias
			request.setAttribute("juego", juego);
			request.setAttribute("categorias", categorias);
			request.getRequestDispatcher(vista).forward(request, response);

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String vista = "";
		Alerta alerta = new Alerta();
		Juego juego = new Juego();
		ArrayList<Categoria>categorias=new ArrayList<Categoria>();
		try {
			// recoger parametros
			String nombre = request.getParameter("nombre");
			String id = request.getParameter("id");
			String precio=request.getParameter("precio");
			String imagen = request.getParameter("imagen");
			int idCategoria=Integer.valueOf(request.getParameter("idCategoria")) ;
			
			
			
			juego = new Juego(nombre);
			juego.setImagen(imagen);
			juego.getCategoria().setId(idCategoria);
			// TODO investigar si se pierde datos por conversion de Double a BigDecimal
			if ( precio!=null && !precio.isEmpty() ) {
				juego.setPrecio(BigDecimal.valueOf(Double.valueOf(precio)));
			}
			
			// llamar al DAO
			dao = JuegoDAOImpl.getInstance();
			
			categorias=categoriaDao.getAll();


			
			// si id== 0 estamos insertando
			if ("0".equals(id) || id == null) {
				// asumir error, si insert ok, sobreescribe alerta con valores ok
				alerta.setMensaje(Constantes.INSERT_ERRONEO + ": " + juego.getNombre());
				alerta.setTipo(Constantes.DANGER);
				//validar pojo
				validaciones(alerta,juego);

				dao.insert(juego);

				alerta.setMensaje(Constantes.INSERT_CORRECTO + ": " + juego.getNombre());
				alerta.setTipo(Constantes.SUCCESS);
			} else {// estamos editando

				alerta.setMensaje(Constantes.UPDATE_ERRONEO + ": " + juego.getNombre());
				alerta.setTipo(Constantes.DANGER);
				
				int idR = Integer.parseInt(id);
				juego.setId(idR);
				//validar pojo
				validaciones(alerta,juego);

				dao.update(juego);
				
				alerta.setMensaje(Constantes.UPDATE_CORRECTO + ": " + juego.getNombre());
				alerta.setTipo(Constantes.SUCCESS);
			}//fin update
			
			// si update o insert ok, vamos al listado
			vista = "inicio";
		} catch (Exception e) {

			e.printStackTrace();
	
			vista = Constantes.CREAR_JUEGO_JSP;
			request.setAttribute("juego", juego);
			request.setAttribute("categorias", categorias);
		} finally {
			request.getSession().setAttribute("alerta", alerta);
			request.getRequestDispatcher(vista).forward(request, response);
		}

	}

	private void validaciones(Alerta alerta, Juego juego) throws Exception {
		Set<ConstraintViolation<Juego>> violations = validator.validate(juego);
		String errores = "";
		for (Iterator iterator = violations.iterator(); iterator.hasNext();) {
			ConstraintViolation<Juego> constraintViolation = (ConstraintViolation<Juego>) iterator.next();

			errores += "<p> <b>" + constraintViolation.getPropertyPath() + "</b>" + constraintViolation.getMessage()
					+ "</p>";
		}
		if (!violations.isEmpty()) {
			alerta.setMensaje(errores);
			alerta.setTipo(Constantes.DANGER);
			throw new Exception();
		}
	}

}
