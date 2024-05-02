package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import it.uniroma3.diadia.ambienti.Stanza;

public class PartitaTest {

	private Partita p1;
	
	@BeforeEach
	void setUp() {
		p1 = new Partita();
	}
	
	@Test
	public void testGetStanzaCorrenteGiusta() {
		assertEquals("Atrio", p1.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testGetStanzaCorrenteGiustaDopoEssersiMosso() {
		assertEquals("Atrio", p1.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testGetStanzaCorrenteErrata() {
		assertNotEquals("Biblioteca", p1.getStanzaCorrente().getNome());
	}

	
	@Test
	public void testFinePartita() {
	    Partita partita = new Partita();
	    partita.getGiocatore().setCfu(0);
	    assertTrue(partita.isFinita());
	}

	
}
