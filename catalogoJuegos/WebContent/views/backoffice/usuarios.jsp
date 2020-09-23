<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../../includes/back-cabecera.jsp"/>
<jsp:include page="../../includes/back-sidenav.jsp"/>
<jsp:include page="../../includes/alerta.jsp"/>
	
	<form action="views/backoffice/usuarios" method="post">
			 <button type="submit" class="btn btn-primary my-3">validar juegos</button>
			 <table class="table" > 
				  <thead class="thead-dark">
				    <tr>
				      <th>Id</th>
				      <th>Nombre</th>
				      <th>Imagen</th>
				      <th>Rol</th>
				      <th>Operaciones</th>
				    </tr>
				  </thead>
				  <tbody>
				    	<c:forEach items="${usuarios }"  var="usuario">
					    	<tr>
					    		<td>${usuario.id}</td>
					    		<td>${usuario.nombre}</td>
					    		<td><img  src="${usuario.imagen}" height="100" width="auto"></td>
					    		<td>${usuario.rol.nombre}</td>
		
					    		<td>NO IMPLEMENTADO<a href="views/backoffice/crear-usuario?id=${juego.id}"><i class="fas fa-edit mx-3"></i></a>
					    			
					    			NO IMPLEMENTADO<a  onclick="confirmar('${juego.nombre}')"
					    			 href="views/backffice/eliminar-usuario?id=${juego.id}"><i class="fas fa-trash-alt"></i></a>
					    			 
					    			 
					    		</td>
					    																																									
					    	</tr>																																																																																		
					    </c:forEach>
				        
				  </tbody>
				
			</table>
		</form> 
  	<jsp:include page="../../includes/back-footer.jsp"/>                