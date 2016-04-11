<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="Style.css"/>
	<title>Photos de l'auteur</title>
</head>
<body>
	<div class="catalogue">
		<H1 class="titre">${auteur}</H1>
		<c:forEach items="${listePhotos}" var="photo">
			<div class="photo">
				<form action="AffichagePhoto" method="post">
						<img src="${photo.img}" width="200" height="150"/>
		   				<input type="submit"  class="petitbouton" name="titrePhoto" value="${photo.titre}">
						<input type="hidden" name="themeCatalogue" value="${photo.cat}"/>
				</form>
			</div>
		       	<p></p>	  		
	  	</c:forEach>
		<div class="barre">
		  	<div class="boutons">
					<form method="post" action="Application">
				            <input type="hidden" name="action" value="lancement"/>
				            <input type="submit" class="bouton" name="nom" value="Retour à la Gallerie" >
				      </form>
			</div>
		</div>
	</div>
</body>
</html>