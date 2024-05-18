package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
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
		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("iniziale")
				.addStanza("vincente")
				.addAdiacenza("iniziale", "vincente", "sud")
				.addAdiacenza("vincente", "iniziale", "nord")
				.getLabirinto();
				
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
