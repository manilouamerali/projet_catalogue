import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
			path = path.replace("/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/projet_catalogue/WEB-INF/classes","");
			
			Document document= builder.parse(new File(path+"projet_catalogue/WebContent/Catalogue.xml"));
			//Document document= builder.parse(new File("C:/Users/Imen/Desktop/GIT/Nouveau dossier/Catalogue/WebContent/"+"Catalogue.xml"));
			
			
			Source input = new DOMSource(document);
			Result output = new StreamResult(new File(path+"projet_catalogue/WebContent/Catalogue.xml"));
			Transformer transformer = null;
			try{
				transformer = TransformerFactory.newInstance().newTransformer();
			}
			catch(TransformerConfigurationException e){
				System.err.println("Impossible de créer transfo");
				System.exit(1);
			}
			transformer.setOutputProperty(OutputKeys.METHOD,"xml");
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			Element racine = document.getDocumentElement();
			
			
			Node noeud = racine.getFirstChild().getNextSibling();
			while(noeud != null)
			{
				if (noeud.getNodeType() == Node.ELEMENT_NODE) {//on est dans catalogue
					Element eNoeud = (Element) noeud;
					System.out.println("Parcours des catalogues : "+ eNoeud.getAttribute("theme"));
					if(eNoeud.getAttribute("theme").equals(this.theme)){
						
						Element newP = document.createElement("photo");
						newP.setAttribute("datePrise", p.getDatePrise());
						newP.setAttribute("titre", p.getTitre());
						Element nInfo = document.createElement("img");
						nInfo.appendChild(document.createTextNode(p.getImg()));
						newP.appendChild(nInfo);
						nInfo = document.createElement("dateAjout");
						nInfo.appendChild(document.createTextNode(p.getDateAjout()));
						newP.appendChild(nInfo);
						nInfo = document.createElement("dimension");
						nInfo.appendChild(document.createTextNode(p.getDimension()));
						newP.appendChild(nInfo);
						
						nInfo = document.createElement("resolution");
						nInfo.appendChild(document.createTextNode(Integer.toString(p.getResolution())));
						newP.appendChild(nInfo);
						nInfo = document.createElement("categorie");
						nInfo.appendChild(document.createTextNode(p.getCategorie()));
						newP.appendChild(nInfo);
						nInfo = document.createElement("commentaire");
						nInfo.appendChild(document.createTextNode(p.getCommentaire()));
						newP.appendChild(nInfo);
						
						nInfo = document.createElement("note");
						nInfo.appendChild(document.createTextNode(Integer.toString(0)));
						newP.appendChild(nInfo);
						nInfo = document.createElement("sommeVotes");
						nInfo.appendChild(document.createTextNode(Integer.toString(0)));
						newP.appendChild(nInfo);
						nInfo = document.createElement("nbVotes");
						nInfo.appendChild(document.createTextNode(Integer.toString(0)));
						newP.appendChild(nInfo);
						Element nP = document.createElement("personne");
						newP.appendChild(nP);
						nInfo = document.createElement("nomP");
						nP.appendChild(nInfo);
						nInfo = document.createElement("prenomP");
						nP.appendChild(nInfo);
						nInfo = document.createElement("email");
						nP.appendChild(nInfo);		
						eNoeud.appendChild(newP);
						System.out.println(newP.getNodeName());
					}
				}
				noeud = noeud.getNextSibling();
			}
			try{
				transformer.transform(input, output);
			} catch(TransformerException e){
				System.err.println("La Transformation a échoué "+e);
				System.exit(1);
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
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