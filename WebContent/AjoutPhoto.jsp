<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Envoi de fichier</title>
    </head>
    <body>
        <form action="Application" method="post" >
            <fieldset>
                <legend>Ajout de photo</legend>

                <label for="description">Description du fichier</label>
                <input type="text" id="description" name="description" value="" />
                <br />

                <label for="fichier">Emplacement du fichier <span class="requis">*</span></label>
                <input type="file" id="fichier" name="fichier" />
                <br />
                
				<label for="datePrise">Date de prise</label>
                <input type="text" id="datePrise" name="datePrise" />
                <br />
				
				<label for="lieu">Lieu</label>
                <input type="text" id="lieu" name="lieu" />
                <br />
                
                <label for="titre">Titre</label>
                <input type="text" id="titre" name="titre" />
                <br />
				
				<label for="dimension">Dimension</label>
                <input type="text" id="dimension" name="dimension" />
                <br />
                
                <label for="nomAuteur">Nom de l'auteur</label>
                <input type="text" id="nomAuteur" name="nomAuteur" />
                <br />
                
                <label for="emailAuteur">Email de l'auteur</label>
                <input type="text" id="emailAuteur" name="emailAuteur" />
                <br />
                
                <label for="prenomAuteur">Prénom de l'auteur</label>
                <input type="text" id="prenomAuteur" name="prenomAuteur" />
                <br />
                
				<label for="resolution">Résolution</label>
                <input type="text" id="resolution" name="resolution" />
                <br />
                
				<label for="categorie">Catégorie</label>
                <input type="text" id="categorie" name="categorie" />
                <br />
			
				<label for="commentaire">Commentaire</label>
                <input type="text" id="commentaire" name="commentaire" />
                <br />
			
				<input type="hidden" name="action" value="nouvellePhoto"/>
<!-- 				ne pas oublier de transferer le theme du catalogue -->
				<input type="hidden" name="themeCatalogue" value="${theme}"/>
                <input type="submit" value="Envoyer" class="sansLabel" />
                <br />                
            </fieldset>
        </form>
    </body>
</html>