<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<jsp:include page="../../includes/cabecera.jsp">
	<jsp:param value="Inicio | Categorias" name="pagina"/>
	<jsp:param value="listado_categorias" name="activa"/>	
</jsp:include>


<div class="container">
	<h1>Listado de categorias</h1>
	<a href="crear-categoria?idCategoria=0">Crear categoria</a>


	 <table class="table" > 
		  <thead class="thead-dark">
		    <tr>
		      <th>Id</th>
		      <th>Nombre</th>
		      <th>Operaciones</th>
		    </tr>
		  </thead>
		  <tbody>
		    	<c:forEach items="${categorias }"  var="categoria">
			    	<tr>
			    		<td>${categoria.id}</td>
			    		<td>${categoria.nombre}</td>
			    		<td><a href="crear-categoria?idCategoria=${categoria.id}"><i class="fas fa-edit mx-3"></i></a>
			    			
			    			<a  onclick="confirmar('${categoria.nombre}')"
			    			 href="crear-categoria?idCategoria=${categoria.id}&operacion=2"><i class="fas fa-trash-alt"></i></a>
			    			 
			    		</td>
			    																																									
			    	</tr>																																																																																		
			    </c:forEach>
		        
		  </tbody>
		
	</table> 
</div>

<c:import url="../../includes/pie.jsp">
</c:import>
