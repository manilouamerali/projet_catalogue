<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Affichage d'un catalogue</title>
	<link rel="stylesheet" type="text/css" href="Style.css"/>
</head>
<body>
	<div class="catalogue">
			<p>${catalogue.theme}</p>
			<c:forEach items="${catalogue.photos}" var="photo">
			<div class="photo">
				<form action="AffichagePhoto" method="post">
					<img src="${photo.img}" width="200" height="150"/>
	   				<p>${photo.titre}</p>
	   				<input type="submit" name="titrePhoto" value="${photo.titre}">
					<input type="hidden" name="themeCatalogue" value="${catalogue.theme}"/>
				</form>
			</div>
		       	<p></p>	  		
		  	</c:forEach>
		
		  	
	<!-- Bouton pour supprimer le catalogue -->
	
		<form action="Application" method="post">
			<input type="submit" name="supprimerCatalogue" value="Supprimer catalogue"/>
			<input type="hidden" name="themeCatalogue" value="${catalogue.theme}"/>
			<input type="hidden" name="action" value="supprimerCatalogue"/>
		</form>
	<!-- 	Bouton pour ajouter une nouvelle photo -->
		<form action="AjouterPhoto" method="post">
			<input type="submit" name="ajouterPhoto" value="Ajouter une photo"/>
			<input type="hidden" name="themeCatalogue" value="${catalogue.theme}"/>
		</form>
	<!-- Bouton pour revenir à la gallerie -->
		<form method="post" action="Application">
	            <input type="hidden" name="action" value="lancement"/>
	            <input type="submit" name="nom" value="Retour à la Gallerie" >
	      </form>
	</div>			
</body>
</html>
