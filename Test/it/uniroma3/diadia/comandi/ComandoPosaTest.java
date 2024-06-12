package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;

import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPosaTest {
	
	private static final String DESCRIZIONE_LABIRINTO 
	= "Stanze: iniziale\n"
	+ "Buie: \n"
	+ "Magiche: \n"
	+ "Bloccate: \n"
	+ "Inizio: iniziale\n"
	+ "Vincente: iniziale\n"
	+ "Attrezzi: \n"
	+ "Mago: \n"
	+ "Strega: \n"
	+ "Cane: \n"
	+ "Uscite: \n";
	
	private AbstractComando posa;
	private Partita partita;
	private IO io;
	
	@BeforeEach
	void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		this.io = new IOConsole(new Scanner(System.in));
		Labirinto labirinto = Labirinto.newBuilder(new StringReader(DESCRIZIONE_LABIRINTO)).getLabirinto();
				/*.addStanzaIniziale("iniziale")
				.getLabirinto();*/
		this.partita = new Partita(labirinto);
		this.posa = new ComandoPosa();
		posa.setParametro("penna");
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
