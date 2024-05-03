package it.uniroma3.diadia;

import it.uniroma3.diadia.comandi.ComandoPrendi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

class ComandoPrendiTest {
	
	private ComandoPrendi prendi;
	private Partita partita;
	private IO io;
	
	@BeforeEach
	void setUp() {
		this.io = new IOConsole();
		this.prendi = new ComandoPrendi("osso");
		this.partita = new Partita();
	}

	/**
	 * Test per vedere se aggiunge correttamente un oggetto in borsa
	 */
	@Test
	void testEseguiGiusta() {
		prendi.esegui(this.io, this.partita);
		assertEquals("osso", this.partita.getGiocatore().getBorsa().getAttrezzo("osso").getNome());
	}
	
	
	/**
	 * Test che prova ad aggiungere un oggetto in borsa non presente in stanza
	 */
	@Test
	void testEseguiNonTrovato() {
		prendi.setParametro("lanterna");
		prendi.esegui(this.io, this.partita);
		assertNull(this.partita.getGiocatore().getBorsa().getAttrezzo("lanterna"));
	}

}
