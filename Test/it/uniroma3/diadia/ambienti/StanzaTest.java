package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo; 

public class StanzaTest {
	
	private Stanza s1;
	private Stanza s2;
	private Attrezzo a1;
	
	@BeforeEach
	public void setUp() {
		s1 = new Stanza("s1");
		s2 = new Stanza("s2");
		s1.impostaStanzaAdiacente("sud", s2);
		s2.impostaStanzaAdiacente("nord", s1);
		a1 = new Attrezzo("a1", 1);
	}
	
	/**
	 * Test su una direzione inesistente
	 */
	@Test
	public void testGetStanzaAdiacenteDirezioneSbagliata() {
		assertNull(s2.getStanzaAdiacente("sud"));
	}
	
	/**
	 * Test su una direzione in cui c'è una stanza
	 */
	@Test
	public void testGetStanzaAdiacenteConStanzaNellaDirezione() {
		assertEquals(s2, s1.getStanzaAdiacente("sud"));
	}
	
	/**
	 * Test su una stanza con più direzioni
	 */
	@Test
	public void testGetStanzaAdiacenteConPiuStanze() {
		Stanza s3 = new Stanza("s3");
		s1.impostaStanzaAdiacente("nord", s3);
		/* s1 contiene anche la stanza s2 in direzione sud */
		assertEquals(s3, s1.getStanzaAdiacente("nord"));
	}
	
	/**
	 * Test per verificare la giusta aggiunta di un attrezzo nella stanza
	 */
	@Test
	public void testAddAttrezzo() {
		assertTrue(s1.addAttrezzo(a1));
		assertEquals(a1, s1.getAttrezzo("a1"));
	}
	
	/**
	 * Test per verificare l'assenza di un attrezzo
	 */
	@Test
	public void testHasAttrezzoConAttrezzoNonPresente() {
		assertFalse(s1.hasAttrezzo("a1"));
	}
	
	/**
	 * Test per verificare la presenza di un attrezzo
	 */
	@Test
	public void testHasAttrezzoConAttrezzoPresente() {
		s1.addAttrezzo(a1);
		assertTrue(s1.hasAttrezzo("a1"));
	}
	
	/**
	 * Test di ricerca di un attrezzo non presente nella stanza
	 */
	@Test
	public void testGetAttrezzoStanzaVuota() {
		assertNull(s1.getAttrezzo("a1"));
	}
	
	/**
	 * Test di ricerca di un attrezzo presente nella stanza
	 */
	@Test
	public void testGetAttrezzoUnAttrezzo() {
		s1.addAttrezzo(a1);
		assertEquals(a1, s1.getAttrezzo("a1"));
	}
	
	/**
	 * Test di ricerca con più oggetti
	 */
	@Test
	public void testGetAttrezzoPiuAttrezzi() {
		s1.addAttrezzo(a1);
		Attrezzo a2 = new Attrezzo("a2", 2);
		s1.addAttrezzo(a2);
		assertEquals(a2, s1.getAttrezzo("a2"));
	}
	
	/**
	 * Test per verificare la rimozione di un attrezzo non presente nella stanza
	 */
	public void testRemoveAttrezzoNonPresente() {
		assertFalse(s1.removeAttrezzo(a1));
	}
	
	/**
	 * Test per verificare la rimozione di un attrezzo dalla stanza
	 */
	public void testRemoveAttrezzoPresente() {
		s1.addAttrezzo(a1);
		assertTrue(s1.removeAttrezzo(a1));
		assertNull(s1.getAttrezzo("a1"));
	}
	
}
