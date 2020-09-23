<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html;charset=UTF-8"%>

<jsp:include page="../../includes/cabecera.jsp">
	<jsp:param value="inicio| Servicio REST" name="pagina"/>
	<jsp:param value="ultimos_juegos" name="activa"/>	
</jsp:include>

<main class="container" >

<h1>REST</h1>

<h2>REST harcodeado</h2>
<a href="rest-1">Ejemplo de REST pintando datos hardcodeados</a>
<pre><code>
{
	"id": 1,
	"descripcion": "acelgas con jamon con brillo",
	"nombre": "acelgas"
}
</code></pre>

<br>
<hr>
<br>
<h2>REST con GSON</h2>
<form action="rest-2" method="POST">
	<input name="id" type="text" required placeholder="introduzca id">
	<input type="submit" value="Ejempo REST 2 con GSON">
</form>






<c:import url="../../includes/pie.jsp">
</c:import>