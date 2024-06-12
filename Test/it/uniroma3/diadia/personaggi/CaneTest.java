package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class CaneTest {
	
	private static final String DESCRIZIONE_LABIRINTO 
	= "Stanze: N10, N11\n"
			+"Buie: \n"
			+"Magiche: \n"
			+"Bloccate: \n"
			+"Inizio: N10\n"
			+"Vincente: N11\n"
			+"Attrezzi: \n"
			+"Mago: \n"
			+"Strega: \n"
			+"Cane: \n"
			+"Uscite: \n";

	Partita p;
	Cane c;
	Labirinto l;
	
	@BeforeEach
	void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		l = Labirinto.newBuilder(new StringReader(DESCRIZIONE_LABIRINTO)).getLabirinto();
				/*.addStanzaIniziale("LabCampusOne")
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("LabCampusOne", "Biblioteca", "ovest")
				.addAdiacenza("Biblioteca", "LabCampusOne", "est")
				.getLabirinto();*/
		p = new Partita(l);
		c = new Cane("Cane","Wof wof!", new Attrezzo("chiave", 1));
	}
	
	@Test
	void testAgisci() {
		int cfuIniziali = p.getGiocatore().getCfu();
		assertEquals("Sei stato morso!", c.agisci(p));
		assertEquals(cfuIniziali-1, p.getGiocatore().getCfu());
	}

	@Test
	void testRiceviRegaloAttrezzoBuono() {
		int cfuIniziali = p.getGiocatore().getCfu();
		assertFalse(p.getStanzaCorrente().hasAttrezzo("chiave"));
		assertEquals("Che fortuna! Era proprio il suo cibo preferito e ha lasciato cadere un attrezzo!",
				c.riceviRegalo(new Attrezzo("osso", 1), p));
		assertTrue(p.getStanzaCorrente().hasAttrezzo("chiave"));
		assertEquals(cfuIniziali, p.getGiocatore().getCfu());
	}
	
	@Test
	void testRiceviRegaloAttrezzoCattivo() {
		int cfuIniziali = p.getGiocatore().getCfu();
		assertFalse(p.getStanzaCorrente().hasAttrezzo("chiave"));
		assertEquals("Il regalo non è piaciuto...Sei stato morso!",
				c.riceviRegalo(new Attrezzo("spada", 1), p));
		assertFalse(p.getStanzaCorrente().hasAttrezzo("chiave"));
		assertEquals(cfuIniziali-1, p.getGiocatore().getCfu());
	}
}
