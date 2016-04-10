<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Affichage de la photo</title>
</head>
<body>
	<img src="${photo.img}"/>
	<p>${photo.titre}</p>
	<p>${photo.commentaire}</p>
	<form action="AffichageAuteur" method="post">
		<div>Auteur</div>
		<div>${photo.auteur.prenomP}</div>
		<div>${photo.auteur.nomP}</div>
		<input type="submit" name="afficherAuteur" value="Afficher auteur"/>
		<input type="hidden" name="nomAuteur" value="${photo.auteur.nomP}"/>
		<input type="hidden" name="prenomAuteur" value="${photo.auteur.prenomP}"/>
	</form>
	<form action="Application" method="post">
		<input type="submit" name="supprimerPhoto" value="Supprimer la photo"/>
		<input type="hidden" name="themeCatalogue" value="${catalogue}"/>
	   	<input type="hidden" name="titrePhoto" value="${photo.titre}"/>
		<input type="hidden" name="action" value="supprimerPhoto"/>
	</form>
	<!-- Bouton pour revenir à la gallerie -->
	<form method="post" action="Application">
            <input type="hidden" name="action" value="lancement"/>
            <input type="submit" name="nom" value="Retour à la Gallerie" >
    </form>
	<!-- Bouton pour revenir au catalogue -->
    <form action="AffichageCatalogue" method="post">
		<input type="submit" name="bouton" value="Retour au catalogue">
		<input type="hidden" name="themeCatalogue" value="${photo.cat}"/>
	</form>
	
</body>
</html>