<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<jsp:include page="includes/cabecera.jsp">
	<jsp:param value="Inicio | Juegos" name="pagina"/>
	<jsp:param value="crear_juego" name="activa"/>	
</jsp:include>


<main class="container">
	<h1>Crear registro</h1>
	

	

	<form action="crear-juego" method="post">
	  		<div class="form-group">
			    <label for="id">Id</label>
			    <input type="text" class="form-control" id="id" aria-describedby="id" name="id" readonly value="${juego.id }">
		 	</div>
		  	<div class="form-group">
			    <label for="nombre">Nombre</label>
			    <input type="text" class="form-control" id="nombre" name="nombre" value="${juego.nombre }" placeholder="Intro nombre entre 3 y 100 caracteres">
		  	</div>
		   	<div class="form-group">
			    <label for="imagen">Imagen</label>
			    <input type="text" class="form-control" id="imagen" name="imagen" value="${juego.imagen }" placeholder="Intro url de la imagen">
		  	</div>
			<div class="form-group">
			    <label for="precio">Precio</label>
			    <input type="text" class="form-control" id="precio" aria-describedby="precio" name="precio" placeholder="0.0" value="${juego.precio }">
		 	</div>	
		 	<div class="form-group">
			 	<select class="custom-select" name="idCategoria">
				  <c:forEach items="${categorias}" var="categoria"> 
					  <option ${(juego.categoria.id eq categoria.id )?"selected":"" } value="${categoria.id }">${categoria.nombre }</option>
				  </c:forEach>
				</select>
		 	</div>
 	
		  
		  <button type="submit" class="btn btn-primary">Submit</button>
	</form>
	


</main>


























<c:import url="includes/pie.jsp">
</c:import>
