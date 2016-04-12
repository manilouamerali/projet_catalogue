import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Photo{
    private String datePrise;
    private String lieu;
    private Personne auteur;
    private String dateAjout;
    private String dateModif;
    private String titre;
    private String dimension;
    private int resolution;
    private String categorie;
    private String commentaire;
    private String img;
    private int nbVotes;
    private int sommeVotes;
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
	
	public String getDateAjout() {
		return dateAjout;
	}
	public void setDateAjout(String dateAjout) {
		this.dateAjout = dateAjout;
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
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	public int getResolution() {
		return resolution;
	}
	public void setResolution(int resolution) {
		this.resolution = resolution;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
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
    
	public int getSommeVotes() {
		return sommeVotes;
	}
	public void setSommeVotes(int sommeVotes) {
		this.sommeVotes = sommeVotes;
	}
	public int getNbVotes() {
		return nbVotes;
	}
	public void setNbVotes(int nbVotes) {
		this.nbVotes = nbVotes;
	}
	public int getNote(){
		return (int)sommeVotes/nbVotes;
	}
	public void creerPhoto(String datePrise, String lieu, String nomAuteur,String prenomAuteur, String emailAuteur, 
			String titre, String dimension, int resolution, String categorie,
			String commentaire,String img, String cat){
		Photo p = new Photo();
		this.setDatePrise(datePrise);
		this.setLieu(lieu);
		this.setAuteur(new Personne(nomAuteur, prenomAuteur, emailAuteur));
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		this.setDateAjout(dateFormat.format(date));
		
		this.setDateModif(dateModif);
		this.setTitre(titre);
		this.setDimension(dimension);
		this.setResolution(resolution);
		this.setCategorie(categorie);
		this.setCommentaire(commentaire);
		this.setImg(img);
		this.setNbVotes(0);
		this.setSommeVotes(0);
		this.setCat(cat);
	}
	public String getCat() {
		return cat;
	}
	public void setCat(String cat) {
		this.cat = cat;
	}
}