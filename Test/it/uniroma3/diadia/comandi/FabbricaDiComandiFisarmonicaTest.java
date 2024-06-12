package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.*;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;

class FabbricaDiComandiFisarmonicaTest {
	
	private FabbricaDiComandiFisarmonica fabCom;
	private IO io;
	
	@BeforeEach
	void setUp() {
		fabCom = new FabbricaDiComandiFisarmonica();
		io = new IOConsole(new Scanner(System.in));
	}

	@Test
	void testCostruisciComandoVai() {
		assertEquals("Vai", fabCom.costruisciComando("vai", io).getNome());
	}
	
	@Test
	void testCostruisciComandoAiuto() {
		assertEquals("Aiuto", fabCom.costruisciComando("aiuto", io).getNome());
	}
	
	@Test
	void testCostruisciComandoFine() {
		assertEquals("Fine", fabCom.costruisciComando("fine", io).getNome());
	}
	
	@Test
	void testCostruisciComandoGuarda() {
		assertEquals("Guarda", fabCom.costruisciComando("guarda", io).getNome());
	}
	
	@Test
	void testCostruisciComandoNonValido() {
		assertEquals("Non valido", fabCom.costruisciComando("", io).getNome());
	}
	
	@Test
	void testCostruisciComandoPosa() {
		assertEquals("Posa", fabCom.costruisciComando("posa", io).getNome());
	}
	
	@Test
	void testCostruisciComandoPrendi() {
		assertEquals("Prendi", fabCom.costruisciComando("prendi", io).getNome());
	}
	
	/**
	 * Se inserisce un comando sconosciuto richiama il comando Guarda
	 */
	@Test
	void testCostruisciComandoSbagliato() {
		assertEquals("Guarda", fabCom.costruisciComando("alza", io).getNome());
	}

}
