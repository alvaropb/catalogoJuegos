<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<jsp:include page="includes/cabecera.jsp">
	<jsp:param value="Catalogo juegos|login" name="pagina"/>
	<jsp:param value="login" name="activa"/>
</jsp:include>





	<div class="container">
		<h1>LOGIN</h1>
		<form action="login" method="post">
						
	
			  	<div class="form-group">
				    <label for="nombre">Nombre</label>
				    <input type="text" class="form-control" id="nombre" name="nombre" value="${usuario.nombre }" placeholder="Nombre usuario">
			  	</div>
				<div class="form-group">
				    <label for="pass">Password</label>
				    <input type="password" class="form-control" id="pass" aria-describedby="pass" name="pass" value="${usuario.pass }">
			 	</div>		
		
				<button type="submit" class="btn btn-primary">Submit</button>
		
		
		</form>
	</div>


<c:import url="includes/pie.jsp">
</c:import>