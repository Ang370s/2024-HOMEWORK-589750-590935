package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPosaTest {
	
	ComandoPosa posa;
	Partita partita;
	
	@BeforeEach
	void setUp() {
		this.partita = new Partita();
		this.posa = new ComandoPosa("penna");
	}

	/**
	 * Test aggiungendo un oggetto in borsa e poi posandolo in stanza 
	 */
	@Test
	void testEseguiGiusto() {
		this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("penna", 1));
		posa.esegui(this.partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("penna"));
	}
	
	/**
	 * Test cercando di posare un oggetto, che non è presente in borsa, in stanza
	 */
	@Test
	void testEseguiNonTrovato() {
		posa.esegui(this.partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("penna"));
	}

}
