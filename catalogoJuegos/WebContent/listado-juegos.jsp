<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<c:import url="includes/cabecera.jsp">

</c:import>
<div class="container">
	<h1>Listado de juegos</h1>
	

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
