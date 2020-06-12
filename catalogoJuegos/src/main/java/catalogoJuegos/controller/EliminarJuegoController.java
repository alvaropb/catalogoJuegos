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

/**
 * Servlet implementation class EliminarJuegoController
 */
@WebServlet(description = "Servlet para eliminar juegos", urlPatterns = { "/eliminar-juego" })
public class EliminarJuegoController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		Juego juegoDelete=new Juego();
		String id=request.getParameter("id");
		juegoDelete.setId(Integer.parseInt(id));
		Alerta alerta=new Alerta();
		Juego juegoR=new Juego();
		String vista="";
		
		JuegoDAOImpl dao=JuegoDAOImpl.getInstance();
		try {
			juegoR=new Juego();
			juegoR=dao.delete(juegoDelete);
			alerta.setMensaje(Constantes.DELETE_CORRECTO+": "+"["+juegoR.getId()+"] "+juegoR.getNombre());
			alerta.setTipo(Constantes.SUCCESS);
			vista=Constantes.INICIO;
			
		} catch (Exception e) {
			alerta.setMensaje(Constantes.DELETE_ERRONEO);
			alerta.setTipo(Constantes.DANGER);
		}finally {
			request.getSession().setAttribute("alerta", alerta);
			request.setAttribute("juego", juegoR);
			request.getRequestDispatcher(vista).forward(request, response);
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
