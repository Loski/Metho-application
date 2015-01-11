package test;

import static org.junit.Assert.*;

import org.junit.*;

import Liste.Etudiant;

public class EtudiantTest {

	protected Etudiant e1;
	protected Etudiant e2;
	protected Etudiant e3;
	
	@Before
	public void setUp(){
		e1 = new Etudiant("A","B");
		e2 = new Etudiant("A","B");
		e3 = new Etudiant("B","C");
	}
	
	@Test
	public void testCompareTo() {
		assertEquals(0, e1.compareTo(e2));
		assertEquals(-1, e1.compareTo(e3));
		assertEquals(1, e3.compareTo(e1));
	}

	@Test
	public void testEqualsEtudiant() {
		assertEquals(true, e1.equals(e2));
		assertEquals(false, e1.equals(e3));
	}

}
