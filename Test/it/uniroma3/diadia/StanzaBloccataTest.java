package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import it.uniroma3.diadia.ambienti.StanzaBloccata;

import it.uniroma3.diadia.ambienti.Stanza;

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

	@Test
	void testGetStanzaAdiacenteBloccata() {
		assertEquals(stanzaBloccata, stanzaBloccata.getStanzaAdiacente("sud"));
	}
	
	@Test
	void testGetStanzaAdiacenteSbloccata() {
		Attrezzo attrezzoChiave = new Attrezzo("chiave", 2);
		stanzaBloccata.addAttrezzo(attrezzoChiave);

		assertEquals(stanzaAdiacente, stanzaBloccata.getStanzaAdiacente("sud"));
	}
	
	@Test
	void testGetStanzaAdiacenteDirezioneInesistente() {
		assertNull(stanzaBloccata.getStanzaAdiacente("nord"));
	}
	
	@Test
	void testGetDescrizione() {
		assertEquals("Stanza Bloccata\nUscite:  sud\nAttrezzi nella stanza: \nDirezione bloccata se non hai l'attrezzo \"chiave\": sud", stanzaBloccata.getDescrizione());
	}

}
