package it.uniroma3.diadia;

import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

class FabbricaDiComandiFisarmonicaTest {
	
	private FabbricaDiComandiFisarmonica fabCom;
	
	@BeforeEach
	void setUp() {
		fabCom = new FabbricaDiComandiFisarmonica();
	}

	@Test
	void testCostruisciComandoVai() {
		assertEquals("Vai", fabCom.costruisciComando("vai").getNome());
	}
	
	@Test
	void testCostruisciComandoAiuto() {
		assertEquals("Aiuto", fabCom.costruisciComando("aiuto").getNome());
	}
	
	@Test
	void testCostruisciComandoFine() {
		assertEquals("Fine", fabCom.costruisciComando("fine").getNome());
	}
	
	@Test
	void testCostruisciComandoGuarda() {
		assertEquals("Guarda", fabCom.costruisciComando("guarda").getNome());
	}
	
	@Test
	void testCostruisciComandoNonValido() {
		assertEquals("Non valido", fabCom.costruisciComando("").getNome());
	}
	
	@Test
	void testCostruisciComandoPosa() {
		assertEquals("Posa", fabCom.costruisciComando("posa").getNome());
	}
	
	@Test
	void testCostruisciComandoPrendi() {
		assertEquals("Prendi", fabCom.costruisciComando("prendi").getNome());
	}
	
	/**
	 * Se inserisce un comando sconosciuto richiama il comando Guarda
	 */
	@Test
	void testCostruisciComandoSbagliato() {
		assertEquals("Guarda", fabCom.costruisciComando("alza").getNome());
	}

}
