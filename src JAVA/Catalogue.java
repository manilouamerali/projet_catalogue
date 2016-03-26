import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Catalogue {
	private Personne auteur;
	private String dateCreation;
	private String theme;
	private String dateModif;
	private List<Photo> photos=new ArrayList<Photo>();
	private Node noeudCatalogue;
	
	public Catalogue(){}
	
	public String getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getDateModif() {
		return dateModif;
	}
	public void setDateModif(String dateModif) {
		this.dateModif = dateModif;
	}
	public List<Photo> getPhotos() {
		return photos;
	}
	public void ajouterPhoto(Photo photo) {
		this.photos.add(photo);
	}
	public Photo retirerPhoto(String titre){
		Photo res=null;
		for(Photo p: photos)
		if(p.getTitre().equals(titre)){		
			res=p;
			photos.remove(p);
			break;
		}
		return res;
	}
	public void creerPhoto(String datePrise, String lieu, Personne auteur, String dateAjout,
			String dateModif, String titre, String dimension, int resolution, String categorie,
			String commentaire,String img){
		Photo p = new Photo();
		p.setDatePrise(datePrise);
		p.setLieu(lieu);
		p.setAuteur(auteur);
		p.setDateAjout(dateAjout);
		p.setDateModif(dateModif);
		p.setTitre(titre);
		p.setDimension(dimension);
		p.setResolution(resolution);
		p.setCategorie(categorie);
		p.setCommentaire(commentaire);
		p.setImg(img);
		
		this.ajouterPhoto(p);
	}
	
	public Personne getAuteur() {
		return auteur;
	}

	public void setAuteur(Personne auteur) {
		this.auteur = auteur;
	}

	public void ecrireXML(Photo p){
		final DocumentBuilderFactory factory  = DocumentBuilderFactory.newInstance();
		try {
			final DocumentBuilder builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Node getNoeudCatalogue() {
		return noeudCatalogue;
	}

	public void setNoeudCatalogue(Node noeudCatalogue) {
		this.noeudCatalogue = noeudCatalogue.cloneNode(true);
	}
}
