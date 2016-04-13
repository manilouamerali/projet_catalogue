<%@page import="com.sun.xml.internal.txw2.Document"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Affichage de la gallerie</title>
        <link rel="stylesheet" type="text/css" href="Style.css"/>
    </head>
    <body>
	<h1 align="center">catalogues : </h1>	
				
				<!-- 	Bouton pour ajouter une nouvelle photo -->
				<div align="center">
					<form action="AjouterCatalogue" method="post">
						<input class="bouton" type="submit" name="ajouterAlbum" value="Ajouter un Album"/>
					</form>
				</div>
	
	<div class="galerie">	
        
	    <c:forEach items="${gallerie.catalogues}" var="catalogue">
	    	<div class="cata">
	 	
		    <form action="AffichageCatalogue" method="post">
				<c:if test="${not empty catalogue.photos}">
					<img class="imagegalerie" src="${catalogue.photos.get(0).img}"/>	  
				</c:if> 			
				<p/>
				<input type="submit" class="bouton" name="themeCatalogue" value="${catalogue.theme}">
			</form>
		</div>
		</c:forEach>
	
	</div>	

<!--    bouton ajouter un catalogue	 -->
    </body>
</html>
