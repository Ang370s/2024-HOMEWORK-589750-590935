package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import it.uniroma3.diadia.ambienti.StanzaBuia;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {
	private StanzaBuia stanzaBuia;
	
	@BeforeEach
	void setUp() {
		stanzaBuia = new StanzaBuia("Stanza Buia", "lanterna");
	}

	@Test
	void testGetDescrizioneStanzaSenzaLanterna() {
		assertEquals("Qui c'è buio pesto!", stanzaBuia.getDescrizione());
	}

	@Test
	void testGetDescrizioneStanzaConLanterna() {
		Attrezzo lanterna = new Attrezzo("lanterna", 1);
		stanzaBuia.addAttrezzo(lanterna);
		assertEquals("Stanza Buia\nUscite: \nAttrezzi nella stanza: lanterna (1kg) ", stanzaBuia.getDescrizione());
	}

}
