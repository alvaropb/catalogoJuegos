<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../../includes/front-cabecera.jsp"/>
<jsp:include page="../../includes/front-sidenav.jsp"/>
<jsp:include page="../../includes/alerta.jsp"/>	
                <main>
                    <div class="container-fluid">
                        <h1 class="mt-4">${msj}</h1>
							
							<form action="views/frontoffice/crear-juegos" method="post">
									  		<c:if test="${not empty juego.imagen}">
									  		<div class="form-group">
											    
											    <img alt="" src="${juego.imagen}">
										 	</div>
								  		</c:if>
								  		
								  		<div class="form-group">
										    <label for="id">Id</label>
										    <input type="text" class="form-control" id="id" aria-describedby="id" name="id" readonly value="${juego.id }">
									 	</div>
									  	<div class="form-group">
										    <label for="nombre">Nombre</label>
										    <input type="text" class="form-control" id="nombre" name="nombre" value="${juego.nombre }" ${( not empty readonly)?'readonly':'' } placeholder="Intro nombre entre 3 y 100 caracteres">
									  	</div>
									   	<div class="form-group">
										    <label for="imagen">Imagen</label>
										    <input type="text" class="form-control" id="imagen" name="imagen" value="${juego.imagen }" ${( not empty readonly)?'readonly':'' } placeholder="Intro url de la imagen">
									  	</div>
										<div class="form-group">
										    <label for="precio">Precio</label>
										    <input type="text" class="form-control" id="precio" aria-describedby="precio" name="precio" ${( not empty readonly)?'readonly':'' } placeholder="0.0" value="${juego.precio }">
									 	</div>	
									 	<div class="form-group">
									 		<label for="idCategoria">Genero</label>
										 	<select class="custom-select" name="idCategoria" ${( not empty readonly)?'disabled':'' } >
											  <c:forEach items="${categorias}" var="categoria"> 
												  <option ${(juego.categoria.id eq categoria.id )?"selected":"" } value="${categoria.id }">${categoria.nombre }</option>
											  </c:forEach>
											</select>
									 	</div>
							 	
									  <c:if test="${empty readonly}">
									  	<button type="submit" class="btn btn-primary">Guardar cambios</button>
									  </c:if>
									  <c:if test="${not empty readonly}">
									  	<a href="inicio" class="btn btn-primary">Volver</a>
									  	
									  </c:if>
							</form>
							
                        <div class="row">
 
                        </div>
                        
                    </div>
                </main>
  <jsp:include page="../../includes/front-footer.jsp"/>             