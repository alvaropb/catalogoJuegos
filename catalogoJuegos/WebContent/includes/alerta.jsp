<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- INICIO ALERT -->
<main>
                    <div class="container-fluid">
                    <c:if test="${not empty alerta }">
                    	<div class="alert alert-${alerta.tipo} alert-dismissible fade show" role="alert">
						  <strong>${alerta.mensaje}</strong> 
						  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
						    <span aria-hidden="true">&times;</span>
						  </button>
						</div>
                    </c:if>
                    <%request.getSession().setAttribute("alerta", null); %>
                    
<!-- FIN ALERT -->