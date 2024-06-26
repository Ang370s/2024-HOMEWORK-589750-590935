package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class GiocatoreTest {
	
	private Giocatore g;
	
	@BeforeEach
	void setUp(){
		this.g = new Giocatore();
	}

	@Test
	void testGetCfu() {
		assertEquals(20, g.getCfu());
	}
	
	@Test
	void testSetCfu() {
		g.setCfu(10);
		assertEquals(10, g.getCfu());
	}

}
