package com.catalogoJuegos.listenner;

import java.util.ArrayList;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

import com.catalogoJuegos.modelo.impl.CategoriaDAOImpl;
import com.catalogoJuegos.modelo.pojo.Categoria;
import com.catalogoJuegos.utilidades.Alerta;
import com.catalogoJuegos.utilidades.Constantes;

/**
 * Application Lifecycle Listener implementation class InicioListener
 *
 */
@WebListener
public class InicioListener implements ServletContextListener {
	private final static Logger LOG = Logger.getLogger(InicioListener.class);

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
			LOG.debug("inicio aplicacion");
		} catch (Exception e) {
			sce.getServletContext().setAttribute(Constantes.ALERTA,new Alerta(Constantes.ERROR_INESPERADO, Constantes.DANGER));
			
			LOG.fatal(e);
		}
    	sce.getServletContext().setAttribute(Constantes.CATEGORIAS, categorias);
    }
	
}
