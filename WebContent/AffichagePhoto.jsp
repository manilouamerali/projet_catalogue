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
	
	<form action="Application" method="post">
		<input type="submit" name="supprimerPhoto" value="Supprimer la photo"/>
		<input type="hidden" name="themeCatalogue" value="${catalogue.theme}"/>
	   	<input type="hidden" name="titrePhoto" value="${photo.titre}"/>
		<input type="hidden" name="action" value="supprimerPhoto"/>
	</form>
</body>
</html>