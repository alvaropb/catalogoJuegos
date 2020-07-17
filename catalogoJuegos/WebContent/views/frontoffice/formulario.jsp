<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../../includes/front-cabecera.jsp"/>
	
                <main>
                    <div class="container-fluid">
                        <h1 class="mt-4">formulario.jsp</h1>
							
							<form action="views/frontoffice/crear-juegos" method="post">
								<input type="text" name="nombre" value="juegoPrueba">
							
								<input type="submit" value="Guardar juego">
							</form>
							
                        <div class="row">
 
                        </div>
                        
                    </div>
                </main>
  <jsp:include page="../../includes/front-footer.jsp"/>             