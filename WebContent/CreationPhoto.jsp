<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
    <head>
        <title>Cr�ation d'une photo</title>
    </head>
    <body>
        <div>
            <form method="post" action="Application">
                <fieldset>
                    <legend>Ajout d'une photo</legend>
    
                    <label for="nom">Nom <span class="requis">*</span></label>
                    <input type="text" id="nom" name="nom" value="" size="20" maxlength="20" />
                    <br />
                    
                    <label for="email">Adresse email</label>
                    <input type="text" id="email" name="email" value="" size="20" maxlength="60" />
                    <br />
                </fieldset>
                <input type="submit" value="Valider"  />
                <input type="reset" value="Remettre � z�ro" /> <br />
            </form>
        </div>
    </body>
</html>