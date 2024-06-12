package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {
	private StanzaBloccata stanzaBloccata;
	private Stanza stanzaAdiacente;
	
	@BeforeEach
	void setUp() {
		stanzaBloccata = new StanzaBloccata("Stanza Bloccata", "sud", "chiave");
		stanzaAdiacente = new Stanza("Stanza Normale");
		stanzaBloccata.impostaStanzaAdiacente("sud", stanzaAdiacente);
	}

	/**
	 * Test per verificare che non avendo l'attrezzo chiave non posso andare nella direzione bloccata
	 */
	@Test
	void testGetStanzaAdiacenteBloccata() {
		assertEquals(stanzaBloccata, stanzaBloccata.getStanzaAdiacente("sud"));
	}
	
	
	/**
	 * Test per verificare che posso andare nella direzione bloccata perche presente l'attrezzo chiave
	 */
	@Test
	void testGetStanzaAdiacenteSbloccata() {
		Attrezzo attrezzoChiave = new Attrezzo("chiave", 2);
		stanzaBloccata.addAttrezzo(attrezzoChiave);

		assertEquals(stanzaAdiacente, stanzaBloccata.getStanzaAdiacente("sud"));
	}
	
	
	/**
	 * Test di direzione inesistente
	 */
	@Test
	void testGetStanzaAdiacenteDirezioneInesistente() {
		assertNull(stanzaBloccata.getStanzaAdiacente("nord"));
	}
	
	/**
	 * Test descrizione stanza con stanze bloccate
	 */
	@Test
	void testGetDescrizione() {
		assertEquals("Stanza Bloccata\nUscite:  SUD\nAttrezzi nella stanza: \n"
				+ "Direzione bloccata se non hai l'attrezzo \"chiave\": SUD", stanzaBloccata.getDescrizione());
	}

}
