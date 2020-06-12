package catalogoJuegos.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import catalogoJuegos.modelo.Usuario;
import catalogoJuegos.modelo.UsuarioDAOImpl;
import catalogoJuegos.utilidades.Alerta;
import catalogoJuegos.utilidades.Constantes;

/**
 * Servlet implementation class LoginController
 */
@WebServlet(description = "Controlador para el login", urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher(Constantes.LOGIN).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Alerta alerta = new Alerta();
		String vista="";

		// recoger parametros
		String nombre = request.getParameter("nombre");
		String pass = request.getParameter("pass");
		Usuario usuario = new Usuario();
		usuario.setNombre(nombre);
		usuario.setPass(pass);


		// llamar al dao
		UsuarioDAOImpl dao = UsuarioDAOImpl.getInstance();
		try {
			usuario = dao.usuarioExiste(usuario);
			if (usuario.getId()==0) {
				alerta.setMensaje(Constantes.LOGIN_ERRONEO);
				alerta.setTipo(Constantes.DANGER);
				vista=Constantes.LOGIN;
			}else {	// si existe


				HttpSession sesion = request.getSession();

				sesion.setAttribute("usuario", usuario);
				sesion.setMaxInactiveInterval(1 * 60 * 60);// 60 minutos
				
				alerta.setMensaje(Constantes.LOGIN_CORRECTO);
				alerta.setTipo(Constantes.SUCCESS);
				vista=Constantes.INICIO;
			}// fin usuario existe
		} catch (Exception e) {

			e.printStackTrace();
		}finally {
			// redirigir a la pagina principal
			request.getSession().setAttribute("alerta", alerta);
			request.getRequestDispatcher(vista).forward(request, response);
		}
		



	}

}
