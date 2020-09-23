<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Migracion de fichero a bbdd: resumen</h1>
<p>fichero leido: ${fichero}</p>
<p>numero de lineas leidas:${numLineasLeidas }</p>
<p>numero de inserciones correctas: ${numInsercionesCorrectas }</p>
<p>numero de lineas erroneas: ${numLineasErroneas }</p>
<p>tiempo de ejecucion:${tiempoEjecucion}</p>
<c:if test="${not empty errores }">
	<li>
		<c:forEach items="${errores }" var="error">
			<ol>${error}</ol>	
		</c:forEach>
	</li>
</c:if>


</body>
</html>