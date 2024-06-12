package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.Scanner;

import org.junit.jupiter.api.*;

class ComandoPrendiTest {
	
	private static final String DESCRIZIONE_LABIRINTO 
	= "Stanze: iniziale\n"
	+ "Buie: \n"
	+ "Magiche: \n"
	+ "Bloccate: \n"
	+ "Inizio: iniziale\n"
	+ "Vincente: iniziale\n"
	+ "Attrezzi: osso 1 iniziale\n"
	+ "Mago: \n"
	+ "Strega: \n"
	+ "Cane: \n"
	+ "Uscite: \n"; 
	
	private AbstractComando prendi;
	private Partita partita;
	private IO io;
	
	@BeforeEach
	void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		this.io = new IOConsole(new Scanner(System.in));
		this.prendi = new ComandoPrendi();
		prendi.setParametro("osso");
		Labirinto labirinto = Labirinto.newBuilder(new StringReader(DESCRIZIONE_LABIRINTO)).getLabirinto();
				/*.addStanzaIniziale("iniziale")
				.addAttrezzo("osso", 1)
				.getLabirinto();*/
		this.partita = new Partita(labirinto);
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
