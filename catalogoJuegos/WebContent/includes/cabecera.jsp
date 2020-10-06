<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${param.pagina}</title>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

<!-- fontawesome -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.0-2/css/all.css" rel="stylesheet" >

<!-- Datatables --> 
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css" >
<script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js" ></script>

<!-- md5  -->
<script src="js/md5.min.js"></script>

<!-- custom js -->
<script type="text/javascript" src="js/custom.js"></script>

<!-- custom css -->
<link rel="stylesheet" href="css/estilos.css" crossorigin="anonymous">


<!-- favicon --> 	
<link rel="icon" href="favicon.ico" type="image/vnd.microsoft.icon" sizes="32x32">

</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="inicio">Catalogo</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
    <div class="navbar-nav">
   		<div class="dropdown">
			  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			    Categorias
			  </button>
			  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
				    <c:forEach items="${categorias }" var="categoria">
					    <a class="dropdown-item" href="inicio?idCategoria=${categoria.id }&categoriaNombre=${categoria.nombre }">${categoria.nombre }</a>
				    </c:forEach>
			  </div>
		</div>
	     <c:if test="${not empty usuario }">
		      <a class="nav-item nav-link ${(param.activa eq 'listado_juegos')?'active':'' }" href="inicio?idCategoria=0">Listado Juegos </a>
		      <a class="nav-item nav-link ${(param.activa eq 'ultimos_juegos')?'active':'' }" href="inicio?idCategoria=-1">Juegos por categoria</a>
		      <a class="nav-item nav-link ${(usuario.rol.id eq 1)?'active text-success':'' }" href="views/frontoffice/inicio">Mi Panel</a>
		     
	     </c:if>

    	<a class="nav-item nav-link" href="doc/index.html">Javadoc </a>
     </div>
  </div>
  
   <c:if test="${empty usuario }">
	  <div>
	  	<a class="nav-item nav-link ${(param.activa eq 'login')?'active':'' }" href="login">Login </a>	
	  </div>
  </c:if>
    
  <c:if test="${not empty usuario }">
	  <div>
	  	<span>usuarios logeados:${applicationScope.numUsuLog}</span>
	  	<span>${usuario.nombre}</span>
	  </div>
	  <div>
	  	<a class="nav-item nav-link  ${(param.activa eq 'login')?'active':'' }" href="logout">Logout </a>	
	  </div>
  </c:if>
  
</nav>

	<!--  guardar alerta en sesion y el alert en cabecera -->
	<c:if test="${not empty alerta }">
		<div class="alert alert-${alerta.tipo} alert-dismissible fade show" role="alert">
		  <strong>${alerta.mensaje}</strong> 
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
	</c:if>
	
	<% request.getSession().removeAttribute("alerta"); %>
	

	
