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
       
<%--         <p>${toto}</p> --%>
        <form action="AffichageCatalogue" method="post">
        <c:forEach items="${gallerie.catalogues}" var="cat">
    		<p>${cat.theme}</p>
    		
<!--     		<a href="/AfficherCatalogue.jsp">  -->
<%--     			<input type="image" src="${cat.photos.get(0).img}"  value="${cat.photos.get(0).titre}" name="nomPhoto"  id="nomPhoto" > --%>
<%--     			<jsp:forward page="AfficherCatalogue.jsp" > --%>
<%-- 					<jsp:param name="parameterName" value="{ cat }" /> --%>
<%-- 				</jsp:forward> --%>
<!--     		</a> -->
    			
    			
    			<input type="image" src="${cat.photos.get(0).img}" name="themeCatalogue" value="${cat.theme}"/>
    			<input type="hidden" name="themeCatalogue" value="${cat.theme}"/>
				<input type="submit" name="nom" value="Afficher ce catalogue" />
				
<%--     			<jsp:forward page="AfficherCatalogue.jsp" > --%>
<%-- 					<jsp:param name="parameterName" value="{ cat }" /> --%>
<%-- 				</jsp:forward> --%>
    		</c:forEach>
		</form>
    					
<%--     		<c:forEach items="${cat.photos}" var="photo"> --%>
<%-- 	        	<p>${photo.lieu}</p> --%>
<%-- 	        	<p>${photo.titre}</p> --%>
<%-- 	        	<img src="${photo.img}"/> --%>
<!-- 	        	<p></p> -->
<%--     		</c:forEach> --%>
		
    </body>
</html>