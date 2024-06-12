package it.uniroma3.diadia.personaggi;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.StringReader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class MagoTest {
	
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
	Mago m;
	Labirinto l;
	
	@BeforeEach
	void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		l = Labirinto.newBuilder(new StringReader(DESCRIZIONE_LABIRINTO)).getLabirinto();
				/*.addStanzaIniziale("LabCampusOne")
				.addAttrezzo("osso", 1)
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("LabCampusOne", "Biblioteca", "ovest")
				.addAdiacenza("Biblioteca", "LabCampusOne", "est")
				.getLabirinto();*/
		p = new Partita(l);
		m = new Mago("Mago","Sono un mago.", new Attrezzo("bacchetta", 1));
	}
	
	@Test
	void testAgisciConAttrezzo() {
		assertFalse(p.getStanzaCorrente().hasAttrezzo("bacchetta"));
		int attrezziIniziali = p.getStanzaCorrente().getAttrezzi().size();
		assertEquals("Sei un vero simpaticone, " +
				"con una mia magica azione, troverai un nuovo oggetto " +
				"per il tuo borsone!", m.agisci(p));
		assertEquals(attrezziIniziali+1, p.getStanzaCorrente().getAttrezzi().size());
		assertTrue(p.getStanzaCorrente().hasAttrezzo("bacchetta"));
	}
	
	@Test
	void testAgisciSenzaAttrezzo() {
		m = new Mago("Mago","Sono un mago senza attrezzo.", null);
		int attrezziIniziali = p.getStanzaCorrente().getAttrezzi().size();
		assertEquals("Mi spiace, ma non ho piu' nulla...", m.agisci(p));
		assertEquals(attrezziIniziali, p.getStanzaCorrente().getAttrezzi().size());
	}

	@Test
	void testRiceviRegalo() {
		int pesoIniziale = 6;
		assertFalse(p.getStanzaCorrente().hasAttrezzo("spada"));
		assertEquals("Grazie mille per il regalo sincero, " +
				"ora te lo restituisco con un peso più leggero!",
				m.riceviRegalo(new Attrezzo("spada", pesoIniziale), p));
		assertTrue(p.getStanzaCorrente().hasAttrezzo("spada"));
		assertEquals(pesoIniziale/2, p.getStanzaCorrente().getAttrezzo("spada").getPeso());
	}

}
