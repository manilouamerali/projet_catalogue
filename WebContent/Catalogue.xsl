<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" indent="yes" encoding="UTF-8"/>
	<xsl:template match="/">

	<html>
	<head>
		<link rel="stylesheet" type="text/css" href="style.css" media="all"/>
	</head>
	<body>
	<div>
		<xsl:for-each select="//catalogue">	
			<div class="gallerycontainer" >
			<xsl:value-of select="nomCat"/>
			<div>
				<xsl:value-of select="@prenomAuteur"/>
				<xsl:value-of select="@NomAuteur"/>
				<xsl:value-of select="@dateCreation"/>
				<xsl:value-of select="dateModif"/>
			</div>
				<div class="photo">
					<div id="photoajout"> 
					<xsl:element name="photo">
						<xsl:element name="img">
							<xsl:attribute name="src">http://www.ecosociosystemes.fr/foret.jpg</xsl:attribute>
						</xsl:element>
					</xsl:element>
					</div>
					
					<!-- <xsl:for-each select="photo">  -->
					<xsl:for-each select="photo[titre='Quai_Panam.jpg']">
					<div>	
							<xsl:value-of select="@lieu"/>
							<xsl:value-of select="@prenomAuteur"/>
							<xsl:value-of select="@NomAuteur"/>
						</div>
						<div><img src="{img}"/></div>
					
					
					
					</xsl:for-each>
					
						<!-- div style="margin-left:20px;margin-bottom:1em;font-size:10pt">
						    	<xsl:element name="img">
							    	<xsl:attribute name="src">
								    	<xsl:value-of select="img/text()"/>
						    		</xsl:attribute>
						    	</xsl:element>
							    <br/>
							    <xsl:value-of select="commentaire/text()"/>
						</div -->
					    
					    <div><xsl:value-of select="dateAjout"/></div>
						<div><xsl:value-of select="dateModif"/></div>
						<div><xsl:value-of select="titre"/></div>
						<div><xsl:value-of select="dimension"/></div>
						<div><xsl:value-of select="resolution"/></div>
						<div><xsl:value-of select="categorie"/></div>
						<br/>
					<!-- </xsl:for-each>  -->
				
				</div>
			</div>
			</xsl:for-each>
			
	</div>
	
	</body>
	
	
	
	</html>
	
	</xsl:template>

</xsl:stylesheet>




