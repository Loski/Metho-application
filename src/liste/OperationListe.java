package Liste;



public interface OperationListe {
	public void sauvegarderListe();
	public void chargerListe();
	public void ajouterEtudiant(Etudiant e);
	public Etudiant supprimerEtudiant(Etudiant e);
}
