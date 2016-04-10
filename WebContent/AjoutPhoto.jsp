<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Envoi de fichier</title>
        <link rel="stylesheet" type="text/css" href="Style.css"/>
    </head>
    <body>
    	<div class="dark-matter">
	        <form action="Application" method="post" >
	            <fieldset>
	                <H1>Ajout de photo</H1>
	
	                <label for="description">
	                	<span>Description du fichier</span>
	                	<input type="text" id="description" name="description" value="" />
	                </label>
	                
	
	                <label for="fichier">
	                	 <span class="requis">Emplacement du fichier</span>
	                	  <input type="file" id="fichier" name="fichier" />
	                </label>
	               
	                
					<label for="datePrise">
						<span>Date de prise</span>
	               		<input type="text" id="datePrise" name="datePrise" />
	                </label>

					
					<label for="lieu">
						<span>Lieu</span>
						<input type="text" id="lieu" name="lieu" />
					</label>
	                
	                
	                <label for="titre">
	                	<span>Titre</span>
	                	<input type="text" id="titre" name="titre" />
	                </label>
					
					<label for="dimension">
						<span>Dimension</span>
	                	<input type="text" id="dimension" name="dimension" />
	                </label>
	                
	                <label for="nomAuteur">
	               		<span>Nom de l'auteur</span>
	                	<input type="text" id="nomAuteur" name="nomAuteur" />
	                </label>

	                
	                <label for="emailAuteur">
	                	<span>Email de l'auteur</span>
	                	<input type="text" id="emailAuteur" name="emailAuteur" />
	                </label>

	                
	                <label for="prenomAuteur">
	                <span>Prénom de l'auteur</span>
	                <input type="text" id="prenomAuteur" name="prenomAuteur" />
	                
	                
					<label for="resolution">
						<span>Résolution</span>
	                	<input type="text" id="resolution" name="resolution" />
	                </label>

					<label for="categorie">
						<span>Catégorie</span>
	               		 <input type="text" id="categorie" name="categorie" />
	                </label>

				
					<label for="commentaire">
						<span>Commentaire</span>
	              		  <input type="text" id="commentaire" name="commentaire" />
	               	</label>

				
					<input type="hidden" name="action" value="nouvellePhoto"/>
	<!-- 				ne pas oublier de transferer le theme du catalogue -->
					<input type="hidden" name="themeCatalogue" value="${theme}"/>
	                <input type="submit" value="Ajouter" class="button" />
	                <br />                
	            </fieldset>
	        </form>
        </div>
    </body>
</html>