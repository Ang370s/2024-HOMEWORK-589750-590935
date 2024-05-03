package it.uniroma3.diadia;

import it.uniroma3.diadia.comandi.ComandoVai;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

class ComandoVaiTest {
	
	private Partita partita;
	private ComandoVai vai;
	private IO io;

	@BeforeEach
	void setUp() {
		this.io = new IOConsole();
		vai = new ComandoVai("sud");
		partita = new Partita();
	}
	
	@Test
	void testEseguiGiusta() {
		vai.esegui(this.io, this.partita);
		assertEquals("Aula N10", this.partita.getStanzaCorrente().getNome());
	}
	
	@Test
	void testEseguiDirezioneInesistente() {
		// vado nell'aula N10 perchè non ha la stanza sud
		this.partita.setStanzaCorrente(this.partita.getStanzaCorrente().getStanzaAdiacente("sud"));
		
		vai.esegui(this.io, this.partita);
		assertEquals("Aula N10", this.partita.getStanzaCorrente().getNome());
	}

}
