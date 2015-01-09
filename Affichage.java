import java.awt.*;

import javax.swing.*;


public class Affichage extends JFrame{

	//private Liste[] listes;
	private JTable tableau;
	
	public Affichage(){
		super("Liste");
		this.initialise();
		/*Toolkit t1 = Toolkit.getDefaultToolkit();
		Dimension d = t1.getScreenSize();
		this.setBounds(0,0,d.width,d.height);*/
		this.setBounds(0,0,800,600);
		this.setResizable(false);
		this.setLocationRelativeTo(null); // Centre la fenetre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void initialise(){
		Container contentpane = this.getContentPane();
		this.setJMenuBar(creerMenu());
		contentpane.add(creerBoutons(),BorderLayout.SOUTH);
		tableau = new JTable();
		contentpane.add(tableau,BorderLayout.CENTER);
		this.afficherToutesLesListes();
		this.afficherListe();
	}
	
	public JMenuBar creerMenu(){
		JMenuBar barre = new JMenuBar();
		JMenu menu = new JMenu("Changer de Liste");
		JMenuItem all = new JMenuItem("Afficher toutes les listes");
		menu.add(all);
		menu.addSeparator();
		
		/*
		 * 
		 */
		
		barre.add(menu);
		return barre;
	}
	
	public JPanel creerBoutons(){
		JPanel bouton = new JPanel();
		JButton save = new JButton("Sauvegarder la liste");
		bouton.add(save);
		return bouton;
	}
	
	public void afficherToutesLesListes(){
		this.setTitle("Toutes les listes");
		this.getContentPane().remove(tableau);
		/*
		 * for(Liste l:listes)
		 * {
		 * 	String[]
		 * }
		 */
		//Object [][] data = 
				 
				 String[] titre = {"Nom","Prenom","Groupe"}; 
				 
				 this.tableau = new JTable(data,titre);

				 this.getContentPane().add(tableau.getTableHeader(),BorderLayout.NORTH);
				 this.getContentPane().add(tableau,BorderLayout.CENTER);
	}
	
	public void afficherListe(/*Liste l*/){
		//this.setTitle(l.getTitre()));
		this.getContentPane().remove(tableau);
		Object [][] data = 
			{{"A","B"},
			{"A","B"},
			{"A","B"},
			{"A","B"},
			{"A","B"}};
				//l.getTableau();
		 
		 String[] titre = {"Nom","Prenom"}; 
		 
		 this.tableau = new JTable(data,titre);

		 this.getContentPane().add(tableau.getTableHeader(),BorderLayout.NORTH);
		 this.getContentPane().add(tableau,BorderLayout.CENTER);
		
	}
	
	public void ajouterEtudiant(){
		
	}
	
	public void supprimerEtudiant(){
		
	}
	
	
	public static void main(String[] args) {
		new Affichage();
		return;
	}
	
}
