package com.catalogoJuegos.listenner;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.catalogoJuegos.modelo.pojo.Usuario;
import com.catalogoJuegos.utilidades.Constantes;




/**
 * 
 * Listener que: recupera el usuario conectado en la sesion y lo añade 
 * a un listado de usuarios conectados en el contexto de la aplicacion
 * Elimina un usuario del listado de usuarios conectados
 * Application Lifecycle Listener implementation class ListenerUsuarioLogeados
 *
 */
@WebListener
public class ListenerUsuarioLogeados implements HttpSessionListener, HttpSessionAttributeListener {

	private static HashMap<String, Usuario> hm = null;
	private final static Logger LOG = Logger.getLogger(ListenerUsuarioLogeados.class);
    /**
     * Default constructor. 
     */
    public ListenerUsuarioLogeados() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent arg0)  { 
        String nombre=arg0.getName();
        ServletContext  ctx=arg0.getSession().getServletContext();
        String idSession=arg0.getSession().getId();
        LOG.trace("Nuevo atributo en sesion:"+nombre);
        
        if (Constantes.USUARIO.equals(nombre)) {
       	 //recuperar el usuario 
       	 Usuario usuario=(Usuario) arg0.getValue();
       	 //recuperar el hashmap de usuarios del contexto de la aplicacion
       	 hm= (HashMap<String, Usuario>) ctx.getAttribute(Constantes.USUARIOS);
       	 //si es nulo, inicializarlo
       	 if (null==hm) {
				hm=new HashMap<String,Usuario>();
			}        	 
       	 //meter el usuario en el hasmap
       	 hm.put(idSession, usuario);
       	 //setear el hashmap en el contexto de la aplicacion
       	 LOG.trace("hashmap de usuarios"+hm);
       	 ctx.setAttribute(Constantes.USUARIOS, hm);
		}
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent arg0)  { 

        // sacar el nombre del atributo, queremos usuario
    	String nombre=arg0.getName();
    	
    	// sacar el idSession
    	String idSession=arg0.getSession().getId();    
    	// si el atributo que se elimina es usuario,
    	if (Constantes.USUARIO.equals(nombre)) {
    		LOG.trace("Se elimina sesion:"+nombre);
    		// se recupera el contexto de la aplicacion
    		ServletContext  ctx=arg0.getSession().getServletContext();
    		//se recupera el hashmap de usuarios
    		LOG.trace("hashmap de usuarios"+hm);
    		hm=(HashMap<String, Usuario>) ctx.getAttribute(Constantes.USUARIOS);
    		
    		//se elimina usuario del hashmap
			hm.remove(idSession);
			LOG.trace("hashmap de usuarios sin usuario"+hm);
    		// se machaca el hashmap con los usuarios
			ctx.setAttribute(Constantes.USUARIOS, hm);
			
		}
    	
         
         
         
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }
	
}
