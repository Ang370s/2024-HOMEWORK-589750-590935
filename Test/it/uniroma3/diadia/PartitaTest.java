package it.uniroma3.diadia;

import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.*;

import it.uniroma3.diadia.ambienti.Labirinto;

public class PartitaTest {
	
	private static final String DESCRIZIONE_LABIRINTO 
	= "Stanze: iniziale, vincente\n"
			+"Buie: \n"
			+"Magiche: \n"
			+"Bloccate: \n"
			+"Inizio: iniziale\n"
			+"Vincente: vincente\n"
			+"Attrezzi: \n"
			+"Mago: \n"
			+"Strega: \n"
			+"Cane: \n"
			+"Uscite: \n";

	private Partita p1;
	private Labirinto l;
	
	@BeforeEach
	public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		this.l = Labirinto.newBuilder(new StringReader(DESCRIZIONE_LABIRINTO)).getLabirinto();
				/*.addStanzaIniziale("iniziale")
				.addStanzaVincente("vincente")
				.getLabirinto();*/
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
