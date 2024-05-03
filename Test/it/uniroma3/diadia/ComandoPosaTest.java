package it.uniroma3.diadia;

import it.uniroma3.diadia.comandi.ComandoPosa;

import it.uniroma3.diadia.IO;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPosaTest {
	
	private ComandoPosa posa;
	private Partita partita;
	private IO io;
	
	@BeforeEach
	void setUp() {
		this.io = new IOConsole();
		this.partita = new Partita();
		this.posa = new ComandoPosa("penna");
	}

	/**
	 * Test aggiungendo un oggetto in borsa e poi posandolo in stanza 
	 */
	@Test
	void testEseguiGiusto() {
		this.partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("penna", 1));
		posa.esegui(this.io, this.partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("penna"));
	}
	
	/**
	 * Test cercando di posare un oggetto, che non è presente in borsa, in stanza
	 */
	@Test
	void testEseguiNonTrovato() {
		posa.esegui(this.io, this.partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("penna"));
	}

}
