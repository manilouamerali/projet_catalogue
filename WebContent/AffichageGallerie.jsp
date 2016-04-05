<%@page import="com.sun.xml.internal.txw2.Document"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Affichage de la gallerie</title>
    </head>
    <body>
		
        
	    <c:forEach items="${gallerie.catalogues}" var="catalogue">
	 		<p>${catalogue.theme}</p>
		    <form action="AffichageCatalogue" method="post">
				<img src="${catalogue.photos.get(0).img}"/>	   			
				<p/>
				<input type="submit" name="themeCatalogue" value="${catalogue.theme}">
			</form>
		</c:forEach>
		
		

<!--    bouton ajouter un catalogue	 -->
    </body>
</html>