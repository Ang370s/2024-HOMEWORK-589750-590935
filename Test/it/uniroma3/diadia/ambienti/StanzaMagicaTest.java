package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import it.uniroma3.diadia.ambienti.StanzaMagica;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaTest {
	
	private StanzaMagica s1;
	private Attrezzo a1;
	private Attrezzo a2;
	
	@BeforeEach
	void setUp() {
		s1 = new StanzaMagica("StanzaMagica", 1);
		a1 = new Attrezzo("Spada magica", 1);
		a2 = new Attrezzo("Bacchetta magica", 2);
	}
	
	/**
	 * Test aggiungendo un attrezzo senza dover cambiare il nome
	 */
	@Test
	public void testAddAttrezzo() {
		s1.addAttrezzo(a1);
		assertEquals(a1, s1.getAttrezzo("Spada magica"));
	}

	/**
	 * Test aggiungendo un attrezzo nella stanza e verificando che il nome sia stato correttamente invertito
	 */
	@Test
	void testAddAttrezzoSuperandoSoglia() {
		s1.addAttrezzo(a1);
		s1.addAttrezzo(a2);
		assertEquals("acigam attehccaB", s1.getAttrezzo("acigam attehccaB").getNome());
		assertNull(s1.getAttrezzo("Bacchetta magica"));
	}
}
