package com.catalogoJuegos.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.catalogoJuegos.modelo.impl.UsuarioDAOImpl;
import com.catalogoJuegos.modelo.pojo.Rol;
import com.catalogoJuegos.modelo.pojo.Usuario;
import com.catalogoJuegos.utilidades.Alerta;
import com.catalogoJuegos.utilidades.Constantes;

/**
 * Servlet implementation class LoginController
 */
@WebServlet(description = "Controlador para el login", urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG=Logger.getLogger(LoginController.class );

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

				sesion.setAttribute(Constantes.USUARIO, usuario);
				sesion.setMaxInactiveInterval(1 * 60 * 60);// 60 minutos
				
				// al iniciar session , guardar un entero con el num de usuarios y 
				//mostrarlo para que todos los usuarios puedan ver el num total de usuarios
				//TODO revisar correcto funcionamiento  
				int numUsus=(int) (request.getServletContext().getAttribute("numUsuLog"));
				request.getServletContext().setAttribute("numUsuLog", numUsus+1);
				
				
				
				alerta.setMensaje(Constantes.LOGIN_CORRECTO);
				alerta.setTipo(Constantes.SUCCESS);
				// redirigir a backoffice si rol =2
				if (usuario.getRol().getId()==Rol.ADMINISTRADOR) {
					vista=Constantes.BACKOFFICE_CONTROLLER;
				}else {
					vista=Constantes.FRONTOFFICE_CONTROLLER; 
				}
				
			}// fin usuario existe
		} catch (Exception e) {

			LOG.error(e);
		}finally {
			// redirigir a la pagina principal
			request.getSession().setAttribute("alerta", alerta);
			request.getRequestDispatcher(vista).forward(request, response);
		}
		



	}

}
