package it.uniroma3.diadia.personaggi;

import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StregaTest {

	private static final String DESCRIZIONE_LABIRINTO 
	= "Stanze: LabCampusOne, Biblioteca, Atrio\n"
			+"Buie: \n"
			+"Magiche: \n"
			+"Bloccate: \n"
			+"Inizio: LabCampusOne\n"
			+"Vincente: Biblioteca\n"
			+"Attrezzi: chiave 1 LabCampusOne, spada 7 Atrio, lanterna 2 Atrio\n"
			+"Mago: \n"
			+"Strega: \n"
			+"Cane: \n"
			+"Uscite: LabCampusOne nord Atrio, LabCampusOne ovest Biblioteca\n";
	
	Partita p;
	Strega s;
	Labirinto l;
	
	@BeforeEach
	void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		l = Labirinto.newBuilder(new StringReader(DESCRIZIONE_LABIRINTO)).getLabirinto();
				/*.addStanzaIniziale("LabCampusOne")
				.addAttrezzo("chiave", 1)
				.addStanzaVincente("Biblioteca")
				.addStanza("Atrio")
				.addAttrezzo("spada", 7)
				.addAttrezzo("lanterna", 2)
				.addAdiacenza("Atrio", "LabCampusOne", "sud")
				.addAdiacenza("LabCampusOne", "Atrio", "nord")
				.addAdiacenza("LabCampusOne", "Biblioteca", "ovest")
				.addAdiacenza("Biblioteca", "LabCampusOne", "est")
				.getLabirinto();*/
		p = new Partita(l);
		s = new Strega("Strega","Sono una strega.");
	}
	
	@Test
	void testAgisciNonSalutato() {
		assertEquals(1, p.getStanzaCorrente().getAttrezzi().size());
		assertEquals("Sei stato trasferito nella stanza con meno attrezzi...", s.agisci(p));
		assertEquals(0, p.getStanzaCorrente().getAttrezzi().size());
		assertEquals("Biblioteca", p.getStanzaCorrente().getNome());
	}
	
	@Test
	void testAgisciSenzaAttrezzo() {
		s.saluta();
		assertEquals(1, p.getStanzaCorrente().getAttrezzi().size());
		assertEquals("Sei stato trasferito nella stanza con più attrezzi!", s.agisci(p));
		assertEquals(2, p.getStanzaCorrente().getAttrezzi().size());
		assertEquals("Atrio", p.getStanzaCorrente().getNome());
	}

	@Test
	void testRiceviRegalo() {
		assertEquals("Hehehehe! Ora l'attrezzo è mio! Hihihi!", s.riceviRegalo(new Attrezzo("penna", 1), p));
		assertFalse(p.getStanzaCorrente().hasAttrezzo("penna"));
		assertFalse(p.getGiocatore().getBorsa().hasAttrezzo("penna"));
	}
}
