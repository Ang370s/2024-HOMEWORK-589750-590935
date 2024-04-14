package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import it.uniroma3.diadia.ambienti.Labirinto;

public class LabirintoTest {
	
	private Labirinto l;
	
	@BeforeEach
	void setUp() {
		l = new Labirinto();
	}
	
	@Test
	public void testGetStanzaVincente() {
		assertEquals("Biblioteca", l.getStanzaVincente().getNome());
	}
	
	@Test
	public void testGetStanzaIniziale() {
		assertEquals("Atrio", l.getStanzaIniziale().getNome());
	}
}
