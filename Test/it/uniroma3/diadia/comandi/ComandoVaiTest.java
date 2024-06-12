package it.uniroma3.diadia.comandi;
import java.io.*;
import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.*;

class ComandoVaiTest {
	private static final String DESCRIZIONE_LABIRINTO 
	= "Stanze: iniziale, vincente\n"
	+ "Buie: \n"
	+ "Magiche: \n"
	+ "Bloccate: \n"
	+ "Inizio: iniziale\n"
	+ "Vincente: vincente\n"
	+ "Attrezzi: \n"
	+ "Mago: \n"
	+ "Strega: \n"
	+ "Cane: \n"
	+ "Uscite: iniziale sud vincente\n"; 
	
	private Partita partita;
	private AbstractComando vai;
	private IO io;

	@BeforeEach
	void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		this.io = new IOConsole(new Scanner(System.in));
		vai = new ComandoVai();
		vai.setParametro("sud");
		Labirinto labirinto = Labirinto.newBuilder(new StringReader(DESCRIZIONE_LABIRINTO)).getLabirinto();
				/*.addStanzaIniziale("iniziale")
				.addStanza("vincente")
				.addAdiacenza("iniziale", "vincente", "sud")
				.addAdiacenza("vincente", "iniziale", "nord")
				.getLabirinto();*/
				
		this.partita = new Partita(labirinto);
	}
	
	@Test
	void testEseguiGiusta() {
		vai.esegui(this.io, this.partita);
		assertEquals("vincente", this.partita.getStanzaCorrente().getNome());
	}
	
	@Test
	void testEseguiDirezioneInesistente() {
		// vado nell'aula vincente perchè non ha la stanza sud
		this.partita.setStanzaCorrente(this.partita.getStanzaCorrente().getStanzaAdiacente("sud"));
		
		vai.esegui(this.io, this.partita);
		assertEquals("vincente", this.partita.getStanzaCorrente().getNome());
	}

}
