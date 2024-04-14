package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;

import it.uniroma3.diadia.giocatore.Borsa;

public class BorsaTest {
	
	private Borsa b;
	private Attrezzo a1;
	private Attrezzo a2;
	private Attrezzo a3;
	
	@BeforeEach
	void setUp(){
		b = new Borsa();
		a1 = new Attrezzo("a1", 1);
		a2 = new Attrezzo("a2", 2);
		a3 = new Attrezzo("a3", 3);
		b.addAttrezzo(a1);
		b.addAttrezzo(a2);
		b.addAttrezzo(a3);
	}
	
	@Test
	void testAddAttrezzoBorsaVuota() {
		assertTrue(new Borsa().addAttrezzo(a1));
	}
	
	@Test
	void testAddAttrezzoBorsaPiena() {
		a2 = new Attrezzo("a2", 20);
		assertFalse(b.addAttrezzo(a2));
	}
	
	@Test
	void testGetAttrezzoBorsaVuota() {
		assertNull(new Borsa().getAttrezzo("a1"));
	}
	
	@Test
	void testGetAttrezzoBorsaPiena() {
		assertEquals(a1, b.getAttrezzo("a1"));
	}

	@Test
	void testRemoveAttrezzoInizio() {
		assertEquals(a1, b.removeAttrezzo("a1"));
	}
	
	@Test
	void testRemoveAttrezzoInMezzo() {
		assertEquals(a2, b.removeAttrezzo("a2"));
	}
	
	@Test
	void testRemoveAttrezzoFine() {
		assertEquals(a3, b.removeAttrezzo("a3"));
	}
	
}
