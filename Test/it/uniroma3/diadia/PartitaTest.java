package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

public class PartitaTest {

	private Partita p1;
	private Labirinto l;
	
	@BeforeEach
	public void setUp() {
		this.l = new LabirintoBuilder()
				.addStanzaIniziale("iniziale")
				.addStanzaVincente("vincente")
				.getLabirinto();
		this.p1 = new Partita(this.l);
	}
	
	@Test
	public void testGetStanzaCorrenteGiusta() {
		assertEquals("iniziale", p1.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testGetStanzaCorrenteGiustaDopoEssersiMosso() {
		assertEquals("iniziale", p1.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testGetStanzaCorrenteErrata() {
		assertNotEquals("vincente", p1.getStanzaCorrente().getNome());
	}

	
	@Test
	public void testFinePartita() {
	    Partita partita = new Partita(this.l);
	    partita.getGiocatore().setCfu(0);
	    assertTrue(partita.isFinita());
	}

	
}
