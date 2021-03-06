<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<jsp:include page="../../includes/cabecera.jsp">
	<jsp:param value="Inicio | juegos" name="pagina"/>
	<jsp:param value="listado_juegos" name="activa"/>	
</jsp:include>


<div class="container">
	<h1>Listado de juegos</h1>
	



	 <table class="table" > 
		  <thead class="thead-dark">
		    <tr>
		      <th>Id</th>
		      <th>Nombre</th>
		      <th>Precio</th>
		      <th>Imagen</th>
		      <th>Categoria</th>
		      
		    </tr>
		  </thead>
		  <tbody>
		    	<c:forEach items="${juegos }"  var="juego">
			    	<tr>
			    		<td>${juego.id}</td>
			    		<td>${juego.nombre}</td>
			    		<td>${juego.precio}</td>
			    		<td><img  src="${juego.imagen}" height="100" width="auto"></td>
			    		<td>${juego.categoria.nombre}</td>

			    																																									
			    	</tr>																																																																																		
			    </c:forEach>
		        
		  </tbody>
		
	</table> 
</div>

<c:import url="../../includes/pie.jsp">
</c:import>
