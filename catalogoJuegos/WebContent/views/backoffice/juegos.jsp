<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../../includes/back-cabecera.jsp"/>
<jsp:include page="../../includes/back-sidenav.jsp"/>
<jsp:include page="../../includes/alerta.jsp"/>
	
	<form action="views/backoffice/juegos" method="post">
			 <button type="submit" class="btn btn-primary my-3">validar juegos</button>
			 <table class="table" > 
				  <thead class="thead-dark">
				    <tr>
				      <th>Id</th>
				      <th>Nombre</th>
				      <th>Precio</th>
				      <th>Imagen</th>
				      <th>Categoria</th>
				      <th>Fecha validacion</th>
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
					    		<td >
						    		<c:choose>
									    <c:when test="${not empty juego.fechaValidacion}">
									       ${juego.fechaValidacion }
									    </c:when>								
									    <c:otherwise>
										        <div class="input-group">
													  <div class="input-group-prepend">
													    <span class="input-group-text">Validar</span>
													  </div>
													  <input name="validarJuego${juego.id}" type="checkbox" aria-label="Validar">
												</div>
									    </c:otherwise>
									</c:choose>
					    			
					    		</td>
					    		<td>NO IMPLEMENTADO<a href="views/backoffice/crear-juegos?id=${juego.id}"><i class="fas fa-edit mx-3"></i></a>
					    			
					    			NO IMPLEMENTADO<a  onclick="confirmar('${juego.nombre}')"
					    			 href="views/backffice/eliminar-juego?id=${juego.id}"><i class="fas fa-trash-alt"></i></a>
					    			 
					    			 
					    		</td>
					    																																									
					    	</tr>																																																																																		
					    </c:forEach>
				        
				  </tbody>
				
			</table>
		</form> 
  	<jsp:include page="../../includes/back-footer.jsp"/>                