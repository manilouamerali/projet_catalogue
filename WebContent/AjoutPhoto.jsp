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

		<form action="UploadPhoto" enctype="multipart/form-data" method="post" >	      
	                <H1>Ajout de photo</H1>
					<input type="hidden" name="themeCatalogue" value="${theme}"/>
	
	                <label for="fichier">
	                	 <span class="requis">Emplacement du fichier</span>
	                	  <input type="file" id="fichier" name="fichier" />
	                </label>
	               
	                               
	                <label for="titre">
	                	<span>Titre</span>
	                	<input type="text" id="titre" name="titre" />
	                </label>
	                
					<label for="datePrise">
						<span>Date de prise</span>
	               		<input type="text" id="datePrise" name="datePrise" />
	                </label>

					
					<label for="lieu">
						<span>Lieu</span>
						<input type="text" id="lieu" name="lieu" />
					</label>
	                
					
					<label for="dimension">
						<span>Dimension</span>
	                	<input type="text" id="dimension" name="dimension" />
	                </label>

	                
	                <label for="nomAuteur">
	               		<span>Nom de l'auteur</span>
	                	<input type="text" id="nomAuteur" name="nomAuteur" />
	                </label>
	                
	                <label for="prenomAuteur">
		                <span>Prénom de l'auteur</span>
		                <input type="text" id="prenomAuteur" name="prenomAuteur" />
	                </label>

				
					<label for="commentaire">
						<span>Commentaire</span>
	              		  <input type="text" id="commentaire" name="commentaire" />
	               	</label>

	                <input type="submit" value="Ajouter" class="button" />
	                <br />                
	            </fieldset>
	        </form>
        </div>
    </body>
</html>