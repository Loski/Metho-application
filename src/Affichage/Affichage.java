package Affichage;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import Liste.*;


public class Affichage extends JFrame{

	private ArrayList<Liste> listes;
	private Liste listeCourante;
	private JTable tableau;
	private JMenuBar barreMenu;
	private JButton ajoutEtudiant;
	private JButton saveButton;
	private JButton suppEtudiant;
	
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
		this.getContentPane().removeAll();
		this.chargerListe();
		Container contentpane = this.getContentPane();
		this.setJMenuBar(creerMenu());
		tableau = new JTable();
		contentpane.add(tableau,BorderLayout.CENTER);
		this.afficherToutesLesListes();
		this.repaint();
		this.validate();
	}
	
	public void chargerListe(){
		this.listes = new ArrayList<Liste>();
		String [] sTab = Liste.lister();
		if(sTab==null)
			sTab= new String[0];
		
		Arrays.sort(sTab);
		
		for(String s:sTab)
		{
			this.listes.add(new Liste(s,new ArrayList<Etudiant>()));
		}
		
		for(Liste l:listes)
			l.chargerListe();
		
	}
	
	public JMenuBar creerMenu(){
		this.barreMenu = new JMenuBar();
		JMenu menu = new JMenu("Changer de Liste");
		JMenuItem all = new JMenuItem("Afficher toutes les listes");
		all.addActionListener(new Listener());
		menu.add(all);
		menu.addSeparator();

		for(Liste l:listes)
		{
			JMenuItem it = new JMenuItem(l.getIdListe());
			it.addActionListener(new Listener());
			menu.add(it);
			
		}
		
		this.barreMenu.add(menu);
		
		
		this.ajoutEtudiant = new JButton("+Etudiant");
		this.ajoutEtudiant.setEnabled(false);
		this.ajoutEtudiant.addActionListener(new Listener());
		this.barreMenu.add(ajoutEtudiant);
		
		this.suppEtudiant = new JButton("-Etudiant");
		this.suppEtudiant.setEnabled(false);
		this.suppEtudiant.addActionListener(new Listener());
		this.barreMenu.add(suppEtudiant);
		
		JButton ajoutListe = new JButton("+Liste");
		ajoutListe.addActionListener(new Listener());
		this.barreMenu.add(ajoutListe);
		
		this.saveButton = new JButton("Sauvegarder la liste");
		this.saveButton.setEnabled(false);
		this.saveButton.addActionListener(new Listener());
		this.barreMenu.add(this.saveButton);
		
		return this.barreMenu;
	}
	
	public void afficherToutesLesListes(){
		this.setTitle("Toutes les listes");
		
		this.chargerListe();
		this.listeCourante=null;
		
		int size=0;
		
		for(Liste l:listes)
			size+=l.getEtudiant().size();
		
		Object[][] data = new Object[size][3];
		
		int i=0;
		
		for(Liste l:listes)
		{
			for(Etudiant e:l.getEtudiant())
			{
				data[i][0]=e.getNom();
				data[i][1]=e.getPrenom();
				data[i][2]=l.getIdListe();
				i++;
			}
		}
				 
		String[] titre = {"Nom","Prenom","Groupe"}; 
		
		//this.trierTab(data);
		
		this.tableau = new JTable(data,titre);

		this.getContentPane().add(tableau.getTableHeader(),BorderLayout.NORTH);
		this.getContentPane().add(tableau,BorderLayout.CENTER);
		repaint();
		validate();
	}
	
	public Object[][] trierTab(Object[][] data){
		
		int i=0;
		Object[][] obj = new Object[data.length][3];
		
		//Trier le tab
		
		return obj;
		
	}
	
	public void afficherListe(Liste l){
		this.ajoutEtudiant.setEnabled(true);
		this.setTitle(l.getIdListe());
		this.getContentPane().remove(tableau.getTableHeader());
		this.getContentPane().remove(tableau);
		this.chargerListe();
		this.listeCourante=l;
		this.saveButton.setEnabled(true);
		Object [][] data = new Object[l.getEtudiant().size()][2];
		
		int i=0;
		
		for(Etudiant e:l.getEtudiant())
		{
			data[i][0]=e.getNom();
			data[i][1]=e.getPrenom();
			i++;
		}
		 
		 String[] titre = {"Nom","Prenom"}; 
		 
		 this.tableau = new JTable(data,titre);
		 this.tableau.addFocusListener(new TabListener());

		 this.getContentPane().add(tableau.getTableHeader(),BorderLayout.NORTH);
		 this.getContentPane().add(tableau,BorderLayout.CENTER);
		 repaint();
		 validate();
		
	}
	
	public void ajouterEtudiant(){
		
		String nom =(String) JOptionPane.showInputDialog("Nom du nouvel étudiant");
		if(nom!=null && !nom.isEmpty())
		{
			String prenom =(String) JOptionPane.showInputDialog("Prénom du nouvel étudiant");
			if(prenom!=null && !prenom.isEmpty())
			{
				this.listeCourante.ajouterEtudiant(new Etudiant(nom,prenom));
				this.listeCourante.trier();
				this.listeCourante.sauvegarderListe();
				this.afficherListe(listeCourante);
			}
			
		}
			
	}
	
	public void ajouterListe(){
		String nom =(String) JOptionPane.showInputDialog("Nom de la liste");
		if(nom!=null && !nom.isEmpty())
		{
			Liste l = new Liste();
			l.setIdListe(nom);
			l.sauvegarderListe();
			this.initialise();
			this.afficherListe(this.trouverListe(l.getIdListe()));
		}
		
		
	}
	
	public void supprimerEtudiant(){
		if(this.tableau.getSelectedRow()>=0)
		this.listeCourante.supprimerEtudiant(this.listeCourante.getEtudiant().get(this.tableau.getSelectedRow()));
		this.listeCourante.sauvegarderListe();
		this.afficherListe(listeCourante);
	}
	
	public Liste trouverListe(String s){
		for(Liste l:listes)
			if(l.getIdListe().equals(s))
				return l;
		
		return null;
	}
	
	public void sauvegarderListe(){
		this.listeCourante = new Liste(this.listeCourante.getIdListe(),new ArrayList<Etudiant>());
		for(int i=0;i<this.tableau.getRowCount();i++)
				listeCourante.ajouterEtudiant(new Etudiant((String) this.tableau.getValueAt(i,0),(String) this.tableau.getValueAt(i, 1)));
		
		this.listeCourante.trier();
		this.listeCourante.sauvegarderListe();
	}
	
	public static void main(String[] args) {
		new Affichage();
		return;
	}
	
	public class Listener implements ActionListener{

		public void actionPerformed(ActionEvent ev) {
			String s=ev.getActionCommand();
			
			if(s.equals("+Liste"))
				ajouterListe();
			else if(s.equals("+Etudiant"))
				ajouterEtudiant();
			else if(s.equals("Afficher toutes les listes"))
				initialise();
			else if(ev.getSource()==saveButton)
				sauvegarderListe();
			else if(ev.getSource()==suppEtudiant)
				supprimerEtudiant();
			else
				afficherListe(trouverListe(s));
		}
		
	}
	
	public class TabListener implements FocusListener{

		public void focusGained(FocusEvent arg0) {
			if(tableau.getSelectedRow()>=0)
				suppEtudiant.setEnabled(true);
			
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			
			//suppEtudiant.setEnabled(false);
			
		}
		
	}
	
}
