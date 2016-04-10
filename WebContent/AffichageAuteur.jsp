<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Photos de l'auteur</title>
</head>
<body>
	<p>${auteur}</p>
	<c:forEach items="${listePhotos}" var="photo">
		<form action="AffichagePhoto" method="post">
				<img src="${photo.img}"/>
   				<p>${photo.titre}</p>
   				<input type="submit" name="titrePhoto" value="${photo.titre}">
				<input type="hidden" name="themeCatalogue" value="${photo.cat}"/>
			</form>
	       	<p></p>	  		
  	</c:forEach>
</body>
</html>