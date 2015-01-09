package Liste;

import java.io.Serializable;
import java.util.Observer;

public class Etudiant implements Comparable<Etudiant>, Serializable {
	private String prenom;
	private String nom;
	public Etudiant(String nom, String prenom) {
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

	@Override
	public int compareTo(Etudiant e2) {
		if(nom.compareTo(e2.nom) == 0)
			return prenom.compareTo(e2.prenom);	
		return nom.compareTo(e2.nom);
	}
	
	public boolean equals(Etudiant e){
		if (e.getNom().equals(this.nom) && e.getPrenom().equals(this.prenom))
			return true;
		
		return false;
	}

	@Override
	public String toString() {
		return "Etudiant [prenom=" + prenom + ", nom=" + nom + "]";
	}
}
 