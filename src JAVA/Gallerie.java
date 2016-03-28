import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

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
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Gallerie {
	
	private static List<Catalogue> catalogues=new ArrayList<Catalogue>();
	private static Map < Personne , List < Photo > > photosAuteur =new Hashtable<Personne , List < Photo > >();

	public List<Catalogue> getCatalogues() {
		return catalogues;
	}

	public void setCatalogues(List<Catalogue> catalogues) {
		this.catalogues = catalogues;
	}

	public Map<Personne, List<Photo>> getPhotosAuteur() {
		return photosAuteur;
	}

	public void setPhotosAuteur(Map<Personne, List<Photo>> photosAuteur) {
		this.photosAuteur = photosAuteur;
	}
	
	public void supprimerCatalogue(String themeCatalogue){
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document document;
		DocumentBuilder builder;
		try {
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			Result output = new StreamResult(new File("Catalogue.xml"));
			
			builder = factory.newDocumentBuilder();
			String path = this.getClass().getResource("/").getPath();
			path = path.replace("WEB-INF/classes","");
			document= builder.parse(new File(path+"Catalogue.xml"));
			
			Source input = new DOMSource(document);
			final Element racine = document.getDocumentElement();
			
			Node catalogue= racine.getFirstChild().getNextSibling(); 
			while(catalogue != null){
				System.out.println(catalogue.getNodeName());		
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
		//A vérifier
		//Corriger le chemin
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document document;
		DocumentBuilder builder;
		try {
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			Result output = new StreamResult(new File("Catalogue.xml"));
			
			builder = factory.newDocumentBuilder();
			String path = this.getClass().getResource("/").getPath();
			path = path.replace("WEB-INF/classes","");
			document= builder.parse(new File(path+"Catalogue.xml"));
			
			Source input = new DOMSource(document);
						
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
									for(Catalogue c: catalogues)
										if(c.getTheme().equals(themeCatalogue)){				
											Photo aRetirer;
											aRetirer=c.retirerPhoto(titrePhoto);
											photosAuteur.get(aRetirer.getAuteur()).remove(aRetirer);
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
			System.out.println(path);
			path = path.replace("WEB-INF/classes","");
			document= builder.parse(new File("C:/Users/Imen/Desktop/GIT/Nouveau dossier/Catalogue/WebContent/"+"Catalogue.xml"));
			
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
								pAuteurPhoto.setNomP(eAuteur.getElementsByTagName("nomP").item(0).getTextContent());
								pAuteurPhoto.setPrenomP(eAuteur.getElementsByTagName("prenomP").item(0).getTextContent());
								pAuteurPhoto.setEmail(eAuteur.getElementsByTagName("email").item(0).getTextContent());
								//System.out.println("\nCurrent Element :" + pAuteurPhoto.getNomP());
								//System.out.println("\nCurrent Element :" + pAuteurPhoto.getNomP()+pAuteurPhoto.getPrenomP());
								if(photosAuteur.containsKey(pAuteurPhoto)){
									photosAuteur.get(pAuteurPhoto).add(p);
									//System.out.println("existe deja:" + pAuteurPhoto.getNomP());
								}
								else{
									List<Photo> l = new ArrayList<Photo>();
									l.add(p);
									photosAuteur.put(pAuteurPhoto, l);
									//System.out.println("n'existe pas:" + pAuteurPhoto.getNomP());
								}
								
								for(List<Photo> lPhotos : photosAuteur.values()){
									for(Photo pho:lPhotos);//c'est la boucle pour afficher les photos pour chaque auteur
										//System.out.println(pho.getImg());
								}
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
}
