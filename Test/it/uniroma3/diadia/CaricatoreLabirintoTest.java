package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.ambienti.StanzaMagica;

import java.io.FileNotFoundException;
import java.io.StringReader;

class CaricatoreLabirintoTest {
	
	private static final String DESCRIZIONE_LABIRINTO = 
			"Stanze: biblioteca, N10, N11\n"
			+ "Buie: N10 pinza\n"
			+ "Magiche: biblioteca 4\n"
			+ "Bloccate: N11 nord chiave\n"
			+ "Inizio: N10\n"
			+ "Vincente: N11\n"
			+ "Attrezzi: martello 1 biblioteca, chiave 1 N11, pinza 2 N10\n"
			+ "Mago: Merlino Potente bacchetta 1 biblioteca\n"
			+ "Strega: Morgana Cattiva N10\n"
			+ "Cane: Pippo Aggressivo chiave 1 N11\n"
			+ "Uscite: biblioteca nord N10, biblioteca sud N11\n";
	
	CaricatoreLabirinto caricatore;
	
	@Test
	void testCarica() throws FormatoFileNonValidoException, FileNotFoundException {
		caricatore = new CaricatoreLabirinto(new StringReader(DESCRIZIONE_LABIRINTO));
		caricatore.carica();
        assertEquals("N10", caricatore.getStanzaIniziale().getNome());
		assertEquals("N11", caricatore.getStanzaVincente().getNome());
		assertEquals("biblioteca", caricatore.getStanze().get("biblioteca").getNome());
		assertTrue(caricatore.getStanze().get("biblioteca").hasAttrezzo("martello"));
		assertTrue(caricatore.getStanze().get("N10").hasAttrezzo("pinza"));
		assertEquals("N10", caricatore.getStanze().get("biblioteca").getStanzaAdiacente("nord").getNome());
		assertEquals("N11", caricatore.getStanze().get("biblioteca").getStanzaAdiacente("sud").getNome());
		assertTrue(((StanzaBuia) caricatore.getStanze().get("N10")).isBuia());
		assertTrue(((StanzaMagica) caricatore.getStanze().get("biblioteca")).isMagica());
		assertTrue(((StanzaBloccata) caricatore.getStanze().get("N11")).isBloccata());
		assertEquals("Merlino", caricatore.getStanze().get("biblioteca").getPersonaggio().getNome());
		assertEquals("Morgana", caricatore.getStanze().get("N10").getPersonaggio().getNome());
		assertEquals("Pippo", caricatore.getStanze().get("N11").getPersonaggio().getNome());
	}

}
