package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilderTest {
	
	private static final String DESCRIZIONE_GENERICO = 
			"Stanze: Atrio, Uscita\n"
			+ "Buie: \n"
			+ "Magiche: \n"
			+ "Bloccate: \n"
			+ "Inizio: Atrio\n"
			+ "Vincente: Uscita\n"
			+ "Attrezzi: \n"
			+ "Mago: \n"
			+ "Strega: \n"
			+ "Cane: \n"
			+ "Uscite: \n"; 
	
	private static final String DESCRIZIONE_MONOLOCALE = 
			"Stanze: Atrio\n"
			+ "Buie: \n"
			+ "Magiche: \n"
			+ "Bloccate: \n"
			+ "Inizio: Atrio\n"
			+ "Vincente: Atrio\n"
			+ "Attrezzi: spada 1 Atrio, spadina 3 Atrio\n"
			+ "Mago: \n"
			+ "Strega: \n"
			+ "Cane: \n"
			+ "Uscite: \n"; 
	
	private static final String DESCRIZIONE_BILOCALE = 
			"Stanze: Atrio, Uscita\n"
			+ "Buie: \n"
			+ "Magiche: \n"
			+ "Bloccate: \n"
			+ "Inizio: Atrio\n"
			+ "Vincente: Uscita\n"
			+ "Attrezzi: \n"
			+ "Mago: \n"
			+ "Strega: \n"
			+ "Cane: \n"
			+ "Uscite: Atrio nord Uscita\n"; 
	
	private static final String DESCRIZIONE_TRILOCALE = 
			"Stanze: Atrio, Uscita, biblioteca\n"
			+ "Buie: \n"
			+ "Magiche: \n"
			+ "Bloccate: \n"
			+ "Inizio: Atrio\n"
			+ "Vincente: Uscita\n"
			+ "Attrezzi: sedia 1 Atrio, libro 5 biblioteca\n"
			+ "Mago: \n"
			+ "Strega: \n"
			+ "Cane: \n"
			+ "Uscite: Atrio sud biblioteca, biblioteca est Uscita\n"; 
	
	private String nomeStanzaIniziale = "Atrio";
	private String nomeStanzaVincente = "Uscita";

	@Test
	public void testMonolocale() throws FileNotFoundException, FormatoFileNonValidoException {
		LabirintoBuilder monolocale = Labirinto.newBuilder(new StringReader(DESCRIZIONE_MONOLOCALE));
				/*.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaIniziale)
				.getLabirinto();*/
	assertEquals(nomeStanzaIniziale,monolocale.getStanzaIniziale().getNome());
	assertEquals(nomeStanzaIniziale,monolocale.getStanzaVincente().getNome());
	}
	
	@Test
	public void testMonolocaleConAttrezzo() throws FileNotFoundException, FormatoFileNonValidoException {
		LabirintoBuilder monolocale = Labirinto.newBuilder(new StringReader(DESCRIZIONE_MONOLOCALE));
				/*.addStanzaIniziale(nomeStanzaIniziale).addAttrezzo("spada",1)
				.addStanzaVincente(nomeStanzaIniziale).addAttrezzo("spadina", 3)
				.getLabirinto();*/
		assertEquals(nomeStanzaIniziale,monolocale.getStanzaIniziale().getNome());
		assertEquals(nomeStanzaIniziale,monolocale.getStanzaVincente().getNome());
		assertEquals("spada",monolocale.getStanzaIniziale().getAttrezzo("spada").getNome());
		assertEquals("spadina",monolocale.getStanzaVincente().getAttrezzo("spadina").getNome());
	}
	
	@Test
	public void testMonolocaleConAttrezzoSingoloDuplicato() throws FileNotFoundException, FormatoFileNonValidoException {
		LabirintoBuilder monolocale = Labirinto.newBuilder(new StringReader(DESCRIZIONE_MONOLOCALE));
				/*.addStanzaIniziale(nomeStanzaIniziale)
				.addAttrezzo("spada",1)
				.addAttrezzo("spada",1)
				.getLabirinto();*/
		monolocale.getStanze().get(nomeStanzaIniziale).removeAttrezzo(new Attrezzo("spadina", 3));
		monolocale.getStanze().get(nomeStanzaIniziale).addAttrezzo(new Attrezzo("spada", 1));
		int size = monolocale.getStanzaIniziale().getAttrezzi().size();
		assertTrue(size==1);
		assertEquals(new ArrayList<>(Arrays.asList(new Attrezzo("spada",1))), new ArrayList<>(monolocale.getStanzaIniziale().getAttrezzi()));
	}
	
	@Test
	public void testBilocale() throws FileNotFoundException, FormatoFileNonValidoException {
		LabirintoBuilder bilocale = Labirinto.newBuilder(new StringReader(DESCRIZIONE_BILOCALE));
				/*.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaVincente)
				.addAdiacenza(nomeStanzaIniziale, nomeStanzaVincente, "nord")
				.addAdiacenza(nomeStanzaVincente, nomeStanzaIniziale, "sud")
				.getLabirinto();*/
		assertEquals(bilocale.getStanzaVincente(),bilocale.getStanzaIniziale().getStanzaAdiacente("nord"));
		assertEquals(new ArrayList<>(Collections.singletonList(Direzione.NORD)), new ArrayList<>(bilocale.getStanzaIniziale().getDirezioni()));
		assertEquals(new ArrayList<>(Collections.singletonList(Direzione.SUD)), new ArrayList<>(bilocale.getStanzaVincente().getDirezioni()));
	}
	
	@Test
	public void testTrilocale() throws FileNotFoundException, FormatoFileNonValidoException {
		LabirintoBuilder trilocale = Labirinto.newBuilder(new StringReader(DESCRIZIONE_TRILOCALE));
				/*.addStanzaIniziale(nomeStanzaIniziale).addAttrezzo("sedia", 1)
				.addStanza("biblioteca")
				.addAdiacenza(nomeStanzaIniziale, "biblioteca", "sud")
				.addAdiacenza("biblioteca", nomeStanzaIniziale, "nord")
				.addAttrezzo("libro antico", 5)
				.addStanzaVincente(nomeStanzaVincente)
				.addAdiacenza("biblioteca", nomeStanzaVincente, "est")
				.addAdiacenza(nomeStanzaVincente,"biblioteca" , "ovest")
				.getLabirinto();*/
		assertEquals(nomeStanzaIniziale, trilocale.getStanzaIniziale().getNome());
		assertEquals(nomeStanzaVincente, trilocale.getStanzaVincente().getNome());
		assertEquals("biblioteca",trilocale.getStanzaIniziale().getStanzaAdiacente("sud").getNome());
	}
	
	@Test
	public void testTrilocaleConStanzaDuplicata()  throws FileNotFoundException, FormatoFileNonValidoException {
		LabirintoBuilder generico = Labirinto.newBuilder(new StringReader(DESCRIZIONE_GENERICO));
		generico.addStanza("stanza generica")
				.addStanza("stanza generica")
				.addAdiacenza(nomeStanzaIniziale, "stanza generica", "nord")
				.getLabirinto();
	}
	
	@Test
	public void testPiuDiQuattroAdiacenti()  throws FileNotFoundException, FormatoFileNonValidoException {
		LabirintoBuilder maze = Labirinto.newBuilder(new StringReader(DESCRIZIONE_GENERICO));
		maze.addStanza("stanza 1")
				.addStanza("stanza 2")
				.addStanza("stanza 3")
				.addStanza("stanza 4")
				.addStanza("stanza 5")
				.addAdiacenza(nomeStanzaIniziale, "stanza 1", "nord")
				.addAdiacenza(nomeStanzaIniziale, "stanza 2", "ovest")
				.addAdiacenza(nomeStanzaIniziale, "stanza 3", "sud")
				.addAdiacenza(nomeStanzaIniziale, "stanza 4", "est")
				.addAdiacenza(nomeStanzaIniziale, "stanza 5", "nord-est") // non dovrebbe essere aggiunta
				.getLabirinto();
				Stanza test = new Stanza("stanza 5");
		assertNull(maze.getStanzaIniziale().getStanzaAdiacente("nord-est"));
		assertTrue(maze.getStanzaIniziale().getMapStanzeAdiacenti().size()<=4);
		assertTrue(!maze.getStanzaIniziale().getMapStanzeAdiacenti().containsValue(test));
		Map<Direzione,Stanza> mappa = new HashMap<>();
		mappa.put(Direzione.NORD, new Stanza("stanza 1"));
		mappa.put(Direzione.OVEST, new Stanza("stanza 2"));
		mappa.put(Direzione.SUD, new Stanza("stanza 3"));
		mappa.put(Direzione.EST, new Stanza("stanza 4"));
		assertEquals(mappa,maze.getStanzaIniziale().getMapStanzeAdiacenti());
	}
	
	@Test
	public void testImpostaStanzaInizialeCambiandola()  throws FileNotFoundException, FormatoFileNonValidoException {
		LabirintoBuilder maze = Labirinto.newBuilder(new StringReader(DESCRIZIONE_GENERICO));
		maze.addStanza("nuova iniziale")
				.addStanzaIniziale("nuova iniziale")
				.getLabirinto();
		assertEquals("nuova iniziale",maze.getStanzaIniziale().getNome());
	}
	
	@Test
	public void testAggiuntaAttrezziAStanze_Iniziale() throws FileNotFoundException, FormatoFileNonValidoException {
		String nomeAttrezzo = "attrezzo";
		int peso = 1;  
		LabirintoBuilder maze = Labirinto.newBuilder(new StringReader(DESCRIZIONE_GENERICO));
		maze.addAttrezzo(nomeAttrezzo, peso, this.nomeStanzaIniziale);
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo,peso);
		assertEquals(attrezzo, maze.getStanzaIniziale().getAttrezzo(nomeAttrezzo));
	}
	
	@Test
	public void testAggiuntaAttrezziAStanze_AppenaAggiunte() throws FileNotFoundException, FormatoFileNonValidoException {
		String nomeAttrezzo = "attrezzo";
		int peso = 1;
		String nomeStanza = "stanza 1";
		LabirintoBuilder maze = Labirinto.newBuilder(new StringReader(DESCRIZIONE_GENERICO));
		maze.addStanza(nomeStanza)
				.addAttrezzo(nomeAttrezzo, peso)
				.getLabirinto();
		assertTrue(maze.getStanze().get(nomeStanza).getAttrezzi().contains(new Attrezzo (nomeAttrezzo,peso)));
		assertEquals(new Attrezzo(nomeAttrezzo,peso),maze.getStanze().get(nomeStanza).getAttrezzo(nomeAttrezzo));
	}
	
	@Test
	public void testAggiuntaAttrezzoAStanze_AppenaAggiunteMultiple() throws FileNotFoundException, FormatoFileNonValidoException {
		String nomeAttrezzo = "attrezzo";
		int peso = 1;
		String nomeStanza = "stanza 1";  
		LabirintoBuilder maze = Labirinto.newBuilder(new StringReader(DESCRIZIONE_GENERICO));
		maze.addStanza(nomeStanza)
				.addAttrezzo(nomeAttrezzo, peso)
				.getLabirinto();
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo,peso);
		List<Attrezzo> attrezzi = new ArrayList<>(maze.getStanze().get(nomeStanza).getAttrezzi());
		assertEquals(attrezzo,attrezzi.get(attrezzi.indexOf(attrezzo)));
	}
	
	@Test
	public void testAggiuntaAttrezzoAStanze_MoltepliciAttrezziStessaStanza() throws FileNotFoundException, FormatoFileNonValidoException {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		int peso1 = 1;
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		LabirintoBuilder maze = Labirinto.newBuilder(new StringReader(DESCRIZIONE_GENERICO));
		maze.addStanza(nomeStanza1)
		.addAttrezzo(nomeAttrezzo1, peso1)
		.addAttrezzo(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = maze.getStanze();
		assertEquals(new Attrezzo(nomeAttrezzo2,peso2),listaStanze.get(nomeStanza1).getAttrezzo(nomeAttrezzo2));
		assertEquals(new Attrezzo(nomeAttrezzo1,peso1),listaStanze.get(nomeStanza1).getAttrezzo(nomeAttrezzo1));
	}
	
	
	@Test  //verifico che gli attrezzi vengano aggiunti all'ultima stanza aggiunta
	public void testAggiuntaAttrezzoAStanze_AttrezzoAggiuntoAllaSecondaStanza() throws FileNotFoundException, FormatoFileNonValidoException {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		int peso1 = 1;
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		String nomeStanza2 = "Stanza 2";
		LabirintoBuilder maze = Labirinto.newBuilder(new StringReader(DESCRIZIONE_GENERICO));
		maze.addStanza(nomeStanza1)
		.addStanza(nomeStanza2)
		.addAttrezzo(nomeAttrezzo1, peso1)
		.addAttrezzo(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = maze.getStanze();
		assertEquals(new Attrezzo(nomeAttrezzo1,peso1),listaStanze.get(nomeStanza2).getAttrezzo(nomeAttrezzo1));
		assertEquals(new Attrezzo(nomeAttrezzo2,peso2),listaStanze.get(nomeStanza2).getAttrezzo(nomeAttrezzo2));
	}
	
	@Test
	public void testAggiuntaAttrezzoAStanze_PrimoAttrezzoInUnaStanzaSecondoAttrezzoSecondaStanza() throws FileNotFoundException, FormatoFileNonValidoException {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		int peso1 = 1;
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		String nomeStanza2 = "Stanza 2";

		LabirintoBuilder maze = Labirinto.newBuilder(new StringReader(DESCRIZIONE_GENERICO));
		maze.addStanza(nomeStanza1)
		.addAttrezzo(nomeAttrezzo1, peso1)
		.addStanza(nomeStanza2)
		.addAttrezzo(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = maze.getStanze();
		assertEquals(new Attrezzo(nomeAttrezzo1,peso1),listaStanze.get(nomeStanza1).getAttrezzo(nomeAttrezzo1));
		assertEquals(new Attrezzo(nomeAttrezzo2,peso2),listaStanze.get(nomeStanza2).getAttrezzo(nomeAttrezzo2));
	}
	
	@Test
	public void testLabirintoConStanzaMagica() throws FileNotFoundException, FormatoFileNonValidoException {
		int sogliaMagica = 1;
		String nomeStanzaMagica = "Stanza Magica";
		LabirintoBuilder maze = Labirinto.newBuilder(new StringReader(DESCRIZIONE_GENERICO));
		maze.addStanzaMagica(nomeStanzaMagica, sogliaMagica);
		StanzaMagica stanzaMagica = (StanzaMagica)maze.getStanze().get(nomeStanzaMagica);
		assertTrue(stanzaMagica.isMagica());
	}
	
	@Test
	public void testLabirintoConStanzaMagica_AggiuntaElementoOltreSogliaMagica() throws FileNotFoundException, FormatoFileNonValidoException {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		String nomeAttrezzo2Inv = "2 ozzertta";
		int sogliaMagica = 1;
		int peso1 = 1;
		int peso2 = 2;
		int peso2_x2 = peso2*2;
		String nomeStanzaMagica = "Stanza Magica";
		LabirintoBuilder maze = Labirinto.newBuilder(new StringReader(DESCRIZIONE_GENERICO));
		maze.addStanzaMagica(nomeStanzaMagica, sogliaMagica)
		.addAttrezzo(nomeAttrezzo1, peso1)
		.addAttrezzo(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = maze.getStanze();
		assertEquals(new Attrezzo(nomeAttrezzo2Inv,peso2_x2), listaStanze.get(nomeStanzaMagica).getAttrezzo(nomeAttrezzo2Inv));
		assertEquals(new Attrezzo(nomeAttrezzo1,peso1), listaStanze.get(nomeStanzaMagica).getAttrezzo(nomeAttrezzo1));
	}
	
	
	@Test
	public void testLabirintoConStanzaBloccata_ConPassepartout() throws FileNotFoundException, FormatoFileNonValidoException {
		LabirintoBuilder maze = Labirinto.newBuilder(new StringReader(DESCRIZIONE_GENERICO));
		maze.addStanzaBloccata("stanza bloccata", "nord", "chiave").addAttrezzo("chiave", 1)
		.addAdiacenza(nomeStanzaIniziale, "stanza bloccata", "nord")
		.addAdiacenza("stanza bloccata", nomeStanzaIniziale, "sud")
		.addStanzaVincente(nomeStanzaVincente)
		.addAdiacenza("stanza bloccata", nomeStanzaVincente, "nord")
		.addAdiacenza(nomeStanzaVincente, "stanza bloccata", "sud");
		Stanza stanzaVincente = new Stanza(nomeStanzaVincente);
		//Asserisce che in presenza di passepartout, invocato il metodo getStanzaAdiacente(), la stanza bloccata ritorna la corretta adiacenza
		assertEquals(stanzaVincente,maze.getStanze().get("stanza bloccata").getStanzaAdiacente("nord"));	
	}
	
	@Test
	public void testLabirintoConStanzaBloccata_SenzaPassepartout() throws FileNotFoundException, FormatoFileNonValidoException {
		LabirintoBuilder maze = Labirinto.newBuilder(new StringReader(DESCRIZIONE_GENERICO));
		maze.addStanzaBloccata("stanza bloccata", "nord", "chiave")
		.addAdiacenza(nomeStanzaIniziale, "stanza bloccata", "nord")
		.addAdiacenza("stanza bloccata", nomeStanzaIniziale, "sud")
		.addStanzaVincente(nomeStanzaVincente)
		.addAdiacenza("stanza bloccata", nomeStanzaVincente, "nord")
		.addAdiacenza(nomeStanzaVincente, "stanza bloccata", "sud");
		Stanza stanzaBloccata = new StanzaBloccata("stanza bloccata", "nord", "chiave");
		//Asserisce che in caso di mancanza di passepartout, invocato il metodo getStanzaAdiacente(), la stanza bloccata ritorna se stessa
		assertEquals(stanzaBloccata,maze.getStanze().get("stanza bloccata").getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testLabirintoCompletoConTutteLeStanze() throws FileNotFoundException, FormatoFileNonValidoException {
		
		LabirintoBuilder labirintoCompleto = Labirinto.newBuilder(new StringReader(DESCRIZIONE_GENERICO));
		labirintoCompleto.addStanzaVincente(nomeStanzaVincente)
				.addStanza("corridoio")
				.addAttrezzo("chiave", 1)
				.addAttrezzo("lanterna", 1)
				.addStanzaBloccata("corridoio bloccato","nord","chiave")
				.addStanzaMagica("stanza magica", 1)
				.addStanzaBuia("stanza buia","lanterna")
				.addStanza("Aula 1")
				.addAdiacenza(nomeStanzaIniziale, "corridoio", "nord")
				.addAdiacenza("corridoio", nomeStanzaIniziale, "sud")
				.addAdiacenza("corridoio", "corridoio bloccato", "nord")
				.addAdiacenza("corridoio bloccato", "corridoio", "sud")
				.addAdiacenza("corridoio bloccato", "Aula 1", "nord")
				.addAdiacenza("Aula 1", "corridoio bloccato", "sud")
				.addAdiacenza("Aula 1", nomeStanzaVincente,"nord")
				.addAdiacenza(nomeStanzaVincente, "Aula 1", "sud")
				.addAdiacenza("corridoio", "stanza magica", "est")
				.addAdiacenza("stanza magica", "corridoio", "ovest")
				.addAdiacenza("corridoio", "stanza buia", "ovest")
				.addAdiacenza("stanza buia", "corridoio", "est")
				.getLabirinto();
		assertEquals(nomeStanzaIniziale, labirintoCompleto.getStanzaIniziale().getNome());
		assertEquals(nomeStanzaVincente, labirintoCompleto.getStanzaVincente().getNome());
		Stanza corridoio = labirintoCompleto.getStanzaIniziale().getStanzaAdiacente("nord");
		assertEquals("corridoio",corridoio.getNome());
		assertTrue(corridoio.getDirezioni().containsAll(Arrays.asList(Direzione.OVEST, Direzione.EST, Direzione.NORD, Direzione.SUD)));
		Map<Direzione,Stanza> mapAdiacenti = new HashMap<>();
		mapAdiacenti.put(Direzione.NORD,new Stanza("corridoio bloccato"));
		mapAdiacenti.put(Direzione.SUD,new Stanza(nomeStanzaIniziale));
		mapAdiacenti.put(Direzione.EST,new Stanza("stanza magica"));
		mapAdiacenti.put(Direzione.OVEST,new Stanza("stanza buia"));
		assertEquals(mapAdiacenti,corridoio.getMapStanzeAdiacenti());
		Attrezzo a1 = new Attrezzo("chiave",1);
		Attrezzo a2 = new Attrezzo("lanterna",1);
		assertEquals(new ArrayList<>(Arrays.asList(a1,a2)), new ArrayList<>(corridoio.getAttrezzi()));
	}
}