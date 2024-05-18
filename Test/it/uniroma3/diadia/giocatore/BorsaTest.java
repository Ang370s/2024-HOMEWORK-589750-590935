package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import java.util.SortedSet;

import org.junit.jupiter.api.*;

import it.uniroma3.diadia.attrezzi.AttrezziPerNome;
import it.uniroma3.diadia.attrezzi.AttrezziPerPeso;
import it.uniroma3.diadia.attrezzi.AttrezziRaggruppati;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import it.uniroma3.diadia.giocatore.Borsa;

public class BorsaTest {
	
	private Borsa b;
	private Borsa bVuota;
	private Attrezzo a1;
	private Attrezzo a2;
	private Attrezzo a3;
	
	@BeforeEach
	void setUp(){
		b = new Borsa();
		bVuota = new Borsa();
		a1 = new Attrezzo("a1", 2);
		a2 = new Attrezzo("a2", 2);
		a3 = new Attrezzo("a3", 1);
		b.addAttrezzo(a1);
		b.addAttrezzo(a2);
		b.addAttrezzo(a3);
	}
	
	/**
	 * Aggiunta attrezzo in borsa vuota
	 */
	@Test
	void testAddAttrezzoBorsaVuota() {
		assertTrue(bVuota.addAttrezzo(a1));
	}
	
	/**
	 * Aggiunta attrezzo troppo pesante nella borsa 
	 * (azione rifiutata)
	 */
	@Test
	void testAddAttrezzoBorsaAttrezzoTroppoPesante() {
		a2 = new Attrezzo("a2", 20);
		assertFalse(b.addAttrezzo(a2));
	}
	
	/**
	 * Ricerca attrezzo non presente in borsa
	 */
	@Test
	void testGetAttrezzoBorsaAttrezzoSbagliato() {
		assertNull(b.getAttrezzo("a4"));
	}
	
	/**
	 * Ricerca attrezzo su borsa vuota
	 */
	@Test
	void testGetAttrezzoBorsaVuota() {
		assertNull(bVuota.getAttrezzo("qualsiasi"));
	}
	
	/**
	 * Ricerca attrezzo presente in borsa
	 */
	@Test
	void testGetAttrezzoBorsaConAttrezziGiusto() {
		assertEquals(a1, b.getAttrezzo("a1"));
	}
	
	/**
	 * Visualizzazione peso di una borsa vuota
	 */
	@Test
	void testGetPesoBorsaVuota() {
		assertEquals(0, bVuota.getPeso());
	}
	
	/**
	 * Visualizzazione peso di una borsa con attrezzi
	 * Verifico anche se è giusta la somma
	 */
	@Test
	void testGetPesoBorsaConAttrezzi() {
		assertEquals(5, b.getPeso());
	}
	
	/**
	 * Verifico se la borsa è vuota su una borsa vuota
	 */
	@Test
	void testIsEmptyBorsaVuota() {
		assertTrue(bVuota.isEmpty());
	}
	
	/**
	 * Verifico se la borsa è vuota su una borsa non vuota
	 */
	@Test
	void testIsEmptyBorsaConAttrezzi() {
		assertFalse(b.isEmpty());
	}
	
	/**
	 * Verifico se c'è l'attrezzo nella borsa vuota
	 */
	@Test
	void testHasAttrezzoBorsaVuota() {
		assertFalse(bVuota.hasAttrezzo("attrezzo"));
	}
	
	/**
	 * Verifico se c'è l'attrezzo nella borsa senza quell'attrezzo
	 */
	@Test
	void testHasAttrezzoBorsaSenzaQuellAttrezzo() {
		assertFalse(b.hasAttrezzo("a4"));
	}
	
	/**
	 * Verifico se c'è l'attrezzo nella borsa con proprio quell'attrezzo
	 */
	@Test
	void testHasAttrezzoBorsaConQuellAttrezzo() {
		assertTrue(b.hasAttrezzo("a2"));
	}
	
	/**
	 * Rimozione attrezzo posizione iniziale dell'array
	 */
	@Test
	void testRemoveAttrezzoInizioArray() {
		assertEquals(a1, b.removeAttrezzo("a1"));
		assertNull(b.getAttrezzo("a1"));
	}
	
	/**
	 * Rimozione attrezzo in ultima posizione
	 */
	@Test
	void testRemoveAttrezzoFineArray() {
		assertEquals(a3, b.removeAttrezzo("a3"));
		assertNull(b.getAttrezzo("a3"));
	}
	
	/**
	 * Rimozione attrezzo sulla borsa vuota
	 */
	@Test
	void testRemoveAttrezzoBorsaVuota() {
		assertNull(bVuota.removeAttrezzo("attrezzo"));
	}
	
	@Test
    void testToStringBorsaVuota() {
        assertEquals("Borsa vuota", bVuota.toString());
    }

    @Test
    void testToStringBorsaConAttrezzi() {
        assertEquals("Contenuto borsa (5kg/10kg): "
        		+ "\n	Ordinato per peso: [ a3 (1kg), a1 (2kg), a2 (2kg) ]"
        		+ "\n	Ordinato per nome: { a1 (2kg), a2 (2kg), a3 (1kg) }"
        		+ "\n	Raggruppato per peso: (1, { a3 (1kg) } ); (2, { a2 (2kg), a1 (2kg) } ); ", b.toString());
    }
    
    /**
     * Test di ordinamento attrezzi in base al peso
     */
    @Test
    public void testGetContenutoOrdinatoPerPeso() {
    	AttrezziPerPeso attrezziOrdinati = b.getContenutoOrdinatoPerPeso();
    	assertEquals("[ a3 (1kg), a1 (2kg), a2 (2kg) ]", attrezziOrdinati.toString());
    }
    
    /**
     * Test di ordinamento attrezzi in base al nome
     */
    @Test
    public void testGetContenutoOrdinatoPerNome() {
    	AttrezziPerNome attrezziOrdinati = b.getContenutoOrdinatoPerNome();
    	assertEquals("{ a1 (2kg), a2 (2kg), a3 (1kg) }", attrezziOrdinati.toString());
    }
    
    /**
     * Test di raggruppamento attrezzi
     */
    @Test
    public void testGetContenutoRaggruppatoPerPeso() {
    	AttrezziRaggruppati attrezziRaggruppati = b.getContenutoRaggruppatoPerPeso();
    	assertEquals("(1, { a3 (1kg) } ); (2, { a1 (2kg), a2 (2kg) } ); ", attrezziRaggruppati.toString());
    }
    
    /**
     * Test di ordinamento per peso e, a parità di peso, per nome
     */
    @Test
    public void testGetSortedSetOrdinatoPerPeso() {
    	SortedSet<Attrezzo> attrezziOrdinatiPerPeso = b.getSortedSetOrdinatoPerPeso();
    	
    	assertEquals("[a3 (1kg), a1 (2kg), a2 (2kg)]", attrezziOrdinatiPerPeso.toString());
    }
    
    // TESTATE TUTTE LE FUNZIONI
	
}
