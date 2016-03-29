import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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
			String path = this.getClass().getResource("/").getPath();
			path = path.replace("WEB-INF/classes","");
			Document document= builder.parse(new File(path+"Catalogue.xml"));
			
			Element racine = document.getDocumentElement();
			Source input = new DOMSource(document);
			
			Node noeud = racine.getFirstChild().getNextSibling();
			while(noeud != null)
			{
				if (noeud.getNodeType() == Node.ELEMENT_NODE) {//on est dans catalogue
					Element eNoeud = (Element) noeud;
					if(eNoeud.getAttribute("theme").equals(this.theme)){
						System.out.println("Ecrire XML "+noeud.getNodeName());
						Element newP = document.createElement("photo");
						Element nInfo = document.createElement("img");
						nInfo.appendChild(document.createTextNode(p.getImg()));
						newP.appendChild(nInfo);
						nInfo = document.createElement("dateAjout");
						nInfo.appendChild(document.createTextNode(p.getDateAjout()));
						newP.appendChild(nInfo);
						nInfo = document.createElement("dimension");
						nInfo.appendChild(document.createTextNode(p.getDimension()));
						newP.appendChild(nInfo);
						/*
						nInfo = document.createElement("resolution");
						nInfo.appendChild(document.createTextNode(p.getResolution()));
						newP.appendChild(nInfo);*/
						nInfo = document.createElement("categorie");
						nInfo.appendChild(document.createTextNode(p.getCategorie()));
						newP.appendChild(nInfo);
						nInfo = document.createElement("commentaire");
						nInfo.appendChild(document.createTextNode(p.getCommentaire()));
						newP.appendChild(nInfo);
						
						nInfo = document.createElement("note");
						//nInfo.appendChild(document.createTextNode(0));
						newP.appendChild(nInfo);
						nInfo = document.createElement("sommeVotes");
						//nInfo.appendChild(document.createTextNode(0));
						newP.appendChild(nInfo);
						nInfo = document.createElement("nbVotes");
						//nInfo.appendChild(document.createTextNode(0));
						newP.appendChild(nInfo);
						Element nP = document.createElement("personne");
						newP.appendChild(nP);
						nInfo = document.createElement("nomP");
						nP.appendChild(nInfo);
						nInfo = document.createElement("prenomP");
						nP.appendChild(nInfo);
						nInfo = document.createElement("email");
						nP.appendChild(nInfo);
						
						noeud.appendChild(newP);
					}
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
