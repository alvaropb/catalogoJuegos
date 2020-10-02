<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../../includes/back-cabecera.jsp"/>
<jsp:include page="../../includes/back-sidenav.jsp"/>
<jsp:include page="../../includes/alerta.jsp"/>


<div class="container">
	<h1>Listado de categorias</h1>
	<a href="views/backoffice/categorias?idCategoria=0" class="btn btn-primary my-3">Crear categoria</a>


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
			    		<td><a href="views/backoffice/categorias?idCategoria=${categoria.id}"><i class="fas fa-edit mx-3"></i></a>
			    			
			    			<a  onclick="confirmar('${categoria.nombre}')"
			    			 href="views/backoffice/categorias?idCategoria=${categoria.id}&operacion=2"><i class="fas fa-trash-alt"></i></a>
			    			 
			    		</td>
			    																																									
			    	</tr>																																																																																		
			    </c:forEach>
		        
		  </tbody>
		
	</table> 
</div>

<jsp:include page="../../includes/back-footer.jsp"/>           