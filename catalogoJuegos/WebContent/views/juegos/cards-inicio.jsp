<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html;charset=UTF-8"%>

<jsp:include page="../../includes/cabecera.jsp">
	<jsp:param value="inicio| Ultimos juegos" name="pagina"/>
	<jsp:param value="ultimos_juegos" name="activa"/>	
</jsp:include>

<main class="container" >
			<h1>${categoriaNombre }</h1>
			<c:forEach items="${juegosCategoria}" var="categoria">
				<h4>${categoria.nombre}</h4>
				<div class="d-flex container-fluid" >		
			
			 		<div class="row w-100 row-cols-1 row-cols-sm-2 row-cols-md-4 row-cols-lg-5 row-cols-xl-5 row-cols-fluid-5}">
				
						<c:forEach items="${categoria.juegos}" var="juego">
											
								<div class="col card " >
									<div class="card-body">
										  <img src="${juego.imagen}"  class="card-img-top img-fluid" height="200"  alt="...">
									</div>
									  <div class="card-body">
										    <h5 class="card-title">${juego.nombre }</h5>
										    <p class="card-text">${juego.nombre } es un juego de la categoria ${juego.categoria.nombre }</p>
									  </div>
									  <ul class="list-group list-group-flush">
										    <li class="list-group-item">Precio:
										     <fmt:setLocale value = "es_ES"/>
				        					 <fmt:formatNumber value = "${juego.precio }" type = "currency" currencySymbol="€"/></li>
										    <li class="list-group-item">Categoria: ${juego.categoria.nombre} </li>
										    
									  </ul>
									  <div class="card-body">
										    <a href="crear-juego?id=${juego.id}&readonly=1" class="card-link">Ver detalle</a>
										    
									  </div>
								</div>
							
						</c:forEach>
					</div>
				</div>
			</c:forEach>
			
		
	
</main>


<c:import url="../../includes/pie.jsp">
</c:import>