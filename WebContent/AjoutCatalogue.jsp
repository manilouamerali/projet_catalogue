<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="utf-8" />
        <title>ajout catalogue</title>
        <link rel="stylesheet" type="text/css" href="Style.css"/>
    </head>
    <body>
    	<div class="dark-matter">

		<form action="AjoutCatalogue" method="post" >	      
	                <H1>Ajout de catalogue</H1>
					
	
	                               
	                <label for="theme">
	                	<span>thème</span>
	                	<input type="text" id="theme" name="theme" />
	                </label>
	                
	                <label for="nom">
	                	<span>Nom créateur</span>
	                	<input type="text" id="nom" name="nom" />
	                </label>
	                <label for="prenom">
	                	<span>Prénom créateur</span>
	                	<input type="text" id="prenom" name="prenom" />
	                </label>
	                
	                <input type="submit" value="Ajouter" class="button" />
	                <br />                
	            </fieldset>
	        </form>
        </div>
    </body>
</html>