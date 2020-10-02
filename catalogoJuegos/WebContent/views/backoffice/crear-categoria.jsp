<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../../includes/back-cabecera.jsp"/>
<jsp:include page="../../includes/back-sidenav.jsp"/>
<jsp:include page="../../includes/alerta.jsp"/>


<main class="container">
	<h1>${msj}</h1>
	

	

	<form action="views/backoffice/categorias" method="post">

	  		
	  		<div class="form-group">
			    <label for="id">Id</label>
			    <input type="text" class="form-control" id="id" aria-describedby="id" name="idCategoria" readonly value="${categoria.id }">
		 	</div>
		  	<div class="form-group">
			    <label for="nombre">Nombre</label>
			    <input type="text" class="form-control" id="nombre" name="categoriaNombre" value="${categoria.nombre }" placeholder="Intro nombre entre 3 y 100 caracteres">
		  	</div>

 	
		  
		  	<button type="submit" class="btn btn-primary">Guardar cambios</button>
		  
		  	<a href="inicio" class="btn btn-primary">Volver</a>
		  	
		
		  
	</form>
	






<jsp:include page="../../includes/back-footer.jsp"/>     






















<c:import url="../../includes/pie.jsp">
</c:import>
