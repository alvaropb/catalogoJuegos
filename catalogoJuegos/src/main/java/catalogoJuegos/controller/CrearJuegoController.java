package catalogoJuegos.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import catalogoJuegos.modelo.Juego;
import catalogoJuegos.modelo.JuegoDAOImpl;
import catalogoJuegos.utilidades.Alerta;
import catalogoJuegos.utilidades.Constantes;
import catalogoJuegos.utilidades.Validaciones;

/**
 * Servlet implementation class CrearJuegoController
 */
@WebServlet(description = "Controller para crear un registro de un nuevo juego", urlPatterns = { "/crear-juego" })
public class CrearJuegoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		String vista = "";
		Juego juego = new Juego();
		JuegoDAOImpl dao = JuegoDAOImpl.getInstance();
		// recoger id de la url
		try {
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

			request.setAttribute("juego", juego);
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
		try {
			// recoger parametros
			String nombre = request.getParameter("nombre");
			String id = request.getParameter("id");
			juego = new Juego(nombre);
			
			
			int idR = Integer.parseInt(id);
			juego.setId(idR);
			juego.setId(idR);

			// llamar al DAO
			JuegoDAOImpl dao = JuegoDAOImpl.getInstance();

			// TODO validacion lenght >3 lenght <100

			if (!Validaciones.longitud(nombre, Constantes.TRES, Constantes.CIEN)) {
				alerta.setMensaje(Constantes.NOMBRE_LONGITUD_INCORRECTA);
				alerta.setTipo(Constantes.WARNING);
				throw new Exception(Constantes.NOMBRE_LONGITUD_INCORRECTA);
			}
			// si id== 0 estamos insertando
			if ("0".equals(id) || id == null) {
				// asumir error, si insert ok, sobreescribe alerta con valores ok
				alerta.setMensaje(Constantes.INSERT_ERRONEO + ": " + juego.getNombre());
				alerta.setTipo(Constantes.DANGER);

				dao.insert(juego);

				alerta.setMensaje(Constantes.INSERT_CORRECTO + ": " + juego.getNombre());
				alerta.setTipo(Constantes.SUCCESS);
			} else {// estamos editando

				alerta.setMensaje(Constantes.UPDATE_ERRONEO + ": " + juego.getNombre());
				alerta.setTipo(Constantes.DANGER);


				dao.update(juego);
				alerta.setMensaje(Constantes.UPDATE_CORRECTO + ": " + juego.getNombre());
				alerta.setTipo(Constantes.SUCCESS);
			}

			vista = "inicio";
		} catch (Exception e) {

			e.printStackTrace();
			alerta.setMensaje(alerta.getMensaje() + e.getMessage());
			vista = Constantes.CREAR_JUEGO_JSP;
			request.setAttribute("juego", juego);
		} finally {
			request.getSession().setAttribute("alerta", alerta);
			request.getRequestDispatcher(vista).forward(request, response);
		}

	}

}
