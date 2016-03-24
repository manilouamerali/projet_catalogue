import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Catalogue {
	private Personne auteur;
	private String dateCreation;
	private String theme;
	private String dateModif;
	private List<Photo> photos=new ArrayList<Photo>();
	
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
	public void retirerPhoto(String titre){
		//A vérifier
		for(Photo p: photos)
			if(p.getTitre().equals(titre))
				photos.remove(p);
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
}
