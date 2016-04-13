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
			<H1 class="titre">${catalogue.theme}</H1>
			<c:forEach items="${catalogue.photos}" var="photo">
			<div class="photo">
				<form action="AffichagePhoto" method="post">
					<img class="imagecatalogue" src="${photo.img}"/>
	   				<input type="submit" class="petitbouton" name="titrePhoto" value="${photo.titre}">
					<input type="hidden" name="themeCatalogue" value="${catalogue.theme}"/>
				</form>
			</div>
		       	<p></p>	  		
		  	</c:forEach>
		
		<div class="barre">	  	
				<!-- Bouton pour supprimer le catalogue -->
				<div class="boutons">
					<form action="Application" method="post">
						<input type="submit" class="bouton" name="supprimerCatalogue" value="Supprimer catalogue"/>
						<input type="hidden" name="themeCatalogue" value="${catalogue.theme}"/>
						<input type="hidden"  name="action" value="supprimer Catalogue"/>
					</form>
				</div>	
				<!-- 	Bouton pour ajouter une nouvelle photo -->
				<div class="boutons">
					<form action="AjouterPhoto" method="post">
						<input class="bouton" type="submit" name="ajouterPhoto" value="Ajouter une photo"/>
						<input type="hidden" name="themeCatalogue" value="${catalogue.theme}"/>
					</form>
				</div>
			<!-- Bouton pour revenir Ã  la gallerie -->
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
