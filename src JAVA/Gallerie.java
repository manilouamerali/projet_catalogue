import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
		catalogues=new ArrayList<Catalogue>();
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
			path = path.replace("WEB-INF/classes","");
			document= builder.parse(new File(path+"Catalogue.xml"));
			//document= builder.parse(new File("C:/Users/Imen/Desktop/GIT/Nouveau dossier/Catalogue/WebContent/"+"Catalogue.xml"));
			
			Source input = new DOMSource(document);
			//Result output = new StreamResult(new File("C:/Users/Imen/Desktop/GIT/Nouveau dossier/Catalogue/WebContent/"+"Catalogue.xml"));
			Result output = new StreamResult(new File("C:/Users/Imen/Desktop/GIT/Nouveau dossier/Catalogue/WebContent/"+"Catalogue.xml"));
			final Element racine = document.getDocumentElement();
			
			Node catalogue= racine.getFirstChild().getNextSibling(); 
			while(catalogue != null){
				//System.out.println(catalogue.getNodeName());		
				if (catalogue.getNodeType() == Node.ELEMENT_NODE) {//on est dans catalogue
					Element eCata = (Element) catalogue;
					if(eCata.getAttribute("theme").equals(themeCatalogue)){
						//supprimer l'ensemble des photos du catalogue puis supprimer le catalogue
						Node child = catalogue.getFirstChild().getNextSibling().getNextSibling().getNextSibling();
						while(child != null){	
							catalogue.removeChild(child);
							
//							if (child.getNodeType() == Node.ELEMENT_NODE) {//on est dans catalogue
//								Element eElement = (Element) child;
//								
//								if(eElement.getAttribute("titre").equals(titrePhoto)){					
//									eElement.getParentNode().removeChild(eElement);
//									for(Catalogue c: catalogues)
//										if(c.getTheme().equals(themeCatalogue)){				
//											Photo aRetirer;
//											aRetirer=c.retirerPhoto(titrePhoto);
//											photosAuteur.get(aRetirer.getAuteur()).remove(aRetirer);
//											break;
//										}
//								}
//							}
							if((child = child.getNextSibling()) == null )
								break;
							else
								child = child.getNextSibling();
					    }
						racine.removeChild(catalogue);
						for(Catalogue c: catalogues)
							if(c.getTheme().equals(themeCatalogue)){				
								Set<Entry<String, List<Photo>>> setHm = photosAuteur.entrySet();
								for (Photo p: c.getPhotos()){
									for(Entry<String, List<Photo>> e :setHm){
										if(e.getKey().equals(p.getAuteur().getPrenomP().concat(p.getAuteur().getNomP())))
											e.getValue().remove(p);
									}
									c.retirerPhoto(p.getTitre());
									
								}
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void supprimerPhoto(String themeCatalogue, String titrePhoto){
		//A v�rifier
		//Corriger le chemin
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document document;
		DocumentBuilder builder;
		try {
			
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			builder = factory.newDocumentBuilder();
			String path = this.getClass().getResource("/").getPath();
			path = path.replace("WEB-INF/classes","");
			document= builder.parse(new File(path+"Catalogue.xml"));
			//document= builder.parse(new File("C:/Users/Imen/Desktop/GIT/Nouveau dossier/Catalogue/WebContent/"+"Catalogue.xml"));

			Source input = new DOMSource(document);
			Result output = new StreamResult(new File(path+"Catalogue.xml"));			
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
									System.out.println("je suis la   "+titrePhoto);

									for(Catalogue c: catalogues)
										if(c.getTheme().equals(themeCatalogue)){			
											Photo aRetirer;
											aRetirer=c.retirerPhoto(titrePhoto);
											Set<Entry<String, List<Photo>>> setHm = photosAuteur.entrySet();
											for(Entry<String, List<Photo>> e :setHm){
												if(e.getKey().equals(aRetirer.getAuteur().getPrenomP().concat(aRetirer.getAuteur().getNomP())))
													e.getValue().remove(aRetirer);
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
			
			path = path.replace("WEB-INF/classes","");
			System.out.println("maj gallerie " +path);
			document = builder.parse(new File(path+"Catalogue.xml"));
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
					//System.out.println("nomAuteur: " + eElement.getAttribute("nomAuteur"));
					//System.out.println(nbPhotos);
					
					for (Node photo= catalogue.getFirstChild().getNextSibling().getNextSibling().getNextSibling(); photo!= null;
							photo= photo.getNextSibling().getNextSibling()) {
					        //System.out.println(photo.getNodeName());
					        
						//System.out.println("\n"+i+" Current Element :" + racineCatalogue.item(i).getTextContent());
						//System.out.println("nombre de zebi"+racineCatalogue.item(i).getTextContent());

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

							//System.out.println("\nCurrent Element :" + ePhoto.getNodeName());
							//System.out.println(ePhoto.getElementsByTagName("resolution").item(0).getTextContent());

							p.setCategorie(ePhoto.getElementsByTagName("categorie").item(0).getTextContent());
							p.setImg(ePhoto.getElementsByTagName("img").item(0).getTextContent());
							p.setDateAjout(ePhoto.getElementsByTagName("dateAjout").item(0).getTextContent());
							p.setDimension(ePhoto.getElementsByTagName("dimension").item(0).getTextContent());
							p.setResolution(Integer.parseInt(ePhoto.getElementsByTagName("resolution").item(0).getTextContent()));
							p.setCommentaire(ePhoto.getElementsByTagName("commentaire").item(0).getTextContent());
							
							Node auteurPhoto= ePhoto.getElementsByTagName("personne").item(0);
							//System.out.println("\nCurrent Element :" + ePhoto.getNodeName());
							
							if (auteurPhoto.getNodeType() == Node.ELEMENT_NODE) {//on est dans auteur
								Element eAuteur= (Element) auteurPhoto;
								Personne pAuteurPhoto=new Personne();
								p.setAuteur(pAuteurPhoto);
								pAuteurPhoto.setNomP(eAuteur.getElementsByTagName("nomP").item(0).getTextContent());
								pAuteurPhoto.setPrenomP(eAuteur.getElementsByTagName("prenomP").item(0).getTextContent());
								pAuteurPhoto.setEmail(eAuteur.getElementsByTagName("email").item(0).getTextContent());
								//System.out.println("\nCurrent Element :" + pAuteurPhoto.getNomP());
								//System.out.println("\nCurrent Element :" + pAuteurPhoto.getNomP()+pAuteurPhoto.getPrenomP());
								if(photosAuteur.containsKey(pAuteurPhoto.getPrenomP()+pAuteurPhoto.getNomP())){
									photosAuteur.get(pAuteurPhoto.getPrenomP()+pAuteurPhoto.getNomP()).add(p);
									//System.out.println("existe deja:" + pAuteurPhoto.getNomP());
								}
								else{
									List<Photo> l = new ArrayList<Photo>();
									l.add(p);
									photosAuteur.put(pAuteurPhoto.getPrenomP()+pAuteurPhoto.getNomP(), l);
									//System.out.println("n'existe pas:" + pAuteurPhoto.getNomP());
								}
								
//								for(String lPhotos : photosAuteur.keySet()){
//									System.out.println(lPhotos);
//								}
//								for(List<Photo> lPhotos : photosAuteur.values()){
//									for(Photo pho:lPhotos)//c'est la boucle pour afficher les photos pour chaque auteur
////										System.out.println(pho.getImg());
//								}
							}
						}
					}
				}	
				//System.out.println("\nCurrent Element :" + photosAuteur.size());
				
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
				System.out.println("ajouterPhoto Gallerie : "+nvelleP.getImg());
				c.ecrireXML(nvelleP);
				//photosAuteur.get(nvelleP.getAuteur()).add(nvelleP);
				break;
			}
	}
}
