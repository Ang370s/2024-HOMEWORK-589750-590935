package it.uniroma3.diadia;

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
		a1 = new Attrezzo("a1", 1);
	}
	
	
	@Test
	public void testGetStanzaAdiacenteVuota() {
		assertNull(s2.getStanzaAdiacente("sud"));
	}
	
	/**
	 * Questo test prova anche il metodo impostaStanzaAdiacente
	 */
	@Test
	public void testGetStanzaAdiacenteConUnaStanza() {
		assertEquals(s2, s1.getStanzaAdiacente("sud"));
	}
	
	@Test
	public void testGetStanzaAdiacenteConPiuStanze() {
		Stanza s3 = new Stanza("s3");
		s1.impostaStanzaAdiacente("nord", s3);
		/* s1 contiene anche la stanza s2 in direzione sud */
		assertEquals(s3, s1.getStanzaAdiacente("nord"));
	}
	
	
	@Test
	public void testAddAttrezzoGiusto() {
		assertTrue(s1.addAttrezzo(a1));
	}
	
	
	@Test
	public void testGetAttrezzoStanzaVuota() {
		assertNull(s1.getAttrezzo("a1"));
	}
	
	@Test
	public void testGetAttrezzoUnAttrezzo() {
		s1.addAttrezzo(a1);
		assertEquals(a1, s1.getAttrezzo("a1"));
	}
	
	@Test
	public void testGetAttrezzoPiuAttrezzi() {
		s1.addAttrezzo(a1);
		Attrezzo a2 = new Attrezzo("a2", 2);
		s1.addAttrezzo(a2);
		assertEquals(a2, s1.getAttrezzo("a2"));
	}
	
}
