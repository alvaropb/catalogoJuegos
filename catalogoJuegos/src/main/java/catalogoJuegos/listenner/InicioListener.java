package catalogoJuegos.listenner;

import java.util.ArrayList;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import catalogoJuegos.modelo.impl.CategoriaDAOImpl;
import catalogoJuegos.modelo.pojo.Categoria;
import catalogoJuegos.utilidades.Alerta;
import catalogoJuegos.utilidades.Constantes;

/**
 * Application Lifecycle Listener implementation class InicioListener
 *
 */
@WebListener
public class InicioListener implements ServletContextListener {


	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // Aqui cuando se destruye la aplicacion
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	CategoriaDAOImpl categoriaDAO=CategoriaDAOImpl.getInstance();
    	ArrayList<Categoria>categorias=new ArrayList<Categoria>();
    	
    	try {
			categorias=categoriaDAO.getAll();
		} catch (Exception e) {
			sce.getServletContext().setAttribute(Constantes.ALERTA,new Alerta(Constantes.ERROR_INESPERADO, Constantes.DANGER));
			e.printStackTrace();
		}
    	sce.getServletContext().setAttribute(Constantes.CATEGORIAS, categorias);
    }
	
}
