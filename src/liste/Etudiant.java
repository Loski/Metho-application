package liste;

public class Etudiant {
	private String prenom;
	private String nom;
	public Etudiant(String prenom, String nom) {
		super();
		this.prenom = prenom;
		this.nom = nom;
	}

	public Etudiant() {
		
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
}
 