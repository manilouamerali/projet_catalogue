import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class Catalogue {
	private Personne auteur;
	private String dateCreation;
	private String theme;
	private String dateModif;
	private List<Photo> photos=new CopyOnWriteArrayList<Photo>();
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
	
	
	public Personne getAuteur() {
		return auteur;
	}

	public void setAuteur(Personne auteur) {
		this.auteur = auteur;
	}
	
	public void creerCatalogue(String theme, String nom, String prenom){
		Personne p = new Personne();
		p.setNomP(nom);
		p.setPrenomP(prenom);
		this.setAuteur(p);
		this.setTheme(theme);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		this.setDateCreation(dateFormat.format(date));
	}

	public void ecrireXML(Photo p){
		Document document;
		try {
			final DocumentBuilderFactory factory  = DocumentBuilderFactory.newInstance();
			final DocumentBuilder builder = factory.newDocumentBuilder();
			String path = this.getClass().getResource("/").getPath();
			path=path.substring(0, path.lastIndexOf("/.metadata"));
			document= builder.parse(new File(path+"/projet_catalogue/WebContent/Catalogue.xml"));

			Source input = new DOMSource(document);
			Result output = new StreamResult(new File(path+"/projet_catalogue/WebContent/Catalogue.xml"));
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			
			transformer.setOutputProperty(OutputKeys.METHOD,"xml");
//			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
//		    transformer.setOutputProperty(OutputKeys.STANDALONE, "no");			
//		    transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			Element racine = document.getDocumentElement();
			
			Node noeud = racine.getFirstChild().getNextSibling();
			while(noeud != null)
			{
				if (noeud.getNodeType() == Node.ELEMENT_NODE) {//on est dans catalogue
					Element eNoeud = (Element) noeud;
//					System.out.println("Parcours des catalogues : "+ eNoeud.getAttribute("theme"));
					if(eNoeud.getAttribute("theme").equals(this.theme)){
						Element newP = document.createElement("photo");
//						newP.setAttribute("datePrise", p.getDatePrise());
						newP.setAttribute("titre", p.getTitre());
						Element nInfo = document.createElement("img");
						nInfo.appendChild(document.createTextNode(p.getTitre()));
						newP.appendChild(nInfo);
//						nInfo = document.createElement("dimension");
//						nInfo.appendChild(document.createTextNode(p.getDimension()));
//						newP.appendChild(nInfo);
//						
//						nInfo = document.createElement("resolution");
//						nInfo.appendChild(document.createTextNode(Integer.toString(p.getResolution())));
//						newP.appendChild(nInfo);
//						nInfo = document.createElement("categorie");
//						nInfo.appendChild(document.createTextNode(p.getCategorie()));
//						newP.appendChild(nInfo);
//						nInfo = document.createElement("commentaire");
//						nInfo.appendChild(document.createTextNode(p.getCommentaire()));
//						newP.appendChild(nInfo);
//						
//						nInfo = document.createElement("note");
//						nInfo.appendChild(document.createTextNode(Integer.toString(0)));
//						newP.appendChild(nInfo);
//						nInfo = document.createElement("sommeVotes");
//						nInfo.appendChild(document.createTextNode(Integer.toString(0)));
//						newP.appendChild(nInfo);
//						nInfo = document.createElement("nbVotes");
//						nInfo.appendChild(document.createTextNode(Integer.toString(0)));
//						newP.appendChild(nInfo);
//						Element nP = document.createElement("personne");
//						newP.appendChild(nP);
//						nInfo = document.createElement("nomP");
//						nP.appendChild(nInfo);
//						nInfo = document.createElement("prenomP");
//						nP.appendChild(nInfo);
//						nInfo = document.createElement("email");
//						nP.appendChild(nInfo);		
						eNoeud.appendChild(newP);
//						System.out.println(newP.getNodeName());
					}
				}
				noeud = noeud.getNextSibling();
			}
			transformer.transform(input, output);
		
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TransformerException e) {
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