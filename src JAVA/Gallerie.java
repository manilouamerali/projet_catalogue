import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class Gallerie {

	private List<Catalogue> catalogues;
	private Map < String , List < Photo > > photosAuteur;

	private static Gallerie instance;
	private Gallerie(){
		catalogues=new CopyOnWriteArrayList<Catalogue>();
		photosAuteur =new HashMap <String , List < Photo > >();
	}
	public static Gallerie getInstance(){
		 if (null == instance) { // Premier appel
	            instance = new Gallerie();
	            instance.majGallerie();
	     }
		 return instance;
	}
	
	public List<Catalogue> getCatalogues() {
		return catalogues;
	}

	public void setCatalogues(List<Catalogue> catalogues) {
		this.catalogues = catalogues;
	}

	public Map<String, List<Photo>> getPhotosAuteur() {
		return instance.photosAuteur;
	}

	public void setPhotosAuteur(Map<String, List<Photo>> photosAuteur) {
		instance.photosAuteur = photosAuteur;
	}
	
	public void supprimerCatalogue(String themeCatalogue){
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document document;
		DocumentBuilder builder;
		try {
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			builder = factory.newDocumentBuilder();
			String path = this.getClass().getResource("/").getPath();
			path=path.substring(0, path.lastIndexOf("/.metadata"));
			document= builder.parse(new File(path+"/projet_catalogue/WebContent/Catalogue.xml"));
			
			Source input = new DOMSource(document);
			Result output = new StreamResult(new File(path+"Catalogue.xml"));
			final Element racine = document.getDocumentElement();
			
			Node catalogue= racine.getFirstChild().getNextSibling(); 
			while(catalogue != null){
				if (catalogue.getNodeType() == Node.ELEMENT_NODE) {//on est dans catalogue
					Element eCata = (Element) catalogue;
					if(eCata.getAttribute("theme").equals(themeCatalogue)){
						catalogue.getParentNode().removeChild(catalogue);
						for(Catalogue c: catalogues)
							if(c.getTheme().equals(themeCatalogue)){				
								Set<Entry<String, List<Photo>>> setHm = photosAuteur.entrySet();
								for (Photo p: c.getPhotos()){
									String pathImg=p.getImg();
									new File(pathImg).delete();
									for(Entry<String, List<Photo>> e :setHm){
										if(e.getKey().equals(p.getAuteur().getPrenomP().concat(p.getAuteur().getNomP())))
											e.getValue().remove(p);
									}
									c.retirerPhoto(p.getTitre());
								}
								//suppression du dossier
								String pathImg = this.getClass().getResource("/").getPath();
								pathImg=pathImg.substring(0, pathImg.lastIndexOf("/.metadata"));
								pathImg +="/projet_catalogue/WebContent/WEB-INF/images/";
								pathImg+=c.getTheme()+"/";
								new File(pathImg).delete();
								instance.getCatalogues().remove(c);
								break;
							}
					}
				}
				if((catalogue = catalogue.getNextSibling())==null)
					break;
				else
					catalogue = catalogue.getNextSibling();
			}
			transformer.transform(input, output);

		} catch (TransformerFactoryConfigurationError | ParserConfigurationException | SAXException | IOException | TransformerException e) {
			e.printStackTrace();
		}
		
	}

	public void supprimerPhoto(String themeCatalogue, String titrePhoto){

		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document document;
		DocumentBuilder builder;
		try {
			
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			builder = factory.newDocumentBuilder();
			
			String path = this.getClass().getResource("/").getPath();
			path=path.substring(0, path.lastIndexOf("/.metadata"));
			document= builder.parse(new File(path+"/projet_catalogue/WebContent/Catalogue.xml"));
			//document= builder.parse(new File(path+"Catalogue.xml"));
//			document= builder.parse(new File("C:/Users/Imen/Desktop/GIT/Nouveau dossier/Catalogue/WebContent/"+"Catalogue.xml"));

			Source input = new DOMSource(document);
			Result output = new StreamResult(new File("path"+"/projet_catalogue/WebContent/Catalogue.xml"));			
			final Element racine = document.getDocumentElement();
			
			Node catalogue= racine.getFirstChild().getNextSibling(); 
			while(catalogue != null){
				if (catalogue.getNodeType() == Node.ELEMENT_NODE) {//on est dans catalogue
					Element eCata = (Element) catalogue;
					
					if(eCata.getAttribute("theme").equals(themeCatalogue)){
						Node child = catalogue.getFirstChild().getNextSibling().getNextSibling().getNextSibling();
						while(child != null){	
							
							if (child.getNodeType() == Node.ELEMENT_NODE) {//on est dans catalogue
								
								Element eElement = (Element) child;
								if(eElement.getAttribute("titre").equals(titrePhoto)){					
									eElement.getParentNode().removeChild(eElement);
									//System.out.println("je suis la   "+titrePhoto);

									for(Catalogue c: catalogues)
										if(c.getTheme().equals(themeCatalogue)){
											Photo aRetirer;
											aRetirer=c.retirerPhoto(titrePhoto);
											//suppression de la photo du dossier
											
											String pathImg=aRetirer.getImg();
											new File(pathImg).delete();
											
											Set<Entry<String, List<Photo>>> setHm = photosAuteur.entrySet();
											for(Entry<String, List<Photo>> e :setHm){
												if(e.getKey().equals(aRetirer.getAuteur().getPrenomP().concat(aRetirer.getAuteur().getNomP())))
													e.getValue().remove(aRetirer);
											}
											if(c.getPhotos().size()==0){
												supprimerCatalogue(c.getTheme());
												racine.removeChild(catalogue);
											}
											break;
										}
								}
							}
							if((child = child.getNextSibling()) == null )
								break;
							else
								child = child.getNextSibling();
							
					    }
					}
				}
				if((catalogue = catalogue.getNextSibling())==null)
					break;
				else
					catalogue = catalogue.getNextSibling();
			}
			System.out.println(input.getSystemId()+"   "+output.getSystemId());

			transformer.transform(input, output);
		} catch (ParserConfigurationException | SAXException | IOException | TransformerFactoryConfigurationError | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	public void majGallerie(){
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document document = null;
		try {
			final DocumentBuilder builder = factory.newDocumentBuilder();
			String path = this.getClass().getResource("/").getPath();
			path=path.substring(0, path.lastIndexOf("/.metadata"));
			System.out.println(path);
			document= builder.parse(new File(path+"/projet_catalogue/WebContent/Catalogue.xml"));
			//document= builder.parse(new File("C:/Users/Imen/Desktop/GIT/Nouveau dossier/Catalogue/WebContent/"+"Catalogue.xml"));
			
			final Element racine = document.getDocumentElement();

			for (Node catalogue= racine.getFirstChild().getNextSibling(); catalogue != null;
			         catalogue = catalogue.getNextSibling().getNextSibling()) {
				//System.out.println(catalogue.getNodeName());
			    
				Catalogue cat=new Catalogue();
				catalogues.add(cat);
				//System.out.println("\n"+temp+" Current Element :" + catalogue.getNodeName());

				if (catalogue.getNodeType() == Node.ELEMENT_NODE) {//on est dans catalogue
					Element eElement = (Element) catalogue;
					Personne pAuteur=new Personne();
					//on parse l'auteur
					Node auteurCatal= catalogue.getFirstChild().getNextSibling(); 
					if (auteurCatal.getNodeType() == Node.ELEMENT_NODE) {//on est dans auteur
						Element eAuteur= (Element) auteurCatal;
						pAuteur.setNomP(eAuteur.getElementsByTagName("nomP").item(0).getTextContent());
						pAuteur.setPrenomP(eAuteur.getElementsByTagName("prenomP").item(0).getTextContent());
						pAuteur.setEmail(eAuteur.getElementsByTagName("email").item(0).getTextContent());
						//System.out.println("nomAuteur: " + pAuteur.getNomP());
					}
					
					cat.setAuteur(pAuteur);
					cat.setTheme(eElement.getAttribute("theme"));
					cat.setDateCreation(eElement.getAttribute("dateCreation"));
					for (Node photo= catalogue.getFirstChild().getNextSibling().getNextSibling().getNextSibling(); photo!= null;
							photo= photo.getNextSibling().getNextSibling()) {
					        
						String pathImg = this.getClass().getResource("/").getPath();
						pathImg=pathImg.substring(0, pathImg.lastIndexOf("/.metadata"));
						pathImg+="/projet_catalogue/WebContent/WEB-INF/images/";
//						pathImg ="C:/Users/Imen/Desktop/GIT/Nouveau dossier/Catalogue/WebContent/WEB-INF/images/";
						pathImg+=cat.getTheme()+"/";
						System.out.println(pathImg);
						
						
						Photo p=new Photo();
						p.setCat(cat.getTheme());
						cat.ajouterPhoto(p);
						//System.out.println(photo.getNodeName());
						if (photo.getNodeType() == Node.ELEMENT_NODE) {
							Element ePhoto= (Element) photo;

							//System.out.println("\nCurrent Element :"+ePhoto.getAttribute("nomAuteur"));

							p.setDatePrise(ePhoto.getAttribute("datePrise"));

							//System.out.println(ePhoto.getAttribute("lieu"));

							p.setLieu(ePhoto.getAttribute("lieu"));
							p.setTitre(ePhoto.getAttribute("titre"));

							p.setCategorie(ePhoto.getElementsByTagName("categorie").item(0).getTextContent());
							
							String titre=ePhoto.getElementsByTagName("img").item(0).getTextContent();
							p.setImg(pathImg+titre);
							p.setDateAjout(ePhoto.getElementsByTagName("dateAjout").item(0).getTextContent());
							p.setDimension(ePhoto.getElementsByTagName("dimension").item(0).getTextContent());
							p.setResolution(Integer.parseInt(ePhoto.getElementsByTagName("resolution").item(0).getTextContent()));
							p.setCommentaire(ePhoto.getElementsByTagName("commentaire").item(0).getTextContent());
							
							Node auteurPhoto= ePhoto.getElementsByTagName("personne").item(0);
							
							if (auteurPhoto.getNodeType() == Node.ELEMENT_NODE) {//on est dans auteur
								Element eAuteur= (Element) auteurPhoto;
								Personne pAuteurPhoto=new Personne();
								p.setAuteur(pAuteurPhoto);
								pAuteurPhoto.setNomP(eAuteur.getElementsByTagName("nomP").item(0).getTextContent());
								pAuteurPhoto.setPrenomP(eAuteur.getElementsByTagName("prenomP").item(0).getTextContent());
								pAuteurPhoto.setEmail(eAuteur.getElementsByTagName("email").item(0).getTextContent());
								if(photosAuteur.containsKey(pAuteurPhoto.getPrenomP()+pAuteurPhoto.getNomP())){
									photosAuteur.get(pAuteurPhoto.getPrenomP()+pAuteurPhoto.getNomP()).add(p);
								}
								else{
									List<Photo> l = new CopyOnWriteArrayList<Photo>();
									l.add(p);
									photosAuteur.put(pAuteurPhoto.getPrenomP()+pAuteurPhoto.getNomP(), l);
								}
							}
						}
					}
				}
			}				
		}
		catch (final ParserConfigurationException e) {
			e.printStackTrace();
		}
		catch (final SAXException e) {
			e.printStackTrace();
		}
		catch (final IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void ajouterPhoto(String theme, Photo nvelleP) {
		for(Catalogue c: catalogues)
			if(c.getTheme().equals(theme)){				
				c.ecrireXML(nvelleP);
				//photosAuteur.get(nvelleP.getAuteur()).add(nvelleP);
				c.getPhotos().add(nvelleP);
				break;
			}
	}
	public void ajouterCatalogue(String parameter, String parameter2, String parameter3) {
		// TODO Auto-generated method stub
		
	}
}
