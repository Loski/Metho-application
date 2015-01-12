package test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.*;

import Liste.Etudiant;
import Liste.Liste;


public class ListeTest {
	
	protected Liste l1;

	@Before
	public void setUp(){
		
		ArrayList<Etudiant> etd1 = new ArrayList<Etudiant>();
		etd1.add(new Etudiant("A","B"));
		
		l1 = new Liste("Liste",etd1);
	}
	
	@Test
	public void testSauvegarderListe() {
		Liste ltest = new Liste("A1",new ArrayList<Etudiant>());
		ltest.sauvegarderListe();
		assertEquals(true, new File("Sauvegarde/A1").exists());
	}

	@Test
	public void testChargerListe() {
		l1.sauvegarderListe();
		ArrayList<Etudiant> etd1 = new ArrayList<Etudiant>();
		etd1.add(new Etudiant("A","B"));
		
		Liste ltest = new Liste("Liste",etd1);
		ltest.chargerListe();
		assertEquals(true, ltest.equals(l1));
	}

	@Test
	public void testAjouterEtudiant() {
		Etudiant eNV = new Etudiant("A0","AO");
		l1.ajouterEtudiant(eNV);
		ArrayList<Etudiant> etd1 = new ArrayList<Etudiant>();
		etd1.add(new Etudiant("A","B"));
		etd1.add(eNV);
		assertEquals(true, etd1.size()==l1.getEtudiant().size());
		int i=0;
		while(i<etd1.size())
		{
			assertEquals(true,l1.getEtudiant().get(i).equals(etd1.get(i)));
			i++;
		}
	}

	@Test
	public void testSupprimerEtudiant() {
		Etudiant eNV = new Etudiant("A0","AO");
		l1.ajouterEtudiant(eNV);
		l1.supprimerEtudiant(eNV);
		assertEquals(true, l1.getEtudiant().size()==1);
	}

	@Test
	public void testTrier() {
		ArrayList<Etudiant> etd1 = new ArrayList<Etudiant>();
		etd1.add(new Etudiant("B","B"));
		etd1.add(new Etudiant("A","A"));
		
		l1.setEtudiant(etd1);
		
		ArrayList<Etudiant> etd2 = new ArrayList<Etudiant>();
		etd2.add(new Etudiant("A","A"));
		etd2.add(new Etudiant("B","B"));
		
		l1.trier();
		int i=0;
		while(i<etd1.size())
		{
			assertEquals(true,l1.getEtudiant().get(i).equals(etd2.get(i)));
			i++;
		}
	}

}
