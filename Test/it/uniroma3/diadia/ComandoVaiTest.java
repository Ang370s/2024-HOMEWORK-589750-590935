package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

class ComandoVaiTest {
	
	Partita partita;
	ComandoVai vai;

	@BeforeEach
	void setUp() {
		vai = new ComandoVai("sud");
		partita = new Partita();
	}
	
	@Test
	void testEseguiGiusta() {
		vai.esegui(this.partita);
		assertEquals("Aula N10", this.partita.getStanzaCorrente().getNome());
	}
	
	@Test
	void testEseguiDirezioneInesistente() {
		// vado nell'aula N10 perchè non ha la stanza sud
		this.partita.setStanzaCorrente(this.partita.getStanzaCorrente().getStanzaAdiacente("sud"));
		
		vai.esegui(this.partita);
		assertEquals("Aula N10", this.partita.getStanzaCorrente().getNome());
	}

}
