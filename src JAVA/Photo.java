import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Photo{
    private String datePrise;
    private String lieu;
    private Personne auteur;
    private String dateModif;
    private String titre;
    private String commentaire;
    private String img;
    private String cat;
    
	public String getDatePrise() {
		return datePrise;
	}
	public void setDatePrise(String datePrise) {
		this.datePrise = datePrise;
	}
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	public String getDateModif() {
		return dateModif;
	}
	public void setDateModif(String dateModif) {
		this.dateModif = dateModif;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Personne getAuteur() {
		return auteur;
	}
	public void setAuteur(Personne auteur) {
		this.auteur = auteur;
	}
	
	public String getCat() {
		return cat;
	}
	public void setCat(String cat) {
		this.cat = cat;
	}
}