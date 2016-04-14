<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Modification de la photo</title>
        <link rel="stylesheet" type="text/css" href="Style.css"/>
    </head>
    <body>
    	<div class="dark-matter">
	        <form action="Application" method="post" >
	            <fieldset>
	                <H1>Modification de la photo</H1>
	
					<label for="datePrise">
						<span>Date de prise</span>
	               		<input type="text" id="datePrise" name="datePrise"  value="${photo.datePrise}"/>
	                </label>

					
					<label for="lieu">
						<span>Lieu</span>
						<input type="text" id="lieu" name="lieu"  value="${photo.lieu}"/>
					</label>
	                
	                           
	                <label for="nomAuteur">
	               		<span>Nom de l'auteur</span>
	                	<input type="text" id="nomAuteur" name="nomAuteur"  value="${photo.auteur.nomP}"/>
	                </label>

	               
	                <label for="prenomAuteur">
	                <span>Prénom de l'auteur</span>
	                <input type="text" id="prenomAuteur" name="prenomAuteur"  value="${photo.auteur.prenomP}"/>
	                </label>
	                
					<label for="commentaire">
						<span>Commentaire</span>
	              		  <input type="text" id="commentaire" name="commentaire"  value="${photo.commentaire}"/>
	               	</label>

				
					<input type="hidden" name="action" value="modifierPhoto"/>
	<!-- 				ne pas oublier de transferer le theme du catalogue -->
					<input type="hidden" id="titre" name="titre"  value="${photo.titre}"/>
					<input type="hidden" name="img" value="${photo.img}"/>
					<input type="hidden" name="themeCatalogue" value="${theme}"/>
	                <input type="submit" value="Modifier" class="button" />
	                <br />                
	            </fieldset>
	        </form>
        </div>
    </body>
</html>