<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<jsp:include page="includes/cabecera.jsp">
	<jsp:param value="Inicio | juegos" name="pagina"/>
	<jsp:param value="listado_juegos" name="activa"/>	
</jsp:include>


<div class="container">
	<h1>Listado de juegos</h1>
	
	<c:if test="${not empty alerta }">
		<div class="alert alert-${alerta.tipo} alert-dismissible fade show" role="alert">
		  <strong>${alerta.mensaje}</strong> 
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
	</c:if>

	 <table class="table"> 
		  <thead class="thead-dark">
		    <tr>
		      <th>Id</th>
		      <th>Nombre</th>
		    </tr>
		  </thead>
		  <tbody>
		    	<c:forEach items="${juegos }"  var="juego">
			    	<tr>
			    		<td>${juego.id}</td>
			    		<td>${juego.nombre}</td>
			    	</tr>
			    </c:forEach>
		        
		  </tbody>
		
	</table> 
</div>

<c:import url="includes/pie.jsp">
</c:import>
