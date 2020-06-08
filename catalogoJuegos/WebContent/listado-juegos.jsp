<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>listado de juegos</title>
</head>
<body>
	<h1>Listado de juegos</h1>
	

	    

	
 <table>
  <thead>
    <tr>
      <th>Id</th>
      <th>Nombre</th>
    </tr>
  </thead>
  <tbody>
    	<c:forEach items="${juegos }"  var="juego">
	    	<tr>
	    		<td>${juego.id}</td>
	    		<td>${juego.nombre}</td>
	    	</tr>
	    </c:forEach>
        
  </tbody>

</table> 

</body>
</html>