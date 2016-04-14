public class Personne {
	private int idP;
	private String nomP;
	private String prenomP;
	public Personne(){
	}
	public Personne(String _nomP,String _prenomP){
		nomP=_nomP;
		prenomP=_prenomP;
	}

	public int getIdP() {
		return idP;
	}

	public int hashCode(){
		return (nomP+prenomP).hashCode();
	}
	public String getNomP() {
		return nomP;
	}

	public boolean equals(Object o){
		if (o instanceof Personne){
			Personne p=(Personne) o;
			return nomP.equals(p.nomP) && prenomP.equals(p.prenomP);
		}
		return false;
	}

	public void setNomP(String nomP) {
		this.nomP = nomP;
	}
	public String getPrenomP() {
		return prenomP;
	}
	public void setPrenomP(String prenomP) {
		this.prenomP = prenomP;
	}
	
}
