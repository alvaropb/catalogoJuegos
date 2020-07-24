<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Mi panel</title>
          <!-- Todas las rutas relativas comienzan por el href indicado -->  
        <base href="${pageContext.request.contextPath}/" />
        <link href="css/sb-styles.css" rel="stylesheet" />
        
        
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js" crossorigin="anonymous"></script>
    <!-- Datatables --> 
		<link rel="stylesheet" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css" >
		<script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js" ></script>
    
    </head>
    <body class="sb-nav-fixed">
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <a class="navbar-brand" href="inicio">Catalogo Juegos</a>


            <!-- Navbar-->  
			<div class="ml-auto mr-0 mr-md-3 my-2 my-md-0">
	            <ul class="navbar-nav ml-auto ml-md-0 d-none d-md-inline-block form-inline">
	                <li class="nav-item dropdown">
	                    <a class="nav-link dropdown-toggle" id="userDropdown" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
	                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
	
	                        <a class="dropdown-item" href="logout">Logout</a>
	                    </div>
	                </li>
	            </ul>
	        </div>
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
		                   <div class="sb-sidenav-menu-heading">Inicio</div>
                            <a class="nav-link" href="views/frontoffice/inicio">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                Inicio
                            </a> 
                            <div class="sb-sidenav-menu-heading">Administracion</div>
                            <a class="nav-link" href="views/frontoffice/juegos?validar=1">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                Juegos validados
                            </a>                     
                            
                            <a class="nav-link" href="views/frontoffice/juegos">
                                <div class="sb-nav-link-icon"><i class="fas fa-chart-area"></i></div>
                                Juegos pendientes de validar
                            </a>
                            <a class="nav-link" href="views/frontoffice/crear-juegos">
                                <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                                Crear juegos
                            </a>
                        </div>
                    </div>
                    <div class="sb-sidenav-footer">
                        <div class="small">Conectado como:</div>
                        ${usuario.nombre }
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
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
                    
                    
                    
                    