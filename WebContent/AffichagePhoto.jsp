<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Affichage de la gallerie</title>
    </head>
    <body>


        <%-- Affichage de la chaîne "message" transmise par la servlet --%>
       
<%--         <p>Nom : ${ gallerie.catalogues.get(0).theme }</p> --%>
<%--         <p>Email : ${ gallerie.catalogues.get(0).auteur.nomP}</p> --%>
       
        <p>${toto}</p>
        <c:forEach items="${gallerie.catalogues}" var="cat">
    		<p>${cat.theme}</p>
    		<p></p>
    		<c:forEach items="${cat.photos}" var="photo">
	        	<p>${photo.lieu}</p>
	        	<p>${photo.titre}</p>
	        	<img src="${photo.img}"/>
	        	<p></p>
    		</c:forEach>
		</c:forEach>
    </body>
</html>