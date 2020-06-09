<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<jsp:include page="includes/cabecera.jsp">
	<jsp:param value="Inicio | Juegos" name="pagina"/>
	<jsp:param value="crear_juego" name="activa"/>	
</jsp:include>


<main class="container">
	<h1>Crear registro</h1>
	
	<c:if test="${not empty alerta }">
		<div class="alert alert-${alerta.tipo} alert-dismissible fade show" role="alert">
		  <strong>${alerta.mensaje}</strong> 
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
	</c:if>
	

	<form action="crear-juego" method="post">
	  		<div class="form-group">
			    <label for="id">Id</label>
			    <input type="text" class="form-control" id="id" aria-describedby="id" name="id" readonly>
		 	</div>
		  	<div class="form-group">
			    <label for="nombre">Nombre</label>
			    <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Intro nombre entre 3 y 100 caracteres">
		  	</div>
		  
		  <button type="submit" class="btn btn-primary">Submit</button>
	</form>
	


</main>


























<c:import url="includes/pie.jsp">
</c:import>
