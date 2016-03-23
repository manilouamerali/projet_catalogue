import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Gallerie {
	private List<Catalogue> catalogues=new ArrayList<Catalogue>();
	
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

	private Map < Personne , List < Photo > > photosAuteur =new Hashtable<Personne , List < Photo > >();
	
	public void majGallerie(){
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
try {
			
			final DocumentBuilder builder = factory.newDocumentBuilder();

			final Document document= (Document) builder.parse(new File("/Users/Imen/Desktop/M1_Cours/XML/Eclipse EE/Nouveau dossier/Catal/WebContent/Catalogue.xml"));

			final Element racine = document.getDocumentElement();

			NodeList racineNoeuds = (NodeList) racine.getChildNodes();
			int nbRacineNoeuds = racineNoeuds.getLength();

			for (int temp = 1; temp < nbRacineNoeuds; temp+=2) {
				Node catalogue= racineNoeuds.item(temp);
				Catalogue cat=new Catalogue();
				catalogues.add(cat);
				//System.out.println("\n"+temp+" Current Element :" + catalogue.getNodeName());

				if (catalogue.getNodeType() == Node.ELEMENT_NODE) {//on est dans catalogue
					Element eElement = (Element) catalogue;
					Personne pAuteur=new Personne();
					//on parse l'auteur
					Node auteurCatal= racineNoeuds.item(1);
					if (auteurCatal.getNodeType() == Node.ELEMENT_NODE) {//on est dans auteur
						Element eAuteur= (Element) auteurCatal;
						pAuteur.setNomP(eAuteur.getElementsByTagName("nomP").item(0).getTextContent());
						pAuteur.setPrenomP(eAuteur.getElementsByTagName("prenomP").item(0).getTextContent());
						pAuteur.setEmail(eAuteur.getElementsByTagName("email").item(0).getTextContent());
						//System.out.println("nomAuteur: " + pAuteur.getNomP());
					}
					
					cat.setAuteur(pAuteur);
					cat.setTheme(eElement.getElementsByTagName("theme").item(0).getTextContent());
					cat.setDateCreation(eElement.getAttribute("dateCreation"));
					//System.out.println("nomAuteur: " + eElement.getAttribute("nomAuteur"));


					NodeList racineCatalogue= (NodeList) catalogue.getChildNodes();

					int nbPhotos = racineCatalogue.getLength();

					for (int i = 5; i< nbPhotos; i+=2) {//on parcourt les photos du catalogue

						//System.out.println("\n"+i+" Current Element :" + racineCatalogue.item(i).getTextContent());
						//System.out.println("nombre de zebi"+racineCatalogue.item(i).getTextContent());

						Node photo= racineCatalogue.item(i);
						Photo p=new Photo();
						cat.ajouterPhoto(p);

						if (photo.getNodeType() == Node.ELEMENT_NODE) {
							Element ePhoto= (Element) photo;

							//System.out.println("\nCurrent Element :"+ePhoto.getAttribute("nomAuteur"));

							//AJOUTER L'AUTEUR

							p.setDatePrise(ePhoto.getAttribute("datePrise"));

							//System.out.println(ePhoto.getAttribute("lieu"));

							p.setLieu(ePhoto.getAttribute("lieu"));

							//System.out.println("\nCurrent Element :" + ePhoto.getNodeName());
							//System.out.println(ePhoto.getElementsByTagName("resolution").item(0).getTextContent());

							p.setCategorie(ePhoto.getElementsByTagName("categorie").item(0).getTextContent());
							p.setImg(ePhoto.getElementsByTagName("img").item(0).getTextContent());
							p.setDateAjout(ePhoto.getElementsByTagName("dateAjout").item(0).getTextContent());
							p.setTitre(ePhoto.getElementsByTagName("titre").item(0).getTextContent());
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
									//System.out.println("\nCurrent Element :" + pAuteurPhoto.getNomP());
								}
								else{
									List<Photo> l = new ArrayList<Photo>();
									l.add(p);
									photosAuteur.put(pAuteurPhoto, l);
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
		}
	}
}
