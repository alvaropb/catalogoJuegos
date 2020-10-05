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
 * Filtro que controla los accesos no autorizados a frontend
 * Acceso solo a usuarios con el rol Usuario
 * @see com.catalogoJuegos.modelo.pojo.Rol
 * Servlet Filter implementation class FrontofficeFilter
 */
@WebFilter(dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD, 
				DispatcherType.INCLUDE, 
				DispatcherType.ERROR
		}
					, description = "Filtro para frontoffice", urlPatterns = { "/views/frontoffice/*" })
public class FrontofficeFilter implements Filter {

	private static final Logger LOG=Logger.getLogger(FrontofficeFilter.class);

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		LOG.trace("Filtro "+FrontofficeFilter.class+" destruido");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		LOG.trace("entrar en doFilter. ServletRequest:"+request+" ServletResponse:"+response);
		
		//parsear request y response a HttpServlerRequest etc
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;
		
		// sacar usuario de la sesion de la request
		Usuario usu=(Usuario)req.getSession().getAttribute("usuario");
		
		// sacar contextPath de la request
		String contextPath=req.getContextPath();
		
		if (usu==null) {// si usu=NULL -> login controller
			res.sendRedirect(contextPath+"/login");
			LOG.error("Se intento entrar en :"+req.getLocalAddr()+"  desde:"+req.getLocalAddr());
		}else if (Rol.ADMINISTRADOR==usu.getRol().getId()) {// si usu=admin
			LOG.error("Se intento entrar en :"+req.getLocalAddr()+"  desde:"+req.getLocalAddr());
			
			req.getRequestDispatcher("/views/backoffice/inicio").forward(req, res);
		}else {// usuario es usu normal->frontoffice
			// pass the request along the filter chain
			chain.doFilter(request, response);
			
		}
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		LOG.trace("Filtro "+FrontofficeFilter.class+" creado");
	}

}
