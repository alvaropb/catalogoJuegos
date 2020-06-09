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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String vista = "";
		Alerta alerta = new Alerta();

		try {
			// recoger parametros
			String nombre = request.getParameter("nombre");
			Juego juego = new Juego(nombre);

			// llamar al DAO
			JuegoDAOImpl dao = JuegoDAOImpl.getInstance();

			// TODO comprobar que no exista ya en la BBDD
			Juego juegoExistente = new Juego();
			juegoExistente = dao.getByName(juego);

			if (juegoExistente != null && juegoExistente.getId() != 0) {

				alerta.setMensaje(Constantes.NOMBRE_YA_EXISTE + ": " + juegoExistente.getNombre());
				alerta.setTipo(Constantes.DANGER);
				throw new Exception(Constantes.NOMBRE_YA_EXISTE);

			}

			// TODO validacion lenght >3 lenght <100
			
			if (!Validaciones.longitud(nombre, Constantes.TRES, Constantes.CIEN)) {
				alerta.setMensaje(Constantes.NOMBRE_LONGITUD_INCORRECTA);
				alerta.setTipo(Constantes.WARNING);
				throw new Exception(Constantes.NOMBRE_LONGITUD_INCORRECTA);
			}

			dao.insert(juego);
			alerta.setMensaje(Constantes.INSERT_CORRECTO + ": " + juego.getNombre());
			alerta.setTipo(Constantes.SUCCESS);

			vista = "inicio";
		} catch (Exception e) {

			e.printStackTrace();
			vista = "crear-juego.jsp";
		} finally {
			request.setAttribute("alerta", alerta);
			request.getRequestDispatcher(vista).forward(request, response);
		}

	}

}
