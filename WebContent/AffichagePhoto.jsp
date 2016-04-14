<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link rel="stylesheet" type="text/css" href="Style.css"/>
<title>Affichage de la photo</title>
</head>
<body class="affichage">
	<div>
		<img src="${photo.img}" width="700" height="400"/>
	</div>
	<div class="info">
		<div class="tt">
			<h3>titre</h3>
			<H2>${photo.titre}</H2>
		</div>
		<div class="tt">
			<h3>date de prise</h3>
			<h2>${photo.datePrise}</h2>
		</div>
		
		<div class="tt">
			<h3>lieu</h3>
			<h2>${photo.img}</h2>
		</div>
		
		<div class="auteur">
			<div class="aa"><h3>Auteur :</h3></div>
			<div class="aa"><h2>${photo.auteur.prenomP}</h2></div>
			<div class="aa"><h2>${photo.auteur.nomP}</h2></div>
	
	
		<div class="aa">
			<form action="AffichageAuteur" method="post">
				
				<input type="submit" class="petitbouton" name="afficherAuteur" value="photos de l'auteur"/>
				<input type="hidden" name="nomAuteur" value="${photo.auteur.nomP}"/>
				<input type="hidden" name="prenomAuteur" value="${photo.auteur.prenomP}"/>
			</form>
		</div>
		</div>
	</div>
	<div class="info">
			<h3>commentaire </h3>
			<h2>${photo.commentaire}</h2>
	</div>
	
	<div class="info">
		<div class="boutons">
			<form action="ModifierPhoto" method="post">
				<input type="submit" name="modifierPhoto" class="bouton" value="Modifier la photo"/>
				<input type="hidden" name="themeCatalogue" value="${catalogue}"/>
			   	<input type="hidden" name="titrePhoto" value="${photo.titre}"/>			
			</form>
		</div>
		<div class="boutons">
			<form action="Application" method="post">
				<input type="submit" name="supprimerPhoto" class="bouton" value="Supprimer la photo"/>
				<input type="hidden" name="themeCatalogue" value="${catalogue}"/>
			   	<input type="hidden" name="titrePhoto" value="${photo.titre}"/>
				<input type="hidden" name="action" value="supprimerPhoto"/>
			</form>
		</div>
		<div class="boutons">
			<!-- Bouton pour revenir à la gallerie -->
			<form method="post" action="Application">
		            <input type="hidden"  name="action" value="lancement"/>
		            <input type="submit" class="bouton" name="nom" value="Retour à la Gallerie" >
		    </form>
	    </div>
	    <div  class="boutons">
			<!-- Bouton pour revenir au catalogue -->
		    <form action="AffichageCatalogue" method="post">
				<input type="submit" class="bouton" name="bouton" value="Retour au catalogue">
				<input type="hidden"  name="themeCatalogue" value="${photo.cat}"/>
			</form>
		</div>
	</div>
</body>
</html>