package com.catalogoJuegos.filter;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.catalogoJuegos.modelo.pojo.Rol;
import com.catalogoJuegos.modelo.pojo.Usuario;



/**
 * Filtro para controlar accesos no deseados a backoffice
 * Solo usuarios con el rol administrador pueden entrar
 * @see com.catalogoJuegos.modelo.pojo.Rol
 * Servlet Filter implementation class BackofficeFilter
 */
@WebFilter(dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD, 
				DispatcherType.INCLUDE, 
				DispatcherType.ERROR
		}
					, description = "Filtro para la url de backoffice", urlPatterns = { "/views/backoffice/*" })
public class BackofficeFilter implements Filter {
	private static final Logger LOG=Logger.getLogger(BackofficeFilter.class);


	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		LOG.trace("Destroy del filtro");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		// parsear servler request a httprequest
		HttpServletRequest servletRequest=(HttpServletRequest)request;
		//parsear servler response a httpresponse
		HttpServletResponse servletResponse=(HttpServletResponse)response;
		
		// sacar el usuario de la sesion
		Usuario usu=(Usuario) servletRequest.getSession().getAttribute("usuario");
		
		// sacar el  contexto de la aplicacion 
		String contextPath=servletRequest.getContextPath();
		
		
		if (usu==null) {// si usuario es NULL , redirigir a login
			servletResponse.sendRedirect(contextPath+"/login");
			//servletRequest.getRequestDispatcher(contextPath+"/views/login/login.jsp").forward(servletRequest, servletResponse);//TODO mirar si hace falta poner el contextPath
			LOG.trace("intento de entrar a :"+servletRequest.getPathInfo()+" desde:"+servletRequest.getLocalAddr());
		}else if(Rol.ADMINISTRADOR==usu.getRol().getId()) {// si usuario es admin->backoffice
			// pass the request along the filter chain
		chain.doFilter(request, response);
		LOG.debug("redireccion correcta");
			
		}else {// si usuario es normal-->frontoffice
			LOG.trace("intento de entrar a :"+servletRequest.getPathInfo()+" desde:"+servletRequest.getLocalAddr());
			// /views/frontoffice/inicio
			servletRequest.getRequestDispatcher("/views/frontoffice/inicio").forward(servletRequest, servletResponse);
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		LOG.trace("Inicio del filtro");

	}

}
