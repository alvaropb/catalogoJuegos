<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../../includes/front-cabecera.jsp"/>
<jsp:include page="../../includes/front-sidenav.jsp"/>
<jsp:include page="../../includes/alerta.jsp"/>
		 <table class="table" > 
			  <thead class="thead-dark">
			    <tr>
			      <th>Id</th>
			      <th>Nombre</th>
			      <th>Precio</th>
			      <th>Imagen</th>
			      <th>Categoria</th>
			      <th>Operaciones</th>
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
				    		<td><a href="views/frontoffice/crear-juegos?id=${juego.id}"><i class="fas fa-edit mx-3"></i></a>
				    			
				    			<a  onclick="confirmar('${juego.nombre}')"
				    			 href="views/frontoffice/eliminar-juego?id=${juego.id}"><i class="fas fa-trash-alt"></i></a>
				    			 
				    		</td>
				    																																									
				    	</tr>																																																																																		
				    </c:forEach>
			        
			  </tbody>
			
		</table> 
  <jsp:include page="../../includes/front-footer.jsp"/>             