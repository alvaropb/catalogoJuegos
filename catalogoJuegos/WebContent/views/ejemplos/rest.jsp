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


<h2>Api Rest</h2>

<p>Una api REST localizada en /api/juego/</p>
<ul class="list-group">
  <li class="list-group-item"><p><span class="badge badge-success">GET</span>/api/juego/ <span class="badge badge-pill badge-dark float-right">200</span> <b class="float-right">Retorno de un listado de juegos</b> </p></li>
  <li class="list-group-item"><p><span class="badge badge-success">GET</span>/api/juego/{id} <span class="badge badge-pill badge-dark float-right">200</span> <span class="badge badge-pill badge-dark float-right">204</span><b class="float-right">Retorno de un unico juego </b>   </p></li>
  <li class="list-group-item"><p><span class="badge badge-success">PUT</span>/api/juego/{juego} <span class="badge badge-pill badge-dark float-right">200</span><span class="badge badge-pill badge-dark float-right">409</span><b class="float-right">Modificacion de un juego</b>  </p></li>
  <li class="list-group-item"><p><span class="badge badge-success">POST</span>/api/juego/{juego} <span class="badge badge-pill badge-dark float-right">201</span><span class="badge badge-pill badge-dark float-right">409</span><b class="float-right">Insercion de un juego </b>  </p></li>
</ul>









<c:import url="../../includes/pie.jsp">
</c:import>