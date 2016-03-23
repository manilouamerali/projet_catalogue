public class Personne {
	private static int cmp=1;
	private int idP;
	private String nomP;
	private String prenomP;
	private String email;
	
	public Personne(){
		idP = cmp++;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
}
