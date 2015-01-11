package Liste;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class Liste implements OperationListe, Serializable {
	private String idListe;
	private ArrayList<Etudiant> etudiant;
	
	public Liste() {
		File dossier = new File("Sauvegarde");
		{
			if (!dossier.exists())
				dossier.mkdirs();
		}
		etudiant = new ArrayList<Etudiant>();
		/*etudiant.add(new Etudiant("Lavaste", "Maxime"));
		etudiant.add(new Etudiant("avaste", "Maxime"));
		etudiant.add(new Etudiant("Lafontaine", "Loic"));*/
		idListe =  "gr1";
	}

	@Override
	public void sauvegarderListe() {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("Sauvegarde").getCanonicalFile() + "/"+ idListe)));
			oos.writeObject(this);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String[] lister()
	{
		return  new File("Sauvegarde/").list();	
	}
	@Override
	public void chargerListe() {
		Liste l = new Liste();
			ObjectInputStream ois = null;
			try {
				ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("Sauvegarde") + "/" + idListe)));
				
				l= (Liste) ois.readObject();		
				this.setIdListe(l.idListe);
				this.setEtudiant(l.etudiant);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}


	@Override
	public void ajouterEtudiant(Etudiant e) {
		etudiant.add(e);
	}
 
	@Override
	public Etudiant supprimerEtudiant(Etudiant e) {
		ListIterator<Etudiant> iterator = etudiant.listIterator();
        while (iterator.hasNext()) {
        	Etudiant etd = iterator.next();
        	if(etd.equals(e))
        		iterator.remove();
        }
        return null;
	}

	public Liste(String idListe, ArrayList<Etudiant> etudiant) {
		this.idListe = idListe;
		this.etudiant = etudiant;
	}

	@Override
	public String toString() {
		return "Liste [idListe=" + idListe + ", etudiant=" + etudiant + "]";
	}

	
	public String getIdListe() {
		return idListe;
	}

	
	public void setIdListe(String idListe) {
		this.idListe = idListe;
	}

	
	public ArrayList<Etudiant> getEtudiant() {
		return etudiant;
	}

	
	public void setEtudiant(ArrayList<Etudiant> etudiant) {
		this.etudiant = etudiant;
	}
	public void trier(){
		Collections.sort(etudiant);
	}
	
}
